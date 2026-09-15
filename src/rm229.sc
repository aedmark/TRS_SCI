/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm229.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 29).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 229)
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
(instance public rm229 of Rm
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
			= glitchText "Start a betting pool on how long the mandate really lasts."
		)
		= choice PrintChoices(
			"An all-staff email announces four days in office starting next month. Your commute is about to double."
			"The Return-to-Office Mandate"
			290
			glitchText
		"Reply to the announcement thread with an enthusiastic 'Exciting news!!'" 0
		"Don't say anything. Just stare at the new calendar for a while." 1
		"Block out the commute time and start planning around the real cost." 2
		"Reply-all asking leadership to justify the policy with actual data." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are up eleven dollars and strangely invested in a policy you hate.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed excitement you will need a long drive to recover from.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The calendar did not change. Neither did you.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You dealt with the schedule instead of the feeling about the schedule.")
		)
		(case 3
			ApplyChoiceEffects(-10 -15 5 TAG_FIGHT)
			Print("You asked the room to show its work. The room did not appreciate it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
