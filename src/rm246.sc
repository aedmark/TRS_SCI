/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm246.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 12).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 246)
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
(instance public rm246 of Rm
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
			= glitchText "Answer your own question instead, at great length, to the room."
		)
		= choice PrintChoices(
			"You asked. They said 'fine' and kept scrolling. That was ten minutes ago."
			"The Unanswered 'How Was Your Day'"
			290
			glitchText
		"Sit in the same room in total silence, waiting for more." 0
		"Fill the silence with details about your own day, unprompted." 1
		"Let 'fine' be enough for now. Try again later." 2
		"Put the phone down in front of them: 'I asked you something.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The room does not respond either. Hurtful, but fair.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You waited for a door that wasn't going to open, anyway.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You performed the conversation for the both of you.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Not every silence needs to be filled immediately or be analyzed.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 5 TAG_FIGHT)
			Print("You demanded the conversation instead of waiting for it to volunteer.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
