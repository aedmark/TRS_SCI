/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm334.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 2).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 334)
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
(instance public rm334 of Rm
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
			= glitchText "Genuinely consider whether you're being paged by the universe."
		)
		= choice PrintChoices(
			"You feel your phone buzz in your pocket. It's not there. You're not wearing anything with pockets."
			"The Phantom Vibration"
			290
			glitchText
		"Check anyway and feign shock when you can't find your phone." 0
		"Laugh it off and immediately forget it happened." 1
		"Notice it, name it, and let it pass." 2
		"Announce, annoyed, that your phone is haunted and someone should fix that." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are not. The universe isn't on speaking terms with you, currently.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(0 -5 -5 TAG_FAWN)
			Print("You reflexively obeyed the call of a muscle spasm.")
		)
		(case 1
			ApplyChoiceEffects(-5 5 0 TAG_FLIGHT)
			Print("You buried the weird little moment under a joke.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You clocked your nervous system doing a bit and didn't argue with it.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You picked a fight with a phone that wasn't even there.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
