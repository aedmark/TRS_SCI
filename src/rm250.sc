/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm250.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 16).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 250)
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
(instance public rm250 of Rm
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
			= glitchText "Call back using a dramatically different, unexplained voice."
		)
		= choice PrintChoices(
			"Two missed calls and a voicemail you haven't pressed play on yet."
			"The Missed Call From Mom"
			290
			glitchText
		"Let the voicemail sit unheard for the rest of the day." 0
		"Call back immediately, bracing for whatever it is." 1
		"Listen to the voicemail first. Then decide." 2
		"Call back irritated, leading with 'is everything okay?' in a tone that isn't really a question." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You are now, for reasons unclear, doing a full Irish accent.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("An unopened voicemail can hold a lot of imagined weight.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -10 TAG_FAWN)
			Print("You armored up before you even knew what for.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("Information turned out to be less scary than the dread. Have fun at Disney World!")
		)
		(case 3
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("You answered dread with an edge nobody on the other end asked for.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
