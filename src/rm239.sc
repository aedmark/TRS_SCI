/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm239.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 5).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 239)
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
(instance public rm239 of Rm
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
			= glitchText "Answer back in an equally strange, unexplained voice of your own."
		)
		= choice PrintChoices(
			"Your parent picks up sounding smaller than you remember. You don't know why yet."
			"The Different Voice on the Phone"
			290
			glitchText
		"Immediately go bright and cheerful to lift the mood before you've even asked what's wrong." 0
		"Ask, flatly, exactly what's going on. No preamble." 1
		"Say 'oh, okay' and let the conversation drift somewhere safer." 2
		"Stay warm but ask a real, gentle question: 'Are you okay right now?'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Neither of you addresses it. Some things stay sacred and weird.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You showed up as sunshine before you knew what kind of day it was.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 5 TAG_FIGHT)
			Print("You skipped the small talk. It cost you nothing you needed.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a real question evaporate into weather talk.")
		)
		(case 3
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You met the smallness with steadiness instead of performance.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
