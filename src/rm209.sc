/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm209.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 9).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 209)
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
(instance public rm209 of Rm
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
			= glitchText "Reply with a photo of an actual empty desk, for evidence."
		)
		= choice PrintChoices(
			"You set your status to 'in a meeting' twenty minutes ago and never actually joined one. Someone just pinged you directly."
			"The Away Message"
			290
			glitchText
		"Type 'sorry, just wrapped up, what's up!' like it's true." 0
		"Let the message sit unread for another eleven minutes." 1
		"Reply honestly: 'Just saw this, give me a minute.'" 2
		"Snap back that you were literally just handling something else." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The desk is, notably, yours. This raises more questions than it answers. You're okay with this.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You performed availability you didn't have.")
		)
		(case 1
			ApplyChoiceEffects(15 -5 0 TAG_FLIGHT)
			Print("You bought time you'll have to pay back later.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You told a true, small, unremarkable thing.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You turned a ping into a small defense.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
