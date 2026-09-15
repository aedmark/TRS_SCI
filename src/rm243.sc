/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm243.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 9).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 243)
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
(instance public rm243 of Rm
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
			= glitchText "Cover the entire kitchen in post-its of your own."
		)
		= choice PrintChoices(
			"'Please rinse dishes before leaving in sink :)'\n\n\n The smiley face is doing a lot of unpaid emotional labor."
			"The Passive-Aggressive Post-it"
			290
			glitchText
		"Rinse everything in the house preemptively for a week." 0
		"Leave a post-it back, on a clean dish and slightly too pointed." 1
		"Just talk to your roommate about it, out loud, later." 2
		"Take the post-it down and pretend you never saw it." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The kitchen now resembles a ransom note made of passively aggressive politeness.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You overcorrected to outrun one sticky note.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 5 TAG_FIGHT)
			Print("You returned the passive-aggression with interest. That'll show 'em.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You used your words instead of a sticky note war. You both get pizza later and use paper plates.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You removed the evidence instead of addressing the message.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
