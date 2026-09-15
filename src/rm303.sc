/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm303.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 4).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 303)
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
(instance public rm303 of Rm
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
			= glitchText "Declare it a national holiday, just for yourself, effective immediately."
		)
		= choice PrintChoices(
			"Nothing is scheduled. Nobody needs anything from you. This is, somehow, the hardest part of the week."
			"The Empty Sunday"
			290
			glitchText
		"Invent an urgent task to feel useful again." 0
		"Scroll until the day disappears without you in it." 1
		"Sit with the unscheduled hour and let it be boring." 2
		"Get irritated at yourself for not being productive on your day off." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Attendance is mandatory. You are the only attendee.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You manufactured a purpose to avoid the quiet.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -15 TAG_FLIGHT)
			Print("You outsourced eight hours to a feed.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 15 TAG_SECURE)
			Print("You survived free time without earning it first. You didn't explode.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 0 TAG_FIGHT)
			Print("You turned rest into something to be mad about.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
