/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm226.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 26).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 226)
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
(instance public rm226 of Rm
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
			= glitchText "Ask your manager to list, out loud, every mistake you've made this quarter."
		)
		= choice PrintChoices(
			"Your manager says the presentation went great. You spend the next hour building a case for why she's wrong."
			"The Imposter Spiral"
			290
			glitchText
		"Deflect the compliment immediately and list everyone else who helped." 0
		"Reread your own slides looking for the mistake everyone's too polite to mention." 1
		"Say 'thank you' and let it be true." 2
		"Close the laptop and go for a walk so you can't keep re-litigating it." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("She could only think of one. You are unreasonably disappointed. She now thinks you're hiding something.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You gave the credit away before anyone could take a closer look at you.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("You found four typos and no evidence of the disaster you were sure was coming.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You accepted a good thing without an asterisk on it.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -5 TAG_FLIGHT)
			Print("You physically removed yourself from the trial. The jury adjourns, for now.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
