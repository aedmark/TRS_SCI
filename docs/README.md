# Documentation map

This directory stores durable project knowledge. Each fact should have one authoritative home; other documents link
to it instead of copying it. `docs/` is gitignored except the files `.gitignore` lists (so `Itch.md` stays private);
a new document needs its own `!` line there.

## Audiences and ownership

| Document | Primary audience | Owns | Does not own |
| --- | --- | --- | --- |
| `../README.md` | Players and newcomers | Purpose, how to run | Internal workflow or session state |
| `../AGENTS.md` | Coding agents and the maintainer | Working rules, conventions, protected areas | Design rationale |
| `../ROADMAP.md` | The maintainer | Planned scope and status | Implementation notes |
| `HANDOFF.md` | The next work session | Current state, next steps | Permanent design rules |
| `ARCHITECTURE.md` | Developers | Rooms, scripts, heap discipline, persistence, pipelines | History |
| `DECISIONS.md` | Future decision-makers | Why durable choices were made; open questions | Routine detail |
| `TESTING.md` | Whoever builds or plays | Build/run commands, checklists | Current results (HANDOFF) |
| `SCI0-GOTCHAS.md` | Anyone editing `.sc` or using SCI Companion | Traps already hit | Full incident stories (archive) |
| `manual.json` | Newcomers and maintainers | Searchable 3x overview source: what, how, and why | Exhaustive implementation detail or current session state |
| `manual.html` | Newcomers and maintainers | Generated standalone rendering of `manual.json` | Hand-edited content |
| `archive/` | Anyone checking history | Retired documents and old session logs | Anything current |

## Update triggers

Update documents because a relevant fact changed, not merely because a session ended.

| Change | Required documentation |
| --- | --- |
| Player-visible behaviour | README if how to run changed; the roadmap item |
| New script, room, resource, persistence slot, or pipeline | ARCHITECTURE |
| Durable tradeoff or reversal | DECISIONS; mark the old decision superseded |
| New check, checklist item, or run command | TESTING |
| A manual subject's behavior, mechanism, or rationale changes | `manual.json`; regenerate `manual.html` |
| A trap that cost time and could recur | SCI0-GOTCHAS |
| Work pauses with context another session needs | HANDOFF |
| New planned work | ROADMAP, with origin and date |

## Style and evidence

- Use exact commands and repository-relative paths.
- Date volatile observations; "confirmed" means seen by the maintainer in a compiled game.
- Link to the source of truth instead of restating it.
- The repository is public: no private paths beyond the maintainer's own project layout, no personal data.
- Keep 3x entries as reader-oriented summaries with evidence links. The detailed Markdown documents remain
  authoritative; do not copy volatile session state into the manual.
