/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm355.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 23).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 355)
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
(instance public rm355 of Rm
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
			= glitchText "Deep-clean the entire bathroom instead of just brushing your teeth."
		)
		= choice PrintChoices(
			"It's almost bedtime and you realize you haven't done a single part of your evening routine. Or your morning one."
			"The Unbrushed Teeth"
			290
			glitchText
		"Do the bare minimum and call it a wash." 0
		"Spiral about what this says about you as a person." 1
		"Just do it now, no narrative required." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The sink has never been shinier. Your teeth remain exactly as they were.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 0 -5 TAG_FLIGHT)
			Print("You lowered the bar until you could technically step over it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You turned one skipped routine into a referendum on your whole character.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You took care of yourself late instead of not at all.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
