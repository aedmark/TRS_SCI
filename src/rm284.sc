/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm284.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 18).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 284)
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
(instance public rm284 of Rm
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
			= glitchText "Stand up and propose your own toast, entirely about yourself."
		)
		= choice PrintChoices(
			"A long, warm speech naming almost everyone important in the room. Almost."
			"The Toast You Weren't Mentioned In"
			290
			glitchText
		"Smile and clap while quietly re-ranking your own importance." 0
		"Compliment the speech extra hard afterward." 1
		"Let one omission be one omission, not a verdict." 2
		"Mention to them afterward that you noticed." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It runs nine minutes. The room claps out of confusion, mostly.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("A toast became a scoreboard, and you lost.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You praised loudest the thing that left you out.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You weren't named. You were, notably, still there.")
		)
		(case 3
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("You turned a passing feeling into a conversation neither of you wanted to have at a party.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
