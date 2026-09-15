/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm312.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 13).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 312)
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
(instance public rm312 of Rm
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
			= glitchText "Write your own unhinged, deeply specific counter-list right now."
		)
		= choice PrintChoices(
			"You're scrolling through someone else's milestones; house, promotion, wedding, etc.  You keep checking your own life against it."
			"The List of Things You're Supposed To Want"
			290
			glitchText
		"Keep scrolling, keep comparing, feel worse with each one." 0
		"Convince yourself you want all of it too, just to feel aligned." 1
		"Close the app. Ask yourself, honestly, what you actually want." 2
		"Fire off a defensive comment about how milestones aren't a real measure of anything." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Item four is just 'made a really good sandwich.' You stand by it.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You audited your life against an idealized list you didn't write. Of course you fell short.")
		)
		(case 1
			ApplyChoiceEffects(10 5 -15 TAG_FAWN)
			Print("You borrowed someone else's wants because yours felt too quiet to trust.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You checked the list against your own name instead of theirs.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 3 TAG_FIGHT)
			Print("You argued with a stranger's wedding photos and, somehow, still lost.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
