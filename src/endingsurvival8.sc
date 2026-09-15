/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival8.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 8 (8 variants), matching CONTENT_ENDINGS[8] in js/content-endings.js. Flat Case Files indices 64-71 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL8_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding8)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("You made it to tomorrow. Good job." #title "Functional Enough")
			(if(MarkCaseFile(64))
				Print("Case Files: Functional Enough, filed." #title "New Case File")
			)
		)
		(case 1
			Print("That's it. Some days that's the entire accomplishment." #title "You're Still Here")
			(if(MarkCaseFile(65))
				Print("Case Files: You're Still Here, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Nothing about today fits a neater category than this. You got through it. That counts." #title "Day Survived")
			(if(MarkCaseFile(66))
				Print("Case Files: Day Survived, filed." #title "New Case File")
			)
		)
		(case 3
			Print("No dramatic collapse, no dramatic triumph. Just a day, ending, with you still in it." #title "The Unremarkable Ending")
			(if(MarkCaseFile(67))
				Print("Case Files: The Unremarkable Ending, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Not the best day. Not the worst. A perfectly forgettable, perfectly fine day, and those matter too." #title "Adequate")
			(if(MarkCaseFile(68))
				Print("Case Files: Adequate, filed." #title "New Case File")
			)
		)
		(case 5
			Print("This isn't the ending with a moral. It's just the one where you made it to the last turn." #title "Good Enough, For Now")
			(if(MarkCaseFile(69))
				Print("Case Files: Good Enough, For Now, filed." #title "New Case File")
			)
		)
		(case 6
			Print("No collapse. No breakthrough. Just a day that happened, the way most of them do." #title "Nothing To Report")
			(if(MarkCaseFile(70))
				Print("Case Files: Nothing To Report, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Most days end like this: quietly, without a headline. This was one of those." #title "The Ordinary Ending")
			(if(MarkCaseFile(71))
				Print("Case Files: The Ordinary Ending, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
