/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm353.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 21).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 353)
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
(instance public rm353 of Rm
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
			= glitchText "Announce to the whole room that you are, in fact, fine."
		)
		= choice PrintChoices(
			"Your nose starts bleeding out of nowhere. Around people."
			"The Sudden Nosebleed"
			290
			glitchText
		"Play it off smoothly like this happens all the time." 0
		"Panic slightly and leave the room without explanation." 1
		"Tilt your head forward, breathe, and just deal with it." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody was worried until you said something. Now everyone's worried.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -5 TAG_FAWN)
			Print("You narrated your own bleeding nose as a completely normal event.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FLIGHT)
			Print("You exited stage left, mid-sentence, mid-nosebleed.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You handled a small crisis like an adult, briefly, on purpose.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
