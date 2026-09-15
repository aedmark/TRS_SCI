/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm378.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 14).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 378)
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
(instance public rm378 of Rm
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
			= glitchText "Applaud, as if it were an intentional stunt."
		)
		= choice PrintChoices(
			"They went down hard on the sidewalk two steps ahead of you, and now there's a very short window to decide what kind of stranger you are."
			"The Person Who Fell In Front Of You"
			290
			glitchText
		"Rush over and apologize on their behalf before they've even said anything." 0
		"Freeze for a second too long, unsure if helping is your job here." 1
		"Ask if they're okay and help them up without making it a scene." 2
		"Keep walking like you didn't quite see it happen." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They did not take a bow. They did, eventually, laugh.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You absorbed a stranger's embarrassment for them before they'd even located it themselves.")
		)
		(case 1
			ApplyChoiceEffects(8 0 -5 TAG_FREEZE)
			Print("By the time you moved, someone else already had. You still feel the half-second of not moving.")
		)
		(case 2
			ApplyChoiceEffects(-8 3 8 TAG_SECURE)
			Print("You did the plain, obvious thing. It was enough.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 -5 TAG_FLIGHT)
			Print("Someone else stopped. You kept your pace and a small, specific discomfort meant only for you.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
