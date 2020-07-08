package mav.bank.driver;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ProjectConfig;

/**	
* Class Name			:	AppiumIOSAppDriver with the implementation of NewDriver interface to get a new driver
* Use					:	AppiumAppDriver creates a new IOS Appium Driver with the specified capabilities for execution through Cloud or Hardware Devices
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

public class AppiumIOSAppDriver implements NewDriver {

	WebDriver remoteDriver = null;

	public static final String USERNAME = ProjectConfig.getPropertyValue("SAUCE_USERNAME");
	public static final String ACCESS_KEY = ProjectConfig.getPropertyValue("SAUCE_AUTOMATE_KEY");
	  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	  //public static final String URL = "https://us1.appium.testobject.com/wd/hub";
	  
	@Override
	public WebDriver getNewDriver() {
		System.out.println("LAUNCHING APPIUM DRIVER");

		String grid_HN = ProjectConfig.getPropertyValue("Appium_Grid_HostName");
		String grid_port = ProjectConfig.getPropertyValue("Appium_Grid_Port");
		//String host = "http://" + "127.0.0.1" + ":" + "4725" + "/wd/hub"; // Hardcoded
		
		String deviceName = ConfigProvider.getConfig("Device");
		String data[] = ProjectConfig.getPropertyValue(deviceName).split("~");
		String deviceId = data[0]; // ProjectConfig.getPropertyValue(deviceName);
										// //Hardcoded
		String host = "http://"+data[2]+":"+data[1]+"/wd/hub";
		System.out.println("appium driver host" + host);
		String os = ConfigProvider.getConfig("OS");
		String appRelPath = ProjectConfig.getPropertyValue("iOSAppPath");
		File appFile = new File(appRelPath);
		String appPath = appFile.getAbsolutePath();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		/*capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, os);
		//capabilities.setCapability(MobileCapabilityType.UDID, "efb59f09e8e3685932394d9f9010012dbbc75277");
		capabilities.setCapability(MobileCapabilityType.UDID, deviceId);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceId);
		capabilities.setCapability(MobileCapabilityType.APP, appPath);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0.2");
		capabilities.setCapability("usePrebuiltWDA", "true");
		capabilities.setCapability("wdaLocalPort", data[3]);*/
		
		capabilities.setCapability("platformName", "iOS");
	    capabilities.setCapability("deviceName", "iPhone Simulator");
	    capabilities.setCapability("platformVersion", "10.0");
	    capabilities.setCapability("app", "sauce-storage:FinacleMobileApp.zip");
	    capabilities.setCapability("deviceOrientation", "portrait");
	    capabilities.setCapability("appiumVersion", "1.6.4");
	    capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 5 Free");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("app", "sauce-storage:FinacleMobileApp.ipa");
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("appiumVersion", "1.6.4");
        capabilities.setCapability("testobject_api_key", "82359CB51AEB45E5AC843DF1F089FA5D");*/
		
		System.out.println(appPath);
		try {
			//remoteDriver = new IOSDriver<>(new URL(host), capabilities);
			remoteDriver = new IOSDriver<>(new URL(URL), capabilities);
			ExReporter.log(LogStatus.INFO, "App driver is initiated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			ExReporter.log(LogStatus.ERROR, e.getMessage());
		}
		return remoteDriver;
	}

}
