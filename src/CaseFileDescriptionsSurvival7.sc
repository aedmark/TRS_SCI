/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival7.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 7) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 56-63 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL7_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival7 index)
	(switch(index)
		(case 56 return("You're carrying more than you'd like to admit, and carrying it fine, for now."))
		(case 57 return("Nothing broke today. A few things bent. You're still counting that as a win."))
		(case 58 return("Not thriving. Not drowning. Actively, deliberately managing, which is its own quiet kind of work."))
		(case 59 return("Something in you is pulled tight and hasn't snapped. You've gotten used to the tightness."))
		(case 60 return("The weight's real. You're still standing under it. That's not nothing."))
		(case 61 return("Not boiling. Not cold. Just holding at a low, steady heat that takes real effort to maintain."))
		(case 62 return("This isn't a crisis. It's just a lot, held at a pace you can actually keep up."))
		(case 63 return("You're wound tighter than you'd like. You're also, somehow, still getting things done."))
	)
	return("")
)
/******************************************************************************/
