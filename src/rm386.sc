/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm386.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 22).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 386)
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
(instance public rm386 of Rm
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
			= glitchText "Walk them there yourself, twelve full blocks out of your way."
		)
		= choice PrintChoices(
			"Someone stops you on the street, clearly lost, and you're maybe seventy percent sure you know the way they need to go."
			"The Stranger Asking For Directions"
			290
			glitchText
		"Give confident, detailed directions despite the seventy percent confidence." 0
		"Say you're not sure and hurry off before they can ask a follow-up." 1
		"Say what you actually know and what you don't, and point them toward someone who might know more." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are now further from your destination than they were from theirs. You made a friend, sort of.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You handed a stranger a confident answer instead of an honest maybe.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You left a lost person slightly more lost, at record speed.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave a stranger the size of your knowledge instead of an inflated one.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
