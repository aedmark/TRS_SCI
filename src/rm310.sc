/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm310.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 11).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 310)
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
(instance public rm310 of Rm
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
			= glitchText "Blame it, loudly and specifically, on Mercury being in retrograde."
		)
		= choice PrintChoices(
			"Your shoulders have been up by your ears for an hour and you genuinely cannot remember when that started."
			"The Ache With No Origin"
			290
			glitchText
		"Ignore it. It'll probably go away on its own." 0
		"Push through it, it's fine, everyone's tired, this is normal." 1
		"Stop. Relax. Roll your shoulders. Unclench your jaw. Take one real breath." 2
		"Get up and go for a walk to shake it off instead of figuring out what it is." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("This explains nothing. It helps somehow, anyway.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You outsourced the problem to future-you. Again. (Jerk).")
		)
		(case 1
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You neglected a body that was actively asking for something.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You gave your body the attention it was asking for. It thanks you by flooding your brain with dopamine.")
		)
		(case 3
			ApplyChoiceEffects(-10 -5 -5 TAG_FLIGHT)
			Print("You moved the tension around the block instead of asking where it came from.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
