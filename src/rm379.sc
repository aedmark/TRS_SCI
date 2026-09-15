/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm379.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 15).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 379)
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
(instance public rm379 of Rm
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
			= glitchText "Ask them to just tell you the whole story anyway. You're invested now."
		)
		= choice PrintChoices(
			"A stranger is on the other end of the line, mid-sentence, about something urgent that has nothing to do with you."
			"The Wrong Number You Answered"
			290
			glitchText
		"Stay on the line and try to help them anyway, even though you have no idea who they meant to call." 0
		"Hang up immediately without saying a word." 1
		"Politely tell them they've got the wrong number and wish them luck." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now know entirely too much about someone named Gary's custody hearing.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You took on a stranger's emergency because hanging up felt ruder than staying confused.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You removed yourself from a problem that was never yours in the first place.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You closed the door gently instead of slamming it or leaving it open.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
