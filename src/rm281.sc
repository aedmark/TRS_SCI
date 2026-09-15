/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm281.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 15).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 281)
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
(instance public rm281 of Rm
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
			= glitchText "Post a single cryptic status implying you know things."
		)
		= choice PrintChoices(
			"You don't know when it happened. You just noticed, scrolling, that a number is one smaller than it used to be."
			"The Unfollow You Noticed"
			290
			glitchText
		"Spend twenty minutes trying to figure out who it was." 0
		"Post something extra likable to make up the difference." 1
		"Close the app. It's one person. It's fine." 2
		"Close the app entirely and delete it off your homescreen for the rest of the day." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You know anything. The mystery deepens for everyone, including you.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You ran a full investigation into a single missing follower.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You tried to outperform a number you can't actually see.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("A number went down. The sky, notably, did not fall. Their loss.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -5 TAG_FLIGHT)
			Print("You removed the scoreboard instead of the score.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
