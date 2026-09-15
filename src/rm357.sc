/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm357.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 25).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 357)
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
(instance public rm357 of Rm
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
			= glitchText "Overcorrect by hugging everyone in the room, unprompted."
		)
		= choice PrintChoices(
			"Someone reaches out for a hug, or just to touch your arm, and your whole body stiffens for a second before you can stop it."
			"The Flinch"
			290
			glitchText
		"Force the hug to look natural and hope nobody noticed the flinch." 0
		"Get through the contact by mentally leaving the room while your body stays in it." 1
		"Let the flinch happen and just say, lightly, 'sorry, jumpy today.'" 2
		"Step back, mumble an excuse, and put distance between you and the contact." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Several people are now confused but more fulfilled. Your arms are tired.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -8 TAG_FAWN)
			Print("You performed comfortable so well even you almost believed it.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("You were there for the hug. Technically.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You named it instead of hiding it, and the moment passed anyway.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 -5 TAG_FLIGHT)
			Print("You put space between yourself and a hug that hadn't done anything wrong.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
