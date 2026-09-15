/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm301.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 2).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 301)
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
(instance public rm301 of Rm
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
			= glitchText "Write a formal reply to nineteen-year-old you, dated properly."
		)
		= choice PrintChoices(
			"You find something you wrote at nineteen. It is more honest than anything you've said out loud this year."
			"The Old Journal Entry"
			290
			glitchText
		"Close it immediately and never mention this happened." 0
		"Feel a specific, targeted contempt for who you used to be." 1
		"Let it be true. You used to know something you forgot." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have now started a pen-pal relationship with your past self. Neat!")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FLIGHT)
			Print("You returned the evidence to its tomb.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -20 TAG_FIGHT)
			Print("You picked a fight with a nineteen-year-old and lost.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 20 TAG_SECURE)
			Print("You let an old truth back into the room. It feels warm.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
