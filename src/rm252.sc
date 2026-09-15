/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm252.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 18).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 252)
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
(instance public rm252 of Rm
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
			= glitchText "Shake the envelope and try to guess the contents like a game show."
		)
		= choice PrintChoices(
			"A letter arrives from someone you haven't spoken to in years. You've been staring at the envelope, unopened, for ten minutes."
			"The Return Address You Don't Recognize"
			290
			glitchText
		"Put it in a drawer. Deal with it 'later.'" 0
		"Open it and immediately plan an apologetic, generous reply." 1
		"Open it. Read it. Feel whatever you feel." 2
		"Rip it open and read it standing up, ready to argue with whatever's inside." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have guessed 'ferret' three times. You weren't even close.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You filed the unknown away instead of facing it.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You started drafting amends before reading the actual letter.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let the envelope just be information.")
		)
		(case 3
			ApplyChoiceEffects(-8 -12 3 TAG_FIGHT)
			Print("You went in swinging at a letter that hadn't done anything yet.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
