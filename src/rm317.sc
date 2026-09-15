/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm317.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 18).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 317)
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
(instance public rm317 of Rm
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
			= glitchText "Call them right now and just say it, all of it, fast."
		)
		= choice PrintChoices(
			"The words were right there. You had the opening. You changed the subject instead, and you're still thinking about it hours later."
			"The Time You Almost Told Someone"
			290
			glitchText
		"Replay the moment, cataloging exactly why you didn't say it." 0
		"Decide it's better this way for everyone. Probably." 1
		"Note that the opening will come again. It's not your only chance." 2
		"Leave the conversation early, before anything else can slip out." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It's out. The world, remarkably, keeps turning.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You held a post-mortem for a conversation that never happened.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You reframed silence as consideration for other people.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You released the pressure of a single missed moment.")
		)
		(case 3
			ApplyChoiceEffects(-10 -5 -5 TAG_FLIGHT)
			Print("You removed yourself from your own opening before it could close on its own.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
