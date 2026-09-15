/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm285.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 19).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 285)
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
(instance public rm285 of Rm
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
			= glitchText "Invent an equally mysterious new friend of your own to mention constantly."
		)
		= choice PrintChoices(
			"Your friend has a new person they mention constantly now. Inside jokes you're not part of. You're happy for them. Mostly."
			"The New Best Friend"
			290
			glitchText
		"Ask enthusiastic questions about the new friend, overselling interest." 0
		"Quietly pull back from making plans, without saying why." 1
		"Name the feeling to yourself: a little jealous, and that's okay." 2
		"Make a pointed comment about how much they've been hanging out lately." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your new friend, 'Gary,' may not exist, but this plan works better than expected.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You interviewed your own replacement with a big smile on.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FLIGHT)
			Print("You left the room before anyone asked you to.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Jealousy, acknowledged, took up less room than it usually does. You all go out for drinks later.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You let the jealousy out sideways, at them. Now they feel guilty for making a new friend.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
