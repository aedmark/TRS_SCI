/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm367.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 3).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 367)
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
(instance public rm367 of Rm
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
			= glitchText "Loudly announce to the whole bus that you're not crying it's just allergies."
		)
		= choice PrintChoices(
			"It hits you three stops from home and there is nowhere to put it. Everyone very pointedly looks at their phones."
			"The Public Cry On The Bus"
			290
			glitchText
		"Smile at the person next to you like everything's fine, tears and all." 0
		"Stare straight ahead and let it happen without acknowledging it at all." 1
		"Let it happen, wipe your face when it passes, and don't apologize for it." 2
		"Get off two stops early just to escape the eyes on the bus." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody asked. Several people now have to carry a bit of something from you they didn't want.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You performed 'fine' with visible evidence directly contradicting it.")
		)
		(case 1
			ApplyChoiceEffects(12 0 -5 TAG_FREEZE)
			Print("You rode it out like weather. Nobody said anything. That's the deal on public transit.")
		)
		(case 2
			ApplyChoiceEffects(-10 3 10 TAG_SECURE)
			Print("You cried on a bus and didn't make it mean anything about you as a person.")
		)
		(case 3
			ApplyChoiceEffects(-10 -8 -5 TAG_FLIGHT)
			Print("You walked the rest of the way so nobody had to watch you do it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
