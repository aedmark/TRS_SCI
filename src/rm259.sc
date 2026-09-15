/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm259.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 25).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 259)
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
(instance public rm259 of Rm
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
			= glitchText "Unplug everything in the apartment out of spite."
		)
		= choice PrintChoices(
			"The electric bill arrives at nearly twice last month's number, with no explanation you can find."
			"The Utility Bill That Doubled"
			290
			glitchText
		"Set up autopay so you never have to actually look at the number again." 0
		"Reread every line item like it's a puzzle with a solution." 1
		"Call and actually ask what changed, instead of guessing." 2
		"Call and immediately demand to speak to a supervisor." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now sit in the dark, on principle, having solved nothing.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("Out of sight. Still, technically, out of your account.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("There is no puzzle. There is just a bigger number.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("Turns out asking a real question gets you a real answer.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You went in swinging before anyone explained anything.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
