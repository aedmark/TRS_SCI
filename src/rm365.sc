/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm365.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 1).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 365)
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
(instance public rm365 of Rm
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
			= glitchText "Ask to speak to the hold music's manager."
		)
		= choice PrintChoices(
			"Forty minutes on hold, then a real person finally picks up, and you have to sound pleasant immediately."
			"The Customer Service Call"
			290
			glitchText
		"Apologize for 'bothering them' before you've even explained the problem." 0
		"Let all forty minutes of frustration out on the person who just picked up." 1
		"Take a breath, state the problem plainly, and stay civil." 2
		"Hang up and decide the problem can just stay unsolved." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("There is no such position. You asked anyway. You were put on hold again.\n\n\n Close enough.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You apologized for needing help before anyone accused you of it.")
		)
		(case 1
			ApplyChoiceEffects(-12 -15 0 TAG_FIGHT)
			Print("Correct target: the hold music. Actual target: an underpaid stranger.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You separated the wait from the person now trying to fix it.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("Forty minutes on hold, zero minutes of resolution.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
