/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm264.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 30).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 264)
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
(instance public rm264 of Rm
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
			= glitchText "Propose settling it with a coin flip."
		)
		= choice PrintChoices(
			"Whoever you live with wants to talk about the shared expenses again. The conversation you've both been avoiding for a month."
			"The Split Bill Argument"
			290
			glitchText
		"Say you're too tired to get into it tonight, again." 0
		"Bring up every uneven expense from the last six months at once." 1
		"Actually sit down, split it fairly, and let the conversation be boring." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody agreed to this. You flipped it anyway. Somehow it worked flawlessly, anyway.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -8 0 TAG_FLIGHT)
			Print("You bought one more night of not talking about it. The bill didn't wait.")
		)
		(case 1
			ApplyChoiceEffects(-12 -15 0 TAG_FIGHT)
			Print("You were right about most of it. It still didn't land well.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 8 TAG_SECURE)
			Print("Nothing dramatic happened. That was sort of the point.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
