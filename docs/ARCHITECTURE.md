# Architecture

How the SCI0 port fits together, for a session that has never seen it. Why things are this way lives in
[DECISIONS.md](DECISIONS.md); the traps behind many of these rules are in [SCI0-GOTCHAS.md](SCI0-GOTCHAS.md).

## The shape, in one paragraph

`SCIV.EXE` boots `Main.sc` (script 0), which loads Case Files from `TRSCASE.DAT` and lands in the office
(`rm003.sc`). The office asks the one-time setup questions, offers Case Files (cabinet) and the text parser, and its
computer starts a session in `rm001.sc`, which resets the run and calls `EndTurn()`. `mechanisms.sc` then picks a
zone weighted toward the worst stat and an unseen event, and `newRoom`s to it. Each event room shows `PrintChoices`,
applies the choice's effects, and calls `EndTurn()` again, until a stat hits its bound or the turns run out. Then
`rm002.sc` prints the ending, records it in Case Files, and returns to the office.

## Code map

Script numbers in parentheses.

| Area | Where | Entry point | Talks to |
| --- | --- | --- | --- |
| Globals, status line, boot | `src/Main.sc` (0) | `init` | `CaseFiles.sc` (load at boot) |
| Session reset | `src/rm001.sc` (1, `SESSION_ROOM`) | `init` | `mechanisms.sc` |
| Ending | `src/rm002.sc` (2, `ENDING_ROOM`) | `printEnding()` | Ending pool scripts, Case Files, office |
| Office hub and parser | `src/rm003.sc` (3, `OFFICE_ROOM`) | `init`, `RoomScript:handleEvent` | Case Files, `PrintChoices`, `TEXT_OFFICE` |
| Choice dialog | `src/printchoices.sc` (100, resident) | `PrintChoices`, `PromptPortraitChoice` | Every event room |
| Turn loop and effects | `src/mechanisms.sc` (106, resident) | `ApplyChoiceEffects`, `EndTurn`, `GoToNextEvent` | Event rooms, `rm002.sc` |
| Case Files menu and persistence | `src/CaseFiles.sc` (107) | `ShowCaseFiles`, `LoadCaseFiles`, `ResetAllData` | `TRSCASE.DAT` |
| Case Files list and View | `src/CaseFileCategory.sc` (141) | `ShowCaseFileCategory` | `CaseFileDescriptionDispatch.sc` (155) |
| Name prompt | `src/PlayerNamePrompt.sc` (142) | `PromptPlayerName` | `mechanisms.sc` (`TRSNAME.DAT`) |
| Menu bar | `src/menubar.sc` | `handleEvent` | Case Files, `TEXT_MENU` |
| Events | `src/rm200.sc`-`src/rm395.sc` (generated) | `init` | `PrintChoices`, `mechanisms.sc` |
| Constants and manifest | `src/game.sh`, `game.ini` | | Everything; a new script needs an entry in both |

| Zone | Rooms | Events |
| --- | --- | --- |
| WORK | 200-233 | 34 |
| HOME | 234-265 | 32 |
| SOCIAL | 266-298 | 33 |
| SELF | 299-331 | 33 |
| BODY | 332-363 | 32 |
| PUBLIC | 364-395 | 32 |

A room number is `<ZONE>_ROOM_BASE + localIndex` (`game.sh`).

## Interfaces and data flow

```text
event room -> PrintChoices (choice index | GLITCH_CHOICE) -> ApplyChoiceEffects -> EndTurn
  -> GoToNextEvent (next room) | newRoom(ENDING_ROOM) -> Case Files -> newRoom(OFFICE_ROOM)
```

| Interface | Producer | Consumer | Contract |
| --- | --- | --- | --- |
| `PrintChoices` return | `printchoices.sc` | Event rooms, office prompts | A real choice index or `GLITCH_CHOICE`; paginates internally at `CHOICES_PER_PAGE` (3); ignores Escape |
| `ShowCaseFiles()` return | `CaseFiles.sc` | `menubar.sc`, `rm003.sc` | Category picked (values start at 1; 0 = cancelled). Caller disposes it before loading `CaseFileCategory.sc` |
| `TRSCASE.DAT` | `CaseFiles.sc` | `CaseFiles.sc` | One line per slot, 109 lines; a shorter older file reads missing slots as 0 |
| `TRSNAME.DAT` | `mechanisms.sc` | `mechanisms.sc` | Player name, lazily loaded on first access |
| `text.NNN` + `src/*text.sh` | `tools/gen-text.js` | `rm003.sc`, `menubar.sc` | Appending entries is safe; reordering shifts indices and needs a recompile |

### Case Files flat index (`CASEFILE_COUNT` = 109)

- `0-71`: 9 survival pools x 8 variants (pool N = `N*8..N*8+7`).
- `72-101`: 3 failure pools x 10, in repression/mask/child order.
- `102-106`: the 5 coping mechanisms, `TAG_FAWN..TAG_SECURE`, from `CASEFILE_MECH_BASE`.
- `107`: Extended Therapy unlock (`CASEFILE_NGPLUS`), hidden; `VIEWABLE_CASEFILE_COUNT` = 107.
- `108`: chosen appearance (`CASEFILE_PORTRAIT`), stored as `gPortraitChoice + 1`, so 0 means never chosen.

