/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm337.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 5).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 337)
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
(instance public rm337 of Rm
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
			= glitchText "Book a massage you will cancel twice and never reschedule."
		)
		= choice PrintChoices(
			"Someone points out that you look really tense. You notice your shoulders are up to your ears."
			"The Shoulders"
			290
			glitchText
		"Laugh it off, drop them for exactly four seconds." 0
		"Get defensive about your own posture." 1
		"Actually roll them out and admit you're tensed up for no reason." 2
		"Change the subject fast and hope nobody looks at your shoulders again." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's the thought that counts. Your shoulders disagree.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -5 TAG_FAWN)
			Print("You roleplay as a relaxed person. Your shoulders forgot their lines immediately.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("Now everyone thinks you're hiding something... Or you think that they think you're hiding something. ")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You told the truth about your own body. Small, but real. Everyone else rolls their shoulders, too.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You redirected the conversation instead of your shoulders.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
