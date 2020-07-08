package mav.bank.driver;

import org.openqa.selenium.WebDriver;

import mav.bank.framework.ConfigProvider;

/**	
* Class Name			:	DriverFactory
* Use					:	DriverFactory handles all the driver related functions for initialization, closure and  getting the current driver in use
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

public class DriverFactory {
	private static ThreadLocal<WebDriver> currentDriver = new ThreadLocal<WebDriver>();

	  /**	
	   * Method Name			:	getCurrentDriver 
	   * Use					:	Function for getting the current driver in execution and returning it to the called function
	   * @return WebDriver a webdriver object which is of the type the current driver in execution
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   */
	
	public static WebDriver getCurrentDriver() {
		WebDriver driver = currentDriver.get();
		if (driver != null) {
			return driver;
		} else {
			return null;
		}
	}

	  /**	
	   * Method Name			:	driverInit 
	   * Use					:	Function for initialization of driver, type of which is defined in the test setup scenario sheet
	   * 							either IOS or Android driver is initialized
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   */
	
	public static void driverInit() {
		String browser = ConfigProvider.getConfig("Platform").toUpperCase();
		if(!browser.contains("BROWSERSTACK"))
			browser=ConfigProvider.getConfig("Browser").toUpperCase();		
		//System.out.println(browser);
		switch (browser) {

		case "APPIUM":
			if (ConfigProvider.getConfig("OS").toUpperCase().equals("ANDROID"))
				currentDriver.set(new AppiumAppDriver().getNewDriver());
			else if (ConfigProvider.getConfig("OS").toUpperCase().equals("IOS"))
				currentDriver.set(new AppiumIOSAppDriver().getNewDriver());
			break;

		case "FIREFOX":
			currentDriver.set(new DesktopDriver().getNewDriver());
			break;
		case "CHROME":
			currentDriver.set(new DesktopDriver().getNewDriver());
			break;
		case "IE":
			//System.out.println("inside ie");
			currentDriver.set(new DesktopDriver().getNewDriver());
			break;
		case "SAFARI":
			currentDriver.set(new DesktopDriver().getNewDriver());
			break;
		case "BROWSERSTACK":
			//System.out.println("Inside Browser Stack");
			currentDriver.set(new BrowserStackDriver().getNewDriver());
			break;
		default:
			//System.out.println("Unknown Driver");
		}
	}

	  /**	
	   * Method Name			:	closeDriver 
	   * Use					:	Function to quit the driver currently under execution, after the completion or failure of an execution
	   * @return null
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   */
	
	public static void closeDriver() {
		
		WebDriver driver = currentDriver.get();
		if (driver != null) {
			//getCurrentDriver().close();
			getCurrentDriver().quit();
		}
		currentDriver.remove();
	}
}