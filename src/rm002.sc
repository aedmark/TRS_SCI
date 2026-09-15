/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm002.sc
 The end-of-run room -- entered via (send gRoom:newRoom(ENDING_ROOM))
 from mechanisms.sc's EndTurn() once a stat hits a fatal threshold or
 gMaxTurns is reached. Re-evaluates the same thresholds (final stat
 values are still sitting in gRepression/gMask/gChild, untouched by the
 room transition) to pick which ending to print, then hands off to
 the office (rm003.sc, OFFICE_ROOM), where the hotspots and the text
 parser live.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script ENDING_ROOM)
/******************************************************************************/
(use "main")
(use "controls")
(use "cycle")
(use "game")
(use "feature")
(use "obj")
(use "inv")
(use "casefiles")
(use "mechanisms")
(use "endingsurvival0")
(use "endingsurvival1")
(use "endingsurvival2")
(use "endingsurvival3")
(use "endingsurvival4")
(use "endingsurvival5")
(use "endingsurvival6")
(use "endingsurvival7")
(use "endingsurvival8")
(use "endingfailure0")
(use "endingfailure1")
(use "endingfailure2")
/******************************************************************************/
(instance public rm002 of Rm
	(properties
		picture 1
		// No neighbors -- init() hands off to the office itself.
		north 0
		east 0
		south 0
		west 0
	)
	(method (init)
		(var playerNameBuf[PLAYER_NAME_BUF_LEN], playedByBuf[32])
		(super:init())

		(send gEgo:
			posn(150 130)
			loop(1)
		)

		SetUpEgo()
		(send gEgo:init())
		// Nothing to walk around/interact with -- take back the control
		// SetUpEgo() grants and hide ego outright (same as TitleScreen.sc).
		ProgramControl()
		(send gEgo:hide())
		(self:printEnding())

		// "Played by <name>" -- matches the original's end-screen credit,
		// skipped entirely if blank (README: "Leave it blank and neither
		// format mentions it").
		GetPlayerName(@playerNameBuf)
		(if(StrLen(@playerNameBuf))
			Format(@playedByBuf "Played by %s" @playerNameBuf)
			Print(@playedByBuf #font gDefaultFont)
		)

		// Everything else -- the hotspots, the parser, Case Files, the
		// next session -- happens in the office. Same picture, so the
		// hand-off is seamless.
		(send gRoom:newRoom(OFFICE_ROOM))
	)
	(method (printEnding)
		// Decides which ending POOL applies; each PrintFailureEndingN()/
		// PrintSurvivalEndingN() (one script per pool) picks a random
		// variant within it and marks the matching Case Files slot
		// itself. Every call Load/DisposeScript-wraps both the pool
		// script and CASEFILES_SCRIPT (MarkCaseFile) -- neither should
		// stay resident, since the clickable computer hotspot makes it
		// easy to rack up many runs/endings in one session.
		(if(>= gRepression 100)
			Load(rsSCRIPT CASEFILES_SCRIPT)
			Load(rsSCRIPT ENDINGFAILURE0_SCRIPT)
			PrintFailureEnding0()
			DisposeScript(ENDINGFAILURE0_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if(<= gMask 0)
			Load(rsSCRIPT CASEFILES_SCRIPT)
			Load(rsSCRIPT ENDINGFAILURE1_SCRIPT)
			PrintFailureEnding1()
			DisposeScript(ENDINGFAILURE1_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if(<= gChild 0)
			Load(rsSCRIPT CASEFILES_SCRIPT)
			Load(rsSCRIPT ENDINGFAILURE2_SCRIPT)
			PrintFailureEnding2()
			DisposeScript(ENDINGFAILURE2_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(self:printSurvivalEnding())
	)
	(method (printSurvivalEnding)
		// A standard-session survival permanently unlocks Extended
		// Therapy (matches checkGameEnd()); an Extended Therapy run
		// surviving doesn't re-trigger it. One Load(CASEFILES_SCRIPT)
		// covers both UnlockNgPlus() and whichever pool fires below,
		// since they always happen together here.
		Load(rsSCRIPT CASEFILES_SCRIPT)
		(if(not gHardMode)
			UnlockNgPlus()
		)
		// Same condition table/order as CONTENT_ENDINGS (first match
		// wins) -- a flat sequence of early-return ifs, not chained
		// else-if (no precedent in this codebase for 3+-branch chaining).
		(if(>= gRepression 70)
			Load(rsSCRIPT ENDINGSURVIVAL0_SCRIPT)
			PrintSurvivalEnding0()
			DisposeScript(ENDINGSURVIVAL0_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((>= gMask 85) and (<= gChild 25))
			Load(rsSCRIPT ENDINGSURVIVAL1_SCRIPT)
			PrintSurvivalEnding1()
			DisposeScript(ENDINGSURVIVAL1_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((>= gChild 75) and (<= gMask 40))
			Load(rsSCRIPT ENDINGSURVIVAL2_SCRIPT)
			PrintSurvivalEnding2()
			DisposeScript(ENDINGSURVIVAL2_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((<= gMask 25) and (>= gChild 25))
			Load(rsSCRIPT ENDINGSURVIVAL3_SCRIPT)
			PrintSurvivalEnding3()
			DisposeScript(ENDINGSURVIVAL3_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((>= gMask 41) and (<= gChild 25))
			Load(rsSCRIPT ENDINGSURVIVAL4_SCRIPT)
			PrintSurvivalEnding4()
			DisposeScript(ENDINGSURVIVAL4_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((<= gRepression 30) and (>= gMask 40) and (<= gMask 70) and (>= gChild 40) and (<= gChild 70))
			Load(rsSCRIPT ENDINGSURVIVAL5_SCRIPT)
			PrintSurvivalEnding5()
			DisposeScript(ENDINGSURVIVAL5_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((<= gRepression 30) and (>= gMask 60) and (>= gChild 60))
			Load(rsSCRIPT ENDINGSURVIVAL6_SCRIPT)
			PrintSurvivalEnding6()
			DisposeScript(ENDINGSURVIVAL6_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		(if((>= gRepression 31) and (<= gRepression 69) and (>= gMask 40) and (>= gChild 40))
			Load(rsSCRIPT ENDINGSURVIVAL7_SCRIPT)
			PrintSurvivalEnding7()
			DisposeScript(ENDINGSURVIVAL7_SCRIPT)
			DisposeScript(CASEFILES_SCRIPT)
			return
		)
		Load(rsSCRIPT ENDINGSURVIVAL8_SCRIPT)
		PrintSurvivalEnding8()
		DisposeScript(ENDINGSURVIVAL8_SCRIPT)
		DisposeScript(CASEFILES_SCRIPT)
	)
)
/******************************************************************************/
