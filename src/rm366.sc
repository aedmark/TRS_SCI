/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm366.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 2).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 366)
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
(instance public rm366 of Rm
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
			= glitchText "Ask them to write the compliment down so you can frame it."
		)
		= choice PrintChoices(
			"Someone in line says, out of nowhere, that they like your energy today. You will never see them again."
			"The Compliment From A Stranger You'll Never See Again"
			290
			glitchText
		"Say a fast 'thanks' and physically speed up to end the interaction." 0
		"Freeze up and say nothing until they look away, confused." 1
		"Say thank you and let it land." 2
		"Deflect hard: 'you don't even know me,' more defensive than it needed to be." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are now holding a receipt that says 'good energy, 2:47 PM.' You will keep this forever.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You outran a kind word like it was a threat.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("A nice moment got the silent treatment. It wasn't personal. Probably.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("A stranger's small kindness got all the way in, for once.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You picked a fight with a compliment.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
