/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm376.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 12).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 376)
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
(instance public rm376 of Rm
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
			= glitchText "Ask the screen, out loud, if it has any tips for you."
		)
		= choice PrintChoices(
			"You're paying with a card. The screen turns around. It's asking for a tip, and it's asking in front of the person you'd be tipping."
			"The Tip Jar"
			290
			glitchText
		"Tip more than you can afford so they don't think badly of you." 0
		"Hit 'no tip' fast and avoid eye contact for the rest of the transaction." 1
		"Tip what you can actually afford and let that be enough." 2
		"Exclaim to the cashier that these screens are a scam, then hit 'no tip' hard." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The screen did not answer. The cashier, after a pause, actually did.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You paid for a stranger's good opinion of you with money you didn't have to spare.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("You made a budget decision and then flinched from it like it was a crime.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You made a number decision and didn't turn it into a referendum on your character.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 3 TAG_FIGHT)
			Print("You picked a fight with a piece of hardware in front of the one person it couldn't help.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
