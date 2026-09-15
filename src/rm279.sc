/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm279.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 13).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 279)
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
(instance public rm279 of Rm
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
			= glitchText "Commit fully: sprint over and hug a near-stranger."
		)
		= choice PrintChoices(
			"Someone you sort of know, from somewhere, makes eye contact across the room. Neither of you commits to a greeting."
			"The Awkward Wave"
			290
			glitchText
		"You suddenly find your phone extremely interesting." 0
		"Overcommit to a huge wave and walk over, unsure why." 1
		"Give a small, real wave and let it be whatever it is." 2
		"Wave back too hard on purpose, daring them to make it weirder." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("This escalated several social contracts beyond what either of you agreed to. It feels right.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You disappeared into a screen to avoid making a decision.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You escalated a maybe into a whole interaction. It's awkward.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 5 TAG_SECURE)
			Print("A half-known person got a half-committed, perfectly adequate wave.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You escalated an awkward wave into a small standoff.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
