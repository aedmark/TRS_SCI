/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm345.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 13).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 345)
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
(instance public rm345 of Rm
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
			= glitchText "Buy one of every single kind out of sheer decision fatigue."
		)
		= choice PrintChoices(
			"You are standing in front of forty kinds of the same cereal and you cannot make your body pick one."
			"The Overwhelm in the Cereal Aisle"
			290
			glitchText
		"Grab the one closest to your hand and leave fast." 0
		"Stand there until someone else's cart forces you to move." 1
		"Breathe, pick the familiar one on purpose, keep walking." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now own more cereal than a household requires. Problem technically solved.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 0 -5 TAG_FLIGHT)
			Print("You escaped the aisle. The cereal was incidental.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("Your body vetoed the decision and nobody overruled it.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 5 TAG_SECURE)
			Print("You made a small decision on purpose. It counts.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
