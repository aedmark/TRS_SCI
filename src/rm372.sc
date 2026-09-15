/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm372.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 8).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 372)
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
(instance public rm372 of Rm
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
			= glitchText "Strike up a conversation with your number ticket like it's a person."
		)
		= choice PrintChoices(
			"You've been standing in the same six feet of floor for forty minutes. The number on your ticket has not moved. Neither has anyone's mood."
			"The DMV Line"
			290
			glitchText
		"Apologize to the person behind you for existing in their line of sight." 0
		"Loudly ask if anyone else thinks this is insane." 1
		"Accept the wait for what it is and let your mind go somewhere else." 2
		"Step out of line and decide to just come back another day." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("B-247 did not respond. You feel closer to it anyway.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You said sorry for taking up floor space that was yours to take up.")
		)
		(case 1
			ApplyChoiceEffects(-10 -12 0 TAG_FIGHT)
			Print("Several strangers agreed with you. Nobody moved any faster because of it.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave the wait exactly the amount of your life it was going to take anyway.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("You spent forty minutes to leave with nothing, on purpose this time.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
