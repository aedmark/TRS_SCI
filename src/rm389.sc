/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm389.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-public-events.js from
 js/events/public.js (PUBLIC event 25).

 One room per event -- this room IS the event: shows its PrintChoices
 dialog, applies the effects, then hands off via EndTurn() to the next
 room. No custom RoomScript needed (ego is hidden/program-controlled).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 389)
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
(instance public rm389 of Rm
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
			= glitchText "Take a bow, as if the drop were a rehearsed part of the show."
		)
		= choice PrintChoices(
			"It hit the floor loudly and rolled somewhere. Multiple strangers definitely saw. You have to decide how big a deal this is."
			"The Thing You Dropped In Public"
			290
			glitchText
		"Laugh it off way harder than it deserves so no one thinks you're bothered." 0
		"Grab it fast and pretend it never happened at all." 1
		"Pick it up, shrug, and move on without narrating it." 2
		)
		(if(== choice GLITCH_CHOICE)
			ApplyGlitch("Someone actually clapped. You are now a performance artist.")
		)(else
			(switch(choice)
		(case 0
			ApplyChoiceEffects(5 8 -5 TAG_FAWN)
			Print("You performed 'unbothered' at a volume the moment didn't actually require.")
		)
		(case 1
			ApplyChoiceEffects(-5 -5 0 TAG_FLIGHT)
			Print("You erased a thirty-cent incident with the urgency of a crime scene.")
		)
		(case 2
			ApplyChoiceEffects(-8 0 8 TAG_SECURE)
			Print("A thing fell. You picked it up.")
		)
			)
		)
		EndTurn()
	)
)
/******************************************************************************/
