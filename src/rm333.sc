/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm333.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 1).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 333)
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
(instance public rm333 of Rm
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
			= glitchText "Buy a $40 mouthguard you will wear exactly once."
		)
		= choice PrintChoices(
			"You catch yourself clenching your jaw so hard your teeth ache. You have no memory of starting."
			"The Locked Jaw"
			290
			glitchText
		"Force a smile until the muscles relax on their own." 0
		"Snap at the next person who asks you a simple question." 1
		"Actually stretch it out and breathe for ten seconds." 2
		"Cancel the call you were dreading and deal with the jaw later." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's in a drawer now. You develop TNJ.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -5 TAG_FAWN)
			Print("You performed relaxation until it was almost real. You even broke a sweat!")
		)
		(case 1
			ApplyChoiceEffects(-15 -15 0 TAG_FIGHT)
			Print("Your jaw unclenched. Someone else's day is now ruined. Good job.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You noticed the tension and let it go. On purpose (for once).")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 -5 TAG_FLIGHT)
			Print("You removed yourself from the thing clenching your jaw, not the clenching.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
