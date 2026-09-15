/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm309.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 10).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 309)
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
(instance public rm309 of Rm
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
			= glitchText "Talk back to it out loud, in public, with real conviction."
		)
		= choice PrintChoices(
			"The criticism arrives in a tone you recognize. It's not how you'd talk to anyone else. It's exactly how someone once talked to you."
			"The Voice In Your Head That Isn't Yours"
			290
			glitchText
		"Agree with it. It's probably right, like it always was." 0
		"Argue back at it, harshly, in your own head." 1
		"Notice it's not your voice. You don't have to use it." 2
		"Turn on something loud enough to drown the thought out entirely." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("A stranger gives you a wide berth. The voice, notably, has no comeback.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -15 TAG_FAWN)
			Print("You gave an old voice the final word again.")
		)
		(case 1
			ApplyChoiceEffects(-5 0 -5 TAG_FIGHT)
			Print("You fought a voice with the same volume it uses.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You separated the message from the messenger you inherited it from.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You muted it instead of naming it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
