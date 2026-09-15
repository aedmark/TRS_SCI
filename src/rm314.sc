/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm314.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-self-events.js from
 js/events/self.js (SELF event 15).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 314)
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
(instance public rm314 of Rm
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
			= glitchText "Write them a postcard. Mail it to yourself, unironically."
		)
		= choice PrintChoices(
			"You try to picture yourself a decade back and feel a strange mix of tenderness and secondhand embarrassment."
			"The You From Ten Years Ago"
			290
			glitchText
		"Cringe hard and mentally list everything that version got wrong." 0
		"Change the subject in your own head before it goes anywhere real." 1
		"Send a little compassion backward. They didn't know what you know now." 2
		"Close the old photo album and don't open that folder again for a while." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("It arrives in three days. Past-you would be delighted it worked.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(10 0 -15 TAG_FIGHT)
			Print("You put a decade-old version of yourself on trial.")
		)
		(case 1
			ApplyChoiceEffects(15 0 -10 TAG_FREEZE)
			Print("You closed the door on a version of you who was just trying, too.")
		)
		(case 2
			ApplyChoiceEffects(-15 0 15 TAG_SECURE)
			Print("You forgave a person for not having information they didn't learn yet.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You put ten years back in a drawer.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
