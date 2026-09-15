/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm305.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 6).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 305)
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
(instance public rm305 of Rm
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
			= glitchText "Say it out loud again, three more times, increasingly loudly."
		)
		= choice PrintChoices(
			"You catch yourself thinking something kind about your own work, unprompted, and immediately feel weird about it."
			"The Accidental Self-Compliment"
			290
			glitchText
		"Correct yourself internally: find the flaw, restore the natural order." 0
		"Change the subject in your own head immediately." 1
		"Let the thought stand. Don't correct it. Just let it be true for a second." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The dog looks concerned. The compliment stands.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 0 -15 TAG_FIGHT)
			Print("You disqualified the thought before it could get comfortable.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You fled a compliment like it was a fire alarm.")
		)
		(case 2
			ApplyChoiceEffects(-15 -5 20 TAG_SECURE)
			Print("You let something kind about yourself survive contact with your own scrutiny.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
