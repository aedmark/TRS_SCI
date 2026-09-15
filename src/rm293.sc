/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm293.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 27).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 293)
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
(instance public rm293 of Rm
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
			= glitchText "Look into whether you could realistically drive three states in one weekend."
		)
		= choice PrintChoices(
			"A close friend's wedding, three states away, right when your budget has zero room for flights and a hotel."
			"The Wedding Invite You Can't Afford to Attend"
			290
			glitchText
		"RSVP yes and figure out the money somehow, some way you haven't found yet." 0
		"Don't respond to the invite for as long as you possibly can." 1
		"Call your friend and just tell them the truth about the money." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You could not. You looked anyway.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(8 10 -12 TAG_FAWN)
			Print("You said yes to the invitation and no, quietly, to your own budget.")
		)
		(case 1
			ApplyChoiceEffects(-5 -10 0 TAG_FLIGHT)
			Print("The deadline is still coming. It doesn't care that you didn't look at it.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("The honest version of 'I can't' turned out to cost you less than the performance of 'I'll try.'")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
