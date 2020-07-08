package mav.bank.driver;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ProjectConfig;

public class AppiumMobBrowAndroid implements NewDriver {

	AndroidDriver remoteDriver = null;

	@Override
	public WebDriver getNewDriver() {
		remoteDriver = getAppiumDriver();
		return remoteDriver;
	}

	private synchronized AndroidDriver getAppiumDriver() {

		String appHost = ProjectConfig.getPropertyValue("andappiumgrid");
		String selPort = ProjectConfig.getPropertyValue("SelPort");
		DesiredCapabilities desiredCap = new DesiredCapabilities();

		String selHost = ProjectConfig.getPropertyValue("SelHost");
		String dev = ConfigProvider.getConfig("Device");
		String devName = ProjectConfig.getPropertyValue(dev).split("~")[0];
		System.out.println("DRIVER Devicename" + devName);
		String devPort = ProjectConfig.getPropertyValue(dev).split("~")[1];

		try {
			System.out.println("SET CAps");
			// desiredCap.setCapability(MobileCapabilityType.PLATFORM_NAME,
			// "Android");
			desiredCap.setCapability(MobileCapabilityType.PLATFORM, "Android");
			desiredCap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			desiredCap.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
			desiredCap.setCapability(MobileCapabilityType.DEVICE_NAME, devName);
			desiredCap.setCapability(MobileCapabilityType.UDID, devName);
			System.out.println("Launch");
			remoteDriver = launchAndroidDriver(appHost, selPort, desiredCap);
			/*
			 * remoteDriver = new AndroidDriver<WebElement>(new URL("http://" +
			 * appHost + ":" + devPort + "/wd/hub"), desiredCap);
			 */
		} catch (Exception e) {
			ExReporter.log(LogStatus.FATAL, "ERROR LAUNCHING DRIVER: " + e.getMessage());
		}

		return remoteDriver;
	}

	private synchronized AndroidDriver launchAndroidDriver(String appHost, String selPort,
			DesiredCapabilities desiredCap) {
		try {
			remoteDriver = new AndroidDriver(new URL("http://" + appHost + ":" + selPort + "/wd/hub"),
					desiredCap);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FATAL, "ERROR LAUNCHING DRIVER: " + e.getMessage());
		}
		return remoteDriver;
	}
}
