/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm292.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 26).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 292)
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
(instance public rm292 of Rm
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
			= glitchText "Have a friend text them from your phone 'as a joke.'"
		)
		= choice PrintChoices(
			"The first date was good. You both said 'we should do this again.' Neither of you has said anything since."
			"The Second Date That Never Got Scheduled"
			290
			glitchText
		"Decide you're clearly not that interested and move on without saying so." 0
		"Wait for them to text first so you don't seem too eager." 1
		"Just text and ask if they want to grab coffee this week." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("This has never worked in the history of dating. It does not work now.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("Cleaner this way, probably. You'll never actually know.")
		)
		(case 1
			ApplyChoiceEffects(8 5 -8 TAG_FAWN)
			Print("You outlasted your own interest waiting for permission to show it.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You said the plain thing instead of waiting for the safer one.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
