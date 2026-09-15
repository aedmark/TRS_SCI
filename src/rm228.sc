/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm228.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-work-events.js from
 js/events/work.js (WORK event 28).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 228)
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
(instance public rm228 of Rm
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
			= glitchText "Casually ask three more coworkers what they make."
		)
		= choice PrintChoices(
			"A spreadsheet gets shared by accident. Someone with two fewer years than you makes more."
			"The Salary You Found Out"
			290
			glitchText
		"Draft a furious email to HR and send it before you can think twice." 0
		"Decide you're probably not remembering your own worth correctly, and let it go." 1
		"Write down exactly what you'd ask for and schedule the actual conversation." 2
		"Close the spreadsheet, close the tab, and try hard not to think about the number." 3
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("You now know everyone's salary, yet still cannot grasp your own self-worth. Progress?")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(-15 -15 0 TAG_FIGHT)
			Print("It's out there now. There is no version of tomorrow's meeting that isn't about this.")
		)
		(case 1
			ApplyChoiceEffects(8 5 -12 TAG_FAWN)
			Print("You talked yourself out of being angry about something worth being angry about.")
		)
		(case 2
			ApplyChoiceEffects(-10 0 8 TAG_SECURE)
			Print("You turned a number in a spreadsheet into a plan.")
		)
		(case 3
			ApplyChoiceEffects(0 -5 -5 TAG_FLIGHT)
			Print("You filed it away instead of looking at it.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
