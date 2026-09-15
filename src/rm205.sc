/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm205.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 5).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 205)
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
(instance public rm205 of Rm
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
			= glitchText "Send a single, unprompted photo of a raccoon looking concerned."
		)
		= choice PrintChoices(
			"Seen 3:12 PM. It is now 6:47 PM. No reply."
			"The Read Receipt"
			290
			glitchText
		"Send a second message: 'no worries just following up!'" 0
		"Close Slack and refuse to open it until tomorrow." 1
		"Assume you've been quietly deleted from their mind." 2
		"Let it sit, and trust that no reply yet doesn't mean no reply ever." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The raccoon says what you cannot. Which is nothing of importance.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You apologized for existing in their inbox (and being impatient).")
		)
		(case 1
			ApplyChoiceEffects(15 -5 0 TAG_FLIGHT)
			Print("Out of sight, technically not out of mind. Close enough.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You Sherlocked your way to a panic attack with zero evidence.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You let the silence be neutral instead of a verdict.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
