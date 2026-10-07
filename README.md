# T.R.S.: Trauma Response Simulator (SCI0)

A genuine Sierra SCI0 game (320x200 16-color EGA, MS-DOS): a port of the browser game *Trauma Response Simulator*.
Ten bad moments per shift, three to five ways to handle each, three stats in the status bar, 102 endings, and an
office with a typed text parser between shifts.

## Play

Needs [DOSBox-X](https://dosbox-x.com/). From this folder:

```bash
dosbox-x -c "MOUNT C \"$PWD\"" -c "C:" -c "SCIV.EXE"
```

## Build

Source is in `src/` (SCI Companion's `.sc` dialect). Open `resource.map` in SCI Companion 3, then Compile All and
Rebuild Resources. Text in `text/` is built with `node tools/gen-text.js`.

Explore the game and its design in the standalone [3x project manual](docs/manual.html). Contributor and agent
documentation: [AGENTS.md](AGENTS.md) and [docs/](docs/README.md).
