/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm322.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 23).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 322)
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
(instance public rm322 of Rm
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
			= glitchText "Start an entirely new, deliberately pointless streak instead."
		)
		= choice PrintChoices(
			"Forty-one days. You missed yesterday. The app has already reset the number to zero, and it feels disproportionately catastrophic."
			"The Streak You Broke"
			290
			glitchText
		"Berate yourself for the missed day, at length." 0
		"Decide the whole habit is ruined now and quietly stop." 1
		"Start again today. The forty-one days still happened." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Day one of 'touching a doorframe for luck.' It's going great.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You treated one missed day like it undid the other forty-one.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("One broken streak took the whole habit down with it.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("A number resetting didn't erase what you'd actually built.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
