/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm302.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 3).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 302)
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
(instance public rm302 of Rm
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
			= glitchText "Get up and reorganize the spice rack, aggressively, at 3 AM."
		)
		= choice PrintChoices(
			"No reason. No noise. Just awake, and your brain has already opened seventeen tabs of decade-old conversations."
			"The 3 AM Wake-Up"
			290
			glitchText
		"Start drafting an apology text you'll delete by morning." 0
		"Get up and reorganize something small and physical." 1
		"Name it: this is just cortisol, not prophecy." 2
		"Argue back at 3 AM brain, out loud, like it's a person you can win against." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("The spices are now alphabetized. You are not more at peace, but the spices are.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 5 -15 TAG_FAWN)
			Print("You litigated a conversation the other person forgot happened.")
		)
		(case 1
			ApplyChoiceEffects(10 0 0 TAG_FLIGHT)
			Print("You redirected 3 AM into a drawer.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 10 TAG_SECURE)
			Print("You out-argued your own nervous system and won. You sleep in victory.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 3 TAG_FIGHT)
			Print("You picked a fight with your own nervous system. It doesn't lose.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
