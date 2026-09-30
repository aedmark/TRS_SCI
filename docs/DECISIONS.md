# Decisions

Short, append-only record of choices that a future session might otherwise re-litigate. One entry per decision.
Newest at the bottom. To reverse a decision, add a new entry that supersedes it; the old one keeps its text and only
its status changes ("superseded by D-MMM").

D-001 to D-006 were made before this file existed and recorded on 2026-09-29 from the archived handoff.

Format:

```
## D-NNN Title  (YYYY-MM-DD, status: proposed | accepted | rejected | superseded by D-MMM)
**Context:** why this came up.
**Decision:** what we chose.
**Alternatives:** what else was considered, and why not.
**Consequences:** what it costs or constrains.
```

---

## D-001 Late-SCI0, built with SCI Companion 3, full 1:1 port  (recorded 2026-09-29, status: accepted)
**Context:** The goal is a genuine Sierra game, not a reskin.
**Decision:** Target late-SCI0 (Police Quest II / Larry 2-3 UI conventions), built with SCI Companion 3
(the maintainer's fork, github.com/aedmark/SCICompanion). Port all six zones; this began as a WORK-only slice.
**Consequences:** Everything compiles at build time; 64KB heap and dialect quirks shape every design.

## D-002 One room per event  (recorded 2026-09-29, status: accepted)
**Context:** A per-zone dispatcher that loaded content chunks fragmented the heap unpredictably: the same
load/use/dispose cycle sometimes reclaimed memory and sometimes did not.
**Decision:** Each of the 196 events is its own room (200-395); the engine's room transition cleans up.
**Alternatives:** Dispatcher plus chunks (the fragmentation above).
**Consequences:** Only one event is resident at a time; adding an event means adding a room and a manifest entry.

## D-003 SCI0-side seeds only  (recorded 2026-09-29, status: accepted)
**Context:** The browser game's mulberry32 RNG is 32-bit; SCI0 arithmetic is 16-bit.
**Decision:** Runs are not required to reproduce browser-version seeds.

## D-004 The office is the hub  (2026-09-14, status: accepted)
**Context:** Players were dropped straight into event cards, with a question before each session.
**Decision:** Boot, Restart and every finished run land in the office. Nothing is asked before a session beyond the
one-time setup (appearance, then name). The appearance persists; only the mirror changes it. Sessions start only
from the computer. Every pop-up the office opens can be backed out of with a visible button.
**Consequences:** The pre-session Case Files review prompt was removed (its `TEXT_UI` entries 16-19 are unused).

## D-005 Permanently cut features  (recorded 2026-09-29, status: accepted)
**Decision:** Not deferred, cut: runtime content packs / `editor.html` (SCI0 compiles everything at build time);
Share Result PNG (no such concept on DOS); ScummVM as a test target (its SCI engine cannot parse these resources;
use DOSBox-X); the browser game's `arcade` mode.

## D-006 Fixed text in text resources, not string literals  (recorded 2026-09-29, status: accepted)
**Context:** Script string literals cost heap; the office stays resident under the Case Files viewer, whose margin
is the tightest in the game.
**Decision:** Hand-written UI text lives in text resources. `TEXT_UI` (0) is edited in SCI Companion; newer text
(`TEXT_OFFICE` 3, `TEXT_MENU` 10) is built from `text/*.txt` by `tools/gen-text.js` into loose patch files.
Generated event and ending text stays in generated scripts.
**Consequences:** Loose patch files must ship with the build; never edit those resources in SCI Companion.

## D-007 Adopt the AGENTS.md documentation layout  (2026-09-29, status: accepted)
**Context:** One 1150-line `SESSION_HANDOFF.md` mixed current state, architecture, decisions, and incident history.
**Decision:** Split into `AGENTS.md`, `ROADMAP.md` and `docs/`, with the old file archived whole. `docs/` stays
gitignored except the files listed in `.gitignore`, so `docs/Itch.md` remains private.
**Consequences:** Run `python3 tools/check_docs.py` after doc changes.

## Open questions

Numbers are permanent; an answered question stays, with the answer and its date.

- **Q-001** Should the office verbs of P2-04 each get their own reply, or should `punch mirror` reach the break-mirror
  reply? The kick/hit/punch branch runs first and claims it. (asked 2026-09-29, by Claude; recommendation: move the
  break-mirror branch above kick)
