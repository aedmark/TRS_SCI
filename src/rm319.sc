/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm319.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 20).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 319)
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
(instance public rm319 of Rm
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
			= glitchText "Draw it from memory, badly, in crayon, right now."
		)
		= choice PrintChoices(
			"You woke up at 4 AM with your heart going and no clear memory of why. The feeling stayed. The plot didn't."
			"The Nightmare You Can't Fully Remember"
			290
			glitchText
		"Lie there, awake, trying to force the memory back." 0
		"Get up and immediately start the day like nothing happened." 1
		"Let the feeling exist without the plot. You don't need the whole story." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The drawing is somehow both deeply upsetting and extremely funny.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You chased a plot your brain had already decided to withhold.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -15 TAG_FLIGHT)
			Print("You outran a feeling by scheduling over it.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You let your body's alarm matter even without a confirmed cause.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
