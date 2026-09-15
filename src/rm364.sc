/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm364.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 0).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 364)
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
(instance public rm364 of Rm
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
			= glitchText "Ask the cashier how THEIR day is going, in aggressive detail."
		)
		= choice PrintChoices(
			"You're barely holding it together behind a very calm face. The cashier asks how your day is going. "
			"The Checkout Line"
			290
			glitchText
		"Say 'great, thanks!' with way more enthusiasm than you actually have." 0
		"Give a flat, one-word answer and stare at the card reader." 1
		"Be honest and let it be a real, if brief, answer." 2
		"Snap back, sharper than you meant: 'Do you actually want to know?'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They were not prepared for this. Neither were you.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed 'great' for someone who will forget this conversation in four seconds.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You disappeared into the transaction and let the small talk die there.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You told a stranger a true thing instead of a convenient one. They gave you a sticker. ")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 3 TAG_FIGHT)
			Print("You handed a stranger's small talk back with interest it didn't ask for.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
