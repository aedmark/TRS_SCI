/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm257.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 23).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 257)
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
(instance public rm257 of Rm
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
			= glitchText "Narrate the empty apartment like a nature documentary."
		)
		= choice PrintChoices(
			"Everyone's finally gone. The apartment is quiet in a way that feels, for one disorienting second, like something's wrong."
			"The Silence After The Front Door Closes"
			290
			glitchText
		"Immediately turn on background noise to fill the silence." 0
		"Start texting people to check if everyone got home okay." 1
		"Let the silence be silence for a minute before doing anything." 2
		"Grab your keys and find a reason to be somewhere else immediately." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'And here, in its natural habitat, the human finally exhales.'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("You filled a quiet room out of habit, not need.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You reached outward to avoid sitting in the quiet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("An empty room turned out to be just an empty room.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -8 TAG_FLIGHT)
			Print("You left before the quiet had a chance to say anything.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
