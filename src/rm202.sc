/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm202.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 2).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 202)
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
(instance public rm202 of Rm
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
			= glitchText "Slide a note under the door reading only: 'I KNOW.'"
		)
		= choice PrintChoices(
			"You walk past a conference room where two managers are talking. They look at you and the door slowly closes."
			"The Closed Door"
			290
			glitchText
		"Begin packing up your desk mentally." 0
		"Work at 300% capacity for the next four hours." 1
		"Remind yourself you are an adult and this is fine." 2
		"Knock and ask, directly, if this is about you." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You know nothing. The door remains closed. Deeply satisfying anyway.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -20 TAG_FLIGHT)
			Print("You braced for the worst-case scenario. Which was Nothing.")
		)
		(case 1
			ApplyChoiceEffects(10 20 -25 TAG_FAWN)
			Print("You overcompensated through labor. Capitalism wins.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 5 TAG_SECURE)
			Print("You chose logic. Your amygdala chose to ignore it.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 3 TAG_FIGHT)
			Print("You confronted the closed door instead of guessing at it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
