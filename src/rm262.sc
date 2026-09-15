/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm262.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 28).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 262)
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
(instance public rm262 of Rm
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
			= glitchText "Watch seventeen YouTube tutorials on fixing it yourself."
		)
		= choice PrintChoices(
			"The washing machine makes a sound it shouldn't and then stops entirely. The repair quote costs almost as much as a new one."
			"The Broken Appliance No One Can Afford to Fix"
			290
			glitchText
		"Leave the wet laundry in it for two days while you decide what to do." 0
		"Tell whoever asks that it's 'basically fine, just being weird.'" 1
		"Get a second quote and actually compare the real numbers." 2
		"Start using the laundromat down the street and avoid thinking about the machine at all." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now understand washing machine repair on a conceptual level. The machine remains tangibly broken.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("The laundry did not decide anything on your behalf.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("It is not fine. You've just decided saying so is easier.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You treated it like a decision instead of a crisis.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 -8 TAG_FLIGHT)
			Print("You outsourced the laundry and postponed the decision indefinitely.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
