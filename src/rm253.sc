/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm253.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 19).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 253)
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
(instance public rm253 of Rm
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
			= glitchText "Attempt to forge a matching handwriting style for your own notes from now on."
		)
		= choice PrintChoices(
			"You found it while cleaning out a drawer. The handwriting stops you cold for a second you didn't expect."
			"The Recipe Card In Her Handwriting"
			290
			glitchText
		"Put it back exactly where it was and close the drawer." 0
		"Make the recipe tonight. Let it mean whatever it means." 1
		"Get frustrated at how much a card can do to you." 2
		"Put the drawer back exactly as it was and avoid that drawer for months." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your grocery lists now look faintly, movingly ancestral.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You returned the moment to the drawer, unopened.")
		)
		(case 1
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You let an ordinary moment hold an old, specific grief.")
		)
		(case 2
			ApplyChoiceEffects(5 -5 -5 TAG_FIGHT)
			Print("You argued with your own feelings and lost. Now you're sad, hungry, and the drawer is still dirty.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -10 TAG_FLIGHT)
			Print("You closed the drawer on more than just the card.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
