/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm002.sc
 The end-of-run room -- entered via (send gRoom:newRoom(ENDING_ROOM))
 from mechanisms.sc's EndTurn() once a stat hits a fatal threshold or
 gMaxTurns is reached. Re-evaluates the same thresholds (final stat
 values are still sitting in gRepression/gMask/gChild, untouched by the
 room transition) to pick which ending to print. Also hosts the two
 clickable office hotspots: filing cabinet (Case Files) and computer
 (start a new run).
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
(include "officetext.sh")
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
(use "user")
(use "casefiles")
(use "casefilecategory")
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
		// No neighbors -- dead-end ending room, no restart flow yet.
		north 0
		east 0
		south 0
		west 0
	)
	(method (init)
		(var playerNameBuf[PLAYER_NAME_BUF_LEN], playedByBuf[32])
		(super:init())
		(self:setScript(RoomScript))

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
		// ProgramControl() disables BOTH ego movement (canControl) and the
		// text parser's typed-input line (canInput) -- User.sc's handleEvent
		// only ever calls getInput()/Parse() when canInput is set. Turn
		// canInput back on here, alone, so "look computer" etc. works while
		// ego still can't be walked around. Safe to leave on: gProgramControl
		// (the flag Game:doit() would use to keep stomping this back to
		// FALSE every frame, Main.sc:340) is never set TRUE anywhere in this
		// codebase, so nothing re-disables it behind our back.
		(User:canInput(TRUE))
		// The parser's replies (see RoomScript). Never disposed, same as
		// TEXT_UI -- and never via DisposeScript(), which only takes script
		// numbers (see game.sh's TEXT_UI note).
		Load(rsTEXT TEXT_OFFICE)

		(self:printEnding())

		// "Played by <name>" -- matches the original's end-screen credit,
		// skipped entirely if blank (README: "Leave it blank and neither
		// format mentions it").
		GetPlayerName(@playerNameBuf)
		(if(StrLen(@playerNameBuf))
			Format(@playedByBuf "Played by %s" @playerNameBuf)
			Print(@playedByBuf #font gDefaultFont)
		)
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
(instance RoomScript of Script
	(properties)
	(method (openCaseFiles)
        (var choice)
        // Two-stage Load/Dispose -- see CaseFileCategory.sc's header for why
        // the menu and viewer scripts must never both be resident.
        Load(rsSCRIPT CASEFILES_SCRIPT)
        = choice ShowCaseFiles()
        DisposeScript(CASEFILES_SCRIPT)
        (if(choice)
            Load(rsSCRIPT CASEFILECATEGORY_SCRIPT)
            (if(== choice 1)
                ShowCaseFileCategory(CASEFILE_SURVIVAL_BASE CASEFILE_SURVIVAL_COUNT "Survival Endings")
            )
            (if(== choice 2)
                ShowCaseFileCategory(CASEFILE_FAILURE_BASE CASEFILE_FAILURE_COUNT "Failure Endings")
            )
            (if(== choice 3)
                ShowCaseFileCategory(CASEFILE_MECH_BASE CASEFILE_MECH_COUNT "Coping Mechanisms")
            )
            DisposeScript(CASEFILECATEGORY_SCRIPT)
        )
	)
	(method (handleEvent pEvent)
        (super:handleEvent(pEvent))
        // Filing cabinet -> Case Files viewer (also reachable by typing
        // "open cabinet", below). Nested ifs rather than one 5-term
        // and-chain -- no precedent in this codebase for and-chains longer
        // than 4.
        (if(not (send pEvent:claimed))
            (if(== (send pEvent:type) evMOUSEBUTTON)
                (if((>= (send pEvent:x) CABINET_X1) and (< (send pEvent:x) CABINET_X2))
                    (if((>= (send pEvent:y) CABINET_Y1) and (< (send pEvent:y) CABINET_Y2))
                        (send pEvent:claimed(TRUE))
                        (self:openCaseFiles())
                    )
                )
            )
        )
        // Computer -> starts a new run (a plain newRoom(), not the menu's
        // kernel-level RestartGame(); rm001.sc's init() does the actual
        // reset). No confirmation prompt -- only clickable once a run has
        // already ended, nothing to lose.
        (if(not (send pEvent:claimed))
            (if(== (send pEvent:type) evMOUSEBUTTON)
                (if((>= (send pEvent:x) COMPUTER_X1) and (< (send pEvent:x) COMPUTER_X2))
                    (if((>= (send pEvent:y) COMPUTER_Y1) and (< (send pEvent:y) COMPUTER_Y2))
                        (send pEvent:claimed(TRUE))
                        (send gRoom:newRoom(INITROOMS_SCRIPT))
                    )
                )
            )
        )
        // Parser-driven office flavor text -- this room only (not during event
        // cards), pure atmosphere with no state changes; "open cabinet" is the
        // one verb that does something, and it's the same Case Files viewer
        // a click already opens. The text itself lives in TEXT_OFFICE
        // (text/office.txt -> text.002 via tools/gen-text.js), not string
        // literals: this room stays resident under the Case Files viewer,
        // whose heap margin is the tightest in the game.
        //
        // Each check is ONE complete, self-contained "verb-group/noun"
        // pattern tried as a flat sequence of early-return ifs -- NOT the
        // docs' split Said('look>') + Said('/noun') idiom or '[/!*]', neither
        // of which works in this build. A failed Said() doesn't consume
        // anything, so no '>' is needed; order only matters in that specific
        // nouns must come before a verb's '/*' wildcard, and the wildcard
        // before the bare verb. Multiple nouns are separate Said() calls
        // joined by `or` (',' is only proven between verbs). Said('look/car')
        // also matches "look in car", so "look at X" needs no pattern of its
        // own.
        (if(not (send pEvent:claimed))
            (if((Said('look,examine,x/computer')) or (Said('look,examine,x/monitor')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_COMPUTER)
                return
            )
            (if(Said('look,examine,x/cabinet'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_CABINET)
                return
            )
            (if(Said('look,examine,x/chair'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_CHAIR)
                return
            )
            (if(Said('look,examine,x/desk'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_DESK)
                return
            )
            (if(Said('look,examine,x/clock'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_CLOCK)
                return
            )
            (if(Said('look,examine,x/door'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_DOOR)
                return
            )
            (if((Said('look,examine,x/mirror')) or (Said('look,examine,x/me')) or (Said('look,examine,x/self')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_SELF)
                return
            )
            (if(Said('look,examine,x/window'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_WINDOW)
                return
            )
            (if((Said('look,examine,x/tree')) or (Said('look,examine,x/plant')) or (Said('look,examine,x/sky')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_OUTSIDE)
                return
            )
            (if(Said('look,examine,x/lamp'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_LAMP)
                return
            )
            (if((Said('look,examine,x/book')) or (Said('look,examine,x/shelf')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_BOOKS)
                return
            )
            (if(Said('look,examine,x/keyboard'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_KEYBOARD)
                return
            )
            (if(Said('look,examine,x/drawer'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_DRAWERS)
                return
            )
            (if(Said('look,examine,x/floor'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_FLOOR)
                return
            )
            (if((Said('look,examine,x/wall')) or (Said('look,examine,x/ceiling')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_WALLS)
                return
            )
            (if(Said('look,examine,x/room'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_ROOM)
                return
            )
            (if(Said('look,examine,x/*'))
                // A recognized noun, just not one with its own line yet.
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_OTHER)
                return
            )
            (if(Said('look,examine,x'))
                // No noun at all.
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_ROOM)
                return
            )
            (if(Said('open/cabinet'))
                (send pEvent:claimed(TRUE))
                (self:openCaseFiles())
                return
            )
            (if(Said('open/drawer'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_OPEN_DRAWER)
                return
            )
            // The vocab's "leave" group also holds exit and walk.
            (if((Said('open/door')) or (Said('leave/*')) or (Said('leave')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LEAVE)
                return
            )
            // The "sit" group also holds rest, lie and sleep.
            (if((Said('sit/chair')) or (Said('sit')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_SIT)
                return
            )
            // The "turn" group also holds press, push, move and friends.
            (if(Said('turn/lamp'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LAMP_SWITCH)
                return
            )
            // "take a breath" must come before take's wildcard below.
            (if((Said('breathe')) or (Said('take/breath')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_BREATHE)
                return
            )
            (if(Said('wait'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_WAIT)
                return
            )
            (if((Said('listen/*')) or (Said('listen')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LISTEN)
                return
            )
            (if((Said('smell/*')) or (Said('smell')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_SMELL)
                return
            )
            (if(Said('take/*'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_TAKE)
                return
            )
            (if((Said('hug/me')) or (Said('hug/self')) or (Said('hug')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_HUG_SELF)
                return
            )
            (if((Said('talk/*')) or (Said('talk')))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_TALK)
                return
            )
            (if(Said('help'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_HELP)
                return
            )
            // Parsed fine but matched nothing above -- answer in the game's
            // own voice instead of falling through to Game:pragmaFail's
            // stock "You've left me responseless." (This also pre-empts
            // Main.sc's template Said('hi'); the parser is only live in
            // this room anyway.)
            (if(== (send pEvent:type) evSAID)
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_FALLBACK)
            )
        )
 	)
)
/******************************************************************************/
