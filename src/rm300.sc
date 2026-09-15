/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm300.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 1).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 300)
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
(instance public rm300 of Rm
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
			= glitchText "Introduce yourself to the reflection like you've never met."
		)
		= choice PrintChoices(
			"You catch your reflection in a dark window and don't recognize yourself for a full second."
			"The Mirror"
			290
			glitchText
		"Immediately look away and think about anything else." 0
		"Stare longer, looking for evidence of who you used to be." 1
		"Say something kind to it, out loud, even though it's weird." 2
		"Glare back at it like the reflection started this." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'Hi, I'm you.' The reflection does not seem convinced either.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You declined an invitation from your own face.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You interrogated a witness who can't testify.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 15 TAG_SECURE)
			Print("You addressed yourself like a person worth addressing. Because you are.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 0 TAG_FIGHT)
			Print("You picked a fight with your own face.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
