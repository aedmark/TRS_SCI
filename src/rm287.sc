/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm287.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 21).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 287)
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
(instance public rm287 of Rm
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
			= glitchText "Walk over and ask, directly, if it's about you."
		)
		= choice PrintChoices(
			"A burst of laughter from a group nearby. You have no evidence it's about you. You're immediately certain it is."
			"The Loud Laugh Across The Room"
			290
			glitchText
		"Replay your last ten minutes of behavior for embarrassing material." 0
		"Change your position in the room, just in case." 1
		"Let the laugh be about literally anything else. It probably is." 2
		"Walk over and ask, pointed, if something's funny." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It was not about you. Good, because that could have been embarrassing!")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You conducted a full review with zero actual evidence.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You relocated to escape a theory you invented about yourself.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Most laughter in a crowded room has nothing to do with you. Unless you're doing something funny.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You turned a guess into a confrontation. You look unhinged and very un-funny.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
