/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsFailure2.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Failure ending (child) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 92-101 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_FAILURE2_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionFailure2 index)
	(switch(index)
		(case 92 return("Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory.\n\n You feel nothing."))
		(case 93 return("Inner Child hit 0%. You drove home and don't remember any of it."))
		(case 94 return("Inner Child bottomed out. You're going through the motions, and the motions are all that's left."))
		(case 95 return("Inner Child hit zero. Someone asked how you were and you answered before you'd actually heard the question."))
		(case 96 return("Inner Child hit 0%. Nothing feels good. Nothing feels bad.\n\nNothing feels at all."))
		(case 97 return("Inner Child bottomed out. You are functioning perfectly and feeling absolutely no joy from it."))
		(case 98 return("Inner Child hit zero. There's a version of you still doing the tasks. You're not entirely sure where the rest of you went."))
		(case 99 return("Inner Child hit 0%. You're present in the room in the way furniture is also present."))
		(case 100 return("Inner Child bottomed out somewhere between one task and the next. You didn't notice it happen. That's the part that should worry you."))
		(case 101 return("Inner Child hit zero. You are technically fine. Technically is doing a lot of work in that sentence."))
	)
	return("")
)
/******************************************************************************/
