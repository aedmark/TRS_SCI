/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm382.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 18).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 382)
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
(instance public rm382 of Rm
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
			= glitchText "Start a betting pool on how long the line will actually take."
		)
		= choice PrintChoices(
			"There's one working stall, a line of eight, and someone near the front who is very audibly running out of patience with the wait."
			"The Restroom Line"
			290
			glitchText
		"Let three people cut ahead of you so no one thinks you mind." 0
		"Loudly point out that this line has not moved once in ten minutes." 1
		"Hold your spot, wait it out, and make small talk with the person next to you." 2
		"Give up your spot entirely and just hold it until you get home." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You won four dollars. The line still has not moved. Worth it.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("You gave away your spot in line three separate times to avoid being thought of as difficult.")
		)
		(case 1
			ApplyChoiceEffects(-8 -8 0 TAG_FIGHT)
			Print("You said the true thing everyone was thinking. It did not fix the plumbing.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You turned a bad wait into a slightly less bad one by just being a person about it.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("You left the line and the problem both unsolved.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
