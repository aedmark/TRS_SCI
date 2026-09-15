/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm294.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 28).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 294)
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
(instance public rm294 of Rm
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
			= glitchText "Show up to the venue alone anyway, on principle."
		)
		= choice PrintChoices(
			"Twenty minutes before you were supposed to meet, the same friend cancels for the third time this month."
			"The Friend Who Cancelled Last Minute (Again)"
			290
			glitchText
		"Reply 'no worries at all!' and mean less than half of it." 0
		"Send a pointed text about the pattern, right then, while you're still annoyed." 1
		"Say it's fine tonight, and bring up the pattern later when you're not standing in your coat." 2
		"Just stop making plans with them for a while, no explanation." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You had a fine time by yourself. This was not the point you were trying to prove.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -10 TAG_FAWN)
			Print("You absorbed the disappointment so smoothly nobody, including you, noticed it happened.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("Accurate. Badly timed. Both true at once.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You separated the moment from the pattern instead of dumping both at once.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You solved it by quietly opting out.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
