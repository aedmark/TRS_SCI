# SCI0 and toolchain gotchas

Traps this project has already fallen into once. Each is short; the full incident stories are in
[archive/SESSION_HANDOFF_2026_09.md](archive/SESSION_HANDOFF_2026_09.md). Add one whenever a session loses time to
something a future session could also hit.

## Diagnosing

- **Alt+M (`MemoryInfo`) does nothing while a modal dialog owns input**, which is exactly when a heap crash happens.
  Drop temporary `Format()` + `Print()` checkpoints into the suspect path instead, then remove them.
- The same inline `Print()` checkpoints are how `Said()` problems were isolated: did the event reach the room, was it
  already claimed, did each pattern match.

## Heap and scripts

- **Scripts auto-load on call and never auto-unload.** The root of nearly every heap bug. See ARCHITECTURE, "Heap
  discipline".
- **Load/Dispose cycling does not reliably reclaim memory.** Cut the number of cycles, not only their size.
- **`DisposeScript()` only means a script number.** Resource types number independently from 0, so
  `DisposeScript(TEXT_UI)` disposed `Main.sc` and crashed with "Oops!". Leave non-script resources loaded.
- **Global arrays in `Main.sc` are not visible to other scripts**; only scalar globals export. Use a script-level
  `(local arr[N])` in the script that needs it.
- **A procedure `(var arr[N])` fails at runtime somewhere between ~1KB and 3.4KB** ("you did something we didn't
  expect") though it compiles. Use script-level locals for big buffers.
- **`paramTotal` includes named parameters** before a rest parameter; subtract them (`DisposeLoad.sc`, `PrintChoices`).
- **A new circular `(use ...)` pair may need a manual bootstrap**: comment out the new symbol on one side, compile
  each side alone (F8), restore, Compile All. Better: avoid creating a new pair.

## Language and text

- **No literal `"` inside a `"..."` string.** Use `'` (text resources built by `gen-text.js` may contain `"`).
- **Non-ASCII bytes are control bytes, not missing glyphs**: an em dash ate a word. Transliterate.
- **Stay flat**: no `if/else` past one level, `switch` only on a plain variable, `and`-chains of at most 4. A deep
  chain was once blamed for a bug it did not cause, but there is still no precedent for more.
- **`fOPENFAIL` and `fOPENCREATE` are swapped** from what the names suggest. `fOPENCREATE` is the safe
  open-existing flag; `fOPENFAIL` can truncate the file you are about to read.
- **Clear a line buffer before each `FGets`**: a read past end of file may leave the previous line there.

## Dialogs and menus

- **Escape returns 0/-1, indistinguishable from a button whose value is 0.** `PrintChoices` and the portrait picker
  rebuild the page on no match and ignore Escape; give any way out its own button and value.
- **`DSelector` state bit 1** = initial focus only; **bit 2** = any claimed event (even scrolling) ends the dialog.
  A browse-only list uses `state(1)`.
- **`DSelector` scrolls until a slot's first byte is 0.** Zero the whole buffer before filling it.
- **`Print()` never paginates** and a dialog taller than 200px renders garbled. Split long text across dialogs.
- **A menu separator consumes an item number.** Inserting an item renumbers every `$MMII` below it, including the
  `SetMenu(... smMENU_SAID ...)` lines (Reset Data ran Quit, 2026-09-15).
- **A menu hotkey cannot reach you while a modal dialog owns input.**

## Parser

- **`ProgramControl()` turns off `canInput` too.** The office calls `(User:canInput(TRUE))` after it (needs
  `(use "user")`); safe because `gProgramControl` is never set.
- **Two `Said()` idioms from SCI Companion's own docs do not work here**: `Said('[/!*]')` and the split
  `Said('verb>')` + `Said('/noun')`. Use one complete `Said('verbs/noun')` per branch in a flat early-return
  sequence; a failed `Said()` claims nothing. Earlier branches win, so order specific before general.
- **A word compiling in `Said()` only proves it exists**, not that it has the needed class. Classes (from
  `Vocab000.h`): Number, Punctuation, Conjunction, Association, Preposition, Article, Qualifying Adjective, Relative
  Pronoun, Noun, Indicative Verb, Adverb, Imperative Verb. There is no plain "Verb"; commands need Imperative Verb.
- **The Vocabulary editor's "New word" and F2-rename do not work under Wine.** Add words from the Script Editor
  (select the word in a `.sc`, right-click, "Add as..."), then check its class in the Vocabulary editor
  (right-click checkboxes work).
- A one-off scrambled-capitalisation bug in typed input was the interpreter/emulator, not the code; a full restart
  cleared it.

## Resources and build

- **`resource.map`/`resource.001` are append-only**; duplicates are cosmetic until "Rebuild Resources".
- **A new script needs the `.sc`, a `game.ini` entry and a `game.sh` constant.** If it still does not appear in SCI
  Companion's Scripts panel, create it with "New empty script".
- **Loose `text.NNN` patches override the packaged resource** silently. Edit `text/*.txt`, never the Text editor.
- A room's text shares its room number (Sierra convention), so `text.003` shares a number with `rm003.sc`: harmless
  as long as nothing calls `DisposeScript()` on a text number.

## Sound

- **An SCI0 sound resource is MIDI plus a per-channel, per-device map.** The Sound Editor's preview ignores it; only
  a real launch honours it. Check each track's checkbox for the device in use (General MIDI, `gm.drv`).
- MIDI is configured separately in DOSBox-X (`~/.config/dosbox-x/dosbox-x-*.conf`: `mididevice = fluidsynth`,
  `fluid.soundfont = /usr/share/soundfonts/FluidR3_GM.sf2`) and in SCI Companion's preview.

## SCI Companion under Wine

- Fixed and upstreamed (icefallgames/SCICompanion #29, #30, #32): the compile hang, the black-only brush, and file
  dialogs forgetting their folder. The maintainer's fork source is `~/WebstormProjects/SCICompanion/`; the built IDE
  and its `Help/` docs are in its `Release/`.
- Every Wine bug so far has been a narrow Win32/GDI behaviour the app relies on and Wine does not reproduce: in-place
  edit boxes fail where real modal dialogs work.
- A new C++ source file needs explicit `<ClCompile>`/`<ClInclude>` entries in the vcxproj, and a project reload.

## Test targets

- **ScummVM** cannot parse this project's resources. **Windows XP NTVDM** shows a black screen (broken EGA
  emulation; sound works). Use DOSBox-X, or DOSBox on the old hardware.
