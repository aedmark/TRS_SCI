/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 endingsurvival1.sc
 GENERATED FILE — do not hand-edit. Produced by tools/gen-endings.js from
 js/content-endings.js. Re-run that script after editing the source data.

 Survival ending pool 1 (8 variants), matching CONTENT_ENDINGS[1] in js/content-endings.js. Flat Case Files indices 8-15 -- see game.sh.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDINGSURVIVAL1_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiles")
/******************************************************************************/
(procedure public (PrintSurvivalEnding1)
	(var variantIndex)
	= variantIndex Random(0 7)
	(switch(variantIndex)
		(case 0
			Print("Nobody has seen the real you in years, including you." #title "The Performer")
			(if(MarkCaseFile(8))
				Print("Case Files: The Performer, filed." #title "New Case File")
			)
		)
		(case 1
			Print("You hit every mark, said every right thing, and couldn't tell anyone what it cost until just now." #title "Note-Perfect")
			(if(MarkCaseFile(9))
				Print("Case Files: Note-Perfect, filed." #title "New Case File")
			)
		)
		(case 2
			Print("You've played the part of yourself so long the understudy might genuinely be better at it by now." #title "The Understudy")
			(if(MarkCaseFile(10))
				Print("Case Files: The Understudy, filed." #title "New Case File")
			)
		)
		(case 3
			Print("The performance was seamless. Nobody asks what happens after the curtain, including you." #title "Flawless Execution")
			(if(MarkCaseFile(11))
				Print("Case Files: Flawless Execution, filed." #title "New Case File")
			)
		)
		(case 4
			Print("You do a very good impression of someone who's fine. Most days, even you almost believe it." #title "The Convincing Copy")
			(if(MarkCaseFile(12))
				Print("Case Files: The Convincing Copy, filed." #title "New Case File")
			)
		)
		(case 5
			Print("Everyone clapped. You bowed. Somewhere backstage, something's been waiting a long time for its cue." #title "Standing Ovation")
			(if(MarkCaseFile(13))
				Print("Case Files: Standing Ovation, filed." #title "New Case File")
			)
		)
		(case 6
			Print("It fits so well now you forget you put it on this morning. Or any morning." #title "The Mask That Fits")
			(if(MarkCaseFile(14))
				Print("Case Files: The Mask That Fits, filed." #title "New Case File")
			)
		)
		(case 7
			Print("Smooth all the way down, as far as anyone can tell, including the part of you that used to check." #title "All Surface, No Depth Charge")
			(if(MarkCaseFile(15))
				Print("Case Files: All Surface, No Depth Charge, filed." #title "New Case File")
			)
		)
	)
)
/******************************************************************************/
