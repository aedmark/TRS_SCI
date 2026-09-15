/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm221.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 21).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 221)
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
(instance public rm221 of Rm
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
			= glitchText "Start a spontaneous printer-line trivia night to pass the time."
		)
		= choice PrintChoices(
			"You're fourth in line at the printer, running late, and the person ahead of you is scrolling their phone between pages."
			"The Printer Line"
			290
			glitchText
		"Stand there, saying nothing, doing the math on how late you'll be." 0
		"Ask, politely but directly, if you can jump in for one page." 1
		"Wait it out and tell yourself it's fine, it's fine, it's fine." 2
		"Wait it out and use the extra two minutes to actually breathe." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody knew this much about the printer. Nobody wanted to.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You calculated the cost instead of asking for the copy.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You asked for the small thing you actually needed. You got it without any dirty looks.")
		)
		(case 2
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You narrated a calm you weren't actually experiencing, accomplishing nothing.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 5 TAG_SECURE)
			Print("You let the delay be a delay, not an emergency.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
