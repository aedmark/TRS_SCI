/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm277.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 11).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 277)
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
(instance public rm277 of Rm
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
			= glitchText "Walk over and loudly introduce yourself as if you'd never met."
		)
		= choice PrintChoices(
			"You didn't know they'd be here. They just walked in, laughing at something, not looking your way yet."
			"The Ex at the Party"
			290
			glitchText
		"Find a reason to be near the exit for the rest of the night." 0
		"Go say 'hi' first, overly warm, before they can find you." 1
		"Stay where you are. Say 'hi' if it happens naturally." 2
		"Make sure they see you having a visibly better time than them." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("This confuses everyone, including, eventually, you.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You mapped an escape route instead of a plan.")
		)
		(case 1
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You got there first so you could control the narrative. They still make you feel powerless.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You didn't need to manage the whole room to survive; they left before they even saw you.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You turned the whole party into a performance for one person.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
