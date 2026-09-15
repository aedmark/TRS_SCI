/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival3.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 3) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 24-31 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL3_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival3 index)
	(switch(index)
		(case 24 return("You stopped filtering. Everything's a little too loud and a little too close to the surface right now."))
		(case 25 return("The mask came off somewhere along the way and you never found a good moment to put it back on."))
		(case 26 return("Whatever usually buffers you from the world just wasn't there today. You felt everything at full volume."))
		(case 27 return("People got the real reaction, in real time, with none of the usual smoothing. It was a lot. For everyone."))
		(case 28 return("You spent the day one comment away from visibly reacting to everything. Some days are just like that."))
		(case 29 return("The usual layer between you and the day just wasn't there. You made it through anyway, a little rawer for it."))
		(case 30 return("You didn't cover anything up today. It wasn't strategic. It just happened, and here you still are."))
		(case 31 return("There was no version of today where you could smooth this over. So you didn't. That's new, for you."))
	)
	return("")
)
/******************************************************************************/
