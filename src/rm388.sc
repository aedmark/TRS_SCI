/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm388.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 24).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 388)
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
(instance public rm388 of Rm
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
			= glitchText "Push every button on the panel, just to see what happens."
		)
		= choice PrintChoices(
			"It's a short ride, but the silence with the one other passenger has gone on for one floor too long to still feel normal."
			"The Silent Elevator"
			290
			glitchText
		"Say something bright and pointless just to fill the air." 0
		"Study the floor numbers with the intensity of someone defusing a bomb." 1
		"Let the silence just be a normal, unremarkable silence." 2
		"Get off one floor early, for no reason you'd admit to." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are now stopping on every floor. The other passenger has begun to visibly reconsider their life choices.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You performed small talk for two floors so the silence wouldn't have to belong to anyone.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -3 TAG_FREEZE)
			Print("You survived an elevator ride by pretending very hard to be a wall fixture.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("Two people stood quietly near each other for a few seconds. Nothing happened. Nothing needed to.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("You added four flights of stairs to your day to skip eleven more seconds of silence.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
