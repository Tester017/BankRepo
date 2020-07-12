package mav.bank.pom.elements.bsf;

import org.openqa.selenium.By;

import mav.bank.library.functions.CommonDef;
import mav.bank.utilities.enums.Locators;

public class outlookHome {
	
	// Main Menu link
	public static By menuNavigation(){
		return CommonDef.locatorValue(Locators.ID, "O365_MainLink_NavMenu");
	}
	
	// Open Air Menu
	public static By openAirMenu(){
		return CommonDef.locatorValue(Locators.XPATH, "//span[text()='OpenAir (PSA) ']");
	}


}
