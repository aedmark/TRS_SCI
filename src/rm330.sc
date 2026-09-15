/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm330.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 31).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 330)
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
(instance public rm330 of Rm
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
			= glitchText "Accept a plan you don't want to go to, purely to prove a point to yourself."
		)
		= choice PrintChoices(
			"You've cancelled the last four plans in a row and told yourself, each time, that you're just 'an introvert who needs space.'"
			"The Urge to Label 'Isolation' as 'Introversion'"
			290
			glitchText
		"Cancel the fifth one too, and repeat the same explanation to yourself." 0
		"Don't examine it. Just stay in and let the pattern keep going." 1
		"Ask yourself honestly whether this is self-care or avoidance, and answer honestly." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You went. It was fine. It proved absolutely nothing either way.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -8 0 TAG_FLIGHT)
			Print("The explanation is getting worn out. You use it anyway because you know the only person holding you accountable is you.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("Staying in didn't recharge anything this time. It just kept happening.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You looked at the label before deciding whether to trust it. The answer was, as usual, the quantumly optimal 'Yes.'")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
