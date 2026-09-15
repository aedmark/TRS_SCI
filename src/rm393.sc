/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm393.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 29).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 393)
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
(instance public rm393 of Rm
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
			= glitchText "Try to upstage the performer entirely."
		)
		= choice PrintChoices(
			"They've locked eyes with you specifically, mid-act, in front of a small crowd, and are clearly hoping you'll play along."
			"The Street Performer's Eye Contact"
			290
			glitchText
		"Go along with whatever bit they're doing, way past your comfort line." 0
		"Break eye contact and walk fast, out of the crowd entirely." 1
		"Play along a little, on your own terms, and enjoy it." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The crowd's attention has shifted to you completely. The performer looks personally betrayed.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You gave a stranger's act more of your dignity than the bit actually required.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You exited a public performance as if you were guilty of a crime you didn't actually commit.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You let yourself be a little bit silly in public, on purpose, for no reason but that it was fun.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
