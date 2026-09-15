/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival8.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 8) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 64-71 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL8_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival8 index)
	(switch(index)
		(case 64 return("You made it to tomorrow. Good job."))
		(case 65 return("That's it. Some days that's the entire accomplishment."))
		(case 66 return("Nothing about today fits a neater category than this. You got through it. That counts."))
		(case 67 return("No dramatic collapse, no dramatic triumph. Just a day, ending, with you still in it."))
		(case 68 return("Not the best day. Not the worst. A perfectly forgettable, perfectly fine day, and those matter too."))
		(case 69 return("This isn't the ending with a moral. It's just the one where you made it to the last turn."))
		(case 70 return("No collapse. No breakthrough. Just a day that happened, the way most of them do."))
		(case 71 return("Most days end like this: quietly, without a headline. This was one of those."))
	)
	return("")
)
/******************************************************************************/
