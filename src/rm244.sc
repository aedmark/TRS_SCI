/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm244.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 10).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 244)
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
(instance public rm244 of Rm
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
			= glitchText "Knock back in a rhythm, just to see if they knock again."
		)
		= choice PrintChoices(
			"You can hear your neighbors arguing again, muffled but unmistakable, through a wall that was not built for privacy."
			"The Wall Between Apartments"
			290
			glitchText
		"Turn up the TV as loud as you can and hope they can heart it." 0
		"Consider, seriously, banging on the wall. Do nothing instead." 1
		"Put on headphones and let it be someone else's problem." 2
		"Grab your stuff and go sit somewhere else in the apartment entirely." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("A wall-based friendship begins, tentatively, in Morse-adjacent taps.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You drowned out a sound you couldn't control.")
		)
		(case 1
			ApplyChoiceEffects(-5 5 -5 TAG_FREEZE)
			Print("You almost inserted yourself into a fight that isn't yours.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Not every wall's noise is yours to carry.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You relocated instead of just tuning it out.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
