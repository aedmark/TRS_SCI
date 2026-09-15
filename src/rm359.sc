/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm359.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 27).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 359)
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
(instance public rm359 of Rm
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
			= glitchText "Immediately open a second bag out of pure defiance."
		)
		= choice PrintChoices(
			"You look down and an entire bag of something is gone. You don't remember deciding to eat any of it."
			"The Bag You Don't Remember Opening"
			290
			glitchText
		"Laugh it off if anyone mentions it and change the subject fast." 0
		"Say nothing to anyone, including yourself, and just move on like it didn't happen." 1
		"Notice, without judgment, that you might be stressed about something specific." 2
		"Throw the empty bag away fast and open the fridge to see what else there is." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Neither bag solved anything. Both are now empty.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You made a joke out of it before anyone could ask a real question.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("Not talking about it didn't make it not have happened.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You looked past the bag to the actual thing underneath it.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -8 TAG_FLIGHT)
			Print("You moved straight past the question and onto the next snack.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
