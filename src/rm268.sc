/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm268.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 2).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 268)
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
(instance public rm268 of Rm
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
			= glitchText "Reply with forty exclamation points, unprompted, for no reason at all."
		)
		= choice PrintChoices(
			"Your friend's reply is just 'oh nice' where it used to be three exclamation points."
			"The Uneven Text Energy"
			290
			glitchText
		"Reread every message you've sent them for the last month." 0
		"Match their energy exactly, one for one." 1
		"Text them something low-stakes and let it go." 2
		"Stop texting first for a while and see if they notice." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have single-handedly restored the energy. Possibly too much of it.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You audited a friendship for crimes that don't exist.")
		)
		(case 1
			ApplyChoiceEffects(5 -10 0 TAG_FIGHT)
			Print("Mutually assured emotional disengagement.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 5 TAG_SECURE)
			Print("You extended trust without an audit.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You ran a quiet experiment instead of just asking.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
