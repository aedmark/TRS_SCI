# Roadmap

Item IDs are permanent: `P<phase>-<nn>`. Never renumber; append new items at the end of their phase.
`[ ]` open · `[~]` in progress (who holds it, since when, and what is left) · `[x]` done · `[-]` dropped (say why,
and the decision).

A finished item says what was done, the decision if any, the evidence, and the date. "Confirmed" means the
maintainer played it in a compiled game. Items marked done before 2026-09-29 were recorded retroactively from the
archived handoff (`docs/archive/SESSION_HANDOFF_2026_09.md`), which has the full stories.

## Phase 1: The port

Goal: every mechanic of the browser game, running in a genuine SCI0 build.

- [x] P1-01 All six zones, 196 events, one room per event (D-002). Confirmed by playtest (before 2026-09-14).
- [x] P1-02 Turn loop: zone weighting toward the worst stat, no-repeat pool, glitch choice, stat clamps, run end.
  Checked faithful to the browser `js/engine.js` (2.5:1 zone weight, rejection-sampled no-repeat).
- [x] P1-03 Full 3-5 choice counts with More/Back pagination at 3 per page. Confirmed, including both 5-choice
  events, before 2026-09-14.
- [x] P1-04 Coping mechanisms: 5 tags, unlock after 3 uses, passive modifiers.
- [x] P1-05 All 102 ending variants (9 survival pools x 8, 3 failure pools x 10), each tracked in Case Files.
- [x] P1-06 Case Files viewer with persistence to `TRSCASE.DAT`, including the four-cause "View" heap fix.
  Confirmed on a fresh save plus one 10-turn run.
- [x] P1-07 Extended Therapy (New Game+). Confirmed, including a full 20-turn run.
- [x] P1-08 Selectable player portrait (4 options, 4 moods), asked once, persisted as Case Files slot 108.
  Confirmed 2026-09-14.
- [x] P1-09 Optional player name (`TRSNAME.DAT`) and Reset Data. Reset Data's menu-ID bug fixed and confirmed
  2026-09-15.
- [x] P1-10 Numeric stat status line (bar gauges tried twice and reverted).

## Phase 2: The office

Goal: a hub the player starts in, returns to, and leaves only when ready (D-004).

- [x] P2-01 Office hub `rm003.sc`: cabinet, computer, one-time setup, back-outs everywhere. Whole checklist
  (`docs/TESTING.md`) walked and confirmed 2026-09-15.
- [x] P2-02 Office text parser, ~20 look targets plus verbs, text in `text/office.txt`. Confirmed 2026-09-14.
- [x] P2-03 Menu bar: real About and a five-page Help from `text/menu.txt`. Confirmed.
- [~] P2-04 More office verbs: kick/hit/punch, cry, sleep, type/play, throw, eat/drink, dance, scream, read, break
  mirror (maintainer, uncommitted since 2026-09-15). Written in `rm003.sc` and `text/office.txt`, `text.003`
  regenerated. Left: add the new words to the vocabulary with the Imperative Verb class, compile, playtest; see
  HANDOFF gotchas for a branch-order bug.

## Phase 3: Art and audio

- [ ] P3-01 Real portrait art: 16 cels, views 801-804 x loops 0-3, 80x60 (maintainer, solo). Mapping in
  `docs/ARCHITECTURE.md`.
- [ ] P3-02 Repaint the office picture to match the parser text: clock, door and mirror are described but not
  drawn in `art/images/room1.jpeg`.
- [-] P3-03 Chase the Sound Editor preview vs. in-game music timbre mismatch (dropped: the maintainer accepts how
  it plays in-game; see `docs/SCI0-GOTCHAS.md` on sound resources).

## Phase 4: Robustness

- [ ] P4-01 Re-run the original Case Files heap repro: a 10-turn run then a 20-turn run in one session, then View
  a file. Only the lighter repro (fresh save, one 10-turn run) was retested after the fix.
- [ ] P4-02 Fix stale paths outside this repo: the old handoff says the repo is `~/RiderProjects/TRS_SCI`, and the
  browser repo's `tools/lib/sci-paths.js` defaults there too; the repo is now `~/PycharmProjects/TRS_SCI`. Found
  2026-09-29 while adopting the docs; the generators will throw unless `TRS_SCI_DIR` is set.

## Phase 5: Later, only if wanted

- [ ] P5-01 Generate `vocab.000` from a word list in the repo, like `text.003`, replacing the Wine-broken "New word"
  workaround. Needs a byte-for-byte round-trip of the current vocab first, and a check of which copy the compiler
  reads when a package and a patch file both exist.

## Phase 6: Release

- [ ] P6-01 itch.io page and distribution zip (`docs/Itch.md`, maintainer). The zip must ship `text.003` and
  `text.010` next to `resource.map`/`resource.001`.
