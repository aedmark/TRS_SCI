/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm265.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-home-events.js from
 js/events/home.js (HOME event 31).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 265)
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
(instance public rm265 of Rm
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
			= glitchText "Offer the pet seventeen different foods in immediate succession."
		)
		= choice PrintChoices(
			"Your dog, or cat, walks right past a full food bowl for the second day in a row, and the vet visit you can't quite afford yet keeps not happening."
			"The Pet Skipping Meals"
			290
			glitchText
		"Tell yourself it's probably nothing and keep watching from across the room." 0
		"Post about it online and let strangers' reassurance stand in for an actual appointment." 1
		"Call and ask what a visit actually costs, then book it anyway." 2
		"Close the food delivery tab, close the vet's website, and go do literally anything else." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nothing worked. You now have six open cans and one unimpressed animal.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -8 TAG_FREEZE)
			Print("Watching didn't make the bowl any emptier or any fuller.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -8 TAG_FAWN)
			Print("Forty people said it's probably fine. None of them are a vet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You let the real number decide instead of your fear of it.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -8 TAG_FLIGHT)
			Print("You put the worry in a drawer next to the bill you also haven't opened.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
