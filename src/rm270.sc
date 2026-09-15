/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm270.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 4).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 270)
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
(instance public rm270 of Rm
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
			= glitchText "Start humming the elevator music, badly, out loud."
		)
		= choice PrintChoices(
			"Stuck in an elevator with a coworker for four floors. Neither of you has said anything for eleven seconds."
			"The Small Talk Void"
			290
			glitchText
		"Comment on the elevator's slowness like it's breaking news." 0
		"Stare at the floor numbers with religious intensity." 1
		"Let the silence be silence." 2
		"Turn and ask, flatly, why neither of you will just say something real." 3
		"Pull out your phone and disappear into it until the doors open." 4
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The coworker joins in. This is now, somehow, a duet.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -5 TAG_FAWN)
			Print("You filled the void with the safest possible noise. Coward.")
		)
		(case 1
			ApplyChoiceEffects(15 -5 0 TAG_FREEZE)
			Print("You willed the doors open through sheer discomfort.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 5 TAG_SECURE)
			Print("Eleven seconds of quiet did not erase you from this earth.")
		)
		(case 3
			ApplyChoiceEffects(-8 -12 3 TAG_FIGHT)
			Print("You called out the silence instead of just enduring it. The doors open two floors later. Not soon enough for either of you.")
		)
		(case 4
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You left the elevator before you actually left the elevator.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
