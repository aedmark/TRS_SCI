/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm311.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 12).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 311)
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
(instance public rm311 of Rm
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
			= glitchText "Lean all the way into it."
		)
		= choice PrintChoices(
			"You hear the sentence leave your mouth and recognize it a half-second too late. It's not yours. It's theirs."
			"The Thing You Said You'd Never Become"
			290
			glitchText
		"Spiral into a full self-indictment for the next hour." 0
		"Laugh it off in the moment and never think about it again... You swear!" 1
		"Notice it. Decide, calmly, that noticing is the first step, not a failure." 2
		"Change the subject out loud before anyone, including you, can dwell on it." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have never committed to a bit this hard. Everyone is a little worried about you.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -20 TAG_FIGHT)
			Print("You sentenced yourself over one inherited sentence.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You buried it under a laugh instead of a look.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught the echo without deciding it was proof of anything permanent.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You talked past the sentence instead of sitting with it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
