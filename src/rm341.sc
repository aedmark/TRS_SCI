/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm341.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 9).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 341)
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
(instance public rm341 of Rm
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
			= glitchText "Turn the bouncing into a full drum solo on the desk."
		)
		= choice PrintChoices(
			"Your leg has been bouncing under the desk for forty-five minutes. You only notice when someone asks if you're okay."
			"The Restless Leg"
			290
			glitchText
		"Say you're fine, laugh, keep bouncing." 0
		"Get up and pace the hallway instead." 1
		"Name it: 'I think I'm anxious about something.'" 2
		"Snap 'I'm FINE' at whoever asked, louder than the question needed." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Everyone in the room now knows exactly how you feel. Loudly.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You answered the question the body was already answering.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 0 TAG_FLIGHT)
			Print("You gave the energy somewhere bigger to go.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let the leg tell on you, and believed it.")
		)
		(case 3
			ApplyChoiceEffects(-5 -15 0 TAG_FIGHT)
			Print("You turned a kind question into a small confrontation.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
