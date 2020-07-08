package mav.bank.driver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.LogStatus;

import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ProjectConfig;

public class DesktopDriver implements NewDriver {

	WebDriver remoteDriver = null;

	@Override
	public WebDriver getNewDriver() {
		remoteDriver = getRemoteDriver();
		return remoteDriver;
	}

	public WebDriver getRemoteDriver() {
		//int trycount = 0;

		String SelHost = ProjectConfig.getPropertyValue("SelHost");
		DesiredCapabilities desiredCap = null;
		InternetExplorerOptions options = null;
		String OS=ConfigProvider.getConfig("OS");
		String version = ConfigProvider.getConfig("Version");
		String versionspec = ProjectConfig.getPropertyValue("versionspecific");
		
		System.out.println(" Daniel from getRemoteDriver" );
		System.out.println(OS);
		System.out.println(version);
		System.out.println(versionspec);
		System.out.println(ConfigProvider.getConfig("Browser").toUpperCase());


		try {

			switch (ConfigProvider.getConfig("Browser").toUpperCase()) {
			case "FIREFOX":
					desiredCap = DesiredCapabilities.firefox();
					desiredCap.setBrowserName("firefox");
					if (versionspec.equals("true"))
						desiredCap.setVersion(version);
				break;
			case "CHROME":
				if(OS.equalsIgnoreCase("MAC")){ 
					System.setProperty("webdriver.chrome.driver","/Users/maveric/maverick-testAutomation/chromedriver");
					desiredCap = DesiredCapabilities.chrome();
					desiredCap.setBrowserName("chrome");
					if (versionspec.equals("true"))
						desiredCap.setVersion(version);
					break;
				}

				else{
					desiredCap = DesiredCapabilities.chrome();
					desiredCap.setBrowserName("chrome");
					if (versionspec.equals("true"))
						desiredCap.setVersion(version);
					break;
				}
			case "IE":
				options = new InternetExplorerOptions();
				/*desiredCap = DesiredCapabilities.internetExplorer();
				desiredCap.setBrowserName("internet explorer");
				desiredCap["se:ieOptions"] = {};
				desiredCap.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 30000);
				desiredCap.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, ProjectConfig.getPropertyValue("url"));
				desiredCap.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
				desiredCap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				desiredCap.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
				desiredCap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
				desiredCap["se:ieOptions"]["ie.ensureCleanSession"] = True*/
				options.introduceFlakinessByIgnoringSecurityDomains();
				options.ignoreZoomSettings();
				options.enablePersistentHovering();
				options.enableNativeEvents();
				options.destructivelyEnsureCleanSession();
				options.withInitialBrowserUrl(ProjectConfig.getPropertyValue("url"));
				if (versionspec.equals("true"))
					desiredCap.setVersion(version);
				break;
			case "SAFARI":
				desiredCap = DesiredCapabilities.safari();
				desiredCap.setBrowserName("safari");
				desiredCap.setCapability("webdriver.safari.noinstall", "true");
				break;
			default:
				desiredCap = DesiredCapabilities.firefox();
				break;
			}
			
			if(ConfigProvider.getConfig("Browser").toUpperCase().equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
				remoteDriver = new InternetExplorerDriver(options);
				//remoteDriver = new RemoteWebDriver(new URL(SelHost), options);
			}
			else if(ConfigProvider.getConfig("Browser").toUpperCase().equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				remoteDriver = new ChromeDriver(desiredCap);
				//remoteDriver = new RemoteWebDriver(new URL(SelHost), options);
			}else
				remoteDriver = new RemoteWebDriver(new URL(SelHost), desiredCap);
			//remoteDriver.navigate().to(ProjectConfig.getPropertyValue("url"));
			remoteDriver.manage().window().maximize();
			ExReporter.log(LogStatus.INFO, "Browser Initiated successfully");

			remoteDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} catch (Exception e) {
			ExReporter.log(LogStatus.FATAL, "Browser Initiation Failed :" + e.getMessage());
		}
		return remoteDriver;
	}
}