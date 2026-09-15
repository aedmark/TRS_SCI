/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm215.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 15).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 215)
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
(instance public rm215 of Rm
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
			= glitchText "Reply with only a countdown timer emoji, repeated forty times."
		)
		= choice PrintChoices(
			"'Small change:' the deadline that was next month is now Friday. The email has an exclamation point in it."
			"The Deadline Moved Up"
			290
			glitchText
		"Reply 'no problem!' before you've checked if it's a problem." 0
		"Push back, in writing, on what's actually possible by Friday." 1
		"Open the file. Close the file. Open a different file." 2
		"Reply with what's actually achievable by Friday, and what isn't." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody has ever communicated dread this efficiently. You're promoted to CEO.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 15 -15 TAG_FAWN)
			Print("You agreed to the math before calculating it. Your team hates you now. Probably.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You said the number out loud. The exclamation point has no power over you.")
		)
		(case 2
			ApplyChoiceEffects(25 0 -15 TAG_FREEZE)
			Print("You orbited the work without landing on it. Nothing gets done.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 10 TAG_SECURE)
			Print("You answered with the truth instead of a reflex.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
