/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm297.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 31).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 297)
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
(instance public rm297 of Rm
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
			= glitchText "Try to convince everyone your account got hacked."
		)
		= choice PrintChoices(
			"You mean to vent about your coworker to one friend. You send it to the group chat that includes that coworker."
			"The Wrong Group Chat Text"
			290
			glitchText
		"Stare at the message, unsent-but-sent, for a full minute before doing anything." 0
		"Immediately send eight apology messages in a row." 1
		"Send one clear apology, own it, and stop typing." 2
		"Call the coworker directly and say the thing to their face, badly, right now." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Nobody believes this. You did not really expect them to.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -8 TAG_FREEZE)
			Print("Time did not undo it. It rarely does.")
		)
		(case 1
			ApplyChoiceEffects(8 8 -10 TAG_FAWN)
			Print("Eight messages later, everyone has now read about the mistake three extra times.")
		)
		(case 2
			ApplyChoiceEffects(-10 5 8 TAG_SECURE)
			Print("One honest sentence did more than eight nervous ones would have. The coworker actually admits they deserved it.")
		)
		(case 3
			ApplyChoiceEffects(-8 -18 5 TAG_FIGHT)
			Print("You turned a screenshot into an actual conversation. Braver, and much worse, in real time.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
