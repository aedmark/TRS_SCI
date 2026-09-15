/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm260.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 26).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 260)
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
(instance public rm260 of Rm
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
			= glitchText "Buy something small and unnecessary out of pure defiance."
		)
		= choice PrintChoices(
			"Your phone buzzes. Balance below zero. You do the math and know exactly which charge did it."
			"The Overdraft Notification"
			290
			glitchText
		"Text a family member asking, in a roundabout way, if they're doing okay 'financially, generally.'" 0
		"Don't check the account again until the fee posts." 1
		"Move what you can, call the bank, and actually look at the number." 2
		"Call the bank immediately and argue the fee down out of pure spite." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The balance is more negative. You feel powerful. Briefly.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You asked about someone else's money to avoid saying anything about yours.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("The fee posted anyway. It always does.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You looked right at it, which turns out to be the hard part.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 3 TAG_FIGHT)
			Print("You fought a machine over twelve dollars and, against all odds, won four of them back.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
