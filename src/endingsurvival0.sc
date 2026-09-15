/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival0.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 0 (8 variants), matching CONTENT_ENDINGS[0] in js/content-endings.js. Flat Case Files indices 0-7 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL0_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding0)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("You didn't explode. You just got very, very good at ticking." #title "The Powder Keg")
			(if(MarkCaseFile(0))
				Print("Case Files: The Powder Keg, filed." #title "New Case File")
			)
		)
		(case 1
			Print("You didn't self-destruct, yet. Today just isn't over anywhere else, either." #title "Holding Pattern")
			(if(MarkCaseFile(1))
				Print("Case Files: Holding Pattern, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Something in you is very close to the surface. You made it to the deadline before it did." #title "Pressure Cooker")
			(if(MarkCaseFile(2))
				Print("Case Files: Pressure Cooker, filed." #title "New Case File")
			)
		)
		(case 3
			Print("You survived by holding something in the whole way through. Your shoulders will remember this tomorrow." #title "The Held Breath")
			(if(MarkCaseFile(3))
				Print("Case Files: The Held Breath, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Not boiling over. Not cooled down either. Just holding at a temperature (that isn't sustainable)." #title "Simmering")
			(if(MarkCaseFile(4))
				Print("Case Files: Simmering, filed." #title "New Case File")
			)
		)
		(case 5
			Print("You made it through; white-knuckled. Nobody else could tell. Your jaw could." #title "The Clenched Jaw Ending")
			(if(MarkCaseFile(5))
				Print("Case Files: The Clenched Jaw Ending, filed." #title "New Case File")
			)
		)
		(case 6
			Print("You crossed the finish line still overheating. The engine didn't seize. It was a close one, though." #title "Running Hot")
			(if(MarkCaseFile(6))
				Print("Case Files: Running Hot, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Whatever's in there is still in there." #title "Barely Contained")
			(if(MarkCaseFile(7))
				Print("Case Files: Barely Contained, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
