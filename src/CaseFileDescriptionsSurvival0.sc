/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival0.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 0) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 0-7 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL0_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival0 index)
	(switch(index)
		(case 0 return("You didn't explode. You just got very, very good at ticking."))
		(case 1 return("You didn't self-destruct, yet. Today just isn't over anywhere else, either."))
		(case 2 return("Something in you is very close to the surface. You made it to the deadline before it did."))
		(case 3 return("You survived by holding something in the whole way through. Your shoulders will remember this tomorrow."))
		(case 4 return("Not boiling over. Not cooled down either. Just holding at a temperature (that isn't sustainable)."))
		(case 5 return("You made it through; white-knuckled. Nobody else could tell. Your jaw could."))
		(case 6 return("You crossed the finish line still overheating. The engine didn't seize. It was a close one, though."))
		(case 7 return("Whatever's in there is still in there."))
	)
	return("")
)
/******************************************************************************/
