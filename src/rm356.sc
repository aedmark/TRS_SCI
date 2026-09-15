/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm356.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 24).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 356)
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
(instance public rm356 of Rm
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
			= glitchText "Start reorganizing your phone's apps by color."
		)
		= choice PrintChoices(
			"It's 2 AM. Your body is exhausted. Your brain has opened fourteen tabs and refuses to close any of them."
			"The Insomnia Loop"
			290
			glitchText
		"Keep lying there, perfectly still, willing sleep to just happen." 0
		"Give up and scroll your phone until your eyes finally give out first." 1
		"Get up, write down whatever's looping, and try again without it in your head." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your home screen has never looked better. You are still awake.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -5 TAG_FREEZE)
			Print("Stillness didn't work. It never really does.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You traded one kind of awake for an eye-straining kind.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the thought somewhere to be that wasn't just your pillow.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
