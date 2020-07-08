package mav.bank.library.teststeps;

import com.relevantcodes.extentreports.LogStatus;

import mav.bank.driver.DriverFactory;
import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ProjectConfig;
import mav.bank.framework.TestData;
import mav.bank.pom.functions.fab.BankFunctions;

/**	
* Class Name			:	RAKTestSteps
* Use					:	RAKTestSteps contains methods that forms the sequence of steps that are to be executed under a particular keyword 
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

public class BankTestSteps {
	/**	
	   * Method Name			:	RAKTestSteps - Constructor
	   * Use					:	The constructor is used for initialization of the reports, driver and logs for a particular keyword
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   */
	
	public BankTestSteps() {
		initReport();
		logDetails();
		DriverFactory.driverInit();
	}

	  /**	
	   * Method Name			:	initReport
	   * Use					:	The initReport method initializes a Extent report for the keyword with the test name and description
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   */
	
	public static void initReport() {
		try {
			String testName = ConfigProvider.getConfig("Testname") + "-" + TestData.getConfig("DataBinding");
			String desc = "";
			if (ProjectConfig.getPropertyValue("versionspecific").equals("true")
					|| ConfigProvider.getConfig("Platform").contains("BrowserStack"))
				desc = ConfigProvider.getConfig("Browser") + "-" + ConfigProvider.getConfig("Version");
			else
				desc = ConfigProvider.getConfig("Browser");
			ExReporter.initTest(testName, desc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	  /**	
	   * Method Name			:	logDetails
	   * Use					:	The logDetails method is used for logging of details of execution
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   */
	
	private void logDetails() {
		//System.out.println(ProjectConfig.getPropertyValue("url"));
		try {
			if (ConfigProvider.getConfig("Platform").equalsIgnoreCase("Browser"))
				ExReporter.log(LogStatus.INFO, "URL: " + ProjectConfig.getPropertyValue("url"));

			else
				ExReporter.log(LogStatus.INFO, "URL: " + ProjectConfig.getPropertyValue("murl"));
			ExReporter.log(LogStatus.INFO, "OS: " + ConfigProvider.getConfig("OS"));
			ExReporter.assignCatogory(ConfigProvider.getConfig("OS"));
			ExReporter.assignCatogory(ConfigProvider.getConfig("Testname"));

			if (ProjectConfig.getPropertyValue("versionspecific").equals("true")
					|| ConfigProvider.getConfig("Platform").contains("BrowserStack")) {
				ExReporter.log(LogStatus.INFO,
						"Browser: " + ConfigProvider.getConfig("Browser") + "-" + ConfigProvider.getConfig("Version"));
				ExReporter.assignCatogory(ConfigProvider.getConfig("Browser") + "-" + ConfigProvider.getConfig("Version"));
			} else {
				ExReporter.log(LogStatus.INFO, "Browser: " + ConfigProvider.getConfig("Browser"));
				ExReporter.assignCatogory(ConfigProvider.getConfig("Browser"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	  /*
	  *//**	
	   * Method Name			:	RAKTestSteps - Constructor
	   * Use					:	The constructor is used for initialization of the reports, driver and logs for a particular keyword
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public RAKTestSteps() {
		initReport();
		logDetails();
		DriverFactory.driverInit();
	}

	  *//**	
	   * Method Name			:	initReport
	   * Use					:	The initReport method initializes a Extent report for the keyword with the test name and description
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void initReport() {
		try {
			String testName = ConfigProvider.getConfig("Testname") + "-" + TestData.getConfig("DataBinding");
			String desc = "";
			if (ProjectConfig.getPropertyValue("versionspecific").equals("true")
					|| ConfigProvider.getConfig("Platform").contains("BrowserStack"))
				desc = ConfigProvider.getConfig("Browser") + "-" + ConfigProvider.getConfig("Version");
			else
				desc = ConfigProvider.getConfig("Browser");
			ExReporter.initTest(testName, desc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	  *//**	
	   * Method Name			:	logDetails
	   * Use					:	The logDetails method is used for logging of details of execution
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	private void logDetails() {
		//System.out.println(ProjectConfig.getPropertyValue("url"));
		try {
			if (ConfigProvider.getConfig("Platform").equalsIgnoreCase("Browser"))
				ExReporter.log(LogStatus.INFO, "URL: " + ProjectConfig.getPropertyValue("url"));

			else
				ExReporter.log(LogStatus.INFO, "URL: " + ProjectConfig.getPropertyValue("murl"));
			ExReporter.log(LogStatus.INFO, "OS: " + ConfigProvider.getConfig("OS"));
			ExReporter.assignCatogory(ConfigProvider.getConfig("OS"));
			ExReporter.assignCatogory(ConfigProvider.getConfig("Testname"));

			if (ProjectConfig.getPropertyValue("versionspecific").equals("true")
					|| ConfigProvider.getConfig("Platform").contains("BrowserStack")) {
				ExReporter.log(LogStatus.INFO,
						"Browser: " + ConfigProvider.getConfig("Browser") + "-" + ConfigProvider.getConfig("Version"));
				ExReporter.assignCatogory(ConfigProvider.getConfig("Browser") + "-" + ConfigProvider.getConfig("Version"));
			} else {
				ExReporter.log(LogStatus.INFO, "Browser: " + ConfigProvider.getConfig("Browser"));
				ExReporter.assignCatogory(ConfigProvider.getConfig("Browser"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  *//**	
	   * Method Name			:	FundsTransferOutsideUae
	   * Use					:	The FundsTransferOutsideUae method is used for step by step execution of 
	   * 							Funds Transfer Transaction Outside of UAE from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public void FundsTransferOutsideUae() {
		try {
			RakFunctions.Login();
			RakFunctions.FundsTransferOutsideUae();
			RakFunctions.TransactionHistory();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}	
		
	}
	
	  *//**	
	   * Method Name			:	FundsTransferWithinUae
	   * Use					:	The FundsTransferWithinUae method is used for step by step execution of 
	   * 							Funds Transfer Transaction Within UAE from Login to Log out along with History View 
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void FundsTransferWithinUae() {
		try {
			RakFunctions.Login();
			RakFunctions.FundsTransferWithinUae();
			RakFunctions.TransactionHistory();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}	
		
	}

	  *//**	
	   * Method Name			:	FundsTransferSelfAccounts
	   * Use					:	The FundsTransferWithinUae method is used for step by step execution of 
	   * 							Funds Transfer Transaction Within UAE from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void FundsTransferSelfAccounts() {
		try {
			RakFunctions.Login();
			RakFunctions.FundsTransferSelfAccounts();
			RakFunctions.TransactionHistorySelfAccount();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}	
		
	}
	
	  *//**	
	   * Method Name			:	RakMoneyTransfer
	   * Use					:	The RakMoneyTransfer method is used for step by step execution of 
	   * 							RAK Money Transfer Transaction from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void RakMoneyTransfer() {
		try {
			RakFunctions.Login();
			RakFunctions.RakMoneyTransfer();
			RakFunctions.TransactionHistory();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}	
		
	}
	
	  *//**	
	   * Method Name			:	FundsTransferWithinRak
	   * Use					:	The FundsTransferWithinRak method is used for step by step execution of 
	   * 							Funds Transfer Transaction With in RAK from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void FundsTransferWithinRak() {
		System.setProperty("chargesCollected", "-");
		System.setProperty("exchangeRate", "-");
		System.setProperty("reference", "-");
		System.setProperty("transactionStatus", "-");
		try {
			RakFunctions.Login();
			RakFunctions.FundsTransferWithinRak();
			RakFunctions.TransactionHistorySelfAccount();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}	
		
	}
	
	  *//**	
	   * Method Name			:	payBill
	   * Use					:	The payBill method is used for step by step execution of 
	   * 							Payment of Bills transaction from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void payBill() {
		try {
			RakFunctions.Login();
			RakFunctions.payBill();
			RakFunctions.transactionHistoryPayBill();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}	
		
	}
	
	  *//**	
	   * Method Name			:	payOwnCard
	   * Use					:	The payOwnCard method is used for step by step execution of 
	   * 							Payment of Own Credit Cards Outstanding transaction from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void payOwnCard() {
		try {
			RakFunctions.Login();
			RakFunctions.payOwnCard();
			RakFunctions.transactionHistoryPayCards();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	payOtherRakCard
	   * Use					:	The payOtherRakCard method is used for step by step execution of 
	   * 							Payment of Other RAK Credit Cards Outstanding from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void payOtherRakCard() {
		try {
			RakFunctions.Login();
			RakFunctions.payOtherRakCard();
			RakFunctions.transactionHistoryPayCards();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	payOutsideBankCard
	   * Use					:	The payOutsideBankCard method is used for step by step execution of 
	   * 							Payment of Outside RAK Bank Credit Cards Outstanding from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void payOutsideBankCard() {
		try {
			RakFunctions.Login();
			RakFunctions.payOutsideBankCard();
			RakFunctions.transactionHistoryPayOtherBankCards();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	payPrepaidCard
	   * Use					:	The payPrepaidCard method is used for step by step execution of 
	   * 							Payment of Prepaid RAK Bank Cards from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void payPrepaidCard() {
		try {
			RakFunctions.Login();
			RakFunctions.payPrepaidCard();
			RakFunctions.transactionHistoryPayCards();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}

	  *//**	
	   * Method Name			:	mobileCash
	   * Use					:	The mobileCash method is used for step by step execution of 
	   * 							Mobile Cash Transaction from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void mobileCash() {
		try {
			RakFunctions.Login();
			RakFunctions.mobileCash();
			RakFunctions.TransactionHistoryMobileCash();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	serviceRequest
	   * Use					:	The serviceRequest method is used for step by step execution of 
	   * 							Service Request - Duplicate Statement View Transaction from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void serviceRequest() {
		try {
			RakFunctions.Login();
			RakFunctions.serviceRequest();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	accountSummary
	   * Use					:	The accountSummary method is used for step by step execution of 
	   * 							Service Request - Account Summary View Transaction from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void accountSummary() {
		try {
			RakFunctions.Login();
			RakFunctions.accountSummary();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	accountDetails
	   * Use					:	The accountDetails method is used for step by step execution of 
	   * 							Service Request - Account Details View Transaction from Login to Log out along with History View
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void accountDetails() {
		try {
			RakFunctions.Login();
			RakFunctions.accountDetails();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	transactionView
	   * Use					:	The transactionView method is used for step by step execution of 
	   * 							Service Request - To View Last 10 Transactions from Login to Log out
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void transactionView() {
		try { 
			RakFunctions.Login();
			RakFunctions.transactionView();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	lodgedCheques
	   * Use					:	The lodgedCheques method is used for step by step execution of 
	   * 							Service Request - To View Last 10 Transactions from Login to Log out
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public void lodgedCheques() {
		try { 
			RakFunctions.Login();
			RakFunctions.lodgedCheques();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void advanceAgainstSalary() {
		try { 
			RakFunctions.Login();
			RakFunctions.advanceAgainstSalary();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}

	public void balanceConfirmation() {
		try { 
			RakFunctions.Login();
			RakFunctions.balanceConfirmation();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void demandDraft() {
		try { 
			RakFunctions.Login();
			RakFunctions.demandDraft();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void blockCards() {
		try { 
			RakFunctions.Login();
			RakFunctions.blockCards();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void redeemCashBack() {
		try { 
			RakFunctions.Login();
			RakFunctions.redeemCashBack();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void unblockCards() {
		try { 
			RakFunctions.Login();
			RakFunctions.unblockCards();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void replaceDebitCard() {
		try { 
			RakFunctions.Login();
			RakFunctions.replaceDebitCard();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void cardDuplicateStatement() {
		try { 
			RakFunctions.Login();
			RakFunctions.cardDuplicateStatement();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void reportLostCreditCard() {
		try { 
			RakFunctions.Login();
			RakFunctions.reportLostCreditCard();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void easyPaymentPlan() {
		try { 
			RakFunctions.Login();
			RakFunctions.easyPaymentPlan();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void smartCash() {
		try { 
			RakFunctions.Login();
			RakFunctions.smartCash();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void amendMaturityInstructionTD() {
		try { 
			RakFunctions.Login();
			RakFunctions.amendMaturityInstructionTD();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void termDepositClosure() {
		try { 
			RakFunctions.Login();
			RakFunctions.termDepositClosure();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void amendMaturityInstructionIslamicTD() {
		try { 
			RakFunctions.Login();
			RakFunctions.amendMaturityInstructionIslamicTD();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void termDepositClosureIslamic() {
		try { 
			RakFunctions.Login();
			RakFunctions.termDepositClosureIslamic();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void creditCardCheque() {
		try { 
			RakFunctions.Login();
			RakFunctions.creditCardCheque();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}

	public void balanceTransfer() {
		try { 
			RakFunctions.Login();
			RakFunctions.balanceTransfer();
			RakFunctions.Logout();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void firstTest() {
		try { 
			RakFunctions.openBrowser();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}

	public void connectTimesheet() {
		try { 
			RakFunctions.connectLogin();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void Prismmaveric() {
		try { 
			System.out.print(" \n Into Prismmaveric in Test Steps \n ");
			RakFunctions.Prismmaveric();
			//RakFunctions.connectLogin_Static();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public void outlookLogin() {
		try { 
			System.out.print(" \n Into outlookLogin in Test Steps \n ");
			RakFunctions.outlookLogin();
			RakFunctions.connectLogin_Static();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	*/
		public void outlookLogin() {
			try { 
				System.out.print(" \n Into outlookLogin in Test Steps \n ");
				BankFunctions.outlookLogin();
				//BankFunctions.connectLogin_Static();
			} catch (Exception e) {
				ExReporter.log(LogStatus.FAIL, e.getMessage());
			}
		}
}
