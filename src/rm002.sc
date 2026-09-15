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
	(method (handleEvent pEvent)
        (var choice)
        (super:handleEvent(pEvent))
        // Filing cabinet -> Case Files viewer. Nested ifs rather than one
        // 5-term and-chain -- no precedent in this codebase for and-chains
        // longer than 4.
        (if(not (send pEvent:claimed))
            (if(== (send pEvent:type) evMOUSEBUTTON)
                (if((>= (send pEvent:x) CABINET_X1) and (< (send pEvent:x) CABINET_X2))
                    (if((>= (send pEvent:y) CABINET_Y1) and (< (send pEvent:y) CABINET_Y2))
                        (send pEvent:claimed(TRUE))
                        // Two-stage Load/Dispose -- see CaseFileCategory.sc's
                        // header for why the menu and viewer scripts must
                        // never both be resident.
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
        // Parser-driven "examine" flavor text -- look/examine/x only, this
        // room only (not during event cards). Pure atmosphere, no state
        // changes. Flat early-return ifs -- see printEnding() above and
        // CaseFiles.sc's ShowCaseFiles() header for why this codebase never
        // nests (if...)(else...) more than one level deep.
        //
        // Each check below is ONE complete, self-contained "verb-group/noun"
        // pattern -- not the split "outer non-claiming Said('look>'), then
        // separate leading-slash Said('/noun') continuation" idiom from SCI
        // Companion's own docs. That split idiom's OTHER piece ('[/!*]')
        // already turned out not to work in this build (see prior revision
        // of this comment, kept in git history), and vocab/word-class was
        // ruled out directly by testing (nouns already existed correctly
        // classified) -- so the remaining suspect was the '>' continuation
        // mechanic itself. Per the kernel docs, a FAILED Said() doesn't
        // consume/claim anything ("if a match is made, the words are used
        // up" -- only on success), so independent complete checks tried in
        // sequence don't need '>' at all; only a successful match needs
        // claimed(TRUE), and `return` right after ends the search. This
        // removes every non-literal Said() idiom this feature was built on,
        // down to just ',' (OR) and '/' (part separator) used in the single
        // most basic way the docs define them.
        (if(not (send pEvent:claimed))
            (if(Said('look,examine,x/computer'))
                (send pEvent:claimed(TRUE))
                Print("The screen that ends a session and starts the next one. It has never once asked if you're ready.")
                return
            )
            (if(Said('look,examine,x/cabinet'))
                (send pEvent:claimed(TRUE))
                Print("Every ending you've ever had, filed and alphabetized. It doesn't judge. It just remembers.")
                return
            )
            (if(Said('look,examine,x/chair'))
                (send pEvent:claimed(TRUE))
                Print("Still warm. You just got up from it.")
                return
            )
            (if(Said('look,examine,x/desk'))
                (send pEvent:claimed(TRUE))
                Print("Bare, except for whatever you didn't leave behind.")
                return
            )
            (if(Said('look,examine,x/clock'))
                (send pEvent:claimed(TRUE))
                Print("Running. It was running before you got here, too.")
                return
            )
            (if(Said('look,examine,x/door'))
                (send pEvent:claimed(TRUE))
                Print("Closed. Nothing on the other side of it yet.")
                return
            )
            (if((Said('look,examine,x/mirror')) or (Said('look,examine,x/me')) or (Said('look,examine,x/self')))
                (send pEvent:claimed(TRUE))
                Print("You look like someone who just finished something. You're not sure what.")
                return
            )
            (if(Said('look,examine,x/*'))
                // A recognized noun, just not one with its own line yet.
                (send pEvent:claimed(TRUE))
                Print("You don't see anything special about that.")
                return
            )
            (if(Said('look,examine,x'))
                // No noun at all -- every specific check AND the wildcard
                // above failed to match.
                (send pEvent:claimed(TRUE))
                Print("A cabinet, a computer, a chair, a desk, a clock that hasn't stopped, and a door back out into the rest of your life. That's the whole office. That's supposed to be enough.")
            )
        )
 	)
)
/******************************************************************************/
