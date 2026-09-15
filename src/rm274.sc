/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm274.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 8).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 274)
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
(instance public rm274 of Rm
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
			= glitchText "Stand up anyway and deliver your own speech, uninvited, mid-reception."
		)
		= choice PrintChoices(
			"You've known the groom for a decade. Someone he met two years ago is giving the speech instead."
			"The Best Man Speech You Weren't Asked to Give"
			290
			glitchText
		"Smile through the whole thing while doing quiet, silent math." 0
		"Decide to bring it up with him, gently, another day." 1
		"Clap the loudest and mean absolutely none of it." 2
		"Feel the sting, let it be small, and enjoy the wedding anyway." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The DJ, unsure what else to do, plays dramatic entrance music.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You ran a decade-long audit during a five-minute toast.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 5 TAG_FIGHT)
			Print("You chose to name the hurt instead of just carrying it.")
		)
		(case 2
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You applauded harder than you felt to hide what you felt.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You let it hurt a little without needing it to mean everything.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
