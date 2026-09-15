/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm208.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 8).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 208)
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
(instance public rm208 of Rm
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
			= glitchText "Stand up and simply read the email aloud instead."
		)
		= choice PrintChoices(
			"Forty-five minutes, twelve people, and the entire message could have fit in three sentences (in an email)."
			"The Meeting That Did Not Need To Be"
			290
			glitchText
		"Nod along and add a supportive 'great point' to three different tangents." 0
		"Mentally exit the call and return only when your name is said." 1
		"Ask, once, if this could be a two-line message next time." 2
		"Cut in over the crosstalk: 'Stop-can we back up, this could've been an email.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The meeting ends four seconds later. Nobody claps, but you think they maybe want to.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You applauded the detour instead of naming it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("Your body stayed. The rest of you clocked out early. Good for you.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You said the quiet part. People respect you for it.")
		)
		(case 3
			ApplyChoiceEffects(-10 -15 5 TAG_FIGHT)
			Print("You named the elephant. The elephant left. The meeting did not, quite.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
