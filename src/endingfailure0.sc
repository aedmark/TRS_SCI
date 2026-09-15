/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingfailure0.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Failure ending pool (repression, 10 variants), matching CONTENT_FAILURE_ENDINGS.repression in js/content-endings.js. Flat Case Files indices 72-81 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGFAILURE0_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintFailureEnding0)
	(var variantIndex)
	= variantIndex Random(0 9)
	(switch(variantIndex)
		(case 0
			Print("Your repression hit 100%. The dam broke. You are currently sobbing in a supply closet." #title "Panic Attack")
			(if(MarkCaseFile(72))
				Print("Case Files: Panic Attack, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Repression maxed out the scale. Something that was supposed to stay buried came up all at once, in the worst possible meeting." #title "Tectonic Reset")
			(if(MarkCaseFile(73))
				Print("Case Files: Tectonic Reset, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Repression blown. You said the quiet part out loud. To the wrong person." #title "The Blowup")
			(if(MarkCaseFile(74))
				Print("Case Files: The Blowup, filed." #title "New Case File")
			)
		)
		(case 3
			Print("You held it in until you couldn't. Now everyone knows exactly how you feel, whether they asked or not." #title "System Overpressure")
			(if(MarkCaseFile(75))
				Print("Case Files: System Overpressure, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Repression hit 100%. You cried in the car for forty minutes before you could turn the key." #title "Full Meltdown")
			(if(MarkCaseFile(76))
				Print("Case Files: Full Meltdown, filed." #title "New Case File")
			)
		)
		(case 5
			Print("You reached capacity. The thing that finally broke you was, embarrassingly, very minor." #title "The Snap")
			(if(MarkCaseFile(77))
				Print("Case Files: The Snap, filed." #title "New Case File")
			)
		)
		(case 6
			Print("It wasn't the big thing. It was the small thing on top of the big thing. Repression is at zero now, mostly because you have none left to hold." #title "Delayed Detonation")
			(if(MarkCaseFile(78))
				Print("Case Files: Delayed Detonation, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Repression hit 100% in front of everybody. There is no version of Monday that fixes this." #title "Public Unraveling")
			(if(MarkCaseFile(79))
				Print("Case Files: Public Unraveling, filed." #title "New Case File")
			)
		)
		(case 8
			Print("You were just fine! You've been fine this whole time! You planned on being fine all day... \n\n\n You are not fine." #title "The Overflow")
			(if(MarkCaseFile(80))
				Print("Case Files: The Overflow, filed." #title "New Case File")
			)
		)
		(case 9
			Print("Repression maxed out and took the rest of your composure down with it." #title "Structural Failure")
			(if(MarkCaseFile(81))
				Print("Case Files: Structural Failure, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