Slots 0-107 are 108 scalar globals `gCF0..gCF107` in `Main.sc` (a global array is not visible to other scripts);
`CaseFileAccess.sc` (136) gives array-like `GetCaseFile`/`SetCaseFile` over them and maps 108 to `gPortraitChoice`.

### Portraits

Views 801-804 (`PORTRAIT_VIEW_0..3`) are the four options; loops 0-3 are neutral/repression/mask/child, one static
80x60 cel each, shown as a `DIcon` in `PrintChoices`. `GetPortraitMood()` leaves neutral once the worst stat's
danger value crosses `PORTRAIT_NEUTRAL_THRESHOLD` (60).

## Heap discipline

The 64KB heap is the constraint behind most of this design. Every script is one of two kinds:

- **Resident** (loaded once, never disposed): `Main.sc`, `Controls.sc` and other stock scripts, `printchoices.sc`,
  `mechanisms.sc`.
- **Load/Dispose-scoped** (loaded right before use, disposed right after, at every call site): `CaseFiles.sc`,
  `CaseFileAccess.sc`, `CaseFileTitles.sc` (137), `CaseFileDescriptionsMechanisms.sc` (140), `CaseFileCategory.sc`,
  `PlayerNamePrompt.sc`, `CaseFileDescriptionsSurvival0-8.sc` (143-151), `CaseFileDescriptionsFailure0-2.sc`
  (152-154), `CaseFileDescriptionDispatch.sc`, `EndingSurvival0-8.sc` (162-170), `EndingFailure0-2.sc` (171-173).

## Invariants

- A script loads on first call and never unloads by itself; `(use ...)` has no runtime effect. Every call site into
  a scoped script needs its own `Load(rsSCRIPT n)`/`DisposeScript(n)`. Enforced by: nothing (review only).
- Minimise the *number* of Load/Dispose cycles per user action, not only their size: a Case Files View does exactly
  one. Enforced by: nothing; regressions show as "Out of heap space" (TESTING, regression checklists).
- `CaseFiles.sc` and `CaseFileCategory.sc` are never resident together. Enforced by: the `ShowCaseFiles` return
  contract.
- Large text is split per pool (~2KB each), never per category (the survival category was 7.24KB compiled, more than
  the largest free block after real play).
- `DisposeScript()` is only ever called with a script number, never a text or view number (`TEXT_UI` 0 collides with
  `Main.sc` 0). Enforced by: nothing.
- Menu constants `$MMII` in `game.sh` count separators as items. Enforced by: nothing.
- Buffers above a few hundred bytes are script-level `(local ...)`, not procedure `(var ...)`.

## State and caches

| What | Where | Written by | Reset by | Committed? |
| --- | --- | --- | --- | --- |
| Case Files, NG+ unlock, appearance | `TRSCASE.DAT` | `CaseFiles.sc` | Reset Data menu item | No, gitignored |
| Player name | `TRSNAME.DAT` | `mechanisms.sc` | Reset Data | No |
| Interpreter logs | `stdout.txt`, `stderr.txt` | `SCIV.EXE` | Each launch | No |
| Compiled resources | `resource.map`, `resource.001` | SCI Companion | "Rebuild Resources" repacks (append-only otherwise) | Yes |
| Compiled scripts | `src/*.sco` | SCI Companion | Recompile | Yes |

## Content pipeline

Events, endings and Case File descriptions are generated in the browser repo
(`~/WebstormProjects/trauma-response-sim/tools/`, from its `js/content*.js`) and written into this repo's `src/`.
The target is located by that repo's `tools/lib/sci-paths.js`: `TRS_SCI_DIR` if set, else a default path that is
stale (P4-02). `tools/lib/zone-events.js` is the shared generator; `gen-<zone>-events.js` x6, `gen-endings.js`,
`gen-casefile-descriptions.js` and `verify-casefile-indices.js` (checks descriptions against the hand-written
`CaseFileTitles.sc`) are the entry points. All are idempotent. Its `tools/lib/sci-string.js` makes strings ASCII-safe.

Text resources are built here: `node tools/gen-text.js` turns each `text/*.txt` into a loose `text.NNN` patch
(`0x83`, `0`, then NUL-terminated entries) plus `src/<name>.sh` index constants, then reads the patch back to verify.
`text/office.txt` -> `text.003` (`TEXT_OFFICE`); `text/menu.txt` -> `text.010` (`TEXT_MENU`). `TEXT_UI` (0) exists
only in the resource package.

## Claims vs. code

- `TEXT_UI` entries 16-19 (the removed Case Files review prompt) are still in the package; nothing reads them.
- `CASEFILE_VIEW_MIN_HEAP` (4096) guards cross-run fragmentation, but real fragmentation was never measured against
  it; the per-pool split is what actually fixed View.
