package mav.bank.driver;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ProjectConfig;

/**	
* Class Name			:	AppiumAppDriver with the implementation of NewDriver interface to get a new driver
* Use					:	AppiumAppDriver creates a new Android Appium Driver with the specified capabilities for execution through Cloud or Hardware Devices
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

public class AppiumAppDriver implements NewDriver {
	WebDriver remoteDriver = null;
	
	public static final String USERNAME = ProjectConfig.getPropertyValue("SAUCE_USERNAME");
	public static final String ACCESS_KEY = ProjectConfig.getPropertyValue("SAUCE_AUTOMATE_KEY");
	  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	  //public static final String URL = "https://eu1.appium.testobject.com/wd/hub";
	
	  /**	
	   * Method Name			:	getNewDriver 
	   * Use					:	Function for creation of a new Ios Driver for handling android mobile execution through cloud or hardware devices
	   * @return WebDriver a webdriver object which is of Ios Web Driver type
	   * Designed By			:	surya kumar
	   * Date Last Modified		:	21-Aug-2017
	   */
	  
	public WebDriver getNewDriver() {
		
		System.out.println("LAUNCHING APPIUM DRIVER");
		
		String grid_HN= ProjectConfig.getPropertyValue("Appium_Grid_HostName");
		String grid_port= ProjectConfig.getPropertyValue("Appium_Grid_Port");
		
		String deviceName = ConfigProvider.getConfig("Device");
		System.out.println(deviceName);
		String data[] = ProjectConfig.getPropertyValue(deviceName).split("~");	
		String deviceId= data[0];
		String host=	"http://"+data[2]+":"+data[1]+"/wd/hub";
		System.out.println("appium driver host"+host);
		String os = ConfigProvider.getConfig("OS");
		String appRelPath = ProjectConfig.getPropertyValue("AppPath");
		File appFile = new File(appRelPath);
		String appPath = appFile.getAbsolutePath();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, os);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceId);
		capabilities.setCapability(MobileCapabilityType.APP, appPath);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		if((ConfigProvider.getConfig("Device").equalsIgnoreCase("MOTOG"))){
			capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,"/Users/rakbank/Desktop/Framework/RAK Bank/Drivers/chrome_2_10/chromedriver");
		}	
		else if((ConfigProvider.getConfig("Device").equalsIgnoreCase("NEXUS"))){
			capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,"/Users/rakbank/Desktop/Framework/RAK Bank/Drivers/chrome_2_30/chromedriver");
		}else if((ConfigProvider.getConfig("Device").equalsIgnoreCase("HUAWEI"))){
			capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,"/Users/rakbank/Desktop/Framework/RAK Bank/Drivers/chrome_2_25/chromedriver");
		}else if((ConfigProvider.getConfig("Device").equalsIgnoreCase("S4"))){
			capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,"/Users/rakbank/Desktop/Framework/RAK Bank/Drivers/chrome_2_32/chromedriver");
		}
		
		//DesiredCapabilities capabilities = new DesiredCapabilities();
/*	    capabilities.setCapability("platformName", "Android");
	    capabilities.setCapability("deviceName", "Android Emulator");
	    capabilities.setCapability("platformVersion", ConfigProvider.getConfig("Version"));
	    capabilities.setCapability("app", "https://conv.rakbankonline.ae/corp3/APK/RAK2FAUNSIGN.apk");
	    capabilities.setCapability("deviceOrientation", "portrait");
	    capabilities.setCapability("appiumVersion", "1.6.4");
	    capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
	    //capabilities.setCapability("testobject_api_key", "82359CB51AEB45E5AC843DF1F089FA5D");
*/	    
		System.out.println(appPath);
		try {
			remoteDriver = new AndroidDriver<>(new URL(host), capabilities);
			//remoteDriver = new AndroidDriver<>(new URL(URL), capabilities);
			ExReporter.log(LogStatus.INFO, "App driver is initiated successfully");
		} catch (Exception e) 
		{
			e.printStackTrace();
			ExReporter.log(LogStatus.ERROR, e.getMessage());
		}
		return remoteDriver;
	}
	
	

}