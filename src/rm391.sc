/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm391.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 27).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 391)
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
(instance public rm391 of Rm
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
			= glitchText "Applaud their confidence and ask for tips on doing it yourself."
		)
		= choice PrintChoices(
			"Someone just walked straight past eleven people, including you, and set their items down at the front like it's nothing."
			"The Line-Cutter"
			290
			glitchText
		"Say nothing and let it happen so you don't have to be The Person Who Says Something." 0
		"Call it out loudly enough that the whole line hears you." 1
		"Say, calmly, that the line ends back there." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They actually gave you a few. You will never use them. You appreciate them anyway.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("You watched a small injustice happen live and filed it under things you'll think about later when you should be asleep.")
		)
		(case 1
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("The line briefly became a jury. The cutter lost. So did the general mood of the store.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You named the small unfairness out loud, without turning it into a war.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
