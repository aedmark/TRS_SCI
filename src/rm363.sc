/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm363.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 31).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 363)
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
(instance public rm363 of Rm
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
			= glitchText "Answer with a completely unrelated fact about your day instead."
		)
		= choice PrintChoices(
			"Someone asks if you're okay, and your throat closes around the answer before you can decide what it actually is."
			"The Tight Throat"
			290
			glitchText
		"Force out a bright 'I'm fine!' before the tightness can turn into anything else." 0
		"Say nothing and just nod until the moment passes on its own." 1
		"Say 'actually, not really' and let the sentence stop there for now." 2
		"Snap 'why do you ask?' before you've decided you meant to sound that sharp." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody knows what just happened, including you. The moment is over, at least.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -10 TAG_FAWN)
			Print("You got the words out. They weren't the true ones. Nobody believes you.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The moment passed. The tightness didn't, not really.")
		)
		(case 2
			ApplyChoiceEffects(-10 3 10 TAG_SECURE)
			Print("Three honest words did more than a paragraph of fine would have.")
		)
		(case 3
			ApplyChoiceEffects(-5 -15 0 TAG_FIGHT)
			Print("The question was gentle. The answer wasn't.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
