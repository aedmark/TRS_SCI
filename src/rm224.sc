/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm224.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 24).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 224)
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
(instance public rm224 of Rm
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
			= glitchText "Reply instantly with 'Your wish is my command, Master.'"
		)
		= choice PrintChoices(
			"A message lands in your inbox at 5:58 PM, two minutes before you were going to log off. It starts with 'quick question.'"
			"The End-of-Day Ping"
			290
			glitchText
		"Stay online another forty-five minutes to answer it fully." 0
		"Reply first thing tomorrow, on purpose." 1
		"Stare at the message, unable to decide, until it's 7 PM anyway." 2
		"Reply immediately: 'This needs to wait until tomorrow. Logging off now.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("This was not the reassurance they were hoping for. You never hear from them again.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 10 -15 TAG_FAWN)
			Print("You extended the day to protect someone else's evening. How noble.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let 5:58 PM mean what it says.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("Indecision cost you the boundary you meant to keep.")
		)
		(case 3
			ApplyChoiceEffects(-10 -15 5 TAG_FIGHT)
			Print("You said no in real time, which is somehow rarer than saying yes.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
