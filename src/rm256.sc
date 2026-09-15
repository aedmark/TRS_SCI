/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm256.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 22).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 256)
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
(instance public rm256 of Rm
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
			= glitchText "Start referring to the wall as 'the incident' from now on."
		)
		= choice PrintChoices(
			"You come home to a wall that's a different color than it was this morning. Nobody mentioned it was happening."
			"The New Paint Color They Didn't Ask About"
			290
			glitchText
		"Say you love it, immediately, before you've decided if you do." 0
		"Ask why you weren't part of the conversation." 1
		"Say nothing and just quietly start disliking the room." 2
		"Say honestly you wish you'd been asked, and that you'll get used to it either way." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The wall is aware of its new title. It does not react.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You approved a decision before forming an opinion.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You named the part that actually bothered you: not the color.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a wall become a symbol instead of just a wall.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You named the actual feeling instead of performing or burying it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
