/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm348.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 16).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 348)
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
(instance public rm348 of Rm
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
			= glitchText "Buy an entire tube of anti-itch cream for a psychosomatic itch."
		)
		= choice PrintChoices(
			"Something itches. You've checked twice. There is nothing there. It still itches."
			"The Itch That Isn't There"
			290
			glitchText
		"Scratch it anyway, repeatedly, in front of everyone." 0
		"Ignore it and hope it forgets about you first." 1
		"Notice it's stress, not skin, and address the actual thing." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The itch was never dermatological. The cream is very soothing regardless.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You enabled a feeling that had no physical location.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You waited out a sensation that was never going to negotiate.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You correctly diagnosed a body signal for once.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
