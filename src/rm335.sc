/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm335.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 3).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 335)
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
(instance public rm335 of Rm
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
			= glitchText "Eat a mystery item from the back of the freezer instead."
		)
		= choice PrintChoices(
			"It's been hours since your lunch break. You still have not eaten. You only just now noticed the shaking."
			"The Skipped Lunch"
			290
			glitchText
		"Push through, you'll eat when this is 'actually done.'" 0
		"Eat standing up over the sink in under ninety seconds." 1
		"Sit down and finally enjoy your meal." 2
		"Snap at whoever scheduled back-to-back meetings through your lunch." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Unidentifiable but savory, slightly freezer-burned, and somehow the best part of your day.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You deferred a basic need to a deadline that keeps moving.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 0 TAG_FLIGHT)
			Print("Technically food. Technically eaten. Moving on. (Your colon hates you).")
		)
		(case 2
			ApplyChoiceEffects(-10 0 15 TAG_SECURE)
			Print("Wild concept: feeding yourself food when your body needs it because you matter.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You picked a fight with the calendar, which cannot hear you.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
