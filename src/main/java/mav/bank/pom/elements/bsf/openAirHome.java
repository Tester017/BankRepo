package mav.bank.pom.elements.bsf;

import org.openqa.selenium.By;

import mav.bank.library.functions.CommonDef;
import mav.bank.utilities.enums.Locators;

public class openAirHome {
	
	public static By createMenu(){
		return CommonDef.locatorValue(Locators.XPATH, "//span[contains(text(),'Create')]");
	}
	
	public static By timesheetNew(){
		return CommonDef.locatorValue(Locators.XPATH, "//span[contains(text(),'Timesheets: Timesheet, New')]");
	}
	
	public static By createTimesheetDropDown(){
		return CommonDef.locatorValue(Locators.XPATH, "//select[@name='range']");
	}
	
	public static By saveCreatedTimesheet(){
		return CommonDef.locatorValue(Locators.XPATH, "//input[@name='save']");
	}
	
	public static By timesheetMenu(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='item-inner']//span[contains(text(),'Timesheets')]");
	}
	
	public static By categoryTimeSheet(String category){
		return CommonDef.locatorValue(Locators.XPATH, "//ul[@class='nav-menu-list']//span[contains(text(),'Timesheets')]//..//following::span[contains(text(),'"+ category +"')]");
	}
	
	public static By selectTimesheet(String date){
		return CommonDef.locatorValue(Locators.XPATH, "//div[text()='"+date+"']/..//following-sibling::td//a");
	}
	
	public static By DropDown(String id){
		return CommonDef.locatorValue(Locators.ID, id);
	}

	public static By inputHours(String id){
		return CommonDef.locatorValue(Locators.ID, id);
	}
	
	public static By addtlInfoLink(String id){
		return CommonDef.locatorValue(Locators.XPATH, "//input[@id='"+id+"']/following-sibling::a/i");
	}
	
	public static By premiseSelect(){
		return CommonDef.locatorValue(Locators.XPATH, "//div[@class='dialogControls']/select");
	}
	
	public static By addtlInfoOK(){
		return CommonDef.locatorValue(Locators.XPATH, "//button[text()='OK']");
	}
	
	public static By saveSubmitButton(){
		return CommonDef.locatorValue(Locators.XPATH, "//button[text()='Save & Submit']");
	}

}
