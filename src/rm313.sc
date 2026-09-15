/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm313.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 14).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 313)
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
(instance public rm313 of Rm
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
			= glitchText "Give the day an oddly specific, ceremonial little ritual."
		)
		= choice PrintChoices(
			"You've been off all day and couldn't say why. Then you check the date."
			"The Anniversary Your Body Remembers Before You Do"
			290
			glitchText
		"Push through the day as if you hadn't noticed anything at all." 0
		"Apologize to everyone around you for being 'off' today." 1
		"Let today be a harder day. You don't owe anyone your usual output." 2
		"Cancel the rest of the day's plans and just leave without explanation." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You have invented a holiday nobody else knows about. It helps.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(20 0 -15 TAG_FREEZE)
			Print("Your body kept the appointment even after your mind tried to skip it.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You apologized for grief that arrived on schedule, uninvited.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You gave a hard day permission to be hard.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -5 TAG_FLIGHT)
			Print("You gave the day less of you instead of naming what the day actually was.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
