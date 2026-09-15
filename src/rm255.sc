/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm255.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 21).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 255)
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
(instance public rm255 of Rm
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
			= glitchText "Leap up and greet them in full detective-noir monologue."
		)
		= choice PrintChoices(
			"Someone's home later than expected. You're awake now, doing math you don't want to be doing."
			"The Sound Of The Garage Door At 2 AM"
			290
			glitchText
		"Lie perfectly still, pretending to be asleep, wide awake." 0
		"Get up and greet them cheerfully, hiding that you were worried." 1
		"Ask in the morning if everything's okay." 2
		"Meet them at the door and demand to know where they've been." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'You've got some explaining to do! At 2 AM! ...In this economy!'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You performed sleep you weren't getting.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You covered the worry with a smile at 2 AM.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You waited for daylight and asked a plain question. They just lost track of time.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 3 TAG_FIGHT)
			Print("You turned worry into an interrogation.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
