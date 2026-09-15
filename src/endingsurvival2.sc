/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 2 (8 variants), matching CONTENT_ENDINGS[2] in js/content-endings.js. Flat Case Files indices 16-23 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL2_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding2)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("You stopped hiding. It cost you more than you expected, but you kept yourself." #title "Radically Undone")
			(if(MarkCaseFile(16))
				Print("Case Files: Radically Undone, filed." #title "New Case File")
			)
		)
		(case 1
			Print("People saw the real thing, unedited. Some of them left. You're still here, which is the point." #title "The Unvarnished Version")
			(if(MarkCaseFile(17))
				Print("Case Files: The Unvarnished Version, filed." #title "New Case File")
			)
		)
		(case 2
			Print("You ran out of energy for the act partway through and just stopped. Turns out that was allowed." #title "Nothing Left to Perform")
			(if(MarkCaseFile(18))
				Print("Case Files: Nothing Left to Perform, filed." #title "New Case File")
			)
		)
		(case 3
			Print("It isn't pretty. It isn't curated. It's yours, all the way through, for the first time in a while." #title "The Honest Wreckage")
			(if(MarkCaseFile(19))
				Print("Case Files: The Honest Wreckage, filed." #title "New Case File")
			)
		)
		(case 4
			Print("You let people see the parts you used to manage. A few flinched. You didn't take it back." #title "Seen and Not Sorry")
			(if(MarkCaseFile(20))
				Print("Case Files: Seen and Not Sorry, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Being yourself had a price today. You paid it and you're still standing in the same shoes." #title "The Costly Truth")
			(if(MarkCaseFile(21))
				Print("Case Files: The Costly Truth, filed." #title "New Case File")
			)
		)
		(case 6
			Print("Nothing about today was smooth. All of it was real, which turned out to matter more." #title "Unpolished")
			(if(MarkCaseFile(22))
				Print("Case Files: Unpolished, filed." #title "New Case File")
			)
		)
		(case 7
			Print("You stopped insulating yourself from being seen. It sparked a little. You're still conducting." #title "The Exposed Wire")
			(if(MarkCaseFile(23))
				Print("Case Files: The Exposed Wire, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
