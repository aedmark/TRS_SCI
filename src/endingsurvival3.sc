/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival3.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 3 (8 variants), matching CONTENT_ENDINGS[3] in js/content-endings.js. Flat Case Files indices 24-31 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL3_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding3)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("You stopped filtering. Everything's a little too loud and a little too close to the surface right now." #title "Raw Nerve")
			(if(MarkCaseFile(24))
				Print("Case Files: Raw Nerve, filed." #title "New Case File")
			)
		)
		(case 1
			Print("The mask came off somewhere along the way and you never found a good moment to put it back on." #title "Nothing Between You And It")
			(if(MarkCaseFile(25))
				Print("Case Files: Nothing Between You And It, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Whatever usually buffers you from the world just wasn't there today. You felt everything at full volume." #title "Exposed Wiring")
			(if(MarkCaseFile(26))
				Print("Case Files: Exposed Wiring, filed." #title "New Case File")
			)
		)
		(case 3
			Print("People got the real reaction, in real time, with none of the usual smoothing. It was a lot. For everyone." #title "The Unfiltered Version")
			(if(MarkCaseFile(27))
				Print("Case Files: The Unfiltered Version, filed." #title "New Case File")
			)
		)
		(case 4
			Print("You spent the day one comment away from visibly reacting to everything. Some days are just like that." #title "Too Close To The Surface")
			(if(MarkCaseFile(28))
				Print("Case Files: Too Close To The Surface, filed." #title "New Case File")
			)
		)
		(case 5
			Print("The usual layer between you and the day just wasn't there. You made it through anyway, a little rawer for it." #title "Skinless")
			(if(MarkCaseFile(29))
				Print("Case Files: Skinless, filed." #title "New Case File")
			)
		)
		(case 6
			Print("You didn't cover anything up today. It wasn't strategic. It just happened, and here you still are." #title "The Open Wound Approach")
			(if(MarkCaseFile(30))
				Print("Case Files: The Open Wound Approach, filed." #title "New Case File")
			)
		)
		(case 7
			Print("There was no version of today where you could smooth this over. So you didn't. That's new, for you." #title "Nowhere to Hide It")
			(if(MarkCaseFile(31))
				Print("Case Files: Nowhere to Hide It, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
