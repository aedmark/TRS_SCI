/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm210.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 10).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 210)
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
(instance public rm210 of Rm
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
			= glitchText "Reply-All to the entire company. Let everyone share in this learning experience."
		)
		= choice PrintChoices(
			"A coworker loops your manager into a thread about a mistake that was mostly, but not entirely, your fault."
			"The CC'd Boss"
			290
			glitchText
		"Immediately reply-all with a full, apologetic breakdown." 0
		"Reply-all clarifying, calmly, which parts were actually yours." 1
		"Leave the thread unanswered and refresh your inbox every four minutes." 2
		"Message your manager directly, briefly, with the actual context." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Democracy, but for blame. Nobody wins but nobody loses, either. Except maybe some brain cells.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You built the case against yourself before anyone asked for one.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 10 TAG_FIGHT)
			Print("You drew a line around the blame instead of absorbing all of it.")
		)
		(case 2
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("You watched the thread instead of joining it.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You handled it directly instead of performing it for an audience.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
