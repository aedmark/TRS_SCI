/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm296.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 30).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 296)
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
(instance public rm296 of Rm
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
			= glitchText "Post something deliberately, aggressively mundane in response."
		)
		= choice PrintChoices(
			"You open the app for one notification and forty minutes later you're deep in someone else's vacation photos, doing math on their life against yours."
			"The Comparison Scroll"
			290
			glitchText
		"Keep scrolling well past the point it started feeling bad." 0
		"Like everything, generously, while feeling worse with every tap." 1
		"Close the app and name, out loud, that it's a highlight reel, not a life." 2
		"Put the phone in another room and go do something with your hands instead." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Twelve people liked a photo of your ceiling. Justice, kinda.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(12 0 -10 TAG_FREEZE)
			Print("You know more about a stranger's trip to Portugal than you do about how you're actually doing right now.")
		)
		(case 1
			ApplyChoiceEffects(5 8 -10 TAG_FAWN)
			Print("You performed happiness for people while quietly auditing your own life against theirs.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You put the comparison down instead of finishing the whole plate of it.")
		)
		(case 3
			ApplyChoiceEffects(-10 -5 -5 TAG_FLIGHT)
			Print("You physically separated yourself from the plate you kept refilling.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
