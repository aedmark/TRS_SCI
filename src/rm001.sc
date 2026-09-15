/******************************************************************************
 SCI Template Game
 By Brian Provinciano
 ******************************************************************************
 rm001.sc
 Contains the first room of your game.

 T.R.S.: entry point for a run, not the turn loop -- one room per event
 (game.sh) means each of the 196 event rooms ends its own turn via
 EndTurn() (mechanisms.sc) and transitions directly to the next.
 Reached only from the office's computer (rm003.sc). This room just
 does the per-run reset and applies whichever mode the office's
 computer prompt picked, then bootstraps the first turn through
 EndTurn() with gTurn pre-set to 0. Never revisited mid-run. The
 one-time setup questions (appearance, name) live in the office.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script 1)
/******************************************************************************/
(use "main")
(use "controls")
(use "cycle")
(use "game")
(use "feature")
(use "obj")
(use "inv")
(use "door")
(use "jump")
(use "dpath")
(use "mechanisms")
/******************************************************************************/
(instance public rm001 of Rm
	(properties
		picture scriptNumber
		// Set up the rooms to go to/come from here
		north 0
		east 0
		south 0
		west 0
	)
	(method (init)
		(var extendedTitleBuf[24])
		// same in every script, starts things up
  		(super:init())
  		(self:setScript(RoomScript))

  		// Reset per-run state -- every run starts here, from the office's
  		// computer (rm003.sc). Deliberately does NOT touch the Case Files
  		// slots, gNgPlusUnlocked or gPortraitChoice -- those are the
  		// persistent, cross-run record.
  		= gRepression STARTING_REPRESSION
  		= gMask STARTING_MASK
  		= gChild STARTING_CHILD
  		= gFawnCount 0
  		= gFawnUnlocked FALSE
  		= gFlightCount 0
  		= gFlightUnlocked FALSE
  		= gFightCount 0
  		= gFightUnlocked FALSE
  		= gFreezeCount 0
  		= gFreezeUnlocked FALSE
  		= gSecureCount 0
  		= gSecureUnlocked FALSE
  		ResetSeenEvents()		/* gSeenEvent persists all session, needs its own per-run reset */

  		// Check which room ego came from and position it
  		(switch(gPreviousRoomNumber)
            /******************************************************
             * Put the cases here for the rooms ego can come from *
             ******************************************************/ /*
            (case north
  				(send gEgo:
  					posn(210 110)
  					loop(2)
  				)
  			)*/
            // Set up ego's position if it hasn't come from any room
  			(default
  				(send gEgo:
  					posn(150 130)
  					loop(1)
  				)
  			)
  		)

		// Set up the ego
		SetUpEgo()
		(send gEgo:init())
		// Pure dialog-driven stat loop, no walking-around gameplay --
		// hide ego and take back the control PlayerControl() grants by
		// default (SetUpEgo() calls it internally).
		ProgramControl()
		(send gEgo:hide())

		// gHardMode was picked by the office computer's prompt (rm003.sc's
		// startSession) -- always FALSE for new players.
		(if(gHardMode)
			= gMaxTurns HARD_MODE_TURNS
			// Message comes straight from TEXT_UI via Print()'s own native
			// support (params[0] < 1000 routes through GetFarText
			// internally, see Controls.sc); #title needs its own GetFarText
			// call first since that path doesn't get the same treatment.
			Load(rsTEXT TEXT_UI)
			GetFarText(TEXT_UI TEXT_UI_EXTENDED_STARTED_TITLE @extendedTitleBuf)
			Print(TEXT_UI TEXT_UI_EXTENDED_STARTED_MSG #title @extendedTitleBuf)
		)(else
			= gMaxTurns DEFAULT_MAX_TURNS
		)

		// Bootstrap the first turn through EndTurn()'s own logic
		// (mechanisms.sc) instead of duplicating it -- gTurn=0 going in
		// means it sees turn 1 exactly like any other turn's own check.
		= gTurn 0
		EndTurn()

        /**************************************************
         * Add the rest of your initialization stuff here *
         **************************************************/
  )
)
/******************************************************************************/
(instance RoomScript of Script
	(properties)
	(method (handleEvent pEvent)
        (super:handleEvent(pEvent))

        /*****************************************
         * Handle the possible said phrases here *
         *****************************************/
        (if(Said('look'))
            Print("You are in an empty room")
        )
 	)
)
/******************************************************************************/
