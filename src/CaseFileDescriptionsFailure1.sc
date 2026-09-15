/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsFailure1.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Failure ending (mask) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 82-91 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_FAILURE1_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionFailure1 index)
	(switch(index)
		(case 82 return("Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but free."))
		(case 83 return("Social Mask hit 0%. You said what you actually thought, out loud, in a group chat with your entire extended family."))
		(case 84 return("Mask hit zero. Turns out the truth doesn't need permission to leave your mouth."))
		(case 85 return("You ran out of mask exactly when someone asked how you were and they got the real answer."))
		(case 86 return("Social Mask bottomed out. Everyone can now see exactly what you've been holding back, and they have thoughts."))
		(case 87 return("Mask hit 0%. You told your boss what you actually think of the quarterly review. It felt incredible for six seconds."))
		(case 88 return("You stopped performing how 'fine' you are, and the room noticed immediately."))
		(case 89 return("Mask hit 0% at the worst possible time in the company meeting. At least it's memorable. You try to tell yourself they were laughing *with* you."))
		(case 90 return("You said the true thing instead of the nice thing. The silence afterward was very loud (and awkward)."))
		(case 91 return("Mask dropped to zero. You are now saying things out loud that used to just be inside thoughts."))
	)
	return("")
)
/******************************************************************************/
