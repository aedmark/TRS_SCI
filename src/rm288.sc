/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm288.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 22).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 288)
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
(instance public rm288 of Rm
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
			= glitchText "Sit on the floor with great, theatrical dignity instead."
		)
		= choice PrintChoices(
			"You go to sit down. 'Oh, that one's taken,' said kindly, but you're now standing in a room full of seated people."
			"The Seat Saved For Someone Else"
			290
			glitchText
		"Laugh it off and hover near the wall instead." 0
		"Stand there a beat too long, unsure what to do with your body." 1
		"Ask, simply, if there's another seat open." 2
		"Slip out to the hallway for a few minutes until you can casually reappear." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The floor, it turns out, has an excellent view.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You made your own displacement look easy.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("A missing chair became a small, public crisis.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You solved a chair problem with a chair question.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -5 TAG_FLIGHT)
			Print("You gave the room time to forget it saw you standing there.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
