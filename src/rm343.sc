/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm343.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 11).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 343)
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
(instance public rm343 of Rm
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
			= glitchText "Convince yourself you're still dreaming for a solid ninety seconds."
		)
		= choice PrintChoices(
			"You wake up two hours late. Your alarm went off. You have no memory of turning it off."
			"The Alarm You Don't Remember Silencing"
			290
			glitchText
		"Rush out the door pretending this is fine and normal." 0
		"Lie there a while longer, the day's already ruined anyway." 1
		"Get up slowly, text that you're late, actually wake up first." 2
		"Skip breakfast, skip the shower, just get out the door as fast as possible." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You were not dreaming. You were extremely, demonstrably late.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -5 TAG_FAWN)
			Print("You performed 'on schedule' at a very unconvincing level.")
		)
		(case 1
			ApplyChoiceEffects(5 -5 -10 TAG_FREEZE)
			Print("You let one bad start decide the whole day's verdict.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You let your body finish waking up before you asked it to perform.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -8 TAG_FLIGHT)
			Print("You outran the morning instead of catching up to it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
