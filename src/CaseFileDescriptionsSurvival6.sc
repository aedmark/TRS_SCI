/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival6.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 6) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 48-55 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL6_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival6 index)
	(switch(index)
		(case 48 return("Not surviving. Not performing. Just, for once, actually okay. You can tell the difference from the inside."))
		(case 49 return("This isn't the version of fine you perform for other people. This is the actual, unperformed version."))
		(case 50 return("Good, without a footnote explaining why it doesn't count. You keep waiting for the footnote. It doesn't come."))
		(case 51 return("Nobody's throwing you a parade for this, and it doesn't need one. You're just genuinely doing well, today."))
		(case 52 return("You didn't have to build this feeling. It was just there today, the way it's supposed to be sometimes."))
		(case 53 return("Nothing underneath you feels like it's about to give way. You're still getting used to that."))
		(case 54 return("This good day wasn't a mask. You checked. Twice. It held up both times."))
		(case 55 return("Same shape as a good day you'd fake for someone else's benefit. The difference is nobody had to be convinced, including you."))
	)
	return("")
)
/******************************************************************************/
