/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival6.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 6 (8 variants), matching CONTENT_ENDINGS[6] in js/content-endings.js. Flat Case Files indices 48-55 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL6_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding6)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("Not surviving. Not performing. Just, for once, actually okay. You can tell the difference from the inside." #title "Actually Okay")
			(if(MarkCaseFile(48))
				Print("Case Files: Actually Okay, filed." #title "New Case File")
			)
		)
		(case 1
			Print("This isn't the version of fine you perform for other people. This is the actual, unperformed version." #title "The Real Thing")
			(if(MarkCaseFile(49))
				Print("Case Files: The Real Thing, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Good, without a footnote explaining why it doesn't count. You keep waiting for the footnote. It doesn't come." #title "No Asterisk")
			(if(MarkCaseFile(50))
				Print("Case Files: No Asterisk, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Nobody's throwing you a parade for this, and it doesn't need one. You're just genuinely doing well, today." #title "Quietly Thriving")
			(if(MarkCaseFile(51))
				Print("Case Files: Quietly Thriving, filed." #title "New Case File")
			)
		)
		(case 4
			Print("You didn't have to build this feeling. It was just there today, the way it's supposed to be sometimes." #title "The Unforced Smile")
			(if(MarkCaseFile(52))
				Print("Case Files: The Unforced Smile, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Nothing underneath you feels like it's about to give way. You're still getting used to that." #title "Solid Ground")
			(if(MarkCaseFile(53))
				Print("Case Files: Solid Ground, filed." #title "New Case File")
			)
		)
		(case 6
			Print("This good day wasn't a mask. You checked. Twice. It held up both times." #title "Earned, Not Performed")
			(if(MarkCaseFile(54))
				Print("Case Files: Earned, Not Performed, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Same shape as a good day you'd fake for someone else's benefit. The difference is nobody had to be convinced, including you." #title "The Genuine Article")
			(if(MarkCaseFile(55))
				Print("Case Files: The Genuine Article, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
