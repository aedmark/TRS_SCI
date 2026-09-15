/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm222.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 22).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 222)
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
(instance public rm222 of Rm
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
			= glitchText "Take a bow for the growing audience behind you."
		)
		= choice PrintChoices(
			"Your badge doesn't scan. Three times in a row. There's a line building behind you now."
			"The Badge Scan Fail"
			290
			glitchText
		"Apologize to everyone behind you individually." 0
		"Freeze up completely, badge in hand, brain empty except for the intense panic that you've been fired." 1
		"Step aside, let people pass, try again without an audience." 2
		"Give up, turn around, and just go home for the day." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Someone starts filming. This will outlive your employment here.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You issued apologies for a malfunctioning badge reader.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("The door and your nervous system both stopped responding.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You removed the audience instead of performing through it.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 -5 TAG_FLIGHT)
			Print("You solved a broken badge reader by removing yourself from its jurisdiction.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
