/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm278.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 12).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 278)
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
(instance public rm278 of Rm
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
			= glitchText "Demand a formal, itemized list of exactly how much better."
		)
		= choice PrintChoices(
			"'You look so much better than you used to' is technically a compliment, but it somehow lands like backdoor insult."
			"The Compliment That Felt Like An Audit"
			290
			glitchText
		"Say 'thank you!' brightly and file the sting away for later." 0
		"Say, lightly, 'I looked good before, too!'" 1
		"Replay the sentence for the rest of the night." 2
		"Say 'thanks, I feel good these days' and let that be the whole sentence." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("They did not prepare a spreadsheet. You are disappointed in their inability to verify their claims.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -15 TAG_FAWN)
			Print("You smiled through the part that actually stung. Now your face hurts.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 10 TAG_FIGHT)
			Print("You corrected the record instead of just absorbing it. Mad respect.")
		)
		(case 2
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("One sentence got more airtime in your head than the whole party. It's all you remember.")
		)
		(case 3
			ApplyChoiceEffects(-5 0 10 TAG_SECURE)
			Print("You took the compliment at face value and moved on.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
