package mav.bank.pom.elements.bsf;

import org.openqa.selenium.By;

import mav.bank.library.functions.CommonDef;
import mav.bank.utilities.enums.Locators;

public class outlookLogin {
	public static By userName(){
		return CommonDef.locatorValue(Locators.XPATH, "//*[@id='i0116']");
	}
	
	public static By usernameNext(){
		return CommonDef.locatorValue(Locators.XPATH, "//*[@id='idSIButton9']");
	}
	
	public static By userPassword(){
		return CommonDef.locatorValue(Locators.XPATH, "//*[@name='passwd']");
	}
	
	public static By homePage(){
		return CommonDef.locatorValue(Locators.XPATH, "//*[@id='O365_MainLink_NavMenu']");
	}
	public static By signin(){
		return CommonDef.locatorValue(Locators.XPATH, "//*[@id='idBtn_Back']");
	}
	public static By unname(){
		return CommonDef.locatorValue(Locators.XPATH, "//*[@id='displayName']");
	}
}


