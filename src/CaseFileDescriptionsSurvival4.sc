/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival4.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 4) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 32-39 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL4_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival4 index)
	(switch(index)
		(case 32 return("You looked fine all day. You have no real idea what was actually fueling that."))
		(case 33 return("From the outside, a completely normal day. On the inside, mostly static."))
		(case 34 return("You showed up, said the right things, looked put-together. Nothing underneath was doing the same."))
		(case 35 return("Everything worked. Nothing felt like anything. Both of those are true at once."))
		(case 36 return("You got through today on muscle memory and social competence. The rest of you clocked out hours ago."))
		(case 37 return("You were pleasant to everyone today. You couldn't say which of your own feelings, if any, showed up."))
		(case 38 return("Nothing cracked today. Nothing underneath the surface was really there to crack, either."))
		(case 39 return("You kept the shape of a normal day. Whatever's supposed to fill that shape took the day off."))
	)
	return("")
)
/******************************************************************************/
