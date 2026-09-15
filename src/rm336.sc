/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm336.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 4).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 336)
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
(instance public rm336 of Rm
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
			= glitchText "Diagnose yourself with seven unrelated conditions via search engine."
		)
		= choice PrintChoices(
			"A headache has been building behind your left eye since you woke up."
			"The Tension Headache"
			290
			glitchText
		"Apologize to it and keep working through the pain." 0
		"Snap the laptop shut and lie in a dark room out of spite." 1
		"Drink water, step outside, take a break." 2
		"Tell everyone you're 'stepping out for air' and just don't come back for a while." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("According to the internet, it's either dehydration or something terminal. No in-between.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You negotiated with a headache. The headache won.")
		)
		(case 1
			ApplyChoiceEffects(-15 -10 5 TAG_FIGHT)
			Print("You declared war on productivity and productivity lost.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("You treated the cause instead of white-knuckling the symptom.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("You left before the headache did.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
