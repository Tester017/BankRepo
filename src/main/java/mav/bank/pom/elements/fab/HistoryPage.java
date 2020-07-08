package mav.bank.pom.elements.fab;

import org.openqa.selenium.By;

import mav.bank.library.functions.CommonDef;
import mav.bank.utilities.enums.Locators;
 
/**	
* Class Name			:	HistoryPage
* Use					:	HistoryPage holds all the elements that are available in the History Page and 
* 							are used in the execution in the form of methods and returns the objects in the form of By
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

public class HistoryPage {

	public static By historyButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//ul//li[text()='History']");
	}
	
	public static By dropDownArrow(String Beneficiary){
		//return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh']/div[1]//div[@class='pull-right rakPaddingPoint5em']/span[1]");
		String visibleText[] = Beneficiary.split("-");
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh']/div[1]//div[contains(text(),'"+visibleText[0]+"')]/following::div[4]/span[1]");
	}
	
	public static By dropDownArrowSelfAccount(String Beneficiary){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh']/div[1]//div[@class='pull-right rakPaddingPoint5em']/span[1]");
		//String visibleText[] = Beneficiary.split("-");
		//return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh']/div[1]//div[contains(text(),'"+visibleText[0]+"')]/following::div[4]/span[1]");
	}
	
	public static By dropDownArrowPayCards(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh']/div[1]//div[@class='pull-right rakPaddingPoint5em']/span[1]");
		//String visibleText[] = Beneficiary.split("-");
		//return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh']/div[1]//div[contains(text(),'"+visibleText[0]+"')]/following::div[4]/span[1]");
	}
	
	public static By viewDetailsLink(String Beneficiary){
		//return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh rakPullTransform']/div[1]//div[@class='pull-right rakPaddingPoint5em']//ul[@class='rakPullDownMenu rakAbsolute rakTop2em rakMarginTop2']/li[1]/a");
		//String part[] = Beneficiary.split(" ");
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh rakPullTransform']/div[1]//div[@class='pull-right rakPaddingPoint5em']//ul[@class='rakPullDownMenu rakAbsolute rakTop2em rakMarginTop2']/li[1]/a");
	}
	
	public static By viewDetailsLinkPayBill(String Beneficiary){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='rakScrollDivHistory rak-cont-paddingvh rakPullTransform']/div[1]//div[1]//div[@class='trans-dash-right rakPeopleNameRow1 rak30  pull-left rakPaddingPoint8em']/div/div[contains(text(),'"+Beneficiary+"')]//parent::div//parent::div[@class='trans-dash-right rakPeopleNameRow1 rak30  pull-left rakPaddingPoint8em']/following-sibling::div[2]//ul/li/a[contains(text(),'View')]");
	}
	
	public static By viewDetailsLinkMobileCash(String Beneficiary){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='nano rakHalfScrollDiv rak-cont-padding rakPullTransform']/div[1]//div[@class='pull-right rakPaddingPoint5em rak10']//ul[@class='rakPullDownMenu rakAbsolute rakTop2em rakMarginTop2']/li[1]/a");
	}
	
	public static By dropDownArrowMobileCash(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='trans-detail rakGreyRow ng-scope']//div[@class='pull-right rakPaddingPoint5em rak10']/span[1]");
		}
	
	public static By transactionStatus(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']//span[contains(text(),'Transaction Status')]/following-sibling::span");
	}
	
	public static By transactionCoreRefrence(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']//span[contains(text(),'Bank Transaction Id')]/following-sibling::span");
	}
	
	public static By transactionBillRefrence(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']//span[contains(text(),'Biller Reference Number')]/following-sibling::span");
	}
	
	public static By transactionReference(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']//span[@class='rak-sum-label ng-binding' and text()='Reference ID']/following::span[1]");
	}
	
	public static By transactionReferenceMobileCash(){
		return CommonDef.locatorValue(Locators.XPATH, "//p[@class='rak-sum-txt']//span[@class='rak-sum-label ng-binding' and text()='Reference Number']/following::span[1]");
	}
	
}
