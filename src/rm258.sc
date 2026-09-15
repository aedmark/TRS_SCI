/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm258.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 24).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 258)
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
(instance public rm258 of Rm
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
			= glitchText "Start browsing apartments you can't afford, in a city you don't live in."
		)
		= choice PrintChoices(
			"An envelope from the landlord. The number at the bottom is $200 more than last year, effective next month."
			"The Rent Increase"
			290
			glitchText
		"Set the letter on the counter and don't look at the actual number again for a week." 0
		"Draft a furious response citing every unfixed thing in the apartment." 1
		"Sit down and actually rework the budget around the new number." 2
		"Pick up an extra shift so you don't have to actually look at the budget." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You've mentally moved to a lake house. Your higher rent is still due next month.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -8 TAG_FREEZE)
			Print("The number didn't go away. It just waited.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("All true. None of it will lower the rent. Or your blood pressure.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You made the number smaller by making it real instead of avoided.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("You outran the number instead of facing it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
