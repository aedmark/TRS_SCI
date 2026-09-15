/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm394.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 30).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 394)
/******************************************************************************/
(use "main")
(use "controls")
(use "cycle")
(use "game")
(use "feature")
(use "obj")
(use "inv")
(use "printchoices")
(use "mechanisms")
/******************************************************************************/
(instance public rm394 of Rm
	(properties
		picture 1
		north 0
		east 0
		south 0
		west 0
	)
	(method (init)
		(var choice, glitchText)
		(super:init())
		SetUpEgo()
		(send gEgo:init())
		ProgramControl()
		(send gEgo:hide())
		= glitchText NULL
		(if(< Random(0 99) GLITCH_CHANCE_PCT)
			= glitchText "Lean all the way in and give them a full store tour anyway."
		)
		= choice PrintChoices(
			"You're wearing a shirt that happens to be the same color as the store's uniform, and a stranger is now approaching you with a question and a basket."
			"The 'Do You Work Here?' Moment"
			290
			glitchText
		"Try your best to actually help them find what they need." 0
		"Say nothing and duck around the next aisle before they finish the sentence." 1
		"Say 'sorry, I don't work here, but I think it's in aisle six.'" 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You just gave a stranger better customer service than most actual employees. No one paid you. You feel weirdly great about it.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You performed employment at a store that has never once paid you.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You evaded a mild case of mistaken identity like it was a subpoena.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You corrected the mistake and still helped, because both things were easy to do at once.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
