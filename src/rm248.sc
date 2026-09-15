/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm248.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 14).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 248)
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
(instance public rm248 of Rm
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
			= glitchText "Start a dramatic heist-movie-style plan to retrieve it."
		)
		= choice PrintChoices(
			"You lent it three weeks ago. You need it now. Asking for it back feels, somehow, enormously selfish."
			"The Borrowed Thing, Not Returned"
			290
			glitchText
		"Buy a replacement instead of asking for it back." 0
		"Ask for it back with four qualifiers and two apologies." 1
		"Ask for it back plainly. It's yours." 2
		"Text them right now, annoyed, demanding it back today." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The plan involves zero actual heisting and a lot of standing outside their door whining.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You paid money to avoid a ten-second conversation.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You apologized for wanting your own thing back.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("This did not, in fact, end the friendship. They bought you dinner in appreciation.")
		)
		(case 3
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("You got your thing back. You also got a slightly different friendship.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
