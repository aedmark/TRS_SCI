/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm206.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 6).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 206)
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
(instance public rm206 of Rm
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
			= glitchText "Lean in. Claim the typo was avant-garde on purpose."
		)
		= choice PrintChoices(
			"You meant to type something professional. Autocorrect had other plans. It has already been sent."
			"The Autocorrect Betrayal"
			290
			glitchText
		"Send six frantic follow-ups explaining what you meant." 0
		"Screenshot it, close the laptop, stare at the wall." 1
		"Let the typo be funny. It's kind of funny." 2
		"Set your status to 'in a meeting' and stay dark until the group chat moves on." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have accidentally invented a new art movement and the accountants are fond of you, now.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 10 -15 TAG_FAWN)
			Print("You buried the joke under a small avalanche of context and shame.")
		)
		(case 1
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You archived the evidence and fled the scene of a victimless crime.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You allowed yourself to be a person who makes typos. You survived.")
		)
		(case 3
			ApplyChoiceEffects(15 -8 -5 TAG_FLIGHT)
			Print("You let the silence do the explaining. It explained nothing, which was the point.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
