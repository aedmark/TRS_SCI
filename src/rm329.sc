/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm329.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 30).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 329)
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
(instance public rm329 of Rm
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
			= glitchText "Try to recreate the exact photo, right now, alone in your room."
		)
		= choice PrintChoices(
			"You find an old photo. You're laughing, genuinely, in a way you don't fully remember how to do anymore. You can't place the feeling."
			"The Photo Where You Look Happy"
			290
			glitchText
		"Stare at it a while, then close the folder without really processing anything." 0
		"Post it with a caption implying everything's still that happy now." 1
		"Let yourself actually miss that feeling instead of performing that you still have it." 2
		"Get annoyed at the photo for making the present look worse by comparison." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It does not look the same. It was never really about the photo.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(8 0 -8 TAG_FREEZE)
			Print("You looked at the feeling instead of into it. Close, but not quite.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("The caption performed something the photo didn't actually contain anymore.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 12 TAG_SECURE)
			Print("Missing something honestly takes up less room than pretending you don't.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You resented a memory for being better than the moment you're in.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
