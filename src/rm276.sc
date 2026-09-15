/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm276.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 10).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 276)
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
(instance public rm276 of Rm
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
			= glitchText "Throw yourself a tiny, one-person parade around the living room."
		)
		= choice PrintChoices(
			"It's 8 PM. No text, no call, nothing. You know they're busy. You also know that nobody is THAT busy..."
			"The Birthday They Forgot"
			290
			glitchText
		"Refresh your phone every few minutes without admitting why." 0
		"Post something upbeat so nobody suspects you noticed." 1
		"Let yourself be a little sad about it. That's allowed." 2
		"Text them directly: 'did you forget what today is?'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The confetti will be found in strange places for weeks.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You kept checking for something you'd already concluded wasn't coming.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You broadcast 'fine' to cover a very 'not fine' feeling.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You didn't need to perform that you were okay, you arrived there naturally.")
		)
		(case 3
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("You forced the acknowledgment you were hoping would arrive on its own.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
