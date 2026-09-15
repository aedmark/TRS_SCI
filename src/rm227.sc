/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm227.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 27).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 227)
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
(instance public rm227 of Rm
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
			= glitchText "Reply-all to the rumor thread with a single question mark."
		)
		= choice PrintChoices(
			"A screenshot from an anonymous coworker forum is going around. It names your department. Nobody official has said a word."
			"The Layoff Rumor"
			290
			glitchText
		"Refresh your email every four minutes for the rest of the day." 0
		"Close every work tab and watch something mindless until it's time to log off." 1
		"Update your resume calmly, then close the laptop for the night." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Forty people saw it. Nobody answered. You have created a new, smaller panic.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -5 TAG_FREEZE)
			Print("You got nothing but a calendar reminder and a headache.")
		)
		(case 1
			ApplyChoiceEffects(-8 -8 0 TAG_FLIGHT)
			Print("You bought yourself a few hours of not knowing, on purpose.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You prepared for the worst without living inside it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
