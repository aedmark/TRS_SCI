/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm220.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 20).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 220)
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
(instance public rm220 of Rm
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
			= glitchText "Comment using only a single, deeply ambiguous emoji."
		)
		= choice PrintChoices(
			"A peer from two roles ago just posted 'excited to announce' a title you quietly wanted for yourself."
			"The LinkedIn Congrats"
			290
			glitchText
		"Like the post and write a warm, specific comment." 0
		"Close the app. Reopen it four minutes later." 1
		"Feel the envy, don't perform past it, close the app anyway." 2
		"Mute their name from your feed without telling yourself why." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Seventeen people have now liked your emoji. Nobody knows what it means. Least of all you.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 15 -15 TAG_FAWN)
			Print("You performed happiness at a volume you didn't feel.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You kept checking a wound to see if it still hurt.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let a small ugly feeling exist without narrating it to anyone or destroying you.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -8 TAG_FLIGHT)
			Print("You made the wound invisible. It's still there, just off-screen.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
