/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm299.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 0).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 299)
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
(instance public rm299 of Rm
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
			= glitchText "Track down the exact cleaning product, immediately, mid-errand."
		)
		= choice PrintChoices(
			"You catch a whiff of a specific cleaning product that smells exactly like your childhood home."
			"The Nostalgic Smell"
			290
			glitchText
		"Hold your breath until you pass out." 0
		"Cry silently in a bathroom stall." 1
		"Make a dark, self-deprecating joke to a coworker." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now own four bottles of it. This is a lot of bottles.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(30 0 -5 TAG_FLIGHT)
			Print("You physically rejected the memory.")
		)
		(case 1
			ApplyChoiceEffects(-20 -10 20 TAG_SECURE)
			Print("You processed an emotion privately. It's okay.")
		)
		(case 2
			ApplyChoiceEffects(5 -15 -10 TAG_FIGHT)
			Print("You weaponized the trauma for comedy.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
