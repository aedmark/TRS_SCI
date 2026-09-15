/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm381.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 17).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 381)
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
(instance public rm381 of Rm
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
			= glitchText "Model the outfit for the entire waiting area whether they asked or not."
		)
		= choice PrintChoices(
			"Three angles of unflattering light and a mirror that doesn't care about your feelings. Someone outside asks how it's going in there."
			"The Fitting Room Mirror"
			290
			glitchText
		"Call out 'great, thanks!' before you've even looked down." 0
		"Stand very still and stare until the moment passes on its own." 1
		"Say 'still deciding' and actually take a second to decide." 2
		"Change back into your regular clothes fast and skip buying anything at all." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You received two genuine compliments and one very confused nod. Worth it.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -8 TAG_FAWN)
			Print("You reassured a stranger through a door about a feeling you hadn't actually had yet.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You had a small, private standoff with a mirror and the mirror won by default.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You let the honest answer be the answer, even through a curtain.")
		)
		(case 3
			ApplyChoiceEffects(-10 -5 -5 TAG_FLIGHT)
			Print("You left the store instead of the mirror. Same problem, different venue.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
