/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm328.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 29).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 328)
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
(instance public rm328 of Rm
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
			= glitchText "Answer with the most impulsive thing that comes to mind, unfiltered."
		)
		= choice PrintChoices(
			"Someone asks, casually, 'so what do you actually want?' and your mind goes completely, embarrassingly blank."
			"The Question You Can't Answer"
			290
			glitchText
		"Give the answer you think they want to hear instead of sitting in the blank." 0
		"Laugh it off and change the subject before the silence gets uncomfortable." 1
		"Just say, honestly, 'I don't actually know yet.'" 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You surprised yourself with the answer. You're still not sure it's 100% true.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You answered the question they asked instead of the one that stumped you.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("The subject changed. The blank is still there, waiting for next time.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("Not knowing, said out loud, turned out to be survivable.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
