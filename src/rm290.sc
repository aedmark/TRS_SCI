/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm290.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-social-events.js from
 js/events/social.js (SOCIAL event 24).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 290)
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
(instance public rm290 of Rm
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
			= glitchText "Reply via a voicemail of your own, without listening to theirs first."
		)
		= choice PrintChoices(
			"A friend called instead of texting, which never happens. The voicemail icon has been sitting there for two hours."
			"The Voicemail You Haven't Listened To"
			290
			glitchText
		"Let it sit. Voicemails are for people who don't need you to reply fast." 0
		"Call back immediately, bracing yourself for bad news that might not be there." 1
		"Listen to it first. Then respond to what's actually there." 2
		"Delete it unheard and text 'sorry, missed your call!' instead." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Two unheard voicemails now orbit each other, unopened, forever.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("An unopened voicemail can hold a lot of imagined weight.")
		)
		(case 1
			ApplyChoiceEffects(5 5 -10 TAG_FIGHT)
			Print("You armored up for a threat you hadn't confirmed yet.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 10 TAG_SECURE)
			Print("The message turned out to be smaller than the dread around it.")
		)
		(case 3
			ApplyChoiceEffects(-5 -5 -5 TAG_FLIGHT)
			Print("You skipped the message entirely and called it handled.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
