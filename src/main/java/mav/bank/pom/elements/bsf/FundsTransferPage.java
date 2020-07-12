package mav.bank.pom.elements.bsf;

import org.openqa.selenium.By;

import mav.bank.library.functions.CommonDef;
import mav.bank.utilities.enums.Locators;

/**	
* Class Name			:	FundsTransferPage
* Use					:	FundsTransferPage holds all the elements that are available in the Funds Transfer Page and 
* 							are used in the execution in the form of methods and returns the objects in the form of By
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

public class FundsTransferPage {

	public static By toAcc(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='toBenTypeArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By continueButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='menu-height-adjust2']//button[@class='btn--special-btn btn-middle rak-button-small ng-binding' and contains(text(),'Continue')]");
	}
	
	public static By availableBalance(String ccy){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@ng-show='rakSendMoney.sendMoney.selectedFromAccount']/p[@ng-show='rakSendMoney.sendMoney.fromAccounts.length > 0']//span[contains(text(),'"+ccy+"')]");
	}
	
	public static By valueSelect(String value){
		if(value.equalsIgnoreCase("All charges to Beneficiary's Account"))
		return CommonDef.locatorValue(Locators.XPATH, "//div[@role='option']/div[contains(text(),'All charges to Beneficiary')]");
		else
		return CommonDef.locatorValue(Locators.XPATH, "//div[@role='option']/div[text()='"+value+"']");
	}

	public static By setButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='mbsc-fr-btn0 mbsc-fr-btn-e mbsc-fr-btn' and text()='Set']");
	}
	
	public static By fromAcc(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='fromAccountArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By remitCurrency(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='input-body']//div[@class='input-body2 input-body2-dropdown']//select[@ng-model='rakSendMoney.sendMoney.selectedRCurr']/preceding-sibling::input");
	}
	
	public static By transactionCurrency(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='input-body']//div[@class='input-body2 input-body2-dropdown']//select[@ng-model='rakSendMoney.sendMoney.selectedCurrency' and @title='Select']/preceding-sibling::input");
	}
	
	public static By transactionCurrencyWithinRAK(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='currencyArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By transactionCurrencySelfAccount(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='fin-input-container']//select[@title='Transaction  Currency']/preceding-sibling::input");
	}
	
	public static By transactionAmount(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rak40with2spacing rakRelative']/following::div[1]//fin-input[@type='money']//div[@class='input-body']//input[@ng-model='rakSendMoney.sendMoney.amount']");
	}
	
	public static By transactionAmountSelfAccount(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rak55 rakRelative']//fin-input[@type='money']//div[@class='input-body2']/input[@ng-model='rakSendMoney.sendMoney.amount']");
	}
	
	public static By transactionCharges(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='chargesArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By transactionReason(){
		//return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='reasonArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='purposeArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By transactionReasonPurpose(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='reasonArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By transactionReasonSelfAccount(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@type='text_restrictedSpecialCharacter']//input[@clear-warning='rakSendMoney.sendMoney.selectedReason']");
	}
	
	public static By transactionPurpose(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='purposeArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By transactionFrequency(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='frequencyArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By proceedButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='label-wrap']//button[@class='btn--special-btn btn-middle rak-button-right ng-binding' and contains(text(),'Proceed')]");
	}

	public static By confirmButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='label-wrap']//button[@class='btn--special-btn btn-middle rak-button-small ng-binding' and contains(text(),'Confirm')]");
	}
	
	public static By transactionMessage(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='summary-BenfReq main-form-heading-ServiceReq']//span[contains(text(),'ransaction')]");
	}
	
	public static By transactionReference(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']//span[@class='rak-sum-label ng-binding' and text()='Reference ID']/following::span[1]");
	}
	
	public static By cancelButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='mbsc-fr-btn1 mbsc-fr-btn-e mbsc-fr-btn' and text()='Cancel']");
	}
	
	public static By exchangeRate(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='fin-input-container rakTransfersexchangerate-block']/p[@class='rakexchangevalue ng-binding']");
	}
	
	public static By toAccNumber(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='toAccountList']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By transactionExchangeRate(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']/span[contains(text(),'Exchange rate')]/following-sibling::span");
	}
	
	public static By transactionChargesCollected(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']/span[contains(text(),'Charges')]/following-sibling::span");
	}
}
