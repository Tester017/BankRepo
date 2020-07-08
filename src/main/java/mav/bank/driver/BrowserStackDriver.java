package mav.bank.driver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.LogStatus;

import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ProjectConfig;
import mav.bank.framework.TestData;

public class BrowserStackDriver implements NewDriver {

	public static final String USERNAME = ProjectConfig.getPropertyValue("BSUserName");
	public static final String AUTOMATE_KEY = ProjectConfig.getPropertyValue("BSKey");
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	WebDriver remoteDriver = null;

	@Override
	public WebDriver getNewDriver() {
		remoteDriver = getRemoteDriver();
		return remoteDriver;
	}

	public WebDriver getRemoteDriver() {
		// int trycount = 0;

		// String SelHost = ProjectConfig.getPropertyValue("SelHost");
		DesiredCapabilities desiredCap = new DesiredCapabilities();
		desiredCap.setCapability("browserstack.local", "true");
		desiredCap.setCapability("build", ProjectConfig.getPropertyValue("buildNumber"));
		desiredCap.setCapability("project", "CAO");
		// String version = ConfigProvider.getConfig("Version");
		// String versionspec =
		// ProjectConfig.getPropertyValue("versionspecific");
		// String dev = ConfigProvider.getConfig("Device");
		String browsr = ConfigProvider.getConfig("Browser");
		String os = ConfigProvider.getConfig("OS");
		String versn = ConfigProvider.getConfig("Version");
		//String device = TestData.getConfig("devicename");

		try {
			switch (browsr) {
			case "ios":
				System.out.println("Iphone Browser");
				desiredCap.setCapability("device", TestData.getConfig("devicename"));
				desiredCap.setCapability("os", "IOS");
				desiredCap.setCapability("browser", "iPhone");
				break;
			case "IE":
				System.out.println("Internet Explorer");
				desiredCap.setCapability("os", os);
				desiredCap.setCapability("browser", "IE");
				desiredCap.setCapability("browser_version", versn);
				break;
			case "Firefox":
				//System.out.println("Firefox Browser");
				desiredCap.setCapability("os", os);
				desiredCap.setCapability("browser", "Firefox");
				desiredCap.setCapability("browser_version", versn);
				break;
			case "Chrome":
				System.out.println("Chrome Browser");
				desiredCap.setCapability("os", os);
				desiredCap.setCapability("browser", "Chrome");
				desiredCap.setCapability("browser_version", versn);
				break;
			case "Safari":
				System.out.println("Safari Browser");
				// desiredCap.setCapability("platform", "MAC");
				desiredCap.setCapability("os", os);
				desiredCap.setCapability("browser", "Safari");
				desiredCap.setCapability("browser_version", versn);
				break;
			case "Android":
				System.out.println("Android Browser");
				desiredCap.setCapability("platform", "ANDROID");
				desiredCap.setCapability("browserName", "Chrome");
				desiredCap.setCapability("device", TestData.getConfig("devicename"));
				//desiredCap.setCapability("platform ","win");
				desiredCap.setCapability("os", "Android");
				desiredCap.setCapability("browser", "Android");
				break;

			case "desktop":
				//System.out.println(" Browser");
				desiredCap.setCapability("os", os);
				desiredCap.setCapability("browser", TestData.getConfig("BrowserName"));
				desiredCap.setCapability("browser_version", versn);
				break;
			default:
				desiredCap = DesiredCapabilities.firefox();
				break;
			}
			remoteDriver = new RemoteWebDriver(new URL(URL), desiredCap);
			if (!(browsr.equals("Android")))
				remoteDriver.manage().window().maximize();
			ExReporter.log(LogStatus.INFO, "Browser Initiated successfully");

			remoteDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} catch (Exception e) {
			try {
				Thread.sleep(2000);
				remoteDriver = new RemoteWebDriver(new URL(URL), desiredCap);
				remoteDriver.manage().window().maximize();
				remoteDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} catch (Exception e1) {
				ExReporter.log(LogStatus.FATAL, "Browser Initiation Failed :" + e1.getMessage());
			}
		}
		return remoteDriver;
	}
}