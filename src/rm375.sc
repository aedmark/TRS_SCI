/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm375.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 11).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 375)
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
(instance public rm375 of Rm
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
			= glitchText "Ask to see the clipboard and start signing up other pedestrians yourself."
		)
		= choice PrintChoices(
			"They've made eye contact from twenty feet away and are already walking toward you with a clipboard and a warm, practiced smile."
			"The Street Canvasser With A Clipboard"
			290
			glitchText
		"Sign up for something you don't care about just to end the conversation faster." 0
		"Pretend to be mid-phone-call and speed-walk past." 1
		"Make eye contact, say 'not today, thanks,' and keep walking." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have somehow become their best volunteer of the day. This was not the plan.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You bought your own escape with a monthly donation you will forget about until it's not forgettable.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You held a phone to your ear and spoke to no one to avoid speaking to someone.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You said no in four words and nothing bad happened.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
