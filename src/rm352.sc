/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm352.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 20).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 352)
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
(instance public rm352 of Rm
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
			= glitchText "Do one dramatic stretch and count it as a full workout."
		)
		= choice PrintChoices(
			"You had a plan to move your body today. Your body voted no."
			"The Skipped Workout"
			290
			glitchText
		"Guilt yourself about it for the rest of the evening." 0
		"Declare fitness dead to you and order takeout in protest." 1
		"Skip it without ceremony, no apology required." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Technically movement occurred. The bar has been set, and it is on the floor.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("You turned a rest day into a tribunal.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 5 TAG_FIGHT)
			Print("You staged a small rebellion against your own to-do list.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let 'not today' be a complete sentence.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
