/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival5.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 5) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 40-47 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL5_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival5 index)
	(switch(index)
		(case 40 return("Nothing is fixed. Nothing is on fire. This might be what okay feels like."))
		(case 41 return("Not great, not terrible. You're starting to suspect that's just what most days actually are."))
		(case 42 return("Everything's balanced today. You know better than to assume that's permanent. You'll take it anyway."))
		(case 43 return("Nothing demanded a reaction today. That's rarer than it should be, and you noticed it."))
		(case 44 return("Not fixed. Not falling apart. Just steady, in a way that felt almost unfamiliar by the end of it."))
		(case 45 return("Not thriving, not failing. Somewhere in the wide, unglamorous middle most of life actually happens in."))
		(case 46 return("Nothing today asked more of you than you had. That in itself felt like a small, quiet win."))
		(case 47 return("The boat didn't rock much today. You're starting to remember what that's supposed to feel like."))
	)
	return("")
)
/******************************************************************************/
