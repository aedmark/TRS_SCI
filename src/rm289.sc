/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm289.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 23).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 289)
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
(instance public rm289 of Rm
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
			= glitchText "Comment on every photo with a single, ominous 'interesting.'"
		)
		= choice PrintChoices(
			"The photos are already up. It looks like it was a good one. You didn't know it was happening."
			"The Group Trip You Weren't Invited To"
			290
			glitchText
		"Scroll through every photo, cataloging who's in how many." 0
		"Like every photo enthusiastically, extra hearts included." 1
		"Close the app. Ask yourself later, calmly, if it's worth mentioning." 2
		"Message the group directly: 'Hey, was this planned somewhere else? Just noticed.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody knows what you meant. You are not entirely sure either. Regardless, it IS interesting.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You turned a vacation you weren't on into forensic evidence.")
		)
		(case 1
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You applauded a trip that stung to see.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You gave the feeling time before deciding what to do with it.")
		)
		(case 3
			ApplyChoiceEffects(-8 -12 3 TAG_FIGHT)
			Print("You asked the question out loud instead of just doing math about it in silence.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
