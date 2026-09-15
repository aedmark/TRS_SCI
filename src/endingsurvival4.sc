/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival4.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 4 (8 variants), matching CONTENT_ENDINGS[4] in js/content-endings.js. Flat Case Files indices 32-39 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL4_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding4)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("You looked fine all day. You have no real idea what was actually fueling that." #title "Coasting on Empty")
			(if(MarkCaseFile(32))
				Print("Case Files: Coasting on Empty, filed." #title "New Case File")
			)
		)
		(case 1
			Print("From the outside, a completely normal day. On the inside, mostly static." #title "Running on Fumes and Good Posture")
			(if(MarkCaseFile(33))
				Print("Case Files: Running on Fumes and Good Posture, filed." #title "New Case File")
			)
		)
		(case 2
			Print("You showed up, said the right things, looked put-together. Nothing underneath was doing the same." #title "Presentable")
			(if(MarkCaseFile(34))
				Print("Case Files: Presentable, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Everything worked. Nothing felt like anything. Both of those are true at once." #title "The Functional Hollow")
			(if(MarkCaseFile(35))
				Print("Case Files: The Functional Hollow, filed." #title "New Case File")
			)
		)
		(case 4
			Print("You got through today on muscle memory and social competence. The rest of you clocked out hours ago." #title "Autopilot, Well-Dressed")
			(if(MarkCaseFile(36))
				Print("Case Files: Autopilot, Well-Dressed, filed." #title "New Case File")
			)
		)
		(case 5
			Print("You were pleasant to everyone today. You couldn't say which of your own feelings, if any, showed up." #title "Numb But Nice About It")
			(if(MarkCaseFile(37))
				Print("Case Files: Numb But Nice About It, filed." #title "New Case File")
			)
		)
		(case 6
			Print("Nothing cracked today. Nothing underneath the surface was really there to crack, either." #title "The Smooth Surface")
			(if(MarkCaseFile(38))
				Print("Case Files: The Smooth Surface, filed." #title "New Case File")
			)
		)
		(case 7
			Print("You kept the shape of a normal day. Whatever's supposed to fill that shape took the day off." #title "Holding the Shape")
			(if(MarkCaseFile(39))
				Print("Case Files: Holding the Shape, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
