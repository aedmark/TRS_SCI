/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm271.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 5).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 271)
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
(instance public rm271 of Rm
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
			= glitchText "Claim, boldly, that you were actually a time traveler that day."
		)
		= choice PrintChoices(
			"Someone brings up a specific thing you said, off-hand, eight months ago. You have no memory of saying it."
			"The Friend Who Remembers Everything"
			290
			glitchText
		"Panic-scan your own memory for context you don't have." 0
		"Agree enthusiastically, like you absolutely remember." 1
		"Say 'I don't actually remember that, tell me more,' and mean it." 2
		"Change the subject fast, before anyone asks a follow-up question." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They seem to accept this explanation more readily than expected. Someone asks if you've ever met Elvis.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 -5 0 TAG_FREEZE)
			Print("You audited a version of yourself with no paper trail and came up empty handed.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You co-signed a memory that isn't yours.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let not-knowing be an ordinary, survivable thing. Because it is.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -5 TAG_FLIGHT)
			Print("You steered the conversation somewhere you actually remembered.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
