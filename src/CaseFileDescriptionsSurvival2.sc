/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival2.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 2) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 16-23 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL2_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival2 index)
	(switch(index)
		(case 16 return("You stopped hiding. It cost you more than you expected, but you kept yourself."))
		(case 17 return("People saw the real thing, unedited. Some of them left. You're still here, which is the point."))
		(case 18 return("You ran out of energy for the act partway through and just stopped. Turns out that was allowed."))
		(case 19 return("It isn't pretty. It isn't curated. It's yours, all the way through, for the first time in a while."))
		(case 20 return("You let people see the parts you used to manage. A few flinched. You didn't take it back."))
		(case 21 return("Being yourself had a price today. You paid it and you're still standing in the same shoes."))
		(case 22 return("Nothing about today was smooth. All of it was real, which turned out to matter more."))
		(case 23 return("You stopped insulating yourself from being seen. It sparked a little. You're still conducting."))
	)
	return("")
)
/******************************************************************************/
