/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm280.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 14).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 280)
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
(instance public rm280 of Rm
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
			= glitchText "Produce an actual calculator and do the math out loud, in real time."
		)
		= choice PrintChoices(
			"You had a salad and water. Someone else had three cocktails. The bill's being split evenly, and everyone's already agreeing."
			"The Split-the-Bill Math"
			290
			glitchText
		"Agree to split evenly and say nothing about the math." 0
		"Suggest, casually, splitting it by what people actually got." 1
		"Pay your share silently and feel weird about it for days." 2
		"Say, easily, 'I'll just cover my part' and hand over the exact amount." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The table goes silent. You do not stop calculating.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You paid for cocktails you didn't order to keep the table easy.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You named the math out loud before it became a resentment. Three other people suddenly agree.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a bill you didn't agree with become a slow-burn grudge.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You solved the math without making it a whole thing.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
