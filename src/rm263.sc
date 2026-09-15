/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm263.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 29).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 263)
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
(instance public rm263 of Rm
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
			= glitchText "Offer to lend double what they asked for, out of guilt."
		)
		= choice PrintChoices(
			"A family member asks to borrow money you don't really have room to lend, in a tone that makes it hard to say no."
			"The Family Loan Request"
			290
			glitchText
		"Say yes immediately and figure out the math later." 0
		"Say no bluntly, then feel terrible about how it came out." 1
		"Say what you can actually afford, clearly, without over-explaining." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Your bank account did not consent to this decision. You may never financially recover from this.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -15 TAG_FAWN)
			Print("You said yes with your mouth before your budget got a vote.")
		)
		(case 1
			ApplyChoiceEffects(-10 -15 0 TAG_FIGHT)
			Print("The no was correct. The delivery could use some work.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("You gave an honest number instead of an apologetic one.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
