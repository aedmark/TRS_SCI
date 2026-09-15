/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm236.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 2).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 236)
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
(instance public rm236 of Rm
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
			= glitchText "Reply only in cryptic, unrelated fortune-cookie wisdom."
		)
		= choice PrintChoices(
			"Your aunt just brought up something from Thanksgiving 2019. Nobody asked."
			"The Family Group Chat"
			290
			glitchText
		"Mute the chat and pretend your phone is broken." 0
		"Draft a measured correction, then delete it three times." 1
		"Send a single laughing emoji and nothing else." 2
		"Type a short, honest correction and actually send it." 3
		"Reply with the actual, unedited version of what happened." 4
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("'The sink remembers what the heart forgets.' Nobody asked. Nobody replies.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 -5 0 TAG_FLIGHT)
			Print("You unplugged from the family server.")
		)
		(case 1
			ApplyChoiceEffects(20 5 -10 TAG_FREEZE)
			Print("You drafted diplomacy and shipped silence.")
		)
		(case 2
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You de-escalated with punctuation.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 10 TAG_SECURE)
			Print("You corrected the record once and stopped carrying it.")
		)
		(case 4
			ApplyChoiceEffects(-10 -15 3 TAG_FIGHT)
			Print("You picked the fight the group chat had been avoiding for years. Secretly, they are all grateful.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
