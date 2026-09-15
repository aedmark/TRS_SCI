/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingfailure1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Failure ending pool (mask, 10 variants), matching CONTENT_FAILURE_ENDINGS.mask in js/content-endings.js. Flat Case Files indices 82-91 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGFAILURE1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintFailureEnding1)
	(var variantIndex)
	= variantIndex Random(0 9)
	(switch(variantIndex)
		(case 0
			Print("Your mask dropped to 0%. You finally said exactly what you thought. You are now unemployed and friendless, but free." #title "Social Exile")
			(if(MarkCaseFile(82))
				Print("Case Files: Social Exile, filed." #title "New Case File")
			)
		)
		(case 1
			Print("Social Mask hit 0%. You said what you actually thought, out loud, in a group chat with your entire extended family." #title "The Unmasking")
			(if(MarkCaseFile(83))
				Print("Case Files: The Unmasking, filed." #title "New Case File")
			)
		)
		(case 2
			Print("Mask hit zero. Turns out the truth doesn't need permission to leave your mouth." #title "Honesty, Uninvited")
			(if(MarkCaseFile(84))
				Print("Case Files: Honesty, Uninvited, filed." #title "New Case File")
			)
		)
		(case 3
			Print("You ran out of mask exactly when someone asked how you were and they got the real answer." #title "No Filter Left")
			(if(MarkCaseFile(85))
				Print("Case Files: No Filter Left, filed." #title "New Case File")
			)
		)
		(case 4
			Print("Social Mask bottomed out. Everyone can now see exactly what you've been holding back, and they have thoughts." #title "The Reveal")
			(if(MarkCaseFile(86))
				Print("Case Files: The Reveal, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Mask hit 0%. You told your boss what you actually think of the quarterly review. It felt incredible for six seconds." #title "Radical Candor (Involuntary)")
			(if(MarkCaseFile(87))
				Print("Case Files: Radical Candor (Involuntary), filed." #title "New Case File")
			)
		)
		(case 6
			Print("You stopped performing how 'fine' you are, and the room noticed immediately." #title "Exiled")
			(if(MarkCaseFile(88))
				Print("Case Files: Exiled, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Mask hit 0% at the worst possible time in the company meeting. At least it's memorable. You try to tell yourself they were laughing *with* you." #title "The Real You, Unscheduled")
			(if(MarkCaseFile(89))
				Print("Case Files: The Real You, Unscheduled, filed." #title "New Case File")
			)
		)
		(case 8
			Print("You said the true thing instead of the nice thing. The silence afterward was very loud (and awkward)." #title "Social Combustion")
			(if(MarkCaseFile(90))
				Print("Case Files: Social Combustion, filed." #title "New Case File")
			)
		)
		(case 9
			Print("Mask dropped to zero. You are now saying things out loud that used to just be inside thoughts." #title "Unfiltered")
			(if(MarkCaseFile(91))
				Print("Case Files: Unfiltered, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
