/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm291.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 25).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 291)
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
(instance public rm291 of Rm
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
			= glitchText "Rewrite your entire profile at midnight out of spite."
		)
		= choice PrintChoices(
			"Three good days of conversation. Then nothing, mid-sentence, two days ago."
			"The Dating App Match Who Stopped Responding"
			290
			glitchText
		"Reread the whole conversation looking for the exact moment it went wrong." 0
		"Send one more message, lighter this time, just in case they missed the first." 1
		"Unmatch and let it be exactly as unremarkable as it actually is." 2
		"Delete the app. All of them, actually." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now describe yourself as 'allegedly fun.' It's an improvement, actually.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("You found nothing. There was nothing to find.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You performed casual. It did not feel casual.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("Three good days doesn't owe you an explanation for a fourth.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 -5 TAG_FLIGHT)
			Print("You solved the problem by removing the entire category it lived in.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
