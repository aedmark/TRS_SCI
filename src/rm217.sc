/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm217.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 17).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 217)
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
(instance public rm217 of Rm
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
			= glitchText "Enter dramatically, out of breath, with an incredulous look in your eye."
		)
		= choice PrintChoices(
			"You join the call two minutes late. Everyone's already talking. Nobody pauses to catch you up."
			"The Two Minutes Late"
			290
			glitchText
		"Whisper 'sorry, sorry' three times while finding your seat." 0
		"Sit silently, too embarrassed to ask what you missed." 1
		"Ask, once, what you missed. Move on." 2
		"Talk over the tangent: 'Stop-back up, what did I miss.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'Quick! There's no time to explain!'")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You apologized for the two minutes and the seven after it.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You chose confusion over one slightly awkward question. Phew!")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You caught up effortlessly. Three people quietly thanked you because they weren't paying attention.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 5 TAG_FIGHT)
			Print("You didn't wait for a pause. You made one.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
