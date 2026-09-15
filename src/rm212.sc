/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm212.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 12).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 212)
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
(instance public rm212 of Rm
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
			= glitchText "Start typing back before they've even sent anything."
		)
		= choice PrintChoices(
			"Your manager's typing indicator appears, disappears, appears again. Three times. Still no message."
			"The Typing Indicator"
			290
			glitchText
		"Stare at the little dots like they're a polygraph." 0
		"Close the tab so you can't watch it happen." 1
		"Keep working. Whatever it is will say itself eventually." 2
		"Send a message first: 'Are you typing a novel or a reply over there?'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your dots meet their dots. A standoff of pure anticipation. Who will win? ...")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -10 TAG_FREEZE)
			Print("You interrogated punctuation that hadn't arrived yet.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -5 TAG_FLIGHT)
			Print("You removed the evidence, not the feeling.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let an unfinished sentence stay unfinished.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You forced the dots into an actual sentence. A very snarky sentence. The dots stopped. Now you get no reply at all.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
