/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm315.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 16).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 315)
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
(instance public rm315 of Rm
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
			= glitchText "Draft their apology yourself, in full, and read it aloud dramatically."
		)
		= choice PrintChoices(
			"You've rehearsed the conversation where they finally say it. It hasn't happened. It might not."
			"The Apology You Never Got"
			290
			glitchText
		"Keep rehearsing the conversation, on a loop, indefinitely." 0
		"Draft the message you'd send them. Don't send it. Yet." 1
		"Consider that closure might have to come from you instead." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's a great apology. Extremely well-written and deeply, deeply fake.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You kept a courtroom open for a trial nobody else is attending.")
		)
		(case 1
			ApplyChoiceEffects(-5 0 5 TAG_FIGHT)
			Print("You gave the anger somewhere to go besides in circles.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You stopped waiting for a door someone else may never open.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
