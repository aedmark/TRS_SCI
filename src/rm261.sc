/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm261.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 27).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 261)
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
(instance public rm261 of Rm
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
			= glitchText "Audit every single subscription you have in the middle of the night."
		)
		= choice PrintChoices(
			"A charge you don't recognize turns out to be a subscription you forgot to cancel eight months ago."
			"The Forgotten Subscription Charge"
			290
			glitchText
		"Decide it's not worth the hassle of calling to complain." 0
		"Call and argue for a full refund on principle." 1
		"Cancel it, note the loss, and set a reminder to check for others." 2
		"Close the banking app and decide to deal with it 'this weekend.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You found six more. You are now afraid of your own bank statement. And your goldfish-like memory.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("You let it go, mostly because asking felt like more of a cost than the actual money lost.")
		)
		(case 1
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("You got half the money back and a mild reputation with customer service.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You closed the leak instead of just being mad about the water.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You postponed eight months into a ninth.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
