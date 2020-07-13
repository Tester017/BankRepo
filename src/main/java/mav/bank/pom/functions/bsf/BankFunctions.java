package mav.bank.pom.functions.bsf;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;

import com.gargoylesoftware.htmlunit.javascript.host.Map;
import com.relevantcodes.extentreports.LogStatus;

import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ProjectConfig;
import mav.bank.framework.TestData;
import mav.bank.library.functions.CommonDef;
import mav.bank.library.functions.MethodDef;
import mav.bank.pom.elements.bsf.FundsTransferPage;
import mav.bank.pom.elements.bsf.HistoryPage;
import mav.bank.pom.elements.bsf.PayCardsPage;
import mav.bank.pom.elements.bsf.openAirHome;
import mav.bank.pom.elements.bsf.outlookHome;
import mav.bank.pom.elements.bsf.outlookLogin;

/**	
* Class Name			:	RakFunctions
* Use					:	RakFunctions contains all the functions that are used in execution that are 
* 							different transactions or reusable functions
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

public class BankFunctions {
	/*
	private static final By ByLinkText = null;
	static String chargesCollected="-";
	static String exchangeRate="-";
	static String reference="-";
	static String transactionStatus="-";

	  *//**	
	   * Method Name			:	Login 
	   * Use					:	Function to login to the RAK Bank mobile application and waits for the Send Money Button
	   * @return Null
	   * @param No args
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void Login(){

		try {
			CommonDef.waitWebView();
			CommonDef.waitClickable(LoginPage.userID());
			CommonDef.sendKeys(LoginPage.userID(), TestData.getConfig("loginid"));
			CommonDef.sendKeys(LoginPage.userPassword(), TestData.getConfig("loginpwd"));
			MethodDef.click(LoginPage.submitButton());
			if(ConfigProvider.getConfig("OS").equalsIgnoreCase("ANDROID"))
				{
					CommonDef.switchToNative();
					CommonDef.waitClickable(LoginPage.okButton());
					MethodDef.click(LoginPage.okButton());
					CommonDef.switchToWebview();
				}
			CommonDef.waitClickable(LandingPage.sendMoney());
			ExReporter.log(LogStatus.PASS,"Logged in Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	FundsTransferOutsideUae 
	   * Use					:	Function to perform Fund transfer transaction Outside of UAE
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void FundsTransferOutsideUae(){

		try {
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			//CLick on Send money option
			MethodDef.click(LandingPage.sendMoney());
			Thread.sleep(1500);
			
			//Select the To Account
			CommonDef.waitClickable(FundsTransferPage.toAcc());
			CommonDef.captureScreen();
			MethodDef.click(FundsTransferPage.toAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("toaccount"));
			
			//Select the From Account
			Thread.sleep(1000);
			CommonDef.fromAccountWait(FundsTransferPage.fromAcc());
			CommonDef.waitClickable(FundsTransferPage.fromAcc());
			MethodDef.click(FundsTransferPage.fromAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			Thread.sleep(4500); //Sleep for the balance to load
			
			//wait for the balance to load
			CommonDef.balanceLoadingWaitOutsideUae(FundsTransferPage.availableBalance(TestData.getConfig("transfercurrency")));
			
			// Select the Remittance Currency
			MethodDef.click(FundsTransferPage.remitCurrency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("remittancecurrency"));
			
			//Select the Transaction Currency
			MethodDef.click(FundsTransferPage.transactionCurrency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("transfercurrency"));
			
			//Enter the Transaction Amount
			CommonDef.sendKeys(FundsTransferPage.transactionAmount(), TestData.getConfig("amount"));
			
			//Exchange Rate Validation
			if(!(TestData.getConfig("transfercurrency").equalsIgnoreCase(TestData.getConfig("remittancecurrency")))){
				if(TestData.getConfig("customertype").equalsIgnoreCase("PAM"))
					CommonDef.exchangeratevalidation("Elite"+TestData.getConfig("remittancecurrency"));
				else if(TestData.getConfig("customertype").equalsIgnoreCase("Staff"))
					CommonDef.exchangeratevalidation("Staff"+TestData.getConfig("remittancecurrency"));
				else
					CommonDef.exchangeratevalidation(TestData.getConfig("remittancecurrency"));
				}
			
			// Select the Transaction Frequency
			MethodDef.click(FundsTransferPage.transactionFrequency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frequency"));
			CommonDef.captureScreen();
			
			CommonDef.flipUp();
			Thread.sleep(1000);
			//Utilities.ScreenShotMobile(driver);
			
			// Select the Transfer Charges
			MethodDef.click(FundsTransferPage.transactionCharges());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("charges"));		
			
			//Select the Transfer Reason
			MethodDef.click(FundsTransferPage.transactionReasonPurpose());  //Works for Corp 4 and Corp 3
			//MethodDef.click(FundsTransferPage.transactionReason());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("reason"));

			// Flip up
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			//Click on Proceed Button
			MethodDef.click(FundsTransferPage.proceedButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),FundsTransferPage.confirmButton());
			CommonDef.waitClickable(FundsTransferPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Scroll up
			CommonDef.flipUp();
			//Scroll up
			CommonDef.flipUp();
			
			//Click on Confirm Button
			MethodDef.click(FundsTransferPage.confirmButton());
			CommonDef.waitClickable(FundsTransferPage.transactionMessage());
			
			CommonDef.valuePrint(FundsTransferPage.transactionReference());
			CommonDef.valuePrint(FundsTransferPage.transactionMessage());
			if(!(TestData.getConfig("transfercurrency").equalsIgnoreCase(TestData.getConfig("remittancecurrency")))){
				exchangeRate =  CommonDef.valuereturn(FundsTransferPage.transactionExchangeRate());
				}
			chargesCollected = CommonDef.valuereturn(FundsTransferPage.transactionChargesCollected());
			CommonDef.writeTransactionValues(chargesCollected,TestData.getConfig("DataBinding"),"Transaction Charges");
			CommonDef.writeTransactionValues(exchangeRate,TestData.getConfig("DataBinding"),"Transaction Exchange Rate");
			
			String validation= CommonDef.messageValidation(FundsTransferPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}
			else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	FundsTransferWithinUae 
	   * Use					:	Function to perform Fund transfer transaction Within UAE
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void FundsTransferWithinUae(){

		try {
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			//CLick on Send money option
			MethodDef.click(LandingPage.sendMoney());
			Thread.sleep(1500);
			
			//Select the To Account
			CommonDef.waitClickable(FundsTransferPage.toAcc());
			CommonDef.captureScreen();
			MethodDef.click(FundsTransferPage.toAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("toaccount"));
			
			//Select the From Account
			Thread.sleep(1500);
			CommonDef.fromAccountWait(FundsTransferPage.fromAcc());
			CommonDef.waitClickable(FundsTransferPage.fromAcc());
			MethodDef.click(FundsTransferPage.fromAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			Thread.sleep(4500); //Sleep for the balance to load
			
			//wait for the balance to load
			CommonDef.balanceLoadingWaitOutsideUae(FundsTransferPage.availableBalance(TestData.getConfig("transfercurrency")));
			
			// Select the Remittance Currency
			MethodDef.click(FundsTransferPage.remitCurrency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("remittancecurrency"));
			
			//Select the Transaction Currency
			MethodDef.click(FundsTransferPage.transactionCurrency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("transfercurrency"));
			
			//Enter the Transaction Amount
			CommonDef.sendKeys(FundsTransferPage.transactionAmount(), TestData.getConfig("amount"));
			
			//Exchange Rate Validation
			if(!(TestData.getConfig("transfercurrency").equalsIgnoreCase(TestData.getConfig("remittancecurrency")))){
				if(TestData.getConfig("customertype").equalsIgnoreCase("PAM"))
					CommonDef.exchangeratevalidation("Elite"+TestData.getConfig("remittancecurrency"));
				else if(TestData.getConfig("customertype").equalsIgnoreCase("Staff"))
					CommonDef.exchangeratevalidation("Staff"+TestData.getConfig("remittancecurrency"));
				else
					CommonDef.exchangeratevalidation(TestData.getConfig("remittancecurrency"));
			}
			
			
			// Select the Transaction Frequency
			MethodDef.click(FundsTransferPage.transactionFrequency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frequency"));
			CommonDef.captureScreen();
			
			//Enter the Transaction Date
			//Code to enter the Transaction date
			
			CommonDef.flipUp();
			//Utilities.ScreenShotMobile(driver);
			
			// Select the Transfer Charges
			MethodDef.click(FundsTransferPage.transactionCharges());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("charges"));		
			
			//Select the Transfer Reason
			MethodDef.click(FundsTransferPage.transactionReason()); //Corp 4 Reason
			//MethodDef.click(FundsTransferPage.transactionReasonPurpose()); //Corp 3 Reason
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("reason"));

			//Select the Purpose
			MethodDef.click(FundsTransferPage.transactionPurpose());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("purpose"));
			
			// Flip up
			CommonDef.captureScreen();
			
			//Click on Proceed Button
			MethodDef.click(FundsTransferPage.proceedButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),FundsTransferPage.confirmButton());
			CommonDef.waitVisible(FundsTransferPage.confirmButton());
			CommonDef.waitClickable(FundsTransferPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Scroll up
			CommonDef.flipUp();
			//Scroll up
			CommonDef.flipUp();
			
			//Click on Confirm Button
			MethodDef.click(FundsTransferPage.confirmButton());
			CommonDef.waitClickable(FundsTransferPage.transactionMessage());
			
			CommonDef.valuePrint(FundsTransferPage.transactionReference());
			CommonDef.valuePrint(FundsTransferPage.transactionMessage());
			
			if(!(TestData.getConfig("transfercurrency").equalsIgnoreCase(TestData.getConfig("remittancecurrency")))){
				exchangeRate =  CommonDef.valuereturn(FundsTransferPage.transactionExchangeRate());
				}
			chargesCollected = CommonDef.valuereturn(FundsTransferPage.transactionChargesCollected());
			CommonDef.writeTransactionValues(chargesCollected,TestData.getConfig("DataBinding"),"Transaction Charges");
			CommonDef.writeTransactionValues(exchangeRate,TestData.getConfig("DataBinding"),"Transaction Exchange Rate");
			
			String validation= CommonDef.messageValidation(FundsTransferPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}

	  *//**	
	   * Method Name			:	FundsTransferSelfAccounts 
	   * Use					:	Function to perform Fund transfer transaction Between Same user Accounts
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void FundsTransferSelfAccounts(){

		try {
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			//CLick on Send money option
			MethodDef.click(LandingPage.sendMoney());
			Thread.sleep(1500);
			
			//Select the To Account
			CommonDef.waitClickable(FundsTransferPage.toAcc());
			CommonDef.captureScreen();
			MethodDef.click(FundsTransferPage.toAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("toaccount"));
			
			//Select the From Account
			CommonDef.waitVisibleNoError(FundsTransferPage.fromAcc());
			CommonDef.waitClickable(FundsTransferPage.fromAcc());
			MethodDef.click(FundsTransferPage.fromAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			Thread.sleep(2000); //Sleep for the balance to load
			
			//wait for the balance to load
			String fromcurrurency[] = TestData.getConfig("fromaccount").split("-");
			CommonDef.balanceLoadingWaitOutsideUae(FundsTransferPage.availableBalance(fromcurrurency[2]));
			
			// Select the To Account Number
			CommonDef.waitVisibleNoError(FundsTransferPage.toAccNumber());
			CommonDef.waitClickable(FundsTransferPage.toAccNumber());
			MethodDef.click(FundsTransferPage.toAccNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("Transfer_To_Acc_Number"));
			
			//Select the Transaction Currency
			MethodDef.click(FundsTransferPage.transactionCurrencySelfAccount());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("transfercurrency"));
			
			//Enter the Transaction Amount
			CommonDef.waitClickable(FundsTransferPage.transactionAmountSelfAccount());
			CommonDef.sendKeys(FundsTransferPage.transactionAmountSelfAccount(),TestData.getConfig("amount"));
			
			String tocurr[] = TestData.getConfig("Transfer_To_Acc_Number").split("-");

			//Exchange Rate Validation
			if (!(tocurr[2].equalsIgnoreCase(fromcurrurency[2]))) {
				if(TestData.getConfig("customertype").equalsIgnoreCase("PAM"))
					CommonDef.exchangeratevalidation("Elite"+TestData.getConfig("remittancecurrency"));
				else if(TestData.getConfig("customertype").equalsIgnoreCase("Staff"))
					CommonDef.exchangeratevalidation("Staff"+TestData.getConfig("remittancecurrency"));
				else
					CommonDef.exchangeratevalidation(TestData.getConfig("remittancecurrency"));
			}	
			
			//Select the Transfer Reason
			CommonDef.sendKeys(FundsTransferPage.transactionReasonSelfAccount(), TestData.getConfig("reason"));
			
			// Select the Transaction Frequency
			MethodDef.click(FundsTransferPage.transactionFrequency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frequency"));
			CommonDef.captureScreen();
			
			// Flip up
			CommonDef.flipUp();

			//Enter the Transaction Date
			//Code to enter the Transaction date

			CommonDef.captureScreen();
			
			//Click on Proceed Button
			MethodDef.click(FundsTransferPage.proceedButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),FundsTransferPage.confirmButton());
			CommonDef.waitClickable(FundsTransferPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Scroll up
			CommonDef.flipUp();
			
			//Click on Confirm Button
			MethodDef.click(FundsTransferPage.confirmButton());
			CommonDef.waitClickable(FundsTransferPage.transactionMessage());
			
			CommonDef.valuePrint(FundsTransferPage.transactionReference());
			CommonDef.valuePrint(FundsTransferPage.transactionMessage());
			
			if (!(tocurr[2].equalsIgnoreCase(fromcurrurency[2]))) {
				exchangeRate =  CommonDef.valuereturn(FundsTransferPage.transactionExchangeRate());
				}
			CommonDef.writeTransactionValues(exchangeRate,TestData.getConfig("DataBinding"),"Transaction Exchange Rate");
			
			String validation= CommonDef.messageValidation(FundsTransferPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	FundsTransferWithinRak 
	   * Use					:	Function to perform Fund transfer transaction With in RAK Bank
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*

	public static void FundsTransferWithinRak(){

		try {
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			//CLick on Send money option
			MethodDef.click(LandingPage.sendMoney());
			Thread.sleep(1500);
			
			//Select the To Account
			CommonDef.waitClickable(FundsTransferPage.toAcc());
			CommonDef.captureScreen();
			MethodDef.click(FundsTransferPage.toAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("toaccount"));
			
			//Select the From Account
			CommonDef.waitVisibleNoError(FundsTransferPage.fromAcc());
			CommonDef.waitClickable(FundsTransferPage.fromAcc());
			MethodDef.click(FundsTransferPage.fromAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			Thread.sleep(2000); //Sleep for the balance to load
			
			//wait for the balance to load
			String fromcurrurency[] = TestData.getConfig("fromaccount").split("-");
			CommonDef.balanceLoadingWaitOutsideUae(FundsTransferPage.availableBalance(fromcurrurency[2]));
			
			//Select the Transaction Currency
			MethodDef.click(FundsTransferPage.transactionCurrencyWithinRAK());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("transfercurrency"));
			
			//Enter the Transaction Amount
			CommonDef.waitClickable(FundsTransferPage.transactionAmountSelfAccount());
			CommonDef.sendKeys(FundsTransferPage.transactionAmountSelfAccount(),TestData.getConfig("amount"));
			
			String exchangeratevalidation = TestData.getConfig("exchangeratevalidation");

			//Exchange Rate Validation
			if ((exchangeratevalidation.equalsIgnoreCase("yes"))) {
				if(TestData.getConfig("customertype").equalsIgnoreCase("PAM"))
					CommonDef.exchangeratevalidation("Elite"+TestData.getConfig("transfercurrency"));
				else if(TestData.getConfig("customertype").equalsIgnoreCase("Staff"))
					CommonDef.exchangeratevalidation("Staff"+TestData.getConfig("transfercurrency"));
				else
					CommonDef.exchangeratevalidation(TestData.getConfig("transfercurrency"));
			}	
			
			// Select the Transaction Frequency
			MethodDef.click(FundsTransferPage.transactionFrequency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frequency"));
			CommonDef.captureScreen();
			
			CommonDef.flipUp();
			CommonDef.flipUpHalf();
			
			//Select the Transfer Reason
			CommonDef.sendKeys(FundsTransferPage.transactionReasonSelfAccount(), TestData.getConfig("reason"));
			
			// Flip up
			//CommonDef.flipUp();
			//CommonDef.captureScreen();
			
			//Enter the Transaction Date
			//Code to enter the Transaction date

			//Click on Proceed Button
			MethodDef.click(FundsTransferPage.proceedButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),FundsTransferPage.confirmButton());
			CommonDef.waitVisible(FundsTransferPage.confirmButton());
			CommonDef.waitClickable(FundsTransferPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Scroll up
			CommonDef.flipUp();
			
			//Click on Confirm Button
			MethodDef.click(FundsTransferPage.confirmButton());
			CommonDef.waitClickable(FundsTransferPage.transactionMessage());
			
			CommonDef.valuePrint(FundsTransferPage.transactionReference());
			CommonDef.valuePrint(FundsTransferPage.transactionMessage());
			
			if ((exchangeratevalidation.equalsIgnoreCase("yes"))) {
				exchangeRate =  CommonDef.valuereturn(FundsTransferPage.transactionExchangeRate());
				}
			CommonDef.writeTransactionValues(exchangeRate,TestData.getConfig("DataBinding"),"Transaction Exchange Rate");
			
			String validation= CommonDef.messageValidation(FundsTransferPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	RakMoneyTransfer 
	   * Use					:	Function to perform a RAK Money Transfer transaction and store the transaction references
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void RakMoneyTransfer(){

		try {
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			//CLick on Send money option
			MethodDef.click(LandingPage.sendMoney());
			Thread.sleep(1500);
			
			//Click on RAK Money Option
			CommonDef.waitClickable(RakMoneyTransferPage.rakMoneyLink());
			MethodDef.click(RakMoneyTransferPage.rakMoneyLink());
			
			//Select the To Account
			CommonDef.waitClickable(RakMoneyTransferPage.toAcc());
			CommonDef.captureScreen();
			MethodDef.click(RakMoneyTransferPage.toAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("toaccount"));
			
			//Select the From Account
			CommonDef.waitVisibleNoError(RakMoneyTransferPage.fromAcc());
			CommonDef.waitClickable(RakMoneyTransferPage.fromAcc());
			MethodDef.click(RakMoneyTransferPage.fromAcc());
			CommonDef.dropDownSelectRakMoney(TestData.getConfig("fromaccount"));
			Thread.sleep(2000); //Sleep for the balance to load
			
			//wait for the balance to load
			String fromcurrurency[] = TestData.getConfig("fromaccount").split("-");
			CommonDef.balanceLoadingWaitOutsideUae(RakMoneyTransferPage.availableBalance(fromcurrurency[2]));
						
			//Select the Transaction Currency
			MethodDef.click(RakMoneyTransferPage.transactionCurrency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("transfercurrency"));
			CommonDef.flipUpHalf();
			
			//Enter the Transaction Amount
			CommonDef.sendKeys(RakMoneyTransferPage.transactionAmount(), TestData.getConfig("amount"));
						
			//Exchange Rate Validation
			if(TestData.getConfig("customertype").equalsIgnoreCase("PAM"))
				CommonDef.exchangeratevalidationRAKMoney("Elite"+TestData.getConfig("remittancecurrency"));
			else if(TestData.getConfig("customertype").equalsIgnoreCase("Staff"))
				CommonDef.exchangeratevalidationRAKMoney("Staff"+TestData.getConfig("remittancecurrency")); 
			else
				CommonDef.exchangeratevalidationRAKMoney(TestData.getConfig("remittancecurrency"));
			
			CommonDef.captureScreen();
			CommonDef.flipUp();
			
			// Select the Transaction Frequency
			MethodDef.click(RakMoneyTransferPage.transactionFrequency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frequency"));
			CommonDef.captureScreen();
						
			//Enter the Transaction Date
			//Code to enter the Transaction date
						
			CommonDef.flipUp();
			//Utilities.ScreenShotMobile(driver);
						
			// Select the Transfer Charges
			MethodDef.click(RakMoneyTransferPage.transactionCharges());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("charges"));		
						
			//Select the Transfer Purpose
			if(!(TestData.getConfig("purpose").equalsIgnoreCase("NULL"))){
			MethodDef.click(RakMoneyTransferPage.transactionPurpose());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("purpose"));
			}
			
			//Select the Transfer Reason
			MethodDef.click(RakMoneyTransferPage.transactionReason());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("reason"));

			// Flip up
			CommonDef.captureScreen();
						
			//Click on Proceed Button
			CommonDef.flipUp();
			MethodDef.click(RakMoneyTransferPage.proceedButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),FundsTransferPage.confirmButton());
			CommonDef.waitClickable(RakMoneyTransferPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
						
			//Scroll up
			CommonDef.flipUp();
			CommonDef.captureScreen();
			CommonDef.flipUp();
			
			//Click on Confirm Button
			MethodDef.click(RakMoneyTransferPage.confirmButton());
			CommonDef.waitClickable(RakMoneyTransferPage.transactionMessage());
						
			CommonDef.valuePrint(RakMoneyTransferPage.transactionReference());
			CommonDef.valuePrint(RakMoneyTransferPage.transactionMessage());
			if(!(TestData.getConfig("transfercurrency").equalsIgnoreCase(TestData.getConfig("remittancecurrency")))){
				exchangeRate =  CommonDef.valuereturn(FundsTransferPage.transactionExchangeRate());
				}
			chargesCollected = CommonDef.valuereturn(FundsTransferPage.transactionChargesCollected());
			CommonDef.writeTransactionValues(chargesCollected,TestData.getConfig("DataBinding"),"Transaction Charges");
			CommonDef.writeTransactionValues(exchangeRate,TestData.getConfig("DataBinding"),"Transaction Exchange Rate");
			
			String validation= CommonDef.messageValidation(RakMoneyTransferPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	TransactionHistorySelfAccount 
	   * Use					:	Function to perform a History View of the Transaction performed last between self accounts
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void TransactionHistorySelfAccount(){

		try {
			// CLick on History Link
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			MethodDef.click(LandingPage.sendMoney());
			CommonDef.waitClickable(HistoryPage.historyButton());
			MethodDef.click(HistoryPage.historyButton());
			Thread.sleep(1000);
			CommonDef.waitClickable(HistoryPage.dropDownArrowSelfAccount(TestData.getConfig("toaccount")));
			CommonDef.captureScreen();
			
			//Click on Drop down Button
			MethodDef.click(HistoryPage.dropDownArrowSelfAccount(TestData.getConfig("toaccount")));
			CommonDef.waitClickable(HistoryPage.viewDetailsLink(TestData.getConfig("toaccount")));
			
			//Click on View Details Button
			MethodDef.click(HistoryPage.viewDetailsLink(TestData.getConfig("toaccount")));
			CommonDef.waitClickable(HistoryPage.transactionStatus());
			CommonDef.captureScreen();
			
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.valuePrint(HistoryPage.transactionReference());
			CommonDef.valuePrint(HistoryPage.transactionStatus());	
			CommonDef.valuePrint(HistoryPage.transactionStatus());
			String transactionMessage = CommonDef.valuereturn(HistoryPage.transactionStatus());
			reference = CommonDef.valuereturn(HistoryPage.transactionReference());
			
			//CommonDef.WritevalueSelfAccounts(reference,exchangeRate);
			CommonDef.writeTransactionValues(reference,TestData.getConfig("DataBinding"),"Transaction Reference");
			//CommonDef.writeTransactionValues(coreReference,TestData.getConfig("DataBinding"),"Core Banking Reference");
			CommonDef.writeTransactionValues(transactionMessage,TestData.getConfig("DataBinding"),"Transaction Status");
			
			ExReporter.log(LogStatus.PASS,"View History Completed Successfully");
			chargesCollected="-";
			exchangeRate="-";
			reference="-";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	TransactionHistory 
	   * Use					:	Function to perform a History View of the Transaction performed last
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void TransactionHistory(){

		try {
			// CLick on History Link
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			MethodDef.click(LandingPage.sendMoney());
			CommonDef.waitVisible(HistoryPage.historyButton());
			CommonDef.waitClickable(HistoryPage.historyButton());
			MethodDef.click(HistoryPage.historyButton());
			Thread.sleep(1000);
			CommonDef.waitClickable(HistoryPage.dropDownArrow(TestData.getConfig("toaccount")));
			CommonDef.captureScreen();
			
			//Click on Drop down Button
			MethodDef.click(HistoryPage.dropDownArrow(TestData.getConfig("toaccount")));
			CommonDef.waitClickable(HistoryPage.viewDetailsLink(TestData.getConfig("toaccount")));
			
			//Click on View Details Button
			MethodDef.click(HistoryPage.viewDetailsLink(TestData.getConfig("toaccount")));
			CommonDef.waitClickable(HistoryPage.transactionStatus());
			CommonDef.captureScreen();
			
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.valuePrint(HistoryPage.transactionReference());
			CommonDef.valuePrint(HistoryPage.transactionStatus());	
			CommonDef.valuePrint(HistoryPage.transactionCoreRefrence());	
			String mobileReference = CommonDef.valuereturn(HistoryPage.transactionReference());
			String coreReference = CommonDef.valuereturn(HistoryPage.transactionCoreRefrence());
			String transactionMessage = CommonDef.valuereturn(HistoryPage.transactionStatus());
			
			//CommonDef.Writevalue(HistoryPage.transactionReference(),HistoryPage.transactionCoreRefrence());
			//CommonDef.Writevalue(HistoryPage.transactionReference(),HistoryPage.transactionCoreRefrence(),exchangeRate,chargesCollected);
			CommonDef.writeTransactionValues(mobileReference,TestData.getConfig("DataBinding"),"Transaction Reference");
			CommonDef.writeTransactionValues(coreReference,TestData.getConfig("DataBinding"),"Core Banking Reference");
			CommonDef.writeTransactionValues(transactionMessage,TestData.getConfig("DataBinding"),"Transaction Status");
			
			ExReporter.log(LogStatus.PASS,"View History Completed Successfully");
			chargesCollected="-";
			exchangeRate="-";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	Logout 
	   * Use					:	Function to perform a Logout Action and waits for the user login
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void Logout(){

		try {
			CommonDef.waitClickable(LandingPage.logOutButton());
			MethodDef.click(LandingPage.logOutButton());
			CommonDef.waitClickable(LandingPage.logoutOkButton());
			MethodDef.click(LandingPage.logoutOkButton());
			if(ConfigProvider.getConfig("OS").equalsIgnoreCase("IOS")){
				CommonDef.waitVisible(LoginPage.userID());
				CommonDef.waitClickable(LoginPage.userID());
			}
			ExReporter.log(LogStatus.PASS,"Logged Out Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	transactionHistoryPayBill 
	   * Use					:	Function to perform a History View of the Pay BillTransaction performed last
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void transactionHistoryPayBill(){

		try {
			
			CommonDef.waitVisible(LandingPage.payBills());
			CommonDef.waitClickable(LandingPage.payBills());
			MethodDef.click(LandingPage.payBills());
			
			// CLick on History Link
			CommonDef.waitClickable(HistoryPage.historyButton());
			MethodDef.click(HistoryPage.historyButton());
			Thread.sleep(1000);
			CommonDef.waitClickable(HistoryPage.dropDownArrow(TestData.getConfig("nickname")));
			CommonDef.captureScreen();
			
			//Click on Drop down Button
			MethodDef.click(HistoryPage.dropDownArrow(TestData.getConfig("nickname")));
			CommonDef.waitClickable(HistoryPage.viewDetailsLinkPayBill(TestData.getConfig("nickname")));
			
			//Click on View Details Button
			MethodDef.click(HistoryPage.viewDetailsLinkPayBill(TestData.getConfig("nickname")));
			CommonDef.waitClickable(HistoryPage.transactionStatus());
			CommonDef.captureScreen();
			
			CommonDef.flipUp();
			
			CommonDef.captureScreen();
			
			CommonDef.valuePrint(HistoryPage.transactionReference());
			CommonDef.valuePrint(HistoryPage.transactionStatus());
			CommonDef.valuePrint(HistoryPage.transactionBillRefrence());
			
			String mobileReference = CommonDef.valuereturn(HistoryPage.transactionBillRefrence());
			String coreReference = CommonDef.valuereturn(HistoryPage.transactionReference());
			String transactionMessage = CommonDef.valuereturn(HistoryPage.transactionStatus());
			
			//CommonDef.Writevalue(HistoryPage.transactionReference(),HistoryPage.transactionBillRefrence(),"-","-");
			CommonDef.writeTransactionValues(mobileReference,TestData.getConfig("DataBinding"),"Transaction Reference");
			CommonDef.writeTransactionValues(coreReference,TestData.getConfig("DataBinding"),"Core Banking Reference");
			CommonDef.writeTransactionValues(transactionMessage,TestData.getConfig("DataBinding"),"Transaction Status");
				
			ExReporter.log(LogStatus.PASS,"View History Completed Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	TransactionHistoryMobileCash 
	   * Use					:	Function to perform a History View of the Mobile Cash Transaction performed last
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void TransactionHistoryMobileCash(){

		try {
			// CLick on History Link
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			MethodDef.click(LandingPage.sendMoney());
			CommonDef.waitClickable(HistoryPage.historyButton());
			MethodDef.click(HistoryPage.historyButton());
			Thread.sleep(1000);
			CommonDef.waitClickable(MobileCashPage.mobileCashLink());
			MethodDef.click(MobileCashPage.mobileCashLink());
			CommonDef.waitClickable(HistoryPage.dropDownArrowMobileCash());
			CommonDef.captureScreen();
			
			//Click on Drop down Button
			MethodDef.click(HistoryPage.dropDownArrowMobileCash());
			CommonDef.waitClickable(HistoryPage.viewDetailsLinkMobileCash(TestData.getConfig("toaccount")));
			
			//Click on View Details Button
			MethodDef.click(HistoryPage.viewDetailsLinkMobileCash(TestData.getConfig("toaccount")));
			CommonDef.waitClickable(HistoryPage.transactionStatus());
			CommonDef.captureScreen();
			
			CommonDef.valuePrint(HistoryPage.transactionReferenceMobileCash());
			CommonDef.valuePrint(HistoryPage.transactionStatus());	
			String mobileReference = CommonDef.valuereturn(HistoryPage.transactionReferenceMobileCash());
			String transactionMessage = CommonDef.valuereturn(HistoryPage.transactionStatus());
			
			//CommonDef.WritevalueSelfAccounts(HistoryPage.transactionReferenceMobileCash(),"-");
			CommonDef.writeTransactionValues(mobileReference,TestData.getConfig("DataBinding"),"Transaction Reference");
			CommonDef.writeTransactionValues(transactionMessage,TestData.getConfig("DataBinding"),"Transaction Status");
			
			ExReporter.log(LogStatus.PASS,"View History Completed Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	payBill 
	   * Use					:	Function to perform a Pay Bill Transaction for EMAAR, DU etc
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*

	public static void payBill(){

		try {
			//CLick on Pay Bills option
			CommonDef.waitVisible(LandingPage.payBills());
			CommonDef.waitClickable(LandingPage.payBills());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			MethodDef.click(LandingPage.payBills());
			Thread.sleep(1500);
			
			//Navigate to Payee's page
			CommonDef.waitClickable(PayBillsPage.payeesButton());
			MethodDef.click(PayBillsPage.payeesButton());
			
			//Select the Payee
			CommonDef.waitClickable(PayBillsPage.createPayee());
			CommonDef.captureScreen();
			CommonDef.scrollPayees(PayBillsPage.toPayee(TestData.getConfig("nickname")));
			//MethodDef.click(PayBillsPage.toPayee(TestData.getConfig("nickname")));
			
			//Click on Pay Link	
			CommonDef.waitClickable(PayBillsPage.payButton(TestData.getConfig("nickname")));
			MethodDef.click(PayBillsPage.payButton(TestData.getConfig("nickname")));
			 
			//Select the From Account
			CommonDef.beneficiaryValidation(PayBillsPage.fromAccount());
			MethodDef.click(PayBillsPage.fromAccount());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			//CommonDef.waitClickable(PayBillsPage.payAmount());
			
			//wait for the balance to load
			String fromcurrurency[] = TestData.getConfig("fromaccount").split("-");
			CommonDef.balanceLoadingWait(PayBillsPage.availableBalance(fromcurrurency[2]));
			
			//if Custom Amount is present
			CommonDef.handleCustomAmount(PayBillsPage.customeRadio());
			CommonDef.captureScreen();
			
			//Enter the Payment Amount
			CommonDef.waitClickable(PayBillsPage.payAmount());
			CommonDef.sendKeys(PayBillsPage.payAmount(),TestData.getConfig("amount"));
			
			//Enter the Frequency
			MethodDef.click(PayBillsPage.transactionFrequency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frequency"));
			CommonDef.captureScreen();
			
			//Enter the Transaction Date
			//Code to enter the Transaction date
			
			CommonDef.flipUp();
			
			//Click on Proceed Button
			MethodDef.click(PayBillsPage.proceedButton());
			CommonDef.waitClickable(PayBillsPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(PayBillsPage.confirmButton());
			CommonDef.waitClickable(PayBillsPage.OKButton());
			CommonDef.captureScreen();
			MethodDef.click(PayBillsPage.OKButton());
			CommonDef.waitClickOK(PayBillsPage.OKButton(), PayBillsPage.doneButton());
			
			CommonDef.waitClickable(PayBillsPage.doneButton());
			CommonDef.captureScreen();
			CommonDef.valuePrint(PayBillsPage.transactionReference());
			CommonDef.valuePrint(PayBillsPage.transactionMessage());
			CommonDef.valuePrint(PayBillsPage.transactionStatus());
			
			String mobileReference = CommonDef.valuereturn(PayBillsPage.transactionReference());
			//String transactionMessage = CommonDef.valuereturn(PayBillsPage.transactionMessage());
			String transactionMessage = CommonDef.valuereturn(PayBillsPage.transactionStatus());
			
			CommonDef.writeTransactionValues(mobileReference,TestData.getConfig("DataBinding"),"Transaction Reference");
			CommonDef.writeTransactionValues(transactionMessage,TestData.getConfig("DataBinding"),"Transaction Status");
			
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			String validation= CommonDef.messageValidation(PayBillsPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
			//Click on Done Button
			MethodDef.click(PayBillsPage.doneButton());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	payOwnCard 
	   * Use					:	Function to perform a Payment of Own Cards Transaction 
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void payOwnCard(){

		try {
			//CLick on Pay Cards option
			CommonDef.waitVisible(LandingPage.payCards());
			CommonDef.waitClickable(LandingPage.payCards());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			MethodDef.click(LandingPage.payCards());
			Thread.sleep(1500);
			
			//Select the Card Type
			CommonDef.waitClickable(PayCardsPage.cardPaymentType());
			CommonDef.captureScreen();
			MethodDef.click(PayCardsPage.cardPaymentType());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardtype"));
			
			//Select the Card Number
			CommonDef.waitClickable(PayCardsPage.cardNumber());
			MethodDef.click(PayCardsPage.cardNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));
			
			//Select the From Account
			CommonDef.waitClickable(PayCardsPage.fromAccount());
			MethodDef.click(PayCardsPage.fromAccount());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			//CommonDef.waitClickable(PayBillsPage.payAmount());
			CommonDef.captureScreen();
			
			//Flip up
			CommonDef.flipUp();
			//CommonDef.flipUpHalf();
			
			//Select the Other Amount Option
			MethodDef.click(PayCardsPage.otherAmountOption());
			Thread.sleep(1000);
			
			//Flip up
			CommonDef.flipUp();
			//CommonDef.flipUp();
			
			//Enter the Amount
			CommonDef.sendKeys(PayCardsPage.payAmount(),TestData.getConfig("amount"));
			
			//Enter the Remarks
			CommonDef.sendKeys(PayCardsPage.payRemarksOwnCard(),TestData.getConfig("remarks"));	
			CommonDef.captureScreen();
			
			//Click on Proceed
			MethodDef.click(PayCardsPage.proceedButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),PayCardsPage.confirmButton());
			CommonDef.waitVisible(PayCardsPage.confirmButton());
			CommonDef.waitClickable(PayCardsPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(PayCardsPage.confirmButton());
			CommonDef.waitClickable(PayCardsPage.transactionMessage());
			
			CommonDef.valuePrint(PayCardsPage.transactionReference());
			CommonDef.valuePrint(PayCardsPage.transactionMessage());
			
			String mobileReference = CommonDef.valuereturn(PayCardsPage.transactionReference());
			String transactionMessage = CommonDef.valuereturn(PayCardsPage.transactionMessage());
			
			CommonDef.writeTransactionValues(mobileReference,TestData.getConfig("DataBinding"),"Transaction Reference");
			CommonDef.writeTransactionValues(transactionMessage,TestData.getConfig("DataBinding"),"Transaction Status");
			
			
			String validation= CommonDef.messageValidation(PayCardsPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	payOtherRakCard 
	   * Use					:	Function to perform a Payment of Other RAK Card Transaction 
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void payOtherRakCard(){

		try {
			//CLick on Pay Cards option
			CommonDef.waitVisible(LandingPage.payCards());
			CommonDef.waitClickable(LandingPage.payCards());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			MethodDef.click(LandingPage.payCards());
			Thread.sleep(1500);
			
			//Select the Card Type
			CommonDef.waitClickable(PayCardsPage.cardPaymentType());
			CommonDef.captureScreen();
			MethodDef.click(PayCardsPage.cardPaymentType());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardtype"));
			
			//Select the Card Payee
			CommonDef.waitVisibleNoError(PayCardsPage.cardPayee());
			CommonDef.waitClickable(PayCardsPage.cardPayee());
			MethodDef.click(PayCardsPage.cardPayee());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardpayee"));
			
			//Select the From Account
			CommonDef.waitClickable(PayCardsPage.fromAccount());
			//Thread.sleep(3000);
			MethodDef.click(PayCardsPage.fromAccount());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			//CommonDef.waitClickable(PayBillsPage.payAmount());
			
			//wait for the balance to load
			String fromcurrurency[] = TestData.getConfig("fromaccount").split("-");
			CommonDef.balanceLoadingWaitOutsideUae(PayCardsPage.availableBalance(fromcurrurency[2]));
			CommonDef.captureScreen();
			
			//Flip up
			CommonDef.flipUp();
	
			//Enter the Amount
			CommonDef.sendKeys(PayCardsPage.payAmountOtherRakCard(),TestData.getConfig("amount"));
			
			//Enter the Remarks
			CommonDef.sendKeys(PayCardsPage.payremarks(),TestData.getConfig("remarks"));
			CommonDef.captureScreen();
			
			//Click on Proceed
			MethodDef.click(PayCardsPage.proceedButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),PayCardsPage.confirmButton());
			CommonDef.waitVisible(PayCardsPage.confirmButton());
			CommonDef.waitClickable(PayCardsPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(PayCardsPage.confirmButton());
			CommonDef.waitClickable(PayCardsPage.transactionMessage());
			
			CommonDef.valuePrint(PayCardsPage.transactionReference());
			CommonDef.valuePrint(PayCardsPage.transactionMessage());
			String validation= CommonDef.messageValidation(PayCardsPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	payOutsideBankCard 
	   * Use					:	Function to perform a Payment of Outside Bank Card Transaction 
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void payOutsideBankCard(){

		try {
			//CLick on Pay Cards option
			CommonDef.waitVisible(LandingPage.payCards());
			CommonDef.waitClickable(LandingPage.payCards());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			MethodDef.click(LandingPage.payCards());
			Thread.sleep(1500);
			
			//Select the Card Type
			CommonDef.waitClickable(PayCardsOutsideBankPage.cardPaymentType());
			CommonDef.captureScreen();
			MethodDef.click(PayCardsOutsideBankPage.cardPaymentType());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardtype"));
			
			//Select the Card Payee
			CommonDef.waitVisibleNoError(PayCardsOutsideBankPage.cardPayee());
			CommonDef.waitClickable(PayCardsOutsideBankPage.cardPayee());
			MethodDef.click(PayCardsOutsideBankPage.cardPayee());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardpayee"));
			
			//Select the From Account
			CommonDef.waitClickable(PayCardsOutsideBankPage.fromAccount());
			//Thread.sleep(3000);
			MethodDef.click(PayCardsOutsideBankPage.fromAccount());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			//CommonDef.waitClickable(PayBillsPage.payAmount());
			
			//wait for the balance to load
			CommonDef.balanceLoadingWaitOutsideUae(PayCardsOutsideBankPage.availableBalance(TestData.getConfig("transfercurrency")));
			
			// Select the Remittance Currency
			MethodDef.click(PayCardsOutsideBankPage.remitCurrency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("remittancecurrency"));
			
			//Select the Transaction Currency
			MethodDef.click(PayCardsOutsideBankPage.transactionCurrency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("transfercurrency"));
			
			//Enter the Transaction Amount
			CommonDef.sendKeys(PayCardsOutsideBankPage.transactionAmount(), TestData.getConfig("amount"));
			
			//Exchange Rate Validation
			if (!(TestData.getConfig("transfercurrency").equalsIgnoreCase(TestData.getConfig("remittancecurrency")))) {
				if (TestData.getConfig("customertype").equalsIgnoreCase("PAM"))
					CommonDef.exchangeRateValidationCards("Elite" + TestData.getConfig("remittancecurrency"));
				else if (TestData.getConfig("customertype").equalsIgnoreCase("Staff"))
					CommonDef.exchangeRateValidationCards("Staff" + TestData.getConfig("remittancecurrency"));
				else
					CommonDef.exchangeRateValidationCards(TestData.getConfig("remittancecurrency"));
			}else {
				MethodDef.click(PayCardsOutsideBankPage.transactionFrequency());
				CommonDef.waitElement(FundsTransferPage.cancelButton());
				MethodDef.click(FundsTransferPage.cancelButton());
			}
			CommonDef.captureScreen();
			CommonDef.flipUp();
			//Utilities.ScreenShotMobile(driver);
			
			// Select the Transaction Frequency
			MethodDef.click(PayCardsOutsideBankPage.transactionFrequency());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frequency"));
			
			//Enter the Transaction Date
			//Code to enter the Transaction date
	
			// Select the Transfer Charges
			MethodDef.click(PayCardsOutsideBankPage.transactionCharges());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("charges"));		
			
			//Select the Transfer Reason
			if(!(TestData.getConfig("reason").equalsIgnoreCase("NULL"))){
			MethodDef.click(PayCardsOutsideBankPage.transactionReason());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("reason"));
			}
			CommonDef.captureScreen();
			// Flip up
			CommonDef.flipUp();
			//CommonDef.captureScreen();
			
			//Click on Proceed Button
			MethodDef.click(PayCardsOutsideBankPage.proceedButton());
			//CommonDef.waitClickable(PayCardsOutsideBankPage.confirmButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),PayCardsOutsideBankPage.confirmButton());
			CommonDef.waitVisibleNoError(PayCardsOutsideBankPage.confirmButton());
			CommonDef.waitVisible(PayCardsOutsideBankPage.confirmButton());
			CommonDef.waitClickable(PayCardsOutsideBankPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Scroll up
			CommonDef.flipUp();
			CommonDef.captureScreen();
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			CommonDef.waitClickable(PayCardsOutsideBankPage.confirmButton());
			MethodDef.click(PayCardsOutsideBankPage.confirmButton());
			CommonDef.waitClickable(PayCardsOutsideBankPage.transactionMessage());
			
			CommonDef.valuePrint(PayCardsOutsideBankPage.transactionReference());
			CommonDef.valuePrint(PayCardsOutsideBankPage.transactionMessage());
			
			if(!(TestData.getConfig("transfercurrency").equalsIgnoreCase(TestData.getConfig("remittancecurrency")))){
				exchangeRate =  CommonDef.valuereturn(PayCardsOutsideBankPage.transactionExchangeRate());
				}
			chargesCollected = CommonDef.valuereturn(PayCardsOutsideBankPage.transactionChargesCollected());
			
			CommonDef.writeTransactionValues(chargesCollected,TestData.getConfig("DataBinding"),"Transaction Charges");
			CommonDef.writeTransactionValues(exchangeRate,TestData.getConfig("DataBinding"),"Transaction Exchange Rate");
			
			String validation= CommonDef.messageValidation(PayCardsOutsideBankPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
			//Scroll up
			CommonDef.flipUp();
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	payPrepaidCard 
	   * Use					:	Function to perform a Payment of Prepaid Card Transaction 
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*

	public static void payPrepaidCard(){

		try {
			//CLick on Pay Cards option
			CommonDef.waitVisible(LandingPage.payCards());
			CommonDef.waitClickable(LandingPage.payCards());
			
			//To perform balance validation
			CommonDef.balanceValidation();
			
			MethodDef.click(LandingPage.payCards());
			Thread.sleep(1500);
			
			//Select the Card Type
			CommonDef.waitClickable(Pay_PrepaidCard_Page.cardPaymentType());
			CommonDef.captureScreen();
			MethodDef.click(Pay_PrepaidCard_Page.cardPaymentType());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardtype"));
			
			//Select the Card Payee
			CommonDef.waitClickable(Pay_PrepaidCard_Page.cardPayee());
			MethodDef.click(Pay_PrepaidCard_Page.cardPayee());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardpayee"));
			
			//Select the From Account
			CommonDef.waitClickable(Pay_PrepaidCard_Page.fromAccount());
			MethodDef.click(Pay_PrepaidCard_Page.fromAccount());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			//CommonDef.waitClickable(PayBillsPage.payAmount());
	
			//wait for the balance to load
			CommonDef.balanceLoadingWaitOutsideUae(Pay_PrepaidCard_Page.availableBalance(TestData.getConfig("transfercurrency")));
			CommonDef.captureScreen();
			
			//Enter the Amount
			CommonDef.sendKeys(Pay_PrepaidCard_Page.payAmount(),TestData.getConfig("amount"));
			
			// Select the Transaction Frequency
			MethodDef.click(Pay_PrepaidCard_Page.payFrequncy());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frequency"));

			//Scroll up
			CommonDef.flipUp();
			
			//Enter the Reason
			CommonDef.sendKeys(Pay_PrepaidCard_Page.payRemarks(),TestData.getConfig("reason"));
			CommonDef.captureScreen();
			
			//Click on Proceed
			MethodDef.click(Pay_PrepaidCard_Page.proceedButton());
			CommonDef.waitClickContinue(FundsTransferPage.continueButton(),Pay_PrepaidCard_Page.confirmButton());
			CommonDef.waitClickable(Pay_PrepaidCard_Page.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(Pay_PrepaidCard_Page.confirmButton());
			CommonDef.waitClickable(Pay_PrepaidCard_Page.transactionMessage());
			
			CommonDef.valuePrint(Pay_PrepaidCard_Page.transactionReference());
			CommonDef.valuePrint(Pay_PrepaidCard_Page.transactionMessage());
			String validation= CommonDef.messageValidation(Pay_PrepaidCard_Page.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	transactionHistoryPayCards 
	   * Use					:	Function to perform a View History Transaction for the Payment of RAK Cards
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void transactionHistoryPayCards(){

		try {
			
			// CLick on History Link
			CommonDef.waitClickable(HistoryPage.historyButton());
			MethodDef.click(HistoryPage.historyButton());
			Thread.sleep(1000);
			CommonDef.waitClickable(HistoryPage.dropDownArrowPayCards());
			CommonDef.captureScreen();
			
			//Click on Drop down Button
			MethodDef.click(HistoryPage.dropDownArrowPayCards());
			CommonDef.waitClickable(HistoryPage.viewDetailsLink(TestData.getConfig("nickname")));
			
			//Click on View Details Button
			MethodDef.click(HistoryPage.viewDetailsLink(TestData.getConfig("nickname")));
			CommonDef.waitClickable(HistoryPage.transactionStatus());
			CommonDef.captureScreen();
			
			CommonDef.flipUp();
			
			CommonDef.captureScreen();
			
			CommonDef.valuePrint(HistoryPage.transactionReference());
			CommonDef.valuePrint(HistoryPage.transactionStatus());
			
			String mobileReference = CommonDef.valuereturn(HistoryPage.transactionReference());
			String transactionMessage = CommonDef.valuereturn(HistoryPage.transactionStatus());
			
			CommonDef.writeTransactionValues(mobileReference,TestData.getConfig("DataBinding"),"Transaction Reference");
			CommonDef.writeTransactionValues(transactionMessage,TestData.getConfig("DataBinding"),"Transaction Status");
			
			//CommonDef.WritevalueSelfAccounts(HistoryPage.transactionReference(),"-");
	
			ExReporter.log(LogStatus.PASS,"View History Completed Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	transactionHistoryPayOtherBankCards 
	   * Use					:	Function to perform a View History Transaction for the Payment of Other Bank Cards
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void transactionHistoryPayOtherBankCards(){

		try {
			
			// CLick on History Link
			CommonDef.waitClickable(HistoryPage.historyButton());
			MethodDef.click(HistoryPage.historyButton());
			Thread.sleep(1000);
			CommonDef.waitClickable(HistoryPage.dropDownArrowPayCards());
			CommonDef.captureScreen();
			
			//Click on Drop down Button
			MethodDef.click(HistoryPage.dropDownArrowPayCards());
			CommonDef.waitClickable(HistoryPage.viewDetailsLink(TestData.getConfig("cardpayee")));
			
			//Click on View Details Button
			MethodDef.click(HistoryPage.viewDetailsLink(TestData.getConfig("cardpayee")));
			CommonDef.waitClickable(HistoryPage.transactionStatus());
			CommonDef.captureScreen();
			
			CommonDef.flipUp();
			
			CommonDef.captureScreen();
			
			CommonDef.valuePrint(HistoryPage.transactionReference());
			CommonDef.valuePrint(HistoryPage.transactionStatus());
			
			String mobileReference = CommonDef.valuereturn(HistoryPage.transactionReference());
			String transactionMessage = CommonDef.valuereturn(HistoryPage.transactionStatus());
			
			CommonDef.writeTransactionValues(mobileReference,TestData.getConfig("DataBinding"),"Transaction Reference");
			CommonDef.writeTransactionValues(transactionMessage,TestData.getConfig("DataBinding"),"Transaction Status");

			//CommonDef.Writevalue(HistoryPage.transactionReference(),HistoryPage.transactionCoreRefrence(),exchangeRate,chargesCollected);
			ExReporter.log(LogStatus.PASS,"View History Completed Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	mobileCash 
	   * Use					:	Function to perform a Mobile Cash transaction
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void mobileCash(){

		try {
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			MethodDef.click(LandingPage.sendMoney());
			Thread.sleep(1500);
			
			//Click on Mobile Cash Option
			CommonDef.waitClickable(MobileCashPage.mobileCashLink());
			MethodDef.click(MobileCashPage.mobileCashLink());
			
			//Select the To Account
			CommonDef.waitClickable(MobileCashPage.toAcc());
			CommonDef.captureScreen();
			MethodDef.click(MobileCashPage.toAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("toaccount"));
			
			//Select the To Beneficiary
			if(TestData.getConfig("toaccount").contains("Beneficiary")){
			CommonDef.waitVisibleNoError(MobileCashPage.toAccBene());
			CommonDef.waitClickable(MobileCashPage.toAccBene());
			MethodDef.click(MobileCashPage.toAccBene());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("Transfer_To_Acc_Number"));
			}
			
			//Select the From Account
			CommonDef.waitVisibleNoError(MobileCashPage.fromAcc());
			CommonDef.waitClickable(MobileCashPage.fromAcc());
			MethodDef.click(MobileCashPage.fromAcc());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromaccount"));
			Thread.sleep(2000); //Sleep for the balance to load
			
			//wait for the balance to load
			String fromcurrurency[] = TestData.getConfig("fromaccount").split("-");
			CommonDef.balanceLoadingWaitOutsideUae(MobileCashPage.availableBalance(fromcurrurency[2]));
			CommonDef.captureScreen();
			
			//Enter the Transaction Amount
			CommonDef.waitClickable(MobileCashPage.transactionAmount());
			CommonDef.sendKeys(MobileCashPage.transactionAmount(),TestData.getConfig("amount"));

			CommonDef.captureScreen();
			
			//Click on Proceed Button
			MethodDef.click(MobileCashPage.proceedButton());
			CommonDef.waitClickable(MobileCashPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(MobileCashPage.confirmButton());
			CommonDef.waitClickable(MobileCashPage.transactionMessage());
			CommonDef.captureScreen();
			CommonDef.valuePrint(MobileCashPage.transactionMessage());
			String validation= CommonDef.messageValidation(MobileCashPage.transactionMessage());
			if(validation.equalsIgnoreCase("pass")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	serviceRequest 
	   * Use					:	Function to perform a Service Request - Duplicate Statement Request for an account
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void serviceRequest(){

		try {
			//CLick on Menu BUtton
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			Thread.sleep(1500);
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());
			
			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestAccounts());
			MethodDef.click(LandingPage.serviceRequestAccounts());
			
			//Select the Service Request for
			CommonDef.waitClickable(LandingPage.duplicateStatementRequest());
			
			
			if(TestData.getConfig("servicerequest").equalsIgnoreCase("Duplicate Statement")){
			MethodDef.click(LandingPage.duplicateStatementRequest());
			
			//Select the Operative Account
			CommonDef.waitClickable(ServiceRequestPage.accountSelect());
			MethodDef.click(ServiceRequestPage.accountSelect());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("operativeaccount"));
			 
			//Select the From date
			String fromdatedata[]=TestData.getConfig("fromdate").split("/");
			CommonDef.waitClickable(ServiceRequestPage.fromDate());
			MethodDef.click(ServiceRequestPage.fromDate());
			CommonDef.datePicker(fromdatedata[0],fromdatedata[1],fromdatedata[2]);

			//Select the To date
			String todatedata[]=TestData.getConfig("todate").split("/");
			CommonDef.waitClickable(ServiceRequestPage.toDate());
			MethodDef.click(ServiceRequestPage.toDate());
			CommonDef.datePicker(todatedata[0],todatedata[1],todatedata[2]);
			
			//Enter the Remarks
			MethodDef.click(ServiceRequestPage.remarks());
			CommonDef.sendKeys(ServiceRequestPage.remarks(),TestData.getConfig("remarks"));
			
			//Select the Dispatch Mode
			if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Branch")){
				MethodDef.click(ServiceRequestPage.radioBranch());
			}else if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Address")){
				MethodDef.click(ServiceRequestPage.radioAddress());
			}
			
			
			//Enter Additional Details
			if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Branch")){
				//Select Emirate
				CommonDef.waitClickable(ServiceRequestPage.selectEmirate());
				MethodDef.click(ServiceRequestPage.selectEmirate());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("emirate"));
				
				//Select Branch
				CommonDef.waitClickable(ServiceRequestPage.selectBranch());
				MethodDef.click(ServiceRequestPage.selectBranch());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("branch"));
			}
			
			CommonDef.captureScreen();
			
			//Click on Proceed Button
			MethodDef.click(ServiceRequestPage.proceedButton());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			//System.out.println(ref[1]);
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail"))
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			CommonDef.WritevalueServiceRequest(ref[1]);
			
			}else if(TestData.getConfig("servicerequest").equalsIgnoreCase("Cheque Book Request")){
				MethodDef.click(LandingPage.chequeBookRequest());
				
				//Select the Account
				CommonDef.waitClickable(ServiceRequestPage.accountSelectChequeBook());
				MethodDef.click(ServiceRequestPage.accountSelectChequeBook());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("operativeaccount"));
				
				//Select the Cheque Leaves
				CommonDef.waitClickable(ServiceRequestPage.chequeLeaves());
				MethodDef.click(ServiceRequestPage.chequeLeaves());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("chequeleaves"));
				
				//Select the Dispatch Mode
				if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Branch")){
					MethodDef.click(ServiceRequestPage.radioBranch());
				}else if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Address")){
					MethodDef.click(ServiceRequestPage.radioAddress());
				}
				
				if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Branch")){
					//Select Emirate
					CommonDef.waitClickable(ServiceRequestPage.selectEmirateChequeBook());
					MethodDef.click(ServiceRequestPage.selectEmirateChequeBook());
					CommonDef.dropDownSelectOutsideUae(TestData.getConfig("emirate"));
					
					//Select Branch
					CommonDef.waitClickable(ServiceRequestPage.selectBranchChequeBook());
					MethodDef.click(ServiceRequestPage.selectBranchChequeBook());
					CommonDef.dropDownSelectOutsideUae(TestData.getConfig("branch"));
				} else if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Address")){
					
					//Select Registered Address
					CommonDef.waitClickable(ServiceRequestPage.radioRegisteredAddress());
					MethodDef.click(ServiceRequestPage.radioRegisteredAddress());
					CommonDef.captureScreen();
					CommonDef.flipUp();
				}
				
				CommonDef.captureScreen();
				
				//Click on Proceed Button
				MethodDef.click(ServiceRequestPage.proceedButtonCheque());
				CommonDef.waitClickable(ServiceRequestPage.confirmButton());
				ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
				CommonDef.captureScreen();
				
				//Click on Confirm Button
				MethodDef.click(ServiceRequestPage.confirmButton());
				CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
				
				CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
				String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
				String ref[]= ref_msg.split("Reference ID is ");
				//System.out.println(ref[1]);
				String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
				if(!validation.equalsIgnoreCase("Fail"))
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				else
				ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
				CommonDef.WritevalueServiceRequest(ref[1]);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	accountSummary 
	   * Use					:	Function to perform a View Account Summary for the account
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void accountSummary(){

		try {
			
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform Account Summary Validation
			String accs=TestData.getConfig("accountnumbers");
			String account[] = accs.split("#");
			int n = account.length;
			for(int i=0 ; i<n ; i++){
			CommonDef.accountSummaryValidation(account[i]);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	accountDetails 
	   * Use					:	Function to perform a View Account Details for the account
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void accountDetails(){

		try {
			
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform Accounts Details Validation
			String accs=TestData.getConfig("accountnumbers");
			String account[] = accs.split("#");
			int n = account.length;
			for(int i=0 ; i<n ; i++){
			CommonDef.accountDetailsValidation(account[i]);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	transactionView 
	   * Use					:	Function to perform a View Account Details for the account
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void transactionView(){

		try {
			
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform Accounts Details Validation
			String accs=TestData.getConfig("accountnumbers");
			String account[] = accs.split("#");
			int n = account.length;
			for(int i=0 ; i<n ; i++){
			CommonDef.transactionViewValidation(account[i]);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	lodgedCheques 
	   * Use					:	Function to perform a View Lodged Cheques for the account
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void lodgedCheques(){

		try {
			
			//CLick on Send money option
			CommonDef.waitVisible(LandingPage.sendMoney());
			CommonDef.waitClickable(LandingPage.sendMoney());
			
			//To perform Accounts Details Validation
			String accs=TestData.getConfig("accountnumbers");
			String account[] = accs.split("#");
			int n = account.length;
			for(int i=0 ; i<n ; i++){
			CommonDef.viewlodgedChequesValidation(account[i]);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	advanceAgainstSalary 
	   * Use					:	Function to perform a Service Request - Advance Against Salary
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void advanceAgainstSalary(){

		try {
			//CLick on Menu BUtton
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			Thread.sleep(1500);
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());
			
			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestAccounts());
			MethodDef.click(LandingPage.serviceRequestAccounts());
			
			//Select the Service Request for Advance Against Salary
			CommonDef.waitClickable(LandingPage.advanceAgainstSalary());
			
			MethodDef.click(LandingPage.advanceAgainstSalary());
			
			//Select the Operative Account
			CommonDef.waitClickable(ServiceRequestPage.accountSelectAASalary());
			MethodDef.click(ServiceRequestPage.accountSelectAASalary());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("operativeaccount"));
			 
			//Enter the Amount
			CommonDef.waitClickable(ServiceRequestPage.AAAAmount());
			CommonDef.clearText(ServiceRequestPage.AAAAmount());
			MethodDef.click(ServiceRequestPage.AAAAmount());
			
			//Click on Proceed Button
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			//System.out.println(ref[1]);
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			//CommonDef.WritevalueServiceRequest(ref[1]);
			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	balanceConfirmation 
	   * Use					:	Function to perform a Service Request - Balance Confirmation
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void balanceConfirmation(){

		try {
			//CLick on Menu BUtton
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			Thread.sleep(1500);
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());
			
			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestAccounts());
			MethodDef.click(LandingPage.serviceRequestAccounts());
			
			//Select the Service Request for Balance Confirmation
			CommonDef.waitClickable(LandingPage.balanceConfirmation());
			MethodDef.click(LandingPage.balanceConfirmation());
			
			//Select the Operative Account
			CommonDef.waitClickable(ServiceRequestPage.accountSelectBalanceConfirmation());
			MethodDef.click(ServiceRequestPage.accountSelectBalanceConfirmation());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("operativeaccount"));
			 
			///Enter the Addressed To Field
			CommonDef.waitClickable(ServiceRequestPage.addressedTo());
			MethodDef.sendKeys(ServiceRequestPage.addressedTo(), TestData.getConfig("addressedto"));
			
			//Select the language
			if(TestData.getConfig("language").equalsIgnoreCase("English")){
				MethodDef.click(ServiceRequestPage.englishLanguage());
			}else{
				MethodDef.click(ServiceRequestPage.arabicLanguage());
			}
			
			//Enter the Address
			MethodDef.sendKeys(ServiceRequestPage.address(), TestData.getConfig("address"));
			
			//Select the Charges Account
			MethodDef.click(ServiceRequestPage.accountSelectChargesBalanceConfirmation());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("chargeaccount"));
			
			//Select the Dispatch Mode
			if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Address")){
				MethodDef.click(ServiceRequestPage.addressRadio());
			}else if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Auditor")){
				MethodDef.click(ServiceRequestPage.auditorRadio());
			}else if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Branch")){
				MethodDef.click(ServiceRequestPage.branchRadio());
			}
			
			//Flip up Screen
			CommonDef.flipUp();
			
			if(!(TestData.getConfig("dispatchmode").equalsIgnoreCase("Branch"))){
				MethodDef.sendKeys(ServiceRequestPage.mobileNumber(), TestData.getConfig("mobilenumber"));
				MethodDef.sendKeys(ServiceRequestPage.notes(), TestData.getConfig("notes"));
			}else{
					//Select Emirate
					CommonDef.waitClickable(ServiceRequestPage.selectEmirateChequeBook());
					MethodDef.click(ServiceRequestPage.selectEmirateChequeBook());
					CommonDef.dropDownSelectOutsideUae(TestData.getConfig("emirate"));
					
					//Select Branch
					CommonDef.waitClickable(ServiceRequestPage.selectBranchChequeBook());
					MethodDef.click(ServiceRequestPage.selectBranchChequeBook());
					CommonDef.dropDownSelectOutsideUae(TestData.getConfig("branch"));
			}
			
			//Click on Proceed Button
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			//System.out.println(ref[1]);
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			//CommonDef.WritevalueServiceRequest(ref[1]);
			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	demandDraft 
	   * Use					:	Function to perform a Service Request - Demand Draft
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void demandDraft(){

		try {
			//CLick on Menu BUtton
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			Thread.sleep(1500);
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());
			
			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestAccounts());
			MethodDef.click(LandingPage.serviceRequestAccounts());
			
			//Select the Service Request for Demand Draft
			CommonDef.waitClickable(LandingPage.demandDraft());
			MethodDef.click(LandingPage.demandDraft());
			
			//Select the From Account
			CommonDef.waitClickable(ServiceRequestPage.accountSelect());
			MethodDef.click(ServiceRequestPage.accountSelect());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("operativeaccount"));
			 
			//wait for the balance to load
			CommonDef.balanceLoadingWaitOutsideUae(ServiceRequestPage.availableBalance("AED"));
			
			//Select the Request for Option
			CommonDef.waitClickable(ServiceRequestPage.requestFor());
			MethodDef.click(ServiceRequestPage.requestFor());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("requestfor"));
			
			//Select the Beneficiary Name
			CommonDef.waitClickable(ServiceRequestPage.beneficiaryName());
			MethodDef.click(ServiceRequestPage.beneficiaryName());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("beneficiaryname"));
			
			CommonDef.captureScreen();
			
			//Enter the Amount
			CommonDef.sendKeys(ServiceRequestPage.DDamount(), TestData.getConfig("amount"));
			
			//Select the Purpose
			MethodDef.click(ServiceRequestPage.purpose());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("purpose"));
			
			//Select Emirate
			CommonDef.waitClickable(ServiceRequestPage.selectEmirateChequeBook());
			MethodDef.click(ServiceRequestPage.selectEmirateChequeBook());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("emirate"));
			
			//Select Branch
			CommonDef.waitClickable(ServiceRequestPage.selectBranchDD());
			MethodDef.click(ServiceRequestPage.selectBranchDD());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("branch"));

			CommonDef.captureScreen();
			CommonDef.flipUp();
			
			//Enter Authorise person details(Optional)
			if((TestData.getConfig("authoriseperson").equalsIgnoreCase("yes"))){
				MethodDef.click(ServiceRequestPage.checkAuthorise());
				CommonDef.flipUp();
				
				//Enter the Person Name
				CommonDef.sendKeys(ServiceRequestPage.authorisePersonName(), TestData.getConfig("authorisepersonname"));
				
				//Enter the Person Contact Number
				CommonDef.sendKeys(ServiceRequestPage.authorisePersonNumber(), TestData.getConfig("authorisepersonnumber"));
				
				//Select the ID Type
				//CommonDef.waitClickable(ServiceRequestPage.authorisePersonID());
				MethodDef.click(ServiceRequestPage.authorisePersonID());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("authorisepersonid"));
				CommonDef.captureScreen();
				
				//Enter the Person ID Number
				CommonDef.sendKeys(ServiceRequestPage.authorisePersonIDNumber(), TestData.getConfig("authorisepersonidnumber"));

			}
			
			//CommonDef.flipUp();
			CommonDef.captureScreen();
			
			//Click on Submit Button
			MethodDef.click(ServiceRequestPage.submitButtonDD());
			
			CommonDef.waitClickable(ServiceRequestPage.confirmButtonDD());
			
			CommonDef.flipUp();
			CommonDef.captureScreen();
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButtonDD());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			//System.out.println(ref[1]);
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");
			//CommonDef.WritevalueServiceRequest(ref[1]);
			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	redeemCashBack 
	   * Use					:	Function to perform a Service Request - Redeem Cash Back
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void redeemCashBack(){

		try {
			
			//CLick on Menu BUtton
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());
			
			if(TestData.getConfig("cardtype").equalsIgnoreCase("Debit Card")){
				
				//Select the Service Request Type Option
				CommonDef.waitClickable(LandingPage.serviceRequestDebitCards());
				MethodDef.click(LandingPage.serviceRequestDebitCards());

				//Select the Service Request for Redeem Cash Back
				CommonDef.waitClickable(LandingPage.redeemCashBack());
				MethodDef.click(LandingPage.redeemCashBack());

				//Select the Card Number
				CommonDef.waitClickable(ServiceRequestPage.cardNumberRedeemCashBack());
				MethodDef.click(ServiceRequestPage.cardNumberRedeemCashBack());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));
				
				//Enter the Amount
				CommonDef.clearText(ServiceRequestPage.redeemAmount());
				CommonDef.sendKeys(ServiceRequestPage.redeemAmount(), TestData.getConfig("amount"));
			}

			//Click on Proceed Button
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	blockCards 
	   * Use					:	Function to perform a Service Request - Block Cards
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void blockCards(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());
			
			if(TestData.getConfig("cardtype").equalsIgnoreCase("Debit Card")){
				
				//Select the Service Request Type Option
				CommonDef.waitClickable(LandingPage.serviceRequestDebitCards());
				MethodDef.click(LandingPage.serviceRequestDebitCards());

				//Select the Service Request for Block Cards
				CommonDef.waitClickable(LandingPage.blockDebitCard());
				MethodDef.click(LandingPage.blockDebitCard());

				//Select the Card Number
				CommonDef.waitClickable(ServiceRequestPage.cardNumber());
				MethodDef.click(ServiceRequestPage.cardNumber());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));
				
				//Select the Reason
				CommonDef.waitClickable(ServiceRequestPage.cardBlockReason());
				MethodDef.click(ServiceRequestPage.cardBlockReason());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("reason"));
				
				//Click on Proceed Button
				CommonDef.captureScreen();
				MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
				CommonDef.waitClickable(ServiceRequestPage.confirmButton());
				ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
				CommonDef.captureScreen();
				
				//Click on Confirm Button
				MethodDef.click(ServiceRequestPage.confirmButton());
				CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
				CommonDef.flipUp();
				CommonDef.captureScreen();
				
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
				CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
				String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
				String ref[]= ref_msg.split("Reference ID is ");
				
				String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
				if(!validation.equalsIgnoreCase("Fail")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
				}else
				ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

				CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
				
			} else if(TestData.getConfig("cardtype").equalsIgnoreCase("Credit Card")){
				
				//Select the Service Request Type Option
				CommonDef.waitClickable(LandingPage.serviceRequestCreditCards());
				MethodDef.click(LandingPage.serviceRequestCreditCards());

				//Select the Service Request for Block Cards
				CommonDef.waitClickable(LandingPage.blockCreditCard());
				MethodDef.click(LandingPage.blockCreditCard());

				//Select the Card Number
				CommonDef.waitClickable(ServiceRequestPage.creditCardNumber());
				MethodDef.click(ServiceRequestPage.creditCardNumber());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));
				
				//Select the Reason
				CommonDef.waitClickable(ServiceRequestPage.creditCardBlockReason());
				MethodDef.click(ServiceRequestPage.creditCardBlockReason());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("reason"));
				
				//Click on Continue Button
				CommonDef.captureScreen();
				MethodDef.click(ServiceRequestPage.continueCreditCardBlock());
				CommonDef.waitClickable(ServiceRequestPage.confirmButton());
				ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
				CommonDef.captureScreen();
				
				//Click on Submit Button
				MethodDef.click(ServiceRequestPage.submitButton());
				CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
				CommonDef.flipUp();
				CommonDef.captureScreen();
				
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
				CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
				String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
				String ref[]= ref_msg.split("Reference ID is ");
				
				String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
				if(!validation.equalsIgnoreCase("Fail")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
				}else
				ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

				CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			}

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	unblockCards 
	   * Use					:	Function to perform a Service Request - Unblock Cards
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void unblockCards(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());
			
			if(TestData.getConfig("cardtype").equalsIgnoreCase("Debit Card")){
				
				//Select the Service Request Type Option
				CommonDef.waitClickable(LandingPage.serviceRequestDebitCards());
				MethodDef.click(LandingPage.serviceRequestDebitCards());

				//Select the Service Request for Block Card
				CommonDef.waitClickable(LandingPage.unblockDebitCard());
				MethodDef.click(LandingPage.unblockDebitCard());

				//Select the Card Number
				CommonDef.waitClickable(ServiceRequestPage.cardNumber());
				MethodDef.click(ServiceRequestPage.cardNumber());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));
				
				//Select the Reason
				CommonDef.waitClickable(ServiceRequestPage.cardBlockReason());
				MethodDef.click(ServiceRequestPage.cardBlockReason());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("reason"));
			}

			//Click on Proceed Button
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.proceedButtonUnblockCard());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	replaceDebitCard 
	   * Use					:	Function to perform a Service Request - Replace Debit Cards for a customer
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void replaceDebitCard(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());
			
			if(TestData.getConfig("cardtype").equalsIgnoreCase("Debit Card")){
				
				//Select the Service Request Type Option
				CommonDef.waitClickable(LandingPage.serviceRequestDebitCards());
				MethodDef.click(LandingPage.serviceRequestDebitCards());

				//Select the Service Request for Replace Card
				CommonDef.waitClickable(LandingPage.replaceDebitCard());
				MethodDef.click(LandingPage.replaceDebitCard());

				//Select the Card Number
				CommonDef.waitClickable(ServiceRequestPage.replaceCardNumber());
				MethodDef.click(ServiceRequestPage.replaceCardNumber());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));
				
				//Select the Delivery Mode
				if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Branch")){
					
					//Click on Branch Button
					MethodDef.click(ServiceRequestPage.radioBranchRDC());
					
					//Select Emirate
					CommonDef.waitClickable(ServiceRequestPage.selectEmirateRDC());
					MethodDef.click(ServiceRequestPage.selectEmirateRDC());
					CommonDef.dropDownSelectOutsideUae(TestData.getConfig("emirate"));
					
					//Select Branch
					CommonDef.waitClickable(ServiceRequestPage.selectBranchRDC());
					MethodDef.click(ServiceRequestPage.selectBranchRDC());
					CommonDef.dropDownSelectOutsideUae(TestData.getConfig("branch"));
				}else if(TestData.getConfig("dispatchmode").equalsIgnoreCase("Courier")){
					
					//Select Address type
					if(TestData.getConfig("addresstype").equalsIgnoreCase("Registered")){
						MethodDef.click(ServiceRequestPage.radioRegisteredAddressRDC());
					}
					else if(TestData.getConfig("addresstype").equalsIgnoreCase("Other")){
						
						//Select the Registered Address Option
						MethodDef.click(ServiceRequestPage.radioOtherAddressRDC());
						
						//Enter the Other Address details
						CommonDef.waitClickable(ServiceRequestPage.address());
						CommonDef.sendKeys(ServiceRequestPage.address(), TestData.getConfig("otheraddress"));
					}
					
					//Select the Authorized person
					if(TestData.getConfig("authoriseperson").equalsIgnoreCase("yes")){
					MethodDef.click(ServiceRequestPage.authorisedPersonRDC());
					CommonDef.flipUp();
					
					//Enter the Person Name
					CommonDef.sendKeys(ServiceRequestPage.authorisePersonNameRDC(), TestData.getConfig("authorisepersonname"));
					
					//Enter the Person Contact Number
					CommonDef.sendKeys(ServiceRequestPage.authorisePersonNumberRDC(), TestData.getConfig("authorisepersonnumber"));
					MethodDef.click(ServiceRequestPage.authorisedPersonRDC());
					}
				}
				
			}

			//Click on Proceed Button
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	balanceTransfer 
	   * Use					:	Function to perform a Service Request - Balance Transfer from a Credit Card
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void balanceTransfer(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			// Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestCreditCards());
			MethodDef.click(LandingPage.serviceRequestCreditCards());
			CommonDef.captureScreen();
			
			// Select the Service Request for Balance Transfer
			CommonDef.flipUp();
			CommonDef.flipUp();
			CommonDef.waitClickable(LandingPage.balanceTransfer());
			MethodDef.click(LandingPage.balanceTransfer());

			// Select the Card Number
			CommonDef.waitClickable(ServiceRequestPage.cardNumber());
			MethodDef.click(ServiceRequestPage.cardNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));

			// Select the Other Card Type
			CommonDef.waitClickable(ServiceRequestPage.BtCardType());
			MethodDef.click(ServiceRequestPage.BtCardType());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardtype"));
			
			// Select the Other Bank Name
			CommonDef.waitClickable(ServiceRequestPage.BtOtherBankName());
			MethodDef.click(ServiceRequestPage.BtOtherBankName());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("bankname"));
			
			CommonDef.captureScreen();
			//Enter the Card Number
			CommonDef.clearText(ServiceRequestPage.BtCardNumber());
			CommonDef.sendKeys(ServiceRequestPage.BtCardNumber(), TestData.getConfig("transfercardnumber"));
			
			//Enter the Amount
			CommonDef.clearText(ServiceRequestPage.BtAmount());
			CommonDef.sendKeys(ServiceRequestPage.BtAmount(), TestData.getConfig("amount"));
			CommonDef.captureScreen();
			// Select the Delivery Channel
			CommonDef.waitClickable(ServiceRequestPage.BtDeliveryChannel());
			MethodDef.click(ServiceRequestPage.BtDeliveryChannel());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("deliverychannel"));
			CommonDef.captureScreen();
			CommonDef.flipUp();
			
			//Flow for if Delivery Channel is Managers Cheque
			if(TestData.getConfig("deliverychannel").equalsIgnoreCase("Managers Cheque")){
			
			//Select the Delivery Mode
			if(TestData.getConfig("deliverymode").equalsIgnoreCase("Branch")){
				
				//Click on Branch Radio Button
				MethodDef.click(ServiceRequestPage.bTBranchRadio());
				
				//Select Branch
				CommonDef.waitClickable(ServiceRequestPage.BtBranchSelect());
				MethodDef.click(ServiceRequestPage.BtBranchSelect());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("branch"));
			}else{
				
				//Select the Registered Address Option
				MethodDef.click(ServiceRequestPage.bTCourierRadio());

			}
			}
			CommonDef.captureScreen();
			CommonDef.flipUp();
			CommonDef.flipUp();
			
			// Select the Remarks
			CommonDef.clearText(ServiceRequestPage.BtRemarks());
			CommonDef.sendKeys(ServiceRequestPage.BtRemarks(), TestData.getConfig("remarks"));
			
			// Select the Balance Transfer Option
			CommonDef.waitClickable(ServiceRequestPage.BtbalanceTransferOption());
			MethodDef.click(ServiceRequestPage.BtbalanceTransferOption());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("balancetransferoption"));
			CommonDef.captureScreen();
			CommonDef.flipUp();
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.btProceedButton());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			CommonDef.flipUp();
			CommonDef.captureScreen();
			CommonDef.flipUp();
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	creditCardCheque 
	   * Use					:	Function to perform a Service Request - Credit Card Cheque for a Credit Card
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	22-Aug-2017
	   *//*
	
	public static void creditCardCheque(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			// Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestCreditCards());
			MethodDef.click(LandingPage.serviceRequestCreditCards());

			// Select the Service Request for Credit Card Cheque
			CommonDef.flipUp();
			CommonDef.flipUp();
			CommonDef.waitClickable(LandingPage.creditCardCheque());
			MethodDef.click(LandingPage.creditCardCheque());

			// Select the Card Number
			CommonDef.waitClickable(ServiceRequestPage.cardNumber());
			MethodDef.click(ServiceRequestPage.cardNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));

			// Select the Purpose
			CommonDef.waitClickable(ServiceRequestPage.creditCardChequePurpose());
			MethodDef.click(ServiceRequestPage.creditCardChequePurpose());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("purpose"));

			//Enter the Beneficiary Name
			CommonDef.waitClickable(ServiceRequestPage.creditCardChequeBeneficiaryName());
			CommonDef.clearText(ServiceRequestPage.creditCardChequeBeneficiaryName());
			CommonDef.sendKeys(ServiceRequestPage.creditCardChequeBeneficiaryName(), TestData.getConfig("beneficiaryname"));
			
			//Enter the Amount
			CommonDef.clearText(ServiceRequestPage.creditCardChequeAmount());
			CommonDef.sendKeys(ServiceRequestPage.creditCardChequeAmount(), TestData.getConfig("amount"));
			
			//Select the Delivery Mode
			if(TestData.getConfig("deliverymode").equalsIgnoreCase("Branch")){
				
				//Click on Branch Radio Button
				MethodDef.click(ServiceRequestPage.creditCardChequeRadioBranch());
				
				//Select Emirate
				CommonDef.waitClickable(ServiceRequestPage.creditCardChequeEmirateSelect());
				MethodDef.click(ServiceRequestPage.creditCardChequeEmirateSelect());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("emirate"));
				
				//Select Branch
				CommonDef.waitClickable(ServiceRequestPage.creditCardChequeBranchSelect());
				MethodDef.click(ServiceRequestPage.creditCardChequeBranchSelect());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("branch"));
			}else{
				
				//Select the Registered Address Option
				MethodDef.click(ServiceRequestPage.creditCardChequeRadioCourier());

			}
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.proceedButtonCreditCardCheque());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	cardDuplicateStatement 
	   * Use					:	Function to perform a Service Request - Duplicate Statement for a Credit Card
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void cardDuplicateStatement(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestCreditCards());
			MethodDef.click(LandingPage.serviceRequestCreditCards());
			CommonDef.flipUp();
			
			//Select the Service Request for Duplicate Statement
			CommonDef.waitClickable(LandingPage.cardDuplicateStatement());
			MethodDef.click(LandingPage.cardDuplicateStatement());

			//Select the Card Number
			CommonDef.waitClickable(ServiceRequestPage.creditCardNumberStatement());
			MethodDef.click(ServiceRequestPage.creditCardNumberStatement());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));

			//Select the From Month
			MethodDef.click(ServiceRequestPage.fromMonth());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("frommonth"));

			//Select the To Month
			MethodDef.click(ServiceRequestPage.toMonth());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("tomonth"));
			
			//Select the From Year
			MethodDef.click(ServiceRequestPage.fromYear());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("fromyear"));
			
			//Select the To Year
			MethodDef.click(ServiceRequestPage.toYear());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("toyear"));
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}

	  *//**	
	   * Method Name			:	reportLostCreditCard 
	   * Use					:	Function to perform a Service Request - Replace a Lost Credit Card
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void reportLostCreditCard(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestCreditCards());
			MethodDef.click(LandingPage.serviceRequestCreditCards());
			CommonDef.flipUp();
			
			//Select the Service Request for replace credit Card
			CommonDef.waitClickable(LandingPage.reportLostCreditCard());
			MethodDef.click(LandingPage.reportLostCreditCard());

			//Select the Card Number
			CommonDef.waitClickable(ServiceRequestPage.creditCardNumberLost());
			MethodDef.click(ServiceRequestPage.creditCardNumberLost());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));

			//Select the Reason
			MethodDef.click(ServiceRequestPage.cardLostReason());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("reason"));

			//Enter the Lost Place
			CommonDef.sendKeys(ServiceRequestPage.lostPlace(), TestData.getConfig("lostplace"));
			
			//Enter the Lost Description
			CommonDef.sendKeys(ServiceRequestPage.lostCircumstances(), TestData.getConfig("description"));
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButtonLostCard());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButtonLostCard());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	easyPaymentPlan 
	   * Use					:	Function to perform a Service Request - Easy Payment Plan
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void easyPaymentPlan(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestCreditCards());
			MethodDef.click(LandingPage.serviceRequestCreditCards());
			CommonDef.flipUp();
			CommonDef.flipUp();
			
			//Select the Service Request for Easy Payment Plan
			CommonDef.waitClickable(LandingPage.easyPaymentPlan());
			MethodDef.click(LandingPage.easyPaymentPlan());

			//Select the Card Number
			CommonDef.waitClickable(ServiceRequestPage.creditCardNumberPaymentPlan());
			MethodDef.click(ServiceRequestPage.creditCardNumberPaymentPlan());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));

			//Select the From date
			String fromdatedata[]=TestData.getConfig("fromdate").split("/");
			CommonDef.waitClickable(ServiceRequestPage.fromDatePaymentPlan());
			MethodDef.click(ServiceRequestPage.fromDatePaymentPlan());
			CommonDef.datePicker(fromdatedata[0],fromdatedata[1],fromdatedata[2]);

			//Select the To date
			String todatedata[]=TestData.getConfig("todate").split("/");
			CommonDef.waitClickable(ServiceRequestPage.toDatePaymentPlan());
			MethodDef.click(ServiceRequestPage.toDatePaymentPlan());
			CommonDef.datePicker(todatedata[0],todatedata[1],todatedata[2]);
			
			//Click on Go Button
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.goButton());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	smartCash 
	   * Use					:	Function to perform a Service Request - Smart Cash
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void smartCash(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestCreditCards());
			MethodDef.click(LandingPage.serviceRequestCreditCards());
			CommonDef.flipUp();
			CommonDef.flipUp();
			
			//Select the Service Request for Smart Cash
			CommonDef.waitClickable(LandingPage.smartCash());
			MethodDef.click(LandingPage.smartCash());

			//Select the Card Number
			CommonDef.waitClickable(ServiceRequestPage.creditCardNumberSmartCash());
			MethodDef.click(ServiceRequestPage.creditCardNumberSmartCash());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("cardnumber"));

			//Select the Account Type
			if(TestData.getConfig("toaccount").equalsIgnoreCase("My Account")){
				MethodDef.click(ServiceRequestPage.myAccountRadio());
				
				//Select the Account Number
				CommonDef.waitClickable(ServiceRequestPage.smartCashAccountNumber());
				MethodDef.click(ServiceRequestPage.smartCashAccountNumber());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("accountnumber"));
				
			}else{
				MethodDef.click(ServiceRequestPage.otherAccountRadio());
				
				//Enter the IBAN Number
				CommonDef.waitClickable(ServiceRequestPage.smartCashIbanNumber());
				CommonDef.sendKeys(ServiceRequestPage.smartCashIbanNumber(), TestData.getConfig("accountnumber"));
			}
			
			//Enter the Smart Cash Amount
			CommonDef.sendKeys(ServiceRequestPage.smartCashAmount(), TestData.getConfig("amount"));
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButton());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButton());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	amendMaturityInstructionTD 
	   * Use					:	Function to perform a Service Request - Amend Maturity Instruction for a Term Deposit
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void amendMaturityInstructionTD(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestDeposits());
			MethodDef.click(LandingPage.serviceRequestDeposits());
			CommonDef.flipUp();
			
			//Select the Service Request to Amend Maturity Instruction
			CommonDef.waitClickable(LandingPage.amendMaturityInstruction());
			MethodDef.click(LandingPage.amendMaturityInstruction());

			//Select the Deposit Number
			CommonDef.waitClickable(ServiceRequestPage.depositNumber());
			MethodDef.click(ServiceRequestPage.depositNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("depositnumber"));
			CommonDef.waitClickable(ServiceRequestPage.depositNumber());
			CommonDef.captureScreen();
			CommonDef.flipUp();
			
			//Select the Amendment Instruction
			if(TestData.getConfig("instruction").equalsIgnoreCase("Deposit Tenor")){
				MethodDef.click(ServiceRequestPage.tenorChangeRadio());
				
				//Select the Type of Range
				if(TestData.getConfig("tenorchangetype").equalsIgnoreCase("Regular")){
					MethodDef.click(ServiceRequestPage.regularTenorRadio());
					
					//Select the Period
					MethodDef.click(ServiceRequestPage.periodSelect());
					CommonDef.dropDownSelectOutsideUae(TestData.getConfig("period"));
					
				}else{
					MethodDef.click(ServiceRequestPage.rangeTenorRadio());
					
					//Select the Date
					String todatedata[]=TestData.getConfig("enddate").split("/");
					CommonDef.waitClickable(ServiceRequestPage.endDate());
					MethodDef.click(ServiceRequestPage.endDate());
					CommonDef.datePicker(todatedata[0],todatedata[1],todatedata[2]);
					
				}
				
				
			}else if(TestData.getConfig("instruction").equalsIgnoreCase("Increase Amount")){
				MethodDef.click(ServiceRequestPage.increaseAmountRadio());
				
				//Enter the Increase Amount
				CommonDef.sendKeys(ServiceRequestPage.depositIncreaseAmount(), TestData.getConfig("amount"));
				
				//Select the Debit Account Number
				MethodDef.click(ServiceRequestPage.debitAmountAccountNumber());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("accountnumber"));
				
			}else if(TestData.getConfig("instruction").equalsIgnoreCase("Decrease Amount")){
				MethodDef.click(ServiceRequestPage.decreaseAmountRadio());
				
				
				//Enter the Increase Amount
				CommonDef.sendKeys(ServiceRequestPage.depositDecreaseAmount(), TestData.getConfig("amount"));
				
				//Select the Debit Account Number
				MethodDef.click(ServiceRequestPage.creditAmountAccountNumber());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("accountnumber"));
				
			}else if(TestData.getConfig("instruction").equalsIgnoreCase("Credit Back")){
				MethodDef.click(ServiceRequestPage.creditbackRadio());
				
				//Select the Account Number
				MethodDef.click(ServiceRequestPage.creditDepositAccountNumber());
				CommonDef.dropDownSelectOutsideUae(TestData.getConfig("accountnumber"));
			}
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			CommonDef.flipUp();
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.submitButtonAmendMaturity());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.submitButtonAmendMaturity());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	amendMaturityInstructionTD 
	   * Use					:	Function to perform a Service Request - Amend Maturity Instruction for a Term Deposit
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void amendMaturityInstructionIslamicTD(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestDeposits());
			MethodDef.click(LandingPage.serviceRequestDeposits());
			CommonDef.flipUp();
			
			//Select the Service Request to Amend Maturity Instruction
			CommonDef.waitClickable(LandingPage.amendMaturityInstructionIslamic());
			MethodDef.click(LandingPage.amendMaturityInstructionIslamic());

			//Select the Deposit Number
			CommonDef.waitClickable(ServiceRequestPage.depositNumber());
			MethodDef.click(ServiceRequestPage.depositNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("depositnumber"));
			CommonDef.waitClickable(ServiceRequestPage.depositNumber());
			CommonDef.captureScreen();
			CommonDef.flipUp();
			
			MethodDef.click(ServiceRequestPage.creditbackRadioIslamic());

			//Select the Account Number
			MethodDef.click(ServiceRequestPage.creditDepositAccountNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("accountnumber"));
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			CommonDef.flipUp();
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.submitButtonAmendMaturity());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.submitButtonAmendMaturity());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	termDepositClosure 
	   * Use					:	Function to perform a Service Request - Closure of a Term Deposit
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void termDepositClosure(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestDeposits());
			MethodDef.click(LandingPage.serviceRequestDeposits());
			CommonDef.flipUp();
			
			//Select the Service Request to Closure of Term Deposit
			CommonDef.waitClickable(LandingPage.depositClosure());
			MethodDef.click(LandingPage.depositClosure());

			//Select the Deposit Number
			CommonDef.waitClickable(ServiceRequestPage.depositNumberClosure());
			MethodDef.click(ServiceRequestPage.depositNumberClosure());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("depositnumber"));
			CommonDef.waitClickable(ServiceRequestPage.depositNumberClosure());
			CommonDef.captureScreen();
			CommonDef.flipUp();
			CommonDef.flipUp();
			
			//Enter the Remarks
			CommonDef.sendKeys(ServiceRequestPage.remarksTDClosure(), TestData.getConfig("remarks"));
			
			//Select the Credit Principal Account
			MethodDef.click(ServiceRequestPage.accountRadio());
			CommonDef.waitClickable(ServiceRequestPage.principalCreditAccountNumber());
			MethodDef.click(ServiceRequestPage.principalCreditAccountNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("accountnumber"));
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			CommonDef.flipUp();
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButtonLostCard());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButtonLostCard());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
			ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
			ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	  *//**	
	   * Method Name			:	termDepositClosureIslamic 
	   * Use					:	Function to perform a Service Request - Closure of a Term Deposit Islamic
	   * @return Null
	   * @param No args
	   * Designed By			:	Surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   *//*
	
	public static void termDepositClosureIslamic(){

		try {
			
			//CLick on Menu Button
			CommonDef.waitVisible(LandingPage.menuButton());
			CommonDef.waitClickable(LandingPage.menuButton());
			MethodDef.click(LandingPage.menuButton());
			
			//Click Service Request Option
			CommonDef.waitClickable(LandingPage.serviceRequestOption());
			MethodDef.click(LandingPage.serviceRequestOption());

			//Select the Service Request Type Option
			CommonDef.waitClickable(LandingPage.serviceRequestDeposits());
			MethodDef.click(LandingPage.serviceRequestDeposits());
			CommonDef.flipUp();
			
			//Select the Service Request to Closure of Term Deposit
			CommonDef.waitClickable(LandingPage.depositClosure());
			MethodDef.click(LandingPage.depositClosure());

			//Select the Deposit Number
			CommonDef.waitClickable(ServiceRequestPage.depositNumberClosure());
			MethodDef.click(ServiceRequestPage.depositNumberClosure());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("depositnumber"));
			CommonDef.waitClickable(ServiceRequestPage.depositNumberClosure());
			CommonDef.captureScreen();
			CommonDef.flipUp();
			CommonDef.flipUp();
			
			//Enter the Remarks
			CommonDef.sendKeys(ServiceRequestPage.remarksTDClosure(), TestData.getConfig("remarks"));
			
			//Select the Credit Principal Account
			MethodDef.click(ServiceRequestPage.accountRadio());
			CommonDef.waitClickable(ServiceRequestPage.principalCreditAccountNumber());
			MethodDef.click(ServiceRequestPage.principalCreditAccountNumber());
			CommonDef.dropDownSelectOutsideUae(TestData.getConfig("accountnumber"));
			
			//Click on Proceed Button
			CommonDef.captureScreen();
			CommonDef.flipUp();
			MethodDef.click(ServiceRequestPage.proceedButtonAdvSalary());
			CommonDef.waitClickable(ServiceRequestPage.confirmButtonLostCard());
			ExReporter.log(LogStatus.PASS,"Transaction Posting completed Successfully");
			CommonDef.captureScreen();
			
			//Click on Confirm Button
			MethodDef.click(ServiceRequestPage.confirmButtonLostCard());
			CommonDef.waitClickable(ServiceRequestPage.transactionMessage());
			CommonDef.flipUp();
			CommonDef.captureScreen();
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			CommonDef.valuePrint(ServiceRequestPage.transactionMessage());
			String ref_msg= CommonDef.valuereturn(ServiceRequestPage.transactionMessage());
			String ref[]= ref_msg.split("Reference ID is ");
			
			String validation= CommonDef.messageValidation(ServiceRequestPage.transactionMessage());
			if(!validation.equalsIgnoreCase("Fail")){
				ExReporter.log(LogStatus.PASS,"Transaction Confirmation completed Successfully");
				CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			}else
				ExReporter.log(LogStatus.FAIL,"Transaction Confirmation Failed");

			CommonDef.writeTransactionValues(ref[1],TestData.getConfig("DataBinding"),"Transaction Reference");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public static void openBrowser(){

		try {
			MethodDef.click(LandingPage.searchButton());
			CommonDef.captureScreen();
			MethodDef.sendKeys(LandingPage.searchButton(), "Facebook");
			ExReporter.log(LogStatus.PASS,"Transaction completed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public static void connectLogin(){

		try {
//			MethodDef.click(LandingPage.searchButton());
//			ProjectConfig.prop.replace("url", "https://connect.maveric-systems.com/index.php/site/login");
//			MethodDef.launchURL();
			MethodDef.launchURL("https://connect.maveric-systems.com/index.php/site/login");
			CommonDef.captureScreen();
			MethodDef.sendKeys(ConnectLogin.userName(), TestData.getConfig("loginid"));
			MethodDef.sendKeys(ConnectLogin.userPassword(), TestData.getConfig("loginpwd"));
			CommonDef.captureScreen();
			MethodDef.click(ConnectLogin.loginButton());
			CommonDef.waitForPageLoad();
			MethodDef.loadWaitIE();
			Thread.sleep(3000);
			
			ExReporter.log(LogStatus.PASS,"Login completed");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public static void connectLogin_Static(){

		try {
//			MethodDef.click(LandingPage.searchButton());
			ProjectConfig.prop.replace("url", "https://connect.maveric-systems.com/index.php/site/login");
			//System.out.println(abc);
			MethodDef.launchURL(); //("https://connect.maveric-systems.com/index.php/site/login");
			CommonDef.captureScreen();
			MethodDef.sendKeys(ConnectLogin.userName(), "ashokgauthamr");
			MethodDef.sendKeys(ConnectLogin.userPassword(), "Ramesh@2019");
			CommonDef.captureScreen();
			MethodDef.click(ConnectLogin.loginButton());
			
			ExReporter.log(LogStatus.PASS,"Login completed");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
	}
	
	public static void Prismmaveric(){

		try {
			
			MethodDef.launchURL();
			System.out.print(" \n Into Prismmaveric in RakFunctions \n ");
//			MethodDef.click(LandingPage.searchButton());
			CommonDef.waitVisible(PrismLogin.userName());
			CommonDef.captureScreen();
			MethodDef.sendKeys(PrismLogin.userName(), TestData.getConfig("loginid"));
			MethodDef.sendKeys(PrismLogin.userPassword(), TestData.getConfig("loginpwd"));
			MethodDef.sendKeys(PrismLogin.userCompany(), TestData.getConfig("logincompany"));
			CommonDef.captureScreen();
			MethodDef.click(PrismLogin.loginButton());
			System.out.println("LoginButton Clicked");
			CommonDef.waitVisible(PrismLogin.adrenalinLogoImage());
			CommonDef.captureScreen();
			ProjectConfig.prop.replace("timeout", "5");
			System.out.println(ProjectConfig.getPropertyValue("timeout"));
			CommonDef.waitVisible(PrismLogin.adrenalinLogoImage());
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			int a = 0;
			while (a < 5) {
				if (CommonDef.waitVisibleNoError(PrismLogin.adrenalinLogoImage())) {
					ExReporter.log(LogStatus.PASS,"Login completed");
					CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
					CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
					CommonDef.captureScreen();
					break;
				}
				if (CommonDef.waitVisibleNoError(PrismLogin.loginErrorMessage())) {
					ExReporter.log(LogStatus.FAIL,"Login Failed");
					CommonDef.writeTransactionValues("FAILED",TestData.getConfig("DataBinding"),"Test Case Status");
					CommonDef.writeTransactionValues("FAILED",TestData.getConfig("DataBinding"),"Transaction Status");
					CommonDef.captureScreen();
					break;
				}
				a += 1;
				System.out.println(a);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
		}
		
	}


	public static void outlookLogin(){

		try {
//			System.out.println("Rak function called");
			ProjectConfig.prop.replace("url", "http://mail.maveric-systems.com/");
			MethodDef.launchURL();
//			MethodDef.launchURL("http://mail.maveric-systems.com/");
			ExReporter.log(LogStatus.PASS,"URL launched");
			MethodDef.sendKeys(outlookLogin.userName(), TestData.getConfig("username"));
			ExReporter.log(LogStatus.PASS,"Username has been entered");
			MethodDef.click(outlookLogin.usernameNext());
			//CommonDef.waitPresent(By.name(TestData.getConfig("loginid")));
			Thread.sleep(5000);
			String disname=CommonDef.getAttribute(By.id("displayName"), "title");
			System.out.println(disname);
			
			if(disname.equalsIgnoreCase(TestData.getConfig("username"))){
				ExReporter.log(LogStatus.PASS,"Username is present in second page");

			}
			else {
				ExReporter.log(LogStatus.FAIL,"Username is not present in second page");

			} 
			
			MethodDef.sendKeys(outlookLogin.userPassword(), TestData.getConfig("password"));
			ExReporter.log(LogStatus.PASS,"Password has been entered");
			MethodDef.click(outlookLogin.usernameNext());
			MethodDef.click(outlookLogin.signin());			
			CommonDef.waitVisible(outlookLogin.homePage());
			ExReporter.log(LogStatus.PASS,"Login completed");
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
	}
	
}}
*/
	public static void outlookLogin(){

		try {
//			System.out.println("Rak function called");
			ProjectConfig.prop.replace("url", "http://mail.maveric-systems.com/");
			MethodDef.launchURL();
//			MethodDef.launchURL("http://mail.maveric-systems.com/");
			ExReporter.log(LogStatus.PASS,"URL launched");
			MethodDef.sendKeys(outlookLogin.userName(), TestData.getConfig("username"));
			ExReporter.log(LogStatus.PASS,"Username has been entered");
			MethodDef.click(outlookLogin.usernameNext());
			//CommonDef.waitPresent(By.name(TestData.getConfig("loginid")));
			Thread.sleep(5000);
			String disname=CommonDef.getAttribute(By.id("displayName"), "title");
			System.out.println(disname);
			
			if(disname.equalsIgnoreCase(TestData.getConfig("username"))){
				ExReporter.log(LogStatus.PASS,"Username is present in second page");

			}
			else {
				ExReporter.log(LogStatus.FAIL,"Username is not present in second page");

			} 
			
			MethodDef.sendKeys(outlookLogin.userPassword(), TestData.getConfig("password"));
			ExReporter.log(LogStatus.PASS,"Password has been entered");
			MethodDef.click(outlookLogin.usernameNext());
			MethodDef.click(outlookLogin.signin());			
			CommonDef.waitVisible(outlookLogin.homePage());
			ExReporter.log(LogStatus.PASS,"Login completed");
			
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Test Case Status");
			CommonDef.writeTransactionValues("PASS",TestData.getConfig("DataBinding"),"Transaction Status");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
	}
	
}
	public static void openAirFromOutlook() {
		try {
			
			MethodDef.click(outlookHome.menuNavigation());
			MethodDef.click(outlookHome.openAirMenu());
						
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
	}
	}
	
	public static void updateTimesheet() {
		try {
			
			if(TestData.getConfig("timesheetCreated").equalsIgnoreCase("Yes"))
			{
				MethodDef.click(openAirHome.timesheetMenu());
				MethodDef.click(openAirHome.categoryTimeSheet(TestData.getConfig("timesheetCategory")));
				MethodDef.click(openAirHome.selectTimesheet(TestData.getConfig("weekstarting")));
			}
			else
			{
				MethodDef.click(openAirHome.createMenu());
				MethodDef.click(openAirHome.timesheetNew());
				CommonDef.dropdown(openAirHome.createTimesheetDropDown(), TestData.getConfig("weekstarting"));
				MethodDef.click(openAirHome.saveCreatedTimesheet());
			}
			
			HashMap<String, Integer> weekColumn = new HashMap<String,Integer>();
			weekColumn.put("mon", 5);
			weekColumn.put("tue", 6);
			weekColumn.put("wed", 7);
			weekColumn.put("thu", 8);
			weekColumn.put("fri", 9);
			weekColumn.put("sat", 10);
			weekColumn.put("sun", 11);

			ArrayList<String> rowExecution = new ArrayList<String>();
			String[] elementList = TestData.getConfig("rowDriver").split("_");
			int rowCount=elementList.length;
			
			for(int i=0;i<elementList.length;i++) rowExecution.add(elementList[i]);
			
			for(int i=1;i<=rowCount;i++)
			{
				CommonDef.dropdown(openAirHome.DropDown("ts_c1_r"+i), TestData.getConfig("project"+i));
				CommonDef.dropdown(openAirHome.DropDown("ts_c2_r"+i), TestData.getConfig("task"+i));
				CommonDef.dropdown(openAirHome.DropDown("ts_c3_r"+i), TestData.getConfig("timeType"+i));
				CommonDef.dropdown(openAirHome.DropDown("ts_c4_r"+i), TestData.getConfig("location"+i));
				
				for (String weekInput : rowExecution) 
				{
					String[] weeks = weekInput.split(";");
					for (String week : weeks) 
					{
						String column = "ts_c"+weekColumn.get(week)+"_r"+i;
						MethodDef.sendKeys(openAirHome.inputHours(column), TestData.getConfig(weekInput+i));
						MethodDef.click(openAirHome.addtlInfoLink(column));
						CommonDef.dropdown(openAirHome.premiseSelect(),TestData.getConfig("premise"));	
						MethodDef.click(openAirHome.addtlInfoOK());
					}
				}
			}
			
//			CommonDef.dropdown(openAirHome.DropDown("ts_c1_r1"), TestData.getConfig("project"));
//			CommonDef.dropdown(openAirHome.DropDown("ts_c2_r1"), TestData.getConfig("task"));
//			CommonDef.dropdown(openAirHome.DropDown("ts_c3_r1"), TestData.getConfig("timeType"));
//			CommonDef.dropdown(openAirHome.DropDown("ts_c4_r1"), TestData.getConfig("location"));
			
//			updateHours("ts_c5_r1","mon");
//			updateHours("ts_c6_r1","tue");
//			updateHours("ts_c7_r1","wed");
//			updateHours("ts_c8_r1","thu");
//			updateHours("ts_c11_r1","sun");
//			
//			CommonDef.dropdown(openAirHome.DropDown("ts_c1_r2"), TestData.getConfig("project2"));
//			CommonDef.dropdown(openAirHome.DropDown("ts_c2_r2"), TestData.getConfig("task2"));
//			CommonDef.dropdown(openAirHome.DropDown("ts_c3_r2"), TestData.getConfig("timeType2"));
//			CommonDef.dropdown(openAirHome.DropDown("ts_c4_r2"), TestData.getConfig("location2"));
//			
//			updateHours("ts_c9_r2","fri");
//			updateHours("ts_c10_r2","sat");
			
//			MethodDef.click(openAirHome.saveSubmitButton());

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			ExReporter.log(LogStatus.FAIL, e.getMessage());
	}
	}
	}
