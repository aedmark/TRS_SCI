/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm340.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 8).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 340)
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
(instance public rm340 of Rm
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
			= glitchText "Buy the extremely bitter anti-biting nail polish."
		)
		= choice PrintChoices(
			"You look down mid-meeting and realize you've bitten a nail down past comfortable. You don't remember starting."
			"The Bitten Nails"
			290
			glitchText
		"Hide your hands and keep going like nothing happened." 0
		"Sit on your hands for the rest of the meeting." 1
		"Notice it, put your hands flat on the table, and let it be a fact." 2
		"Snap at yourself under your breath and jam your hands into your pockets." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now know exactly how bitter it is. Repeatedly.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You concealed the evidence. The habit remains unfiled.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -5 TAG_FREEZE)
			Print("You restrained the symptom instead of asking about the cause.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You caught yourself mid-habit without turning it into a crisis.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 -5 TAG_FIGHT)
			Print("You declared war on your own hands.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
