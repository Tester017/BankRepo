package mav.bank.pom.elements.fab;

import org.openqa.selenium.By;

import mav.bank.library.functions.CommonDef;
import mav.bank.utilities.enums.Locators;

/**	
* Class Name			:	PayCardsPage
* Use					:	PayCardsPage holds all the elements that are available in the Pay Cards Page and 
* 							are used in the execution in the form of methods and returns the objects in the form of By
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

public class PayCardsPage {

	public static By cardPaymentType(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='toBenfTypeList']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By cardNumber(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='toOwnCardList']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By cardPayee(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@option-array='toBenfCardlist']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By availableBalance(String ccy){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@ng-show='rakPayCards.common.availBal']//p[contains(text(),'"+ccy+"')]");
	}
	
	public static By fromAccount(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@ng-show='rakPayCards.ownCards.isCardSelected']//fin-input[@option-array='fromAccountArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By fromAccountOutsideBank(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@ng-hide='rakPayCards.ownCards.isPromoSelected' and @aria-hidden='false']//fin-input[@option-array='fromAccountArray']//select[@class='fin-input fin-select ng-pristine ng-untouched ng-valid mbsc-comp mbsc-sel-hdn']/preceding-sibling::input");
	}
	
	public static By otherAmountOption(){
		//return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakCCtab-block']/div[@role='button']/span[contains(text(),'Other Amount')]/ancestor::div[@class='rakCCAmttab']");
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakCCtab-block']//div//span[contains(text(),'Other Amount')]");
	}
	
	public static By payAmount(){
		return CommonDef.locatorValue(Locators.XPATH, "//fin-input[@type='money']//div[@class='input-body2']/input");
	}
	
	public static By payAmountOtherRakCard(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@ng-show='rakPayCards.ownCards.selectedCardBenfType==rakPayCards.TxnTypeConstant.RAKCARDS']//fin-input[@type='money']//div[@class='input-body2']/input");
	}
	
	public static By payremarks(){
		//return CommonDef.locatorValue(Locators.XPATH, "//div[@aria-hidden='false']/fin-input[@placeholder='Remarks']//div[@class='input-body']//input[@placeholder='Remarks']");
		return CommonDef.locatorValue(Locators.XPATH, "//div[@ng-show= 'rakPayCards.ownCards.selectedCardBenfType==rakPayCards.TxnTypeConstant.RAKCARDS']//fin-input[@placeholder='Remarks']//div[@class='input-body']//input[@placeholder='Remarks']");
	}
	
	public static By payRemarksOwnCard(){
		//return CommonDef.locatorValue(Locators.XPATH, "//div[@aria-hidden='false']/fin-input[@placeholder='Remarks']//div[@class='input-body']//input[@placeholder='Remarks']");
		return CommonDef.locatorValue(Locators.XPATH, "//div[@ng-show= 'rakPayCards.ownCards.selectedCardBenfType==rakPayCards.TxnTypeConstant.OWNCARDS']//fin-input[@placeholder='Remarks']//div[@class='input-body']//input[@placeholder='Remarks']");
	}
	
	public static By proceedButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@ng-show='rakPayCards.ownCards.isCardSelected']//div[@class='label-wrap']//button[@class='btn--special-btn btn-middle rak-button-right ng-binding' and contains(text(),'Proceed')]");
	}
	
	public static By confirmButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='label-wrap']//button[@class='btn--special-btn btn-middle rak-button-small ng-binding' and contains(text(),'Confirm')]");
	}
	
	public static By transactionMessage(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='summary-ServiceReq']//span[contains(text(),'ransaction')]");
	}
	
	public static By transactionReference(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']//span[@class='rak-sum-label ng-binding' and text()='Reference ID']/following::span[1]");
	}
	
}
