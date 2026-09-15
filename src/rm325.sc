/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm325.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 26).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 325)
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
(instance public rm325 of Rm
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
			= glitchText "Google 'average age people figure their life out' at midnight."
		)
		= choice PrintChoices(
			"You do the math on how old you are versus how old you thought 'having it together' would happen by. The math is not flattering."
			"The Age You Thought You'd Have This Figured Out By"
			290
			glitchText
		"Get irrationally angry at your younger self for the timeline they set." 0
		"Spiral quietly for the rest of the evening without telling anyone." 1
		"Notice the timeline was arbitrary, made up by a much younger, much less informed you." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The internet does not agree with itself. You are somehow less comforted.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("Younger you didn't know anything. It's a strange thing to resent them for.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("The spiral had an audience of exactly one and no exit.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You fired a deadline nobody real ever actually set.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
