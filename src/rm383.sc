/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm383.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 19).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 383)
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
(instance public rm383 of Rm
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
			= glitchText "Introduce yourself as if this were the first time you've ever met."
		)
		= choice PrintChoices(
			"Someone waves at you like they know you well. You have absolutely no idea who this is, and they are now three feet away and closing."
			"The Face You Can't Place"
			290
			glitchText
		"Fake total recognition and hope context clues fill in the blanks." 0
		"Suddenly find your phone extremely urgent and duck the interaction." 1
		"Admit you're blanking and ask them to remind you how you know each other." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It was, in fact, the first time. You had actually never met this person.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed an entire friendship's worth of warmth for someone whose name you can't remember.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You outran a conversation you were fully capable of having.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You told the truth instead of performing memory you didn't have. They didn't mind at all.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
