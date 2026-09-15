/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm286.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 20).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 286)
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
(instance public rm286 of Rm
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
			= glitchText "Actually pull out a calendar and demand a date, right now."
		)
		= choice PrintChoices(
			"The fourth time this month someone's said it. Nobody, including you, has ever proposed an actual date."
			"The 'We Should Hang Out Sometime' That Never Happens"
			290
			glitchText
		"Say it back warmly, knowing it means nothing either time." 0
		"Actually suggest a specific day and time." 1
		"Let the phrase pass, again, unchallenged." 2
		"Say, honestly, 'I'd actually like that! When's good for you?' and mean it." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("A date gets picked. Everyone is stunned, including the calendar.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You kept a nice-sounding ritual alive instead of a friendship.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You called the bluff, including your own. It worked.")
		)
		(case 2
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("A fifth 'sometime' joined the pile of the first four. Nice collection!")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You turned a script into an actual invitation.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
