/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm298.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 32).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 298)
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
(instance public rm298 of Rm
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
			= glitchText "'Accidentally' plan something that conflicts with their next couple's outing."
		)
		= choice PrintChoices(
			"Your friend has a new partner, and lately every group hangout quietly becomes about the two of them."
			"The New Partner Absorbing the Friend Group"
			290
			glitchText
		"Say how much you love the new couple, louder than you feel it." 0
		"Start finding reasons to skip the group hangouts for a while." 1
		"Tell your friend directly that you miss the just-us version of hanging out." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Petty, effective, and everyone can tell exactly what you did.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You clapped for something that, honestly, you have mixed feelings about.")
		)
		(case 1
			ApplyChoiceEffects(-5 -8 0 TAG_FLIGHT)
			Print("Easier than saying anything. Also, quietly, lonelier.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You said how you felt instead of just fading out of the group chat.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
