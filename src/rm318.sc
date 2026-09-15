/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm318.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 19).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 318)
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
(instance public rm318 of Rm
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
			= glitchText "Announce it to a stranger on the street, at volume."
		)
		= choice PrintChoices(
			"You did something genuinely well today. Saying so, even to yourself, feels like it's asking for trouble."
			"The Pride You Can't Say Out Loud"
			290
			glitchText
		"Immediately find the flaw to balance out the pride." 0
		"Feel the pride, quietly, and never mention it to anyone." 1
		"Say it out loud, once, to yourself. 'I did that well.'" 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("A stranger claps for you. You're both very proud of yourself..")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You disqualified the good thing before it could get comfortable.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -5 TAG_FREEZE)
			Print("You let it exist, but only in a locked room.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 20 TAG_SECURE)
			Print("You let something good about yourself survive contact with your own scrutiny, again.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
