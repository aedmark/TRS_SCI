/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm266.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 0).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 266)
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
(instance public rm266 of Rm
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
			= glitchText "Ask them, with complete sincerity, for their thoughts on oat milk."
		)
		= choice PrintChoices(
			"Someone is standing exactly in front of the specific brand of oat milk you need. They are taking a very long time."
			"The Grocery Store Aisle"
			290
			glitchText
		"Pretend to look at regular milk until they leave." 0
		"Say 'Excuse me' using a voice three octaves higher than normal." 1
		"Abandon the oat milk. You didn't deserve it anyway." 2
		"Say 'excuse me' at a normal volume and just wait." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now know more about oat milk than many food scientists. And have a new tennis partner.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 10 -10 TAG_FREEZE)
			Print("You sacrificed your time and dignity to avoid taking up space.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -5 TAG_FAWN)
			Print("You utilized the Customer Service Voice. It was effective.")
		)
		(case 2
			ApplyChoiceEffects(15 5 -10 TAG_FLIGHT)
			Print("You punished yourself for a stranger's existence.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You asked for the small thing you truly wanted.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
