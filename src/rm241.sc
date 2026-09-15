/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm241.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 7).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 241)
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
(instance public rm241 of Rm
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
			= glitchText "Slide a folded paper airplane of concern underneath it."
		)
		= choice PrintChoices(
			"It's usually open. Tonight it's closed, and you don't know why."
			"The Closed Bedroom Door"
			290
			glitchText
		"Stand outside it for a full minute, deciding nothing." 0
		"Knock and immediately apologize for whatever it is." 1
		"Knock. Ask if everything is okay." 2
		"Go back downstairs and turn the TV up instead of finding out." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It does not fly well on carpet. The gesture remains.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You waited outside a door you could have just knocked on.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You apologized before you knew the charge.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You offered an opening instead of an assumption.")
		)
		(case 3
			ApplyChoiceEffects(15 -8 -8 TAG_FLIGHT)
			Print("You let the door stay a mystery a while longer.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
