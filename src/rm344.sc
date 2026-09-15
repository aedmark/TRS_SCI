/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm344.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 12).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 344)
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
(instance public rm344 of Rm
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
			= glitchText "Attempt to crack a joint that does not, physiologically, crack."
		)
		= choice PrintChoices(
			"You've cracked your knuckles, neck, and back four times each in the last hour. It's becoming a whole thing."
			"The Cracking Joints"
			290
			glitchText
		"Keep doing it, quieter, so no one notices." 0
		"Crack something loudly on purpose, right at someone." 1
		"Get up and actually move for a minute instead." 2
		"Excuse yourself to the bathroom until the urge passes." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It did not crack. You will try again in eleven minutes.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You hid the fidget instead of asking what it was fidgeting about.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("You turned a nervous habit into a small act of war.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the restlessness an actual job to do.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("You left the room rather than sit with the fidget.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
