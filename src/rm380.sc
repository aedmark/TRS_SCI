/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm380.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 16).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 380)
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
(instance public rm380 of Rm
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
			= glitchText "Get out of the car and offer to play rock paper scissors."
		)
		= choice PrintChoices(
			"You both saw the spot at the same time. Neither of you has backed down, and there is now a small line of cars forming behind you both."
			"The Parking Lot Standoff"
			290
			glitchText
		"Wave them through with a big smile even though you got there first." 0
		"Rev the engine and hold your ground until they give up." 1
		"Point them to the spot you can see open two rows down instead." 2
		"Just drive off and circle the lot again rather than deal with it." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You always play rock first. This time, you didn't. And you lost.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("You gave up a spot you were entitled to so a stranger wouldn't be briefly annoyed at you.")
		)
		(case 1
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("You won a parking spot and lost thirty seconds of goodwill from everyone now stuck behind you.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 5 TAG_SECURE)
			Print("You solved the actual problem instead of winning the argument about it.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 -5 TAG_FLIGHT)
			Print("You left the standoff to whoever wanted it more.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
