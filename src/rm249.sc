/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm249.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 15).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 249)
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
(instance public rm249 of Rm
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
			= glitchText "Photobomb the memory. Mentally. With your current face."
		)
		= choice PrintChoices(
			"It's on the hallway wall, everyone smiling, from a year you remember very differently than the picture suggests."
			"The Photo From An Easier Year"
			290
			glitchText
		"Stop and stare at it longer than you meant to, every time." 0
		"Consider taking it down. Don't, yet." 1
		"Let the photo be a photo, not a verdict on the year." 2
		"Take a different route down the hall so you stop passing it." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Past-you and present-you now occupy the same photograph, spiritually. So there.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You keep visiting a version of the year that didn't happen.")
		)
		(case 1
			ApplyChoiceEffects(0 -5 5 TAG_FIGHT)
			Print("You noticed the mismatch and let yourself notice it.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("One picture doesn't get to outvote your memory.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You rerouted your whole day around one photograph.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
