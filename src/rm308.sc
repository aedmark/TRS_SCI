/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm308.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 9).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 308)
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
(instance public rm308 of Rm
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
			= glitchText "Lean all the way in and do a full, committed impression of them."
		)
		= choice PrintChoices(
			"You caught yourself doing the exact thing, the exact way, that used to make you flinch when they did it."
			"The Habit You Picked Up From A Parent"
			290
			glitchText
		"Get angry at yourself for the resemblance." 0
		"Pretend you didn't notice and keep doing it anyway." 1
		"Notice it. Name it. Try, gently, to do the next one differently." 2
		"Change the subject in your head immediately and think about literally anything else." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's uncannily accurate. Everyone is deeply unsettled.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You punished the habit instead of just noticing it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let the pattern run unexamined, again.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught a pattern mid-motion, which is most of the work. Keep going.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You outran the resemblance instead of looking at it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
