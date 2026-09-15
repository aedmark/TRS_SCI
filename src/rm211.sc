/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm211.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 11).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 211)
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
(instance public rm211 of Rm
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
			= glitchText "Stand up and take a long, silent bow."
		)
		= choice PrintChoices(
			"In the meeting, your manager describes your idea as something the team 'landed on together.' Nobody looks at you."
			"The Unclaimed Credit"
			290
			glitchText
		"Say nothing. Add it to the list you're keeping in your head." 0
		"Laugh it off and agree it really was a group effort." 1
		"Mention afterward, privately and plainly, that the idea was yours." 2
		"Speak up in the meeting, right then, and correct the record." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Three people clap before realizing they don't know why. Doesn't matter; validation is transferable but NOT refundable.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You filed it under evidence, case still open.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You co-signed the erasure to keep the room comfortable.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 15 TAG_SECURE)
			Print("You said the true thing to one person instead of nobody. Feel better?")
		)
		(case 3
			ApplyChoiceEffects(-10 -20 5 TAG_FIGHT)
			Print("You corrected the record in real time. The room got very interested in their notes.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
