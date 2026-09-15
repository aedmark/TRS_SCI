/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm339.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 7).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 339)
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
(instance public rm339 of Rm
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
			= glitchText "Google your symptoms while your chest gets tighter reading the results."
		)
		= choice PrintChoices(
			"Your chest has felt tight since the second cup of coffee. It's been four hours."
			"The Tight Chest"
			290
			glitchText
		"Have a third cup, see what happens." 0
		"Ignore it, it'll pass, it always passes." 1
		"Switch to water and sit somewhere quiet for a minute." 2
		"Leave your desk and wander the building until it eases, unannounced." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Peak efficiency: causing the exact symptom you were worried about.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -5 TAG_FIGHT)
			Print("You met a warning sign with more of the thing that caused it.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You filed it under 'later' along with everything else.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 5 TAG_SECURE)
			Print("You gave your nervous system one thing to not fight.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("You disappeared for twenty minutes and called it a walk.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
