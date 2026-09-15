/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsFailure0.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Failure ending (repression) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 72-81 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_FAILURE0_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionFailure0 index)
	(switch(index)
		(case 72 return("Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet."))
		(case 73 return("Repression maxed out the scale. Something that was supposed to stay buried came up all at once, in the worst possible meeting."))
		(case 74 return("Repression blown. You said the quiet part out loud. To the wrong person."))
		(case 75 return("You held it in until you couldn't. Now everyone knows exactly how you feel, whether they asked or not."))
		(case 76 return("Repression hit 100%. You cried in the car for forty minutes before you could turn the key."))
		(case 77 return("You reached capacity. The thing that finally broke you was, embarrassingly, very minor."))
		(case 78 return("It wasn't the big thing. It was the small thing on top of the big thing. Repression is at zero now, mostly because you have none left to hold."))
		(case 79 return("Repression hit 100% in front of everybody. There is no version of Monday that fixes this."))
		(case 80 return("You were just fine! You've been fine this whole time! You planned on being fine all day... \n\n\n You are not fine."))
		(case 81 return("Repression maxed out and took the rest of your composure down with it."))
	)
	return("")
)
/******************************************************************************/
