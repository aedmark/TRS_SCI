/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm326.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 27).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 326)
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
(instance public rm326 of Rm
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
			= glitchText "Flip a coin and pretend the outcome doesn't matter to you."
		)
		= choice PrintChoices(
			"It's still sitting there, unmade, quietly getting heavier every week you don't touch it."
			"The Decision You've Been Avoiding for Months"
			290
			glitchText
		"Add it to tomorrow's list again, same as every day this month." 0
		"Ask five different people what they'd do, hoping one of them decides for you." 1
		"Give yourself an actual deadline and one criterion to decide by." 2
		"Plan something else entirely, something big and distracting, instead." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It landed. Your reaction to it told you exactly what you actually wanted.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -8 TAG_FREEZE)
			Print("Tomorrow's list is getting long. This is still at the top of it.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("Five people, five opinions, and the decision is still, somehow, yours.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You made the decision smaller instead of making it disappear.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 -10 TAG_FLIGHT)
			Print("You built an impressive amount of momentum in every direction except the correct one.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
