/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm307.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 8).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 307)
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
(instance public rm307 of Rm
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
			= glitchText "Interview the photo directly, out loud, like a documentary subject."
		)
		= choice PrintChoices(
			"You're smiling in it. You don't remember if you were actually happy or just good at looking like it, even then."
			"The Childhood Photo You Can't Place A Feeling On"
			290
			glitchText
		"Stare at it longer, trying to force a memory that isn't there." 0
		"Decide it must have been a happy day. Move on quickly." 1
		"Let 'I don't know how I felt' be a complete, acceptable answer." 2
		"Get irritated at the photo for not just telling you the truth." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The photo declines to comment... Which is ironic, since they're worth a thousand words each, minimum.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You interrogated a photograph for information it doesn't have.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FAWN)
			Print("You assigned the photo a feeling so you wouldn't have to sit with the unknown.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 15 TAG_SECURE)
			Print("Not knowing turned out to be allowed. It always is.")
		)
		(case 3
			ApplyChoiceEffects(-5 -10 0 TAG_FIGHT)
			Print("You picked a fight with a piece of paper. It did not blink first.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
