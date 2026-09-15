/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm275.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 9).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 275)
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
(instance public rm275 of Rm
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
			= glitchText "Start your own group chat. Name it something deeply petty."
		)
		= choice PrintChoices(
			"You find out, by accident, that there's a group chat that doesn't include you. It's been active for months."
			"The Group Chat Without You"
			290
			glitchText
		"Say nothing and quietly recalibrate every friendship in your life." 0
		"Act completely unbothered, performing it a little too well." 1
		"Notice it stings. Don't make it a bigger story than it is." 2
		"Mute every group chat you're actually in, preemptively." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It has one member. It is thriving.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -20 TAG_FREEZE)
			Print("One missing chat became a referendum on everyone you know.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You built a very convincing case for a feeling you don't have.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A feeling, felt and not expanded, passes on its own.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -10 TAG_FLIGHT)
			Print("You left before anyone could leave you out again.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
