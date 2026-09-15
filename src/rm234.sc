/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm234.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 0).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 234)
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
(instance public rm234 of Rm
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
			= glitchText "Sigh back, louder, escalating into a full operatic aria."
		)
		= choice PrintChoices(
			"Your partner sighs audibly in the other room. You have absolutely zero context for why."
			"The Sigh"
			290
			glitchText
		"Assume it's your fault and quietly clean the kitchen." 0
		"Ask aggressively, 'IS SOMETHING WRONG?!'" 1
		"Put on noise-canceling headphones." 2
		"Ask, calmly, 'hey, everything okay?' and actually wait for the answer." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Neither of you knows what started this. Both of you are committed now.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 15 -20 TAG_FAWN)
			Print("You traded self-worth for perceived safety and clean dishes.")
		)
		(case 1
			ApplyChoiceEffects(-15 -25 5 TAG_FIGHT)
			Print("You struck first to avoid being struck. Classic.")
		)
		(case 2
			ApplyChoiceEffects(25 -10 0 TAG_FLIGHT)
			Print("Avoidance achieved. The tension is stored in your jaw.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You asked instead of assuming or hiding.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
