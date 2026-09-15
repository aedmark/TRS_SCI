/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm321.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 22).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 321)
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
(instance public rm321 of Rm
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
			= glitchText "Post the most unhinged, honest thing you can think of instead."
		)
		= choice PrintChoices(
			"You've refreshed the same three apps four times in the last ten minutes, looking for a number that proves something."
			"The Urge To Check If You're Still Likable"
			290
			glitchText
		"Keep refreshing. The number hasn't proven anything yet, but the next one might." 0
		"Post something calibrated to perform well, just to be sure." 1
		"Put the phone down. The question doesn't need an answer right now." 2
		"Throw the phone across the couch and go do something that doesn't have a number attached." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Engagement still goes way down... but you, somehow, feel better.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You outsourced your worth to a number that resets every day.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You engineered proof instead of just existing for a minute.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You let the urge exist without feeding it.")
		)
		(case 3
			ApplyChoiceEffects(-10 -5 -5 TAG_FLIGHT)
			Print("You removed the scoreboard from the room, at least for now.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
