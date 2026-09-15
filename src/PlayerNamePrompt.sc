/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 PlayerNamePrompt.sc
 Optional name entry (README: "remembered for next time") -- one DEdit
 control plus an OK button, following the same DEdit-in-a-Dialog shape
 syswindow.sc's own GetReplaceName uses for the Save Game description
 field.

 Load/Dispose-scoped, NOT always resident, even though it's only called
 from rm001.sc (itself not always resident either): a full Dialog/DText/
 DEdit/DButton construction is real permanent code size, and this is
 needed at most once per session (rm001.sc only calls it when no name is
 stored yet). Putting it in printchoices.sc originally (which IS always
 resident, for PrintChoices/PromptPortraitChoice, both called every
 turn/run) cost a real, hard-confirmed regression: a heap-fragmentation
 "Out of heap space" crash on the very first Case Files view of a
 session, where two full runs used to be needed before this project's
 existing heap guard even applied. Moving this one-off dialog out of the
 permanently-resident set gives that margin back. See
 CaseFileCategory.sc's own header for the fuller heap-fragmentation
 story this codebase has already been through more than once.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script PLAYERNAMEPROMPT_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "printchoices")
/******************************************************************************/
(procedure public (PromptPlayerName buf)
	// buf is the caller's own buffer (rm001.sc); DEdit writes into it
	// directly via text(buf), same as newName:text(strDescription) in
	// syswindow.sc's GetReplaceName, so it already holds whatever was
	// typed by the time this returns -- no separate read-back step.
	(var hDialog, hDText, hEdit, hButton, promptBuf[80], titleBuf[24], btnBuf[16])
	Load(rsTEXT TEXT_UI)
	GetFarText(TEXT_UI TEXT_UI_NAME_PROMPT_LABEL @promptBuf)
	GetFarText(TEXT_UI TEXT_UI_NAME_PROMPT_TITLE @titleBuf)
	GetFarText(TEXT_UI TEXT_UI_NAME_PROMPT_OK_BTN @btnBuf)

	= hDialog (Dialog:new())
	(send hDialog:
		window(gTheWindow)
		name("NameD")
		text(@titleBuf)
	)

	= hDText (DText:new())
	(send hDText:
		text(@promptBuf)
		font(gDefaultFont)
		setSize( (- PLAYER_NAME_DIALOG_WIDTH 8) )
		moveTo(4 4)
	)
	(send hDialog:add(hDText))

	= hEdit (DEdit:new())
	(send hEdit:
		text(buf)
		font(gDefaultFont)
		max(PLAYER_NAME_MAX_LEN)
		setSize()
		moveTo(4 (+ (send hDText:nsBottom) 4))
	)
	(send hDialog:add(hEdit))

	= hButton (DButton:new())
	(send hButton:
		text(@btnBuf)
		font(SMALL_FONT)
	)
	SizeButtonToWidth(hButton BUTTON_MAX_WIDTH)
	(send hButton:moveTo(4 (+ (send hEdit:nsBottom) 6)))
	(send hDialog:add(hButton))

	(send hDialog:
		setSize()
		center()
	)
	(send hDialog:open(nwTITLE -1))
	(send hDialog:doit(hEdit))
	(send hDialog:dispose())
)
/******************************************************************************/
