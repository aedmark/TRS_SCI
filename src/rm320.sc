/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm320.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 21).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 320)
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
(instance public rm320 of Rm
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
			= glitchText "Apologize to an inanimate object instead. With real emotion."
		)
		= choice PrintChoices(
			"Someone bumps into you. You apologize. To them. For being bumped into."
			"The Reflex To Apologize For Existing"
			290
			glitchText
		"Apologize again, just to be safe." 0
		"Notice it happened and feel weird about it for the rest of the day." 1
		"Notice the reflex. Don't perform an apology for the apology." 2
		"Catch yourself mid-apology and snap, out loud, 'why am I sorry?'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The doorknob accepts your apology graciously. It has no other choice.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You doubled down on an apology that was never yours to give.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You let a two-second reflex become an all-day mood.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You caught the reflex mid-air and let it just pass through.")
		)
		(case 3
			ApplyChoiceEffects(-10 -10 5 TAG_FIGHT)
			Print("You interrupted a lifelong reflex with your own irritation. Progress is loud sometimes.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
