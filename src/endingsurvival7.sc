/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival7.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 7 (8 variants), matching CONTENT_ENDINGS[7] in js/content-endings.js. Flat Case Files indices 56-63 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL7_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding7)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("You're carrying more than you'd like to admit, and carrying it fine, for now." #title "The Long Fuse")
			(if(MarkCaseFile(56))
				Print("Case Files: The Long Fuse, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Nothing broke today. A few things bent. You're still counting that as a win." #title "Holding It Together, Mostly")
			(if(MarkCaseFile(57))
				Print("Case Files: Holding It Together, Mostly, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Not thriving. Not drowning. Actively, deliberately managing, which is its own quiet kind of work." #title "Managing")
			(if(MarkCaseFile(58))
				Print("Case Files: Managing, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Something in you is pulled tight and hasn't snapped. You've gotten used to the tightness." #title "The Working Tension")
			(if(MarkCaseFile(59))
				Print("Case Files: The Working Tension, filed." #title "New Case File")
			)
		)
		(case 4
			Print("The weight's real. You're still standing under it. That's not nothing." #title "Under Pressure, Upright")
			(if(MarkCaseFile(60))
				Print("Case Files: Under Pressure, Upright, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Not boiling. Not cold. Just holding at a low, steady heat that takes real effort to maintain." #title "Simmer Setting")
			(if(MarkCaseFile(61))
				Print("Case Files: Simmer Setting, filed." #title "New Case File")
			)
		)
		(case 6
			Print("This isn't a crisis. It's just a lot, held at a pace you can actually keep up." #title "The Sustainable Strain")
			(if(MarkCaseFile(62))
				Print("Case Files: The Sustainable Strain, filed." #title "New Case File")
			)
		)
		(case 7
			Print("You're wound tighter than you'd like. You're also, somehow, still getting things done." #title "Tightly Wound, Still Functional")
			(if(MarkCaseFile(63))
				Print("Case Files: Tightly Wound, Still Functional, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
