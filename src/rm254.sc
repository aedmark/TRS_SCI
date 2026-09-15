/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm254.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 20).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 254)
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
(instance public rm254 of Rm
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
			= glitchText "Redraw the seating chart yourself, exiling the two feuding relatives to the garage."
		)
		= choice PrintChoices(
			"You've been placed, again, between the two relatives most likely to start a debate over the mashed potatoes."
			"The Holiday Seating Chart"
			290
			glitchText
		"Prepare a mental list of neutral topics to redirect toward." 0
		"Accept your fate and mentally leave the table early." 1
		"Re-assign yourself to the kids table and enjoy yourself." 2
		"Develop a sudden, urgent need to help in the kitchen for the entire meal." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The garage now has better conversation than the dining room.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 10 -10 TAG_FAWN)
			Print("You showed up armed with small talk as crowd control.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You were present in body only, and only barely. Nobody noticed.")
		)
		(case 2
			ApplyChoiceEffects(-5 -5 10 TAG_SECURE)
			Print("You provided your own accommodation and now your nephews think you're cool.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -5 TAG_FLIGHT)
			Print("You escaped to the stovetop and let the potatoes take the heat instead.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
