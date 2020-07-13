package mav.bank.alltestpack;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import mav.bank.framework.JiraPolicy;
import mav.bank.framework.TestData;
import mav.bank.library.teststeps.BankTestSteps;

//Test Push 2

//Test Push 3

//Test push 4

//Test Build

//Test Tags

//Third Tag

/**	
* Class Name			:	RAKTests
* Use					:	Class for all the Tests that are to be executed using Testng Framework
* Designed By			:	surya kumar
* Date Last Modified	:	20-Aug-2017
*/

public class BankTests extends BankTestNGMethods {
	/*
	//@SuppressWarnings("static-access")
	
	*//**	
	* FundsTransferOutsideUae - Function for execution of Fund transfer transaction Outside of UAE
	* Has no Return Type and Input is from Test Data Sheets
	* Call is made to FundsTransferOutsideUae Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Funds Transfer Transaction Outside UAE", dataProvider = "TestDataParallel")
	public static void FundsTransferOutsideUae(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.FundsTransferOutsideUae();
	}
	
	*//**	
	* FundsTransferWithinUae - Function for execution of Fund transfer transaction Within UAE
	* Has no Return Type and Input is from Test Data Sheets
	* Call is made to FundsTransferWithinUae Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	
	@Test(description = "Funds Transfer Transaction Within UAE", dataProvider = "TestDataParallel")
	public static void FundsTransferWithinUae(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.FundsTransferWithinUae();
	}
	
	*//**	
	* FundsTransferSelfAccounts - Function for execution of Fund transfer transaction between Self Accounts
	* Has no Return Type and Input is from Test Data Sheets
	* Call is made to FundsTransferSelfAccounts Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	
	@Test(description = "Funds Transfer Between Self Accounts", dataProvider = "TestDataParallel")
	public static void FundsTransferSelfAccounts(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.FundsTransferSelfAccounts();
	}
	
	*//**	
	* FundsTransferSelfAccounts - Function for execution of Fund transfer transaction between Self Accounts
	* Has no Return Type and Input is from Test Data Sheets
	* Call is made to FundsTransferSelfAccounts Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	
	@Test(description = "RAK Money Transfer Validation", dataProvider = "TestDataParallel")
	public static void RakMoneyTransfer(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.RakMoneyTransfer();
	}
	
	*//**	
	* FundsTransferWithinRak - Function for execution of Fund transfer transaction Within RAK Bank
	* Has no Return Type and Input is from Test Data Sheets
	* Call is made to FundsTransferWithinRak Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Funds Transfer Transaction Within RAK", dataProvider = "TestDataParallel")
	public static void FundsTransferWithinRak(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.FundsTransferWithinRak();
	}

	*//**	
	* payBill - Function for execution of Pay Bill transaction 
	* Has no Return Type and Input is from Test Data Sheets
	* Call is made to payBill Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Pay Bill Transaction", dataProvider = "TestDataParallel")
	public static void payBill(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.payBill();
	}
	
	*//**	
	* payBill - Function for execution of Pay Bill transaction 
	* Has no Return Type and Input is from Test Data Sheets
	* Call is made to payBill Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Pay Own Cards Transaction", dataProvider = "TestDataParallel")
	public static void payOwnCard(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.payOwnCard();
	}

	*//**	
	* payOtherRakCard - Function for execution of Payment of Other RAK Card transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to payOtherRakCard Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Pay Other RAK Cards Transaction", dataProvider = "TestDataParallel")
	public static void payOtherRakCard(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.payOtherRakCard();
	}
	
	*//**	
	* payOutsideBankCard - Function for execution of Payment of Credit Cards Outside of Bank transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to payOutsideBankCard Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Pay Outise BANK Cards Transaction", dataProvider = "TestDataParallel")
	public static void payOutsideBankCard(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.payOutsideBankCard();
	}
	
	*//**	
	* payPrepaidCard - Function for execution of Payment of Prepaid RAK Card transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to payPrepaidCard Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Pay Pre paid Card", dataProvider = "TestDataParallel")
	public static void payPrepaidCard(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.payPrepaidCard();
	}
	
	*//**	
	* mobileCash - Function for execution of Mobile Cash  transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to mobileCash Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Mobile Cash Transaction", dataProvider = "TestDataParallel")
	public static void mobileCash(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.mobileCash();
	}

	*//**	
	* serviceRequest - Function for execution of Service Request - Duplicate Statement  transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to serviceRequest Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Service Request Creation", dataProvider = "TestDataParallel")
	public static void serviceRequest(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.serviceRequest();
	}
	
	*//**	
	* accountSummary - Function for execution of Service Request - Account Summary transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to accountSummary Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Account Summary", dataProvider = "TestDataParallel")
	public static void accountSummary(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.accountSummary();
	}
	
	*//**	
	* accountDetails - Function for execution of Service Request - Account Details transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to accountDetails Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Account Details View", dataProvider = "TestDataParallel")
	public static void accountDetails(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.accountDetails();
	}
	
	*//**	
	* accountDetails - Function for execution of Service Request - Account Details transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to accountDetails Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Account Last Transactions View", dataProvider = "TestDataParallel")
	public static void transactionView(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.transactionView();
	}
	
	*//**	
	* lodgedCheques - Function for execution of Service Request - View Lodged Cheques transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to lodgedCheques Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "View Lodged Cheques", dataProvider = "TestDataParallel")
	public static void lodgedCheques(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.lodgedCheques();
	}

	*//**	
	* advanceAgainstSalary - Function for execution of Service Request - Advance Against Salary transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to advanceAgainstSalary Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Advance Against Salary", dataProvider = "TestDataParallel")
	public static void advanceAgainstSalary(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.advanceAgainstSalary();
	}
*//********************
	/**	
	* firstTest - Function for execution of Service Request - Credit Card Cheque Issuance transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to creditCardCheque Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	11-Nov-2017
	*//*
	@Test(description = "connectTimesheet", dataProvider = "TestDataParallel")
	public static void connectTimesheet(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.connectTimesheet();
	}
	
	@Test(description = "connectLogin", dataProvider = "TestDataParallel")
	public static void connectLogin(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.connectTimesheet();
	}
*//*************
	/**	
	* balanceConfirmation - Function for execution of Service Request - Balance Confirmation transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to balanceConfirmation Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Balance Confirmation", dataProvider = "TestDataParallel")
	public static void balanceConfirmation(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.balanceConfirmation();
	}
	
	*//**	
	* demandDraft - Function for execution of Service Request - Demand Draft Issuance transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to demandDraft Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Demand Draft Issuance", dataProvider = "TestDataParallel")
	public static void demandDraft(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.demandDraft();
	}
	
	*//**	
	* blockCards - Function for execution of Service Request - Block Cards(Debit/Credit) transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to blockCards Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Block Cards", dataProvider = "TestDataParallel")
	public static void blockCards(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.blockCards();
	}
	
	*//**	
	* blockCards - Function for execution of Service Request - Unblock Cards(Debit/Credit) transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to blockCards Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Unblock Cards", dataProvider = "TestDataParallel")
	public static void unblockCards(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.unblockCards();
	}
	
	*//**	
	* redeemCashBack - Function for execution of Redeem Cash Back transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to redeemCashBack Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Redeem Cash Back", dataProvider = "TestDataParallel")
	public static void redeemCashBack(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.redeemCashBack();
	}
	
	*//**	
	* replaceDebitCard - Function for execution of Replace Debit Cards transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to replaceDebitCard Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Replace Debit Card", dataProvider = "TestDataParallel")
	public static void replaceDebitCard(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.replaceDebitCard();
	}
	
	*//**	
	* cardDuplicateStatement - Function for execution of Duplicate Card Statement transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to cardDuplicateStatement Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Duplicate Card Statement", dataProvider = "TestDataParallel")
	public static void cardDuplicateStatement(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.cardDuplicateStatement();
	}
	
	*//**	
	* reportLostCreditCard - Function for execution of Service Request - Report Lost Credit Card transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to reportLostCreditCard Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Report Lost Credit Card", dataProvider = "TestDataParallel")
	public static void reportLostCreditCard(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.reportLostCreditCard();
	}
	
	*//**	
	* easyPaymentPlan - Function for execution of Service Request - Easy Payment Plan Initiation transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to easyPaymentPlan Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Easy Payment Plan", dataProvider = "TestDataParallel")
	public static void easyPaymentPlan(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.easyPaymentPlan();
	}
	
	*//**	
	* easyPaymentPlan - Function for execution of Service Request - Easy Payment Plan Initiation transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to easyPaymentPlan Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Smart Cash", dataProvider = "TestDataParallel")
	public static void smartCash(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.smartCash();
	}
	
	*//**	
	* amendMaturityInstructionTD - Function for execution of Service Request - Amend Maturity Instruction Term Deposits transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to amendMaturityInstructionTD Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Term Deposit Amend Maturity Instruction", dataProvider = "TestDataParallel")
	public static void amendMaturityInstructionTD(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.amendMaturityInstructionTD();
	}
	
	*//**	
	* termDepositClosure - Function for execution of Service Request - Closure of Term Deposits transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to termDepositClosure Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Closure of Term Deposit", dataProvider = "TestDataParallel")
	public static void termDepositClosure(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.termDepositClosure();
	}
	
	*//**	
	* amendMaturityInstructionIslamicTD - Function for execution of Service Request -Amend Maturity Instruction Term Deposits(Islamic) transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to amendMaturityInstructionIslamicTD Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Term Deposit Amend Maturity Instruction Islamic", dataProvider = "TestDataParallel")
	public static void amendMaturityInstructionIslamicTD(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.amendMaturityInstructionIslamicTD();
	}
	
	*//**	
	* termDepositClosureIslamic - Function for execution of Service Request - Closure of Term Deposits(Islamic) transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to termDepositClosureIslamic Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Closure of Term Deposit Islamic", dataProvider = "TestDataParallel")
	public static void termDepositClosureIslamic(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.termDepositClosureIslamic();
	}
	
	*//**	
	* creditCardCheque - Function for execution of Service Request - Credit Card Cheque Issuance transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to creditCardCheque Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Credit Card Cheque", dataProvider = "TestDataParallel")
	public static void creditCardCheque(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.creditCardCheque();
	}
	
	*//**	
	* balanceTransfer - Function for execution of Service Request - Credit Card Cheque Issuance transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to creditCardCheque Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "Balance Transfer - Credit Card", dataProvider = "TestDataParallel")
	public static void balanceTransfer(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.balanceTransfer();
	}
	
	
	*//**	
	* firstTest - Function for execution of Service Request - Credit Card Cheque Issuance transaction 
	* Has no Return Type and Input is from Test Data Sheet
	* Call is made to creditCardCheque Functions in RAK Functions Class
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*//*
	@Test(description = "First Test Selenium 3.14", dataProvider = "TestDataParallel")
	public static void firstTest(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		RAKTestSteps steps = new RAKTestSteps();
		steps.firstTest();
	}
		

	@Test(description = "First Test Selenium 3.14", dataProvider = "TestDataParallel")
	public static void Prismmaveric(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		System.out.print(" \n Into RAKTests \n ");
		RAKTestSteps steps = new RAKTestSteps();
		steps.Prismmaveric();
//		steps.accountCreation();
	}
		
	@Test(description = "First Test Selenium 3.14", dataProvider = "TestDataParallel")
	public static void outlookLogin(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		System.out.print(" \n Into RAKTests \n ");
		RAKTestSteps steps = new RAKTestSteps();
		steps.outlookLogin();
		
		}
*/
	
	@JiraPolicy(logTicketReady=true)
	@Test(description = "First Test Selenium 3.14", dataProvider = "TestDataParallel")
	public static void outlookLogin(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		System.out.print(" \n Into RAKTests \n ");
		BankTestSteps steps = new BankTestSteps();
		steps.outlookLogin();
		}
	
	@Test(description = "First Test Selenium 3.14", dataProvider = "TestDataParallel")
	public static void openAir(Map<String, String> brow, Map<String, String> data, ITestContext ctx)
			throws InterruptedException {
		BankTestSteps steps = new BankTestSteps();
		String[] lifecycles = TestData.getConfig("Lifecycles").split(";");
		for (String lifecycle : lifecycles) {
				
		switch(lifecycle)
		{
		case "outlookLogin":
			steps.outlookLogin();
			break;
		case "openAirProcess":
			steps.updateTimesheet();
			break;	
		
		}
		}
	}
}
