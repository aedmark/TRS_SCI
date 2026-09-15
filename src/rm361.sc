/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm361.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 29).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 361)
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
(instance public rm361 of Rm
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
			= glitchText "Drink a fourth coffee and decide this will definitely be the one that works."
		)
		= choice PrintChoices(
			"You slept eight hours. You wake up exactly as tired as when you laid down, if not more so."
			"The Exhaustion Sleep Didn't Fix"
			290
			glitchText
		"Push through the day on caffeine and sheer stubbornness." 0
		"Tell everyone who asks that you're 'just a little tired, no big deal.'" 1
		"Actually cancel one non-essential thing today to protect what's left of your energy." 2
		"Cancel everything non-negotiable-sounding too, and disappear for the day." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It was not the one that worked. Your entire body is vibrating and still tired.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("You made it through. 'Made it through' is doing a lot of work in that sentence.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You minimized it into something small enough that nobody, including you, has to look at it.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 10 TAG_SECURE)
			Print("You spent the energy on rest instead of one more obligation you didn't have room for.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 -5 TAG_FLIGHT)
			Print("The day happened without you in it. You'll deal with the fallout tomorrow, just as tired.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
