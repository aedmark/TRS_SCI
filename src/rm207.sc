/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm207.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 7).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 207)
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
(instance public rm207 of Rm
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
			= glitchText "Respond back at them entirely in weaponized corporate buzzwords."
		)
		= choice PrintChoices(
			"Your manager says 'let's discuss your growth areas' in a tone that reveals absolutely nothing."
			"The Performance Review Buzzword"
			290
			glitchText
		"Mentally practice your groveling technique to beg for your job back." 0
		"Walk in and ask directly what 'growth areas' means, specifically." 1
		"Prepare a mental defense file of every accomplishment from the last three years." 2
		"Ask calmly what specifically they mean, and actually listen to the answer." 3
		"Nod along and change the subject to a project update instead." 4
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have synergized so hard the meeting ended early out of confusion. Nobody dares to circle back.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(25 0 -10 TAG_FREEZE)
			Print("You spent energy defending a career you still have.")
		)
		(case 1
			ApplyChoiceEffects(-15 -10 10 TAG_FIGHT)
			Print("You demanded the noun behind the euphemism.")
		)
		(case 2
			ApplyChoiceEffects(10 10 -10 TAG_FAWN)
			Print("You built a case for a trial nobody scheduled.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You asked instead of pre-defending.")
		)
		(case 4
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You steered the meeting away from the actual question.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
