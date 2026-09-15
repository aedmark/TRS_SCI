/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsMechanisms.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Coping mechanism descriptions for the Case Files viewer's "View" detail
 screen, flat indices 102-106 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_MECHANISMS_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionMechanisms index)
	(switch(index)
		(case 102 return("You've learned that being needed is safer than being seen. It pads your Social Mask a little every time, and quietly costs your Inner Child."))
		(case 103 return("Removing yourself from the room becomes reflex. It bleeds off Repression fast, but your Social Mask takes the hit every time you leave."))
		(case 104 return("You stopped swallowing it. Repression drops hard and immediate, but the damage to your Social Mask is worse."))
		(case 105 return("You go somewhere else while your body stays in the room. Repression quietly climbs while you're gone, and your Inner Child pays the toll."))
		(case 106 return("An actual regulated response instead of a coping one. It's the only mechanism that heals instead of trading: Repression drops, Inner Child grows."))
	)
	return("")
)
/******************************************************************************/
