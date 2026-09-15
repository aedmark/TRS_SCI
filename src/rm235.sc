/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm235.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 1).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 235)
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
(instance public rm235 of Rm
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
			= glitchText "Build an elaborate dish-based sculpture instead of washing them."
		)
		= choice PrintChoices(
			"Your partner says 'hey, whenever you get a chance' about the dishes. Their tone was completely neutral."
			"The Dishes in the Sink"
			290
			glitchText
		"Do the dishes at 11 PM, narrating your resentment internally while sighing externally, and heavily." 0
		"Say 'I was GOING to' with more heat than the sentence needed." 1
		"Leave the room to 'find something' for four minutes. Flee state." 2
		"Say 'I'll get to it tonight' and actually mean it, no sighing required." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's actually kind of impressive. It does not count as washing them, though.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 10 -15 TAG_FAWN)
			Print("You complied loudly. Nobody is impressed.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 5 TAG_FIGHT)
			Print("The dishes were never the real defendant.")
		)
		(case 2
			ApplyChoiceEffects(10 -5 -5 TAG_FLIGHT)
			Print("A tactical retreat from a sink.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You did the small thing without turning it into a referendum.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
