/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm247.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 13).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 247)
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
(instance public rm247 of Rm
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
			= glitchText "Install a tiny sign declaring the thermostat a neutral zone."
		)
		= choice PrintChoices(
			"It's been adjusted three times today. Nobody has said a word about it out loud."
			"The Thermostat War"
			290
			glitchText
		"Set it where you want it and leave a note explaining why." 0
		"Just wear a sweater and say nothing, forever. Probably." 1
		"Leave it wherever they last set it, every time." 2
		"Bring it up out loud and actually agree on a number together." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Switzerland would be proud. (Nobody honors the treaty).")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You made your preference visible instead of silent. The note lasted 2 hours before it went missing.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You sacrificed your physical comfort to avoid a two-minute conversation.")
		)
		(case 2
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You denied yourself a temperature change just to keep the peace.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You turned a silent war into one two-minute conversation.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
