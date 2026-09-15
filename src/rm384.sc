/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm384.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 20).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 384)
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
(instance public rm384 of Rm
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
			= glitchText "Ask if you can hand out flyers too. You don't know what they're for."
		)
		= choice PrintChoices(
			"A stranger presses a flyer into your hand on the sidewalk and watches, expectantly, to see what you'll do with it."
			"The Flyer Someone Hands You"
			290
			glitchText
		"Take it, thank them warmly, and hold onto it for three more blocks before tossing it." 0
		"Speed up before they can even fully extend their arm." 1
		"Say 'no thanks' and keep walking at your normal pace." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now work here, apparently, for the next twenty minutes and zero dollars.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You carried a flyer you didn't want for three blocks so a stranger wouldn't watch you not want it.")
		)
		(case 1
			ApplyChoiceEffects(-5 -3 0 TAG_FLIGHT)
			Print("You dodged a piece of paper like it was an arrow.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You declined a piece of paper without treating it like a moral event.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
