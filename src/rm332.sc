/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm332.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 0).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 332)
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
(instance public rm332 of Rm
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
			= glitchText "Reorganize your entire closet."
		)
		= choice PrintChoices(
			"You're awake at an ungodly hour for no reason, running a full inventory of every mistake you've made since birth."
			"The Wake-Up Call"
			290
			glitchText
		"Get up and scroll your phone until the sky turns on again" 0
		"Lie perfectly still and pretend this isn't happening." 1
		"Get up, write down the one thing actually bothering you, and go back to bed." 2
		"Draft the confrontation you'll never actually have, word for word." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your shirts are now sorted by emotional association. Impressive. And unhelpful.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You traded sleep for a different, worse kind of tired.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -5 TAG_FREEZE)
			Print("You waited it out. It did not go anywhere.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the thought somewhere else to live besides your brain.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 3 TAG_FIGHT)
			Print("You refought old arguments with people who aren't even in the room.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
