/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm225.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 25).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 225)
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
(instance public rm225 of Rm
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
			= glitchText "Reply with an equally cryptic single word of your own."
		)
		= choice PrintChoices(
			"You send a detailed update. The reply is a single word: 'Noted.'"
			"The Manager's One-Word Reply"
			290
			glitchText
		"Reread the word eleven times, hunting for a tone that isn't there." 0
		"Send a follow-up asking if everything's okay." 1
		"Take the word at face value and move on with your day." 2
		"Reply asking, directly, what exactly 'noted' is supposed to mean." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("A one-word war has begun. Nobody will walk away alive.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You built an entire mood off four letters. Now your tummy hurts.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You went looking for reassurance and found a boundary.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Sometimes 'noted' just means noted.")
		)
		(case 3
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("You asked the question everyone thinks but nobody sends.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
