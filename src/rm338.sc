/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm338.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-body-events.js from
 js/events/body.js (BODY event 6).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 338)
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
(instance public rm338 of Rm
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
			= glitchText "Have a coworker read it out loud to you instead."
		)
		= choice PrintChoices(
			"An email notification appears. Your stomach drops before your eyes even finish reading the subject line."
			"The Stomach Drop"
			290
			glitchText
		"Open it immediately, brace for impact." 0
		"Let it sit unread while your stomach keeps dropping anyway." 1
		"Take one breath, then open it at your own pace." 2
		"Reply before finishing the email, already annoyed at whatever it says." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Outsourcing Dread: an underrated coping strategy.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -5 TAG_FLIGHT)
			Print("You ran toward the thing that scared you. Godspeed.")
		)
		(case 1
			ApplyChoiceEffects(10 0 -10 TAG_FREEZE)
			Print("You delayed the information, not the feeling. Then you dry heave into the wastebasket.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 5 TAG_SECURE)
			Print("You let your body catch up before you made a decision.")
		)
		(case 3
			ApplyChoiceEffects(-10 -10 0 TAG_FIGHT)
			Print("You answered a feeling, not the actual email.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
