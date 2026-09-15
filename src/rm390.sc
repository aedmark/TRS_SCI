/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm390.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 26).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 390)
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
(instance public rm390 of Rm
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
			= glitchText "Ask an employee, with real hope, if you can still buy your two items outside."
		)
		= choice PrintChoices(
			"It goes off while you're two items into your shopping list, and everyone has to decide, together, how seriously to take it."
			"The Fire Alarm Mid-Errand"
			290
			glitchText
		"Apologize to the employee at the door like the alarm is somehow your fault." 0
		"Sprint outside faster than the actual emergency protocol requires." 1
		"Walk out calmly with everyone else and wait for the all-clear." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They said no. You asked again. They said no again, more slowly.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized for a fire alarm you did not build, own, or trigger.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 3 TAG_FLIGHT)
			Print("You treated a possible drill like a certain inferno. Better safe, you tell yourself.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You treated an alarm like an alarm: worth responding to, not worth panicking over.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
