/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionsSurvival1.sc
 GENERATED FILE — do not hand-edit. Produced by
 tools/gen-casefile-descriptions.js from js/content-endings.js and
 js/content-mechanisms.js.

 Survival ending (pool 1) descriptions for the Case Files viewer's "View" detail
 screen, flat indices 8-15 (game.sh's index scheme).
 Load/DisposeScript-scoped -- only needed while this category's
 screen is open.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONS_SURVIVAL1_SCRIPT)
/******************************************************************************/
(procedure public (CaseFileDescriptionSurvival1 index)
	(switch(index)
		(case 8 return("Nobody has seen the real you in years, including you."))
		(case 9 return("You hit every mark, said every right thing, and couldn't tell anyone what it cost until just now."))
		(case 10 return("You've played the part of yourself so long the understudy might genuinely be better at it by now."))
		(case 11 return("The performance was seamless. Nobody asks what happens after the curtain, including you."))
		(case 12 return("You do a very good impression of someone who's fine. Most days, even you almost believe it."))
		(case 13 return("Everyone clapped. You bowed. Somewhere backstage, something's been waiting a long time for its cue."))
		(case 14 return("It fits so well now you forget you put it on this morning. Or any morning."))
		(case 15 return("Smooth all the way down, as far as anyone can tell, including the part of you that used to check."))
	)
	return("")
)
/******************************************************************************/
