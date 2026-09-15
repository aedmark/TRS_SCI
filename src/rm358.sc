/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm358.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 26).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 358)
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
(instance public rm358 of Rm
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
			= glitchText "Diagnose yourself with something dramatic via a search engine"
		)
		= choice PrintChoices(
			"A dull ache starts behind your eyes around hour six of sustained screen time, and it isn't going anywhere."
			"The Strain Headache"
			290
			glitchText
		"Push through it and keep working like the headache isn't happening." 0
		"Snap the laptop shut harder than necessary and complain to whoever's nearby." 1
		"Actually step away for ten whole minutes." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The internet has once again ruined your day. You are tired, not sick.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The headache did not care that you ignored it. It rarely does.")
		)
		(case 1
			ApplyChoiceEffects(-8 -10 0 TAG_FIGHT)
			Print("The laptop survived. Your reputation for calmness took the hit.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("Time away did more than the last two hours of pushing through did.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
