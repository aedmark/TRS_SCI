/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm240.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 6).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 240)
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
(instance public rm240 of Rm
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
			= glitchText "Hold a tiny, formal funeral for the leftovers."
		)
		= choice PrintChoices(
			"You made extra on purpose, left a note. It's still in the fridge, exactly where you left it."
			"The Leftover They Didn't Eat"
			290
			glitchText
		"Eat it yourself and say nothing about the note." 0
		"Leave it in there for three more days, unable to deal with it." 1
		"Ask, simply, if they want any leftovers before you eat the rest." 2
		"Text: 'Did you even see the leftovers? I made them for you.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Several words were said. None of them were 'I'm sorry.'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You absorbed the disappointment along with the leftovers.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("The food and the feeling both went untouched.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A question, asked plainly, is not an accusation. Plus, you get dinner.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 3 TAG_FIGHT)
			Print("You put the disappointment into words instead of Tupperware. Now you're disappointed for a different reason.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
