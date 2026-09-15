/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival5.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 5 (8 variants), matching CONTENT_ENDINGS[5] in js/content-endings.js. Flat Case Files indices 40-47 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL5_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding5)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("Nothing is fixed. Nothing is on fire. This might be what okay feels like." #title "Fragile Equilibrium")
			(if(MarkCaseFile(40))
				Print("Case Files: Fragile Equilibrium, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Not great, not terrible. You're starting to suspect that's just what most days actually are." #title "The Uneasy Middle")
			(if(MarkCaseFile(41))
				Print("Case Files: The Uneasy Middle, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Everything's balanced today. You know better than to assume that's permanent. You'll take it anyway." #title "Level, For Now")
			(if(MarkCaseFile(42))
				Print("Case Files: Level, For Now, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Nothing demanded a reaction today. That's rarer than it should be, and you noticed it." #title "A Quiet Enough Day")
			(if(MarkCaseFile(43))
				Print("Case Files: A Quiet Enough Day, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Not fixed. Not falling apart. Just steady, in a way that felt almost unfamiliar by the end of it." #title "Holding Steady")
			(if(MarkCaseFile(44))
				Print("Case Files: Holding Steady, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Not thriving, not failing. Somewhere in the wide, unglamorous middle most of life actually happens in." #title "The In-Between")
			(if(MarkCaseFile(45))
				Print("Case Files: The In-Between, filed." #title "New Case File")
			)
		)
		(case 6
			Print("Nothing today asked more of you than you had. That in itself felt like a small, quiet win." #title "Manageable")
			(if(MarkCaseFile(46))
				Print("Case Files: Manageable, filed." #title "New Case File")
			)
		)
		(case 7
			Print("The boat didn't rock much today. You're starting to remember what that's supposed to feel like." #title "Even Keel")
			(if(MarkCaseFile(47))
				Print("Case Files: Even Keel, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
