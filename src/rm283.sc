/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm283.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 17).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 283)
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
(instance public rm283 of Rm
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
			= glitchText "Send the group a full, dramatic exit statement the next morning."
		)
		= choice PrintChoices(
			"You said you were tired. You weren't tired. You just needed to be somewhere with fewer people in it."
			"The Party You Left Early"
			290
			glitchText
		"Send an apologetic follow-up text explaining yourself." 0
		"Lie awake replaying whether anyone noticed you'd gone." 1
		"Let leaving early just be a thing you did. No debrief required." 2
		"Tell the group chat directly: 'I left because it was too much. That's allowed.'" 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It reads like a press release nobody asked for.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 10 -10 TAG_FAWN)
			Print("You justified a boundary nobody actually questioned.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -15 TAG_FREEZE)
			Print("You audited an exit that was, to everyone else, unremarkable and totally fine.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("You left when you needed to and didn't file a report about it. Irish Goodbye FTW.")
		)
		(case 3
			ApplyChoiceEffects(-8 -15 5 TAG_FIGHT)
			Print("You defended a boundary nobody was actually attacking.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
