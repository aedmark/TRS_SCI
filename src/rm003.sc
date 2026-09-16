/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 rm003.sc
 The office -- the game's hub. Boot and "Restart Game" land here
 (initrooms.sc), and so does every finished run once rm002.sc has shown its
 ending cards. Nothing here pushes the player into the event cards: the
 only questions are the one-time setup ones (appearance, then name), and a
 session starts only when they pick one from the computer's prompt (which
 they can always back out of), handing off to rm001.sc. Until then they can look around, sit, and review their
 Case Files for as long as they like.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
(include "officetext.sh")
/******************************************************************************/
(script OFFICE_ROOM)
/******************************************************************************/
(use "main")
(use "controls")
(use "cycle")
(use "game")
(use "feature")
(use "obj")
(use "inv")
(use "user")
(use "printchoices")
(use "mechanisms")
(use "casefiles")
(use "casefilecategory")
(use "playernameprompt")
/******************************************************************************/
(instance public rm003 of Rm
	(properties
		picture 1
		north 0
		east 0
		south 0
		west 0
	)
	(method (init)
		(var playerNameBuf[PLAYER_NAME_BUF_LEN])
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
		// FALSE every frame, Main.sc) is never set TRUE anywhere in this
		// codebase, so nothing re-disables it behind our back.
		(User:canInput(TRUE))
		// The parser's replies (see RoomScript). Never disposed, same as
		// TEXT_UI -- and never via DisposeScript(), which only takes script
		// numbers (see game.sh's TEXT_UI note).
		Load(rsTEXT TEXT_OFFICE)

		// A fresh arrival is boot or "Restart Game"; coming back from
		// rm002.sc's ending cards, the music is already playing and the
		// player has just been shown plenty.
		(if(<> gPreviousRoomNumber ENDING_ROOM)
			// Background music, sound resource 3, gm.drv/General MIDI (see
			// resource.cfg). stop() first so a restart never layers tracks.
			// Sound objects persist across room transitions once started.
			(send gTheMusic:
				prevSignal(0)
				stop()
				number(3)
				loop(-1)
				play()
			)
		)
		// Appearance comes before any other question, and is asked only
		// until it's been chosen once -- after that, only the mirror
		// (RoomScript) reopens it. Checked on every arrival, not just fresh
		// ones, so Reset Data mid-session is picked up on the way back in.
		(if(== gPortraitChoice -1)
			(RoomScript:choosePortrait())
		)
		// Player name -- optional, asked only while none is stored (Reset
		// Data blanks it). PromptPlayerName is Load/Dispose-scoped, not
		// always resident -- see PlayerNamePrompt.sc's header for the real
		// heap-fragmentation regression that put it there.
		GetPlayerName(@playerNameBuf)
		(if(not StrLen(@playerNameBuf))
			Load(rsSCRIPT PLAYERNAMEPROMPT_SCRIPT)
			PromptPlayerName(@playerNameBuf)
			DisposeScript(PLAYERNAMEPROMPT_SCRIPT)
			SetPlayerName(@playerNameBuf)
		)
		(if(<> gPreviousRoomNumber ENDING_ROOM)
			Print(TEXT_OFFICE TXT_OFFICE_ARRIVE)
		)
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
	(method (choosePortrait)
        // Shared by init()'s first-time check and "look mirror". The picker
        // lives in always-resident printchoices.sc; saving goes through
        // CaseFiles.sc, which doesn't, hence the Load/Dispose.
        = gPortraitChoice PromptPortraitChoice()
        Load(rsSCRIPT CASEFILES_SCRIPT)
        SavePortraitChoice(gPortraitChoice)
        DisposeScript(CASEFILES_SCRIPT)
	)
	(method (startSession)
        (var promptBuf[72], titleBuf[16], standardBuf[32], extendedBuf[48],
            notYetBuf[16], choice)
        // The computer's one question, always with a way back out, so a
        // mis-click or a change of heart just leaves the player in the
        // office. Returning players pick Standard or Extended Therapy here
        // (this used to be rm001.sc's question); new players just confirm a
        // standard run. Values are game.sh's SESSION_* -- "Not yet" is a
        // real button the player clicks, since PrintChoices ignores Escape
        // rather than turning it into a choice (see its header).
        Load(rsTEXT TEXT_UI)
        GetFarText(TEXT_UI TEXT_UI_NEWSESSION_TITLE @titleBuf)
        GetFarText(TEXT_OFFICE TXT_OFFICE_NOT_YET_BTN @notYetBuf)
        (if(gNgPlusUnlocked)
            GetFarText(TEXT_UI TEXT_UI_NEWSESSION_PROMPT @promptBuf)
            GetFarText(TEXT_UI TEXT_UI_STANDARD_BTN @standardBuf)
            GetFarText(TEXT_UI TEXT_UI_EXTENDED_BTN @extendedBuf)
            = choice PrintChoices(
                @promptBuf
                @titleBuf
                290
                NULL
                @standardBuf SESSION_STANDARD
                @extendedBuf SESSION_EXTENDED
                @notYetBuf SESSION_NOT_YET
            )
        )(else
            GetFarText(TEXT_OFFICE TXT_OFFICE_SESSION_PROMPT @promptBuf)
            GetFarText(TEXT_OFFICE TXT_OFFICE_BEGIN_BTN @standardBuf)
            = choice PrintChoices(
                @promptBuf
                @titleBuf
                290
                NULL
                @standardBuf SESSION_STANDARD
                @notYetBuf SESSION_NOT_YET
            )
        )
        (if(== choice SESSION_NOT_YET)
            return
        )
        = gHardMode (== choice SESSION_EXTENDED)
        (send gRoom:newRoom(SESSION_ROOM))
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
        // Computer -> the session prompt (startSession, above), which can
        // always be backed out of. Also "turn on computer" / "use
        // computer", below.
        (if(not (send pEvent:claimed))
            (if(== (send pEvent:type) evMOUSEBUTTON)
                (if((>= (send pEvent:x) COMPUTER_X1) and (< (send pEvent:x) COMPUTER_X2))
                    (if((>= (send pEvent:y) COMPUTER_Y1) and (< (send pEvent:y) COMPUTER_Y2))
                        (send pEvent:claimed(TRUE))
                        (self:startSession())
                    )
                )
            )
        )
        // Parser-driven office flavor text. Pure atmosphere with no state
        // changes, except the verbs that do what a click already does
        // ("open cabinet", "turn on computer") and the mirror, which
        // reopens the appearance picker. The text itself lives in
        // TEXT_OFFICE (text/office.txt -> text.003 via tools/gen-text.js),
        // not string literals: this room stays resident under the Case
        // Files viewer, whose heap margin is the tightest in the game.
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
            // The only way to change appearance once it's been chosen.
            (if(Said('look,examine,x/mirror'))
                (send pEvent:claimed(TRUE))
                Print(TEXT_OFFICE TXT_OFFICE_LOOK_MIRROR)
                (self:choosePortrait())
                return
            )
            (if((Said('look,examine,x/me')) or (Said('look,examine,x/self')))
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
            // Same as clicking the computer. 'turn<on/...' is this codebase's
            // first use of the '<' preposition operator (documented, but
            // untested here), so "turn off computer" doesn't start a
            // session. "use" only parses as a command once it has the
            // Imperative Verb class in the vocab.
            (if((Said('turn<on/computer')) or (Said('use/computer')))
                (send pEvent:claimed(TRUE))
                (self:startSession())
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
