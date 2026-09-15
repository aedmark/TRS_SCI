/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm387.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 23).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 387)
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
(instance public rm387 of Rm
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
			= glitchText "Ask if they want to just come get lunch with you instead."
		)
		= choice PrintChoices(
			"Someone asks if you have any spare change, right as you're fumbling for your keys and trying not to make eye contact."
			"The Person Asking For Change"
			290
			glitchText
		"Hand over more than you meant to and apologize for not having more than that." 0
		"Say 'sorry, not today' and keep walking without slowing down." 1
		"Say no if you mean no, or give what you can if you mean yes, and mean it either way." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You had lunch with a total stranger and heard a genuinely wild story. You paid. Worth it.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized to a stranger for the size of your own generosity.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You gave a fast, plain no and let it be exactly that, no more.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("Whatever you did, you did it without performing guilt about it afterward.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
