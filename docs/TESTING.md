# Testing

How to build and check the game, what each check proves, and what it cannot. There are no automated game tests:
the evidence is the maintainer's playtest of a compiled build, recorded in HANDOFF with the date.

## The checks

| Check | How | Proves | Does not prove | Who |
| --- | --- | --- | --- | --- |
| Docs | `python3 tools/check_docs.py` | Links, IDs, placeholders, and the 3x manual source/build are consistent | Anything about the game | Anyone |
| Text build | `node tools/gen-text.js` | Each `text.NNN` round-trips; entries are ASCII and fit `Print()`'s 1012-byte buffer | That the room uses the right index | Anyone |
| Compile | SCI Companion: Compile All, then Rebuild Resources | Syntax, symbols, and that vocab words exist | Word classes, runtime heap, behaviour | Maintainer |
| Play | DOSBox-X, see below | The feature works in the real interpreter | Other emulators or hardware | Maintainer |

A batch of new, cross-referencing scripts can need 2-3 compile rounds; that is expected while the errors keep
shrinking.

## Running the game

```bash
dosbox-x -c "MOUNT C \"$PWD\"" -c "C:" -c "SCIV.EXE"
```

Run from the repo root. For a fresh save, move `TRSCASE.DAT` and `TRSNAME.DAT` aside first and put them back after:
they are the maintainer's real progress.

## Change-to-check matrix

| Changed area | Minimum checks | Additional evidence |
| --- | --- | --- |
| Documentation only | `python3 tools/check_docs.py` | |
| 3x manual source or generator | `python3 tools/check_docs.py`; open `docs/manual.html` and check navigation, search, and narrow-screen layout | |
| `text/*.txt` | `node tools/gen-text.js`, compile, read each changed reply in game | |
| Office parser (`rm003.sc`) | Compile; the parser checklist below | New words' vocab classes checked in the Vocabulary editor |
| Anything Case Files touches, or a new resident script | Compile; View a file after a full run | The heavy heap repro (P4-01) |
| Menus (`menubar.sc`, `game.sh` menu IDs) | Every item below the change, by click and by hotkey | |
| Generated content | Regenerate in the browser repo, compile, play a run | `verify-casefile-indices.js` for Case Files |

## Regression checklists

### Office hub (walked in full 2026-09-15)

- Fresh save -> title -> portrait picker, then name, then the welcome, all in the office.
- Computer -> "Start a new session?" with Begin / Not yet. "Not yet" stays; Begin -> 10-turn run. Escape does
  nothing.
- Finish -> ending cards -> office, with no welcome and the music not restarting.
- `look mirror` -> picker; a new pick shows in event dialogs. Quit and relaunch: not asked again.
- After surviving once: computer -> Standard / Extended / Not yet. Escape does nothing there or during an event.
- Filing cabinet and `^f` -> category menu with a working Close button.
- `turn on computer` starts a session; `turn off computer` must not (the first `<` in a `Said()`).
- Restart Game -> office.
- Reset Data, then finish a run -> asked for appearance and name again. Quit (`^q`) still quits.

### Office parser

- Bare `look`, and `look at <noun>` for each noun in `rm003.sc`'s `RoomScript`.
- `open cabinet` -> Case Files, then View a file (the heap-sensitive path).
- `open drawer`, `sit`, `breathe`, `take a breath`, `turn off lamp`, `help`.
- Parses but unhandled (`sing song`, or any verb with no branch) -> the FALLBACK line. Unknown word -> the stock
  "I don't understand".
- If every reply fails at once, suspect `text.003` not being loaded, not the `Said()` patterns.

### Choices

- A 3-, 4- and 5-choice event ("The Typo", "The Performance Review Buzzword"), including a glitch on the last page
  and Back from page 2.

### Help screen

- F1 shows "How To Play (1 of 5)" through "(5 of 5)", More on the first four, OK on the last. Adding a paragraph
  needs a matching `Print()` in `menubar.sc`.

## Known pitfalls

See [SCI0-GOTCHAS.md](SCI0-GOTCHAS.md). The ones that bite testing most:

- The Sound Editor's preview is not what the game plays.
- Alt+M and menu hotkeys do nothing while a dialog is open.
- A word that compiles may still lack the class its `Said()` needs; the symptom is the FALLBACK reply.
