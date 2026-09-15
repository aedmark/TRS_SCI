/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingfailure2.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Failure ending pool (child, 10 variants), matching CONTENT_FAILURE_ENDINGS.child in js/content-endings.js. Flat Case Files indices 92-101 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGFAILURE2_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintFailureEnding2)
	(var variantIndex)
	= variantIndex Random(0 9)
	(switch(variantIndex)
		(case 0
			Print("Your inner child hit 0%. You are now a hollow shell operating purely on muscle memory.\n\n You feel nothing." #title "Total Disassociation")
			(if(MarkCaseFile(92))
				Print("Case Files: Total Disassociation, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Inner Child hit 0%. You drove home and don't remember any of it." #title "Autopilot Engaged")
			(if(MarkCaseFile(93))
				Print("Case Files: Autopilot Engaged, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Inner Child bottomed out. You're going through the motions, and the motions are all that's left." #title "The Hollow")
			(if(MarkCaseFile(94))
				Print("Case Files: The Hollow, filed." #title "New Case File")
			)
		)
		(case 3
			Print("Inner Child hit zero. Someone asked how you were and you answered before you'd actually heard the question." #title "Nobody Home")
			(if(MarkCaseFile(95))
				Print("Case Files: Nobody Home, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Inner Child hit 0%. Nothing feels good. Nothing feels bad.\n\nNothing feels at all." #title "Flatline")
			(if(MarkCaseFile(96))
				Print("Case Files: Flatline, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Inner Child bottomed out. You are functioning perfectly and feeling absolutely no joy from it." #title "Muscle Memory Only")
			(if(MarkCaseFile(97))
				Print("Case Files: Muscle Memory Only, filed." #title "New Case File")
			)
		)
		(case 6
			Print("Inner Child hit zero. There's a version of you still doing the tasks. You're not entirely sure where the rest of you went." #title "The Static")
			(if(MarkCaseFile(98))
				Print("Case Files: The Static, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Inner Child hit 0%. You're present in the room in the way furniture is also present." #title "Checked Out")
			(if(MarkCaseFile(99))
				Print("Case Files: Checked Out, filed." #title "New Case File")
			)
		)
		(case 8
			Print("Inner Child bottomed out somewhere between one task and the next. You didn't notice it happen. That's the part that should worry you." #title "The Long Blink")
			(if(MarkCaseFile(100))
				Print("Case Files: The Long Blink, filed." #title "New Case File")
			)
		)
		(case 9
			Print("Inner Child hit zero. You are technically fine. Technically is doing a lot of work in that sentence." #title "Running on Empty")
			(if(MarkCaseFile(101))
				Print("Case Files: Running on Empty, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
