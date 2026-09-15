/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm395.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 31).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 395)
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
(instance public rm395 of Rm
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
			= glitchText "Take a bow for the sneeze, like it was the whole point of coming today."
		)
		= choice PrintChoices(
			"It happens right in the middle of a silence you did not create and loud enough that it may as well have been an announcement."
			"The Sneeze In The Quiet Room"
			290
			glitchText
		"Apologize three separate times to three separate directions." 0
		"Freeze completely and hope everyone just forgets it happened." 1
		"Say a quiet 'excuse me' and let the room move on, because it will." 2
		"Say, loudly and a little defensively, 'it's just a sneeze, everyone relax.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Someone actually said 'bless you' with real enthusiasm. A win, of sorts, for the sneeze.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You apologized for a biological reflex like it was a breach of contract.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -3 TAG_FREEZE)
			Print("You went very still, as if stillness now could undo a sneeze from three seconds ago.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 5 TAG_SECURE)
			Print("You let a small, human noise be exactly that small.")
		)
		(case 3
			ApplyChoiceEffects(-5 -15 0 TAG_FIGHT)
			Print("Nobody had actually reacted yet. Now they have something to react to.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
