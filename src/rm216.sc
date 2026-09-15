/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm216.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 16).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 216)
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
(instance public rm216 of Rm
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
			= glitchText "Lean into the frozen frame. Hold the expression on purpose now."
		)
		= choice PrintChoices(
			"Your video froze mid-sentence on an expression you didn't choose. Twelve people saw it for four full seconds."
			"The Zoom Freeze"
			290
			glitchText
		"Open with a self-deprecating joke about your wifi." 0
		"Turn your camera off for the rest of the call." 1
		"Say 'sorry, it's a freezing in here' and keep going." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have become a meme in your own meeting. People appreciate your whimsy, but respect you slightly less.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You pre-apologized for a router's decision.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You removed the risk by removing yourself.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A frozen face is not, it turns out, a permanent record. It's universal.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
