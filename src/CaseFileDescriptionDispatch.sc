/******************************************************************************
 T.R.S. → SCI0 port
 ******************************************************************************
 CaseFileDescriptionDispatch.sc
 Picks and loads the right CaseFileDescriptions{Survival,Failure}N/
 Mechanisms script for one flat Case File index, copying its text into
 the caller's buffer.

 Split out of CaseFileCategory.sc, which used to have this whole
 dispatch switch inline -- a real, confirmed regression: CaseFileCategory.sc
 is resident for the ENTIRE time a category list is open (loaded once by
 the caller, disposed only when the list closes), so a 12-branch
 Load/StrCpy/DisposeScript switch living inside it was permanently
 inflating that whole-session footprint, not just the instant of a View
 click. That extra size was enough on its own to crash opening the list
 (before "View" was ever clickable) even though each individual
 description script is small. This script exists so that dispatch code
 is only ever resident for the brief moment a View click actually needs
 it, matching every other Load/Dispose-scoped file in this codebase.
 ******************************************************************************/
(include "sci.sh")
(include "game.sh")
/******************************************************************************/
(script CASEFILEDESCRIPTIONDISPATCH_SCRIPT)
/******************************************************************************/
(use "main")
(use "controls")
(use "casefiledescriptionssurvival0")
(use "casefiledescriptionssurvival1")
(use "casefiledescriptionssurvival2")
(use "casefiledescriptionssurvival3")
(use "casefiledescriptionssurvival4")
(use "casefiledescriptionssurvival5")
(use "casefiledescriptionssurvival6")
(use "casefiledescriptionssurvival7")
(use "casefiledescriptionssurvival8")
(use "casefiledescriptionsfailure0")
(use "casefiledescriptionsfailure1")
(use "casefiledescriptionsfailure2")
(use "casefiledescriptionsmechanisms")
/******************************************************************************/
(procedure public (LoadCaseFileDescription baseIndex localIndex flatIndex descBuf)
	(var poolNum)
	(if(== baseIndex CASEFILE_SURVIVAL_BASE)
		= poolNum (/ localIndex CASEFILE_SURVIVAL_POOL_SIZE)
		(switch(poolNum)
			(case 0
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL0_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival0(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL0_SCRIPT)
			)
			(case 1
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL1_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival1(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL1_SCRIPT)
			)
			(case 2
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL2_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival2(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL2_SCRIPT)
			)
			(case 3
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL3_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival3(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL3_SCRIPT)
			)
			(case 4
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL4_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival4(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL4_SCRIPT)
			)
			(case 5
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL5_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival5(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL5_SCRIPT)
			)
			(case 6
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL6_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival6(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL6_SCRIPT)
			)
			(case 7
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL7_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival7(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL7_SCRIPT)
			)
			(case 8
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_SURVIVAL8_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionSurvival8(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_SURVIVAL8_SCRIPT)
			)
		)
	)
	(if(== baseIndex CASEFILE_FAILURE_BASE)
		= poolNum (/ localIndex CASEFILE_FAILURE_POOL_SIZE)
		(switch(poolNum)
			(case 0
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_FAILURE0_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionFailure0(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_FAILURE0_SCRIPT)
			)
			(case 1
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_FAILURE1_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionFailure1(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_FAILURE1_SCRIPT)
			)
			(case 2
				Load(rsSCRIPT CASEFILEDESCRIPTIONS_FAILURE2_SCRIPT)
				StrCpy(descBuf CaseFileDescriptionFailure2(flatIndex))
				DisposeScript(CASEFILEDESCRIPTIONS_FAILURE2_SCRIPT)
			)
		)
	)
	(if(== baseIndex CASEFILE_MECH_BASE)
		Load(rsSCRIPT CASEFILEDESCRIPTIONS_MECHANISMS_SCRIPT)
		StrCpy(descBuf CaseFileDescriptionMechanisms(flatIndex))
		DisposeScript(CASEFILEDESCRIPTIONS_MECHANISMS_SCRIPT)
	)
)
/******************************************************************************/
