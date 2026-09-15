/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm238.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 4).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 238)
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
(instance public rm238 of Rm
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
			= glitchText "Send one more message: just a single, staring emoji."
		)
		= choice PrintChoices(
			"You sent a long message to a parent, explaining how you actually feel. Marked read. Nothing since."
			"The Left-On-Read Text"
			290
			glitchText
		"Open your messages to literally anyone else and get absorbed in something safer." 0
		"Reread your message eleven times, hunting for the sentence that broke it." 1
		"Send nothing else. Let the silence belong to them, not you." 2
		"Send one more message demanding to know why they haven't answered." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The read receipt updates. The silence gets a face now.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You changed the channel on your own heart.")
		)
		(case 1
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You performed an autopsy on a conversation that isn't dead yet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You stopped staring at a doorknob that isn't yours to turn.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You turned the silence into a second, angrier message.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
