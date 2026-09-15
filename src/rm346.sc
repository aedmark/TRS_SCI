/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm346.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 14).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 346)
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
(instance public rm346 of Rm
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
			= glitchText "Construct an elaborate, confident lie about how you got it."
		)
		= choice PrintChoices(
			"You notice a bruise on your arm. You have absolutely no memory of how it got there."
			"The Bruise You Don't Remember"
			290
			glitchText
		"Cover it and move on, it's fine, you're fine." 0
		"Poke it a few times, oddly detached from the pain." 1
		"Actually stop and wonder what's been going on with you lately." 2
		"Press on it hard, annoyed at your own body for keeping secrets from you." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody asked. You told them anyway. The story was *very* good.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(0 10 -10 TAG_FAWN)
			Print("You concealed the evidence and skipped the question it was asking.")
		)
		(case 1
			ApplyChoiceEffects(5 0 -15 TAG_FREEZE)
			Print("You observed your own body like it belonged to someone else.")
		)
		(case 2
			ApplyChoiceEffects(-5 0 15 TAG_SECURE)
			Print("You treated a small mystery as information instead of noise.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 -5 TAG_FIGHT)
			Print("You took it out on the bruise. The bruise kept being a bruise, unbothered.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
