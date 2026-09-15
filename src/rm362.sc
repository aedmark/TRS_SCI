/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm362.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 30).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 362)
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
(instance public rm362 of Rm
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
			= glitchText "Blow on your hands dramatically like you're in a survival documentary."
		)
		= choice PrintChoices(
			"Your hands have been cold for an hour, in a room that isn't. You notice it right as you're trying to sign something important."
			"The Cold Hands"
			290
			glitchText
		"Apologize for the shaky signature and joke about the room being cold." 0
		"Just push through and hope no one notices your hands." 1
		"Pause, shake it out, and just wait until your hands actually feel steady." 2
		"Get irritated at your own body for picking now to do this." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It didn't warm anything up. It did get a laugh.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You blamed the thermostat for something the thermostat had nothing to do with.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("Someone noticed. You'll never know if it mattered.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You waited for your own body instead of overriding it.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 -5 TAG_FIGHT)
			Print("You argued with your own nervous system. It did not concede the point.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
