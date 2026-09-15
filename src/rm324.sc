/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm324.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 25).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 324)
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
(instance public rm324 of Rm
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
			= glitchText "Open three different budgeting apps in the same five minutes."
		)
		= choice PrintChoices(
			"You open the app, see the number, close it immediately, and open it again ninety seconds later like it might have changed."
			"The Banking App"
			290
			glitchText
		"Check it six more times without doing anything differently." 0
		"Tell yourself other people are worse with money, so it's fine." 1
		"Actually write the number down somewhere and make one small plan around it." 2
		"Get sharply angry at whatever got you here in the first place." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now have three different opinions on how broke you are.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("The number stayed the same. Checking isn't the same as changing it.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("Comparing down didn't actually move the number either.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You stopped checking and started, in a small way, handling it.")
		)
		(case 3
			ApplyChoiceEffects(-10 -5 -5 TAG_FIGHT)
			Print("The number didn't move. You definitely did, internally, at volume.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
