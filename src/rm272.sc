/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm272.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 6).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 272)
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
(instance public rm272 of Rm
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
			= glitchText "Show up in full costume, several genres removed from the event's theme."
		)
		= choice PrintChoices(
			"You said yes to something two weeks ago. It's tonight. Every fiber of you wants to cancel."
			"The RSVP You Regret"
			290
			glitchText
		"Draft a vague excuse about not feeling well." 0
		"Text 'actually can't make it' with zero elaboration and hit send." 1
		"Go anyway. Perform enthusiasm you do not currently possess." 2
		"Go, stay an hour, and leave honestly whenever you're done." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are the only knight at what turns out to be a beach party.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 -5 -5 TAG_FLIGHT)
			Print("You built an exit out of half a lie. Nobody believes you.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 10 TAG_FIGHT)
			Print("You chose a white lie over a padded truth. You spend the night regretting it.")
		)
		(case 2
			ApplyChoiceEffects(10 15 -20 TAG_FAWN)
			Print("You showed up as the version of you that RSVPs on time. You have fun, anyway.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You showed up for part of it and let that be enough.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
