/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm230.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 30).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 230)
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
(instance public rm230 of Rm
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
			= glitchText "Send the recruiter a reply arguing your own case, after the decision's already final."
		)
		= choice PrintChoices(
			"The recruiter finally writes back. 'We've decided to move forward with other candidates at this time.'"
			"The Rejection Email"
			290
			glitchText
		"Reread the email six times looking for a hidden opening." 0
		"Close the tab, close the laptop, and do literally anything else." 1
		"Let yourself be disappointed for a minute, then ask for feedback anyway." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It changed nothing. For a brief moment, you feel like you really accomplished something.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("The email said the same thing on the sixth read as it did on the first.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("The feeling is still there. It's just unsupervised now.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You made room for the disappointment and asked a useful question anyway.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
