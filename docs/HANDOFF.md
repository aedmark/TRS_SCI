# Session Handoff

Read this first when resuming unfinished work. Rewrite the top half whenever current state changes materially or work
pauses with context another session needs. The session log below it is append-only history. "Current state" fits in
about 80 lines; what does not fit is history.

Protocol: see [AGENTS.md](../AGENTS.md) (`CLAUDE.md` imports it). Plan: [ROADMAP.md](../ROADMAP.md).
Architecture: [ARCHITECTURE.md](ARCHITECTURE.md). Decisions: [DECISIONS.md](DECISIONS.md).
Tests: [TESTING.md](TESTING.md). Traps: [SCI0-GOTCHAS.md](SCI0-GOTCHAS.md). Older sessions: [archive/](archive/README.md).

---

## Current state

_Last updated: 2026-10-07, session 3, on `master` at `6b4cc73`: applying the 3x documentation scheme is
uncommitted. The previously documented agent-layout work is now committed._

**Where things stand:** the port is feature-complete (Phases 1 and 2 of the roadmap) and was fully playtested
through 2026-09-15. What is left is P2-04 (more office verbs), real portrait and office art (P3-01, P3-02), one
heavier heap retest (P4-01), and the itch.io release (P6-01).

**Verified** (by the maintainer, in DOSBox-X on the Linux host; carried over, not re-run this session)

| Check | Result |
| --- | --- |
| Office hub checklist (TESTING) | All pass, 2026-09-15 (Reset Data after the menu-ID fix) |
| Office parser checklist | Pass, 2026-09-14 |
| 3/4/5-choice pagination | Pass, before 2026-09-14 |
| Case Files View after one 10-turn run on a fresh save | Pass |
| `python3 tools/check_docs.py` | 0 errors, 2026-10-07 (session 3) |

**Not verified**
- P2-04's new verbs: not compiled or played as far as the docs record; their words may not be in the vocabulary.
- The heavier Case Files repro (10-turn then 20-turn run, then View): P4-01.
- Any target but DOSBox-X.

**Gotchas for the next session**
- The 3x source is `docs/manual.json`; never hand-edit generated `docs/manual.html`. `python3 tools/check_docs.py`
  validates both and reports a stale build.
- In the P2-04 code, the kick/hit/punch branch uses `Said('punch/*')` and runs before the break-mirror branch, so
  `punch mirror` gets the kick reply (Q-001).
- The new verbs (kick, hit, punch, cry, weep, sob, sleep, nap, type, play, throw, eat, drink, dance, scream, yell,
  shout, read, break, smash) need the Imperative Verb class; add them via the Script Editor, not "New word".
- The browser repo's generators default to the old `~/RiderProjects/TRS_SCI` path (P4-02); set `TRS_SCI_DIR`.
- `docs/` is gitignored except the files `.gitignore` lists; a new doc needs its own `!` line there.

## Next steps (in order)

1. Maintainer: answer Q-001, finish P2-04 (vocab classes, compile, play each new verb), commit.
2. Maintainer: P4-01 heavy heap repro when Case Files is next touched.
3. P3-01 / P3-02 art, P6-01 release, P4-02 path fix in the browser repo.

## Open questions for maintainers

- Q-001 Should `punch mirror` reach the break-mirror reply? Blocks finishing P2-04.

## Session log

Newest first. Past 10 entries, move the oldest to `docs/archive/` and leave a pointer here.

### Session 3: 2026-10-07: apply the 3x documentation scheme

**Contributor:** Codex
**Goal:** Apply the supplied 3x documentation template without replacing the project's authoritative docs.
**Done:** D-008.
**Changed:** Vendored the supplied scheme; added `docs/manual.json` and generated `docs/manual.html`; linked the
manual from the root README and documentation map; extended `tools/check_docs.py` to validate the source and detect
a stale generated file.
**Decisions:** D-008.
**Verified:** 3x validation and build, the scheme's unit tests, and `python3 tools/check_docs.py`.
**Not verified:** The standalone HTML still needs a quick visual browser review. No game files were touched, built,
or run.
**Problems / surprises:** None.
**Corrections:** None.
**Left undone:** Nothing in the requested documentation integration.
**Next session should start with:** Next steps above.

### Session 2: 2026-09-29: adopt the agent documentation layout

**Contributor:** Claude (Claude Code)
**Goal:** Adopt `docs/agent-template` and retrofit the existing handoff into it.
**Done:** D-007.
**Changed:** Added `AGENTS.md`, `CLAUDE.md`, `ROADMAP.md`, `README.md`, `docs/{README,HANDOFF,ARCHITECTURE,DECISIONS,
TESTING,SCI0-GOTCHAS}.md`, `docs/archive/`, `tools/check_docs.py` (reads AGENTS.md's "Repository map"). Moved
`SESSION_HANDOFF.md` whole to `docs/archive/SESSION_HANDOFF_2026_09.md`, including the maintainer's uncommitted
edits. `.gitignore` now tracks those docs while keeping the rest of `docs/` private. Removed the template.
**Decisions:** D-001 to D-006 recorded retroactively; D-007 new.
**Verified:** `python3 tools/check_docs.py`.
**Not verified:** Nothing in the game was built or run; no game files were touched.
**Problems / surprises:** The old handoff's repo path (`~/RiderProjects/TRS_SCI`) is stale (P4-02).
**Corrections:** None.
**Left undone:** SECURITY, CONTRIBUTING and CHANGELOG were not adopted: a single-maintainer game with no secrets or
release history yet. Add CHANGELOG at the first itch.io release.
**Next session should start with:** Next steps above.

### Session 1: 2026-09-14 to 2026-09-15: port, office hub, menu fixes (summary)

**Contributor:** maintainer with Claude
**Done:** P1-01 to P1-10, P2-01 to P2-03; P2-04 started. Full history in
[archive/SESSION_HANDOFF_2026_09.md](archive/SESSION_HANDOFF_2026_09.md) and the browser repo's git history.
