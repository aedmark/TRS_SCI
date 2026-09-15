/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm347.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 15).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 347)
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
(instance public rm347 of Rm
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
			= glitchText "Strike up a loud, urgent conversation about the weather."
		)
		= choice PrintChoices(
			"The elevator doors close and your heart rate spikes for no reason you can name."
			"The Racing Heart in the Elevator"
			290
			glitchText
		"Smile at the stranger next to you like everything's normal." 0
		"Get out at the wrong floor just to escape the box." 1
		"Count your breaths until the doors open again." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody wanted this conversation. It happened anyway. Heart rate: unchanged.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 10 -5 TAG_FAWN)
			Print("You performed calm at a stranger who will never know otherwise.")
		)
		(case 1
			ApplyChoiceEffects(-10 -5 0 TAG_FLIGHT)
			Print("You solved the feeling by relocating the problem, briefly.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("You rode it out instead of running from it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
