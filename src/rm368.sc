/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm368.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 4).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 368)
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
(instance public rm368 of Rm
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
			= glitchText "Answer with a completely unrelated childhood memory instead."
		)
		= choice PrintChoices(
			"The interviewer asks you to describe your biggest weakness, and you have exactly one chance to answer this well."
			"The Interview With A Stranger Who Holds All The Cards"
			290
			glitchText
		"Give the fake answer everyone knows is fake because it's clearly a humblebrag in jackass clothing." 0
		"Blank out and give a rambling non-answer that goes nowhere." 1
		"Give an actual, specific weakness and what you're doing about it." 2
		"'Honestly, I think that question rewards fake humility.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The interviewer is now taking very different notes than they were a minute ago.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed vulnerability shaped like win. It wins you nothing but an eyeroll.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("You watched yourself not answer the question and could not intervene.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You told a stranger something true, on purpose, in a room built for performance.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 5 TAG_FIGHT)
			Print("You argued with the format instead of playing along with it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
