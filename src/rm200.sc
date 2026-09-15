/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm200.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 0).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 200)
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
(instance public rm200 of Rm
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
			= glitchText "Reply in fluent, unhinged interpretive dance. Over email. Somehow."
		)
		= choice PrintChoices(
			"You sent an email to your boss with a minor typo. Your brain immediately interprets this as a fatal error that will result in death or financial ruin."
			"The Typo"
			290
			glitchText
		"Send a frantic three-paragraph apology." 0
		"Stare at the wall and dissociate for 20 minutes." 1
		"Tell yourself 'it's just a typo' while sweating profusely." 2
		"Reply-all with a terse non-apology: 'Typo. Not fixing it.'" 3
		"Close the laptop and go for a walk before you can send a second, worse email." 4
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("HR has several follow-up questions. So does everyone else. Nobody's mad, though.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-5 10 -15 TAG_FAWN)
			Print("You debased yourself for a misplaced comma.")
		)
		(case 1
			ApplyChoiceEffects(20 0 -5 TAG_FREEZE)
			Print("You fled your physical body. The typo remains. Nobody died.")
		)
		(case 2
			ApplyChoiceEffects(-10 -5 10 TAG_SECURE)
			Print("You attempted self-soothing. It was highly unconvincing and only mildly successful.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 3 TAG_FIGHT)
			Print("You picked a fight with a comma and, somehow, won.")
		)
		(case 4
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You put physical distance between yourself and send.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
