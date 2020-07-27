package mav.bank.library.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ContextAware;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import mav.bank.driver.DriverFactory;
import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ProjectConfig;
import mav.bank.framework.TestData;
import mav.bank.pom.elements.bsf.FundsTransferPage;
import mav.bank.utilities.enums.Locators;

/**	
* Class Name			:	CommonDef
* Use					:	CommonDef class contains all the common functions used across scripting and execution e.g- Wait functions, click, screenshot etc
* Designed By			:	surya kumar
* Date Last Modified	:	21-Aug-2017
*/

/*?PraveenSekar?*/

public class CommonDef {

	// static WebDriver driver = DriverFactory.getCurrentDriver();

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMHmmss");
		return sdf.format(cal.getTime());
	}
	
	public static String captureScreen() {
		String path;
		File trgtPath = null;
		try {
			WebDriver driverLoc;
			File source=null;
			if(ConfigProvider.getConfig("OS").equalsIgnoreCase("IOS")){
			driverLoc = DriverFactory.getCurrentDriver();
			WebDriver augmentedDriver = new Augmenter().augment(driverLoc);
			source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			}else if(ConfigProvider.getConfig("OS").equalsIgnoreCase("ANDROID")){
				switchToNative();
				driverLoc = DriverFactory.getCurrentDriver();
				WebDriver augmentedDriver = new Augmenter().augment(driverLoc);
				source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				switchToWebview();
			}else {
				driverLoc = DriverFactory.getCurrentDriver();
				WebDriver augmentedDriver = new Augmenter().augment(driverLoc);
				source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			}
			//File source = ((TakesScreenshot) driverLoc).getScreenshotAs(OutputType.FILE);
			//path = ExReporter.reportPath + "/" + source.getName();
			String temp = ConfigProvider.getConfig("Testname") + "/" +TestData.getConfig("DataBinding") + "/" + TestData.getConfig("DataBinding")+ "_" + now() + ".png";
			path = ExReporter.reportPath + "/" + temp;
			//System.out.println(path);
			trgtPath = new File(path);

			FileUtils.copyFile(source, trgtPath);
			//return source.getName();// trgtPath.getAbsolutePath();
			return temp;
		} catch (Exception e) {
			return "";
		}
		// return trgtPath.getAbsolutePath();

	}

	public static String valuereturn(By by) {
		String value = null;
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			value = driver.findElement(by).getText().toString();
			System.out.println(value);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
		return value;
	}
	
	public static void waitForPageLoad() {
		WebDriver driver = DriverFactory.getCurrentDriver();
		ExpectedCondition<Boolean> expect = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, 120);
		try {
			wait.until(expect);
		} catch (Exception E) {
			ExReporter.log(LogStatus.INFO, "Page Load Condition failed. Continuing with test");
		}
	}

	public static By locatorValue(Locators locatorTpye, String value) {
		By by = null;
		switch (locatorTpye) {
		case ID:
			by = By.id(value);
			break;
		case NAME:
			by = By.name(value);
			break;
		case XPATH:
			by = By.xpath(value);
			break;
		case CSS:
			by = By.cssSelector(value);
			break;
		case LINKTEXT:
			by = By.linkText(value);
			break;
		case PARTIAL_LINKTEXT:
			by = By.partialLinkText(value);
			break;
		case TAG_NAME:
			by = By.tagName(value);
			break;
		case CLASS_NAME:
			by = By.className(value);
			break;
		case NA:
			break;
		}
		return by;
	}

	public static WebElement findElement(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			return driver.findElement(by);
		} catch (Exception E) {
			ExReporter.log(LogStatus.ERROR, "Element not located in the page :" + E.getMessage());
			return null;
		}
	}

	public static void waitWebView() {
	try {
		WebDriver driver = DriverFactory.getCurrentDriver();
		waitloop : for(int i = 0 ; i<50; i++){
			Set<String> contextNames = ((ContextAware) driver).getContextHandles();
			for (String contextName : contextNames) {
				//System.out.println(contextName);
				if (contextName.startsWith("WEBVIEW")) {
					CommonDef.switchToWebview();
					break waitloop;
				}else{
					//System.out.println("waiting");
					Thread.sleep(2000);
				}
			}
			}
	}catch (Exception e) {
		// TODO Auto-generated catch block
		ExReporter.log(LogStatus.FAIL, e.getMessage());
	}
	}
	
	public static List<WebElement> findElements(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			return driver.findElements(by);
		} catch (Exception E) {
			ExReporter.log(LogStatus.ERROR, "Elements not located in the page");
			return null;
		}
	}
	
	/**
	 * Used to find multiple elements in Payments module
	 */
	public static ArrayList<String> findElementsText(By by) {
		try {
			ArrayList<String> mylist = new ArrayList<String>();						
			List<WebElement> elems = findElements(by);
			for (WebElement elem : elems) {
				mylist.add(elem.getText());			
			}
			return mylist;
		} catch (Exception E) {
			ExReporter.log(LogStatus.ERROR, "Elements not located in the page");
			return null;
		}
	}

	public static void click(By by) {
		try {
			WebElement elem = findElement(by);
			elem.click();
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page");
		} catch (Exception E) {
			ExReporter.log(LogStatus.ERROR, "Elements not located in the page");
		}
	}
	
	public static void done() {
		try {
			switchToNative();
			WebDriver driver = DriverFactory.getCurrentDriver();
			((IOSDriver) driver).hideKeyboard("Done");
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page");
			switchToWebview();
		} catch (Exception E) {
			ExReporter.log(LogStatus.ERROR, "Elements not located in the page");
			switchToWebview();
		}
	}

	public static void clickThrowError(By by, String elemname) {
		WebDriver driver = DriverFactory.getCurrentDriver();
		WebElement elem = driver.findElement(by);// findElement(by);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", elem);
		ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page: " + elemname);
	}

	public static void clickJS(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebElement elem = findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page");
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "Elements not located in the page");
		}
	}

	public static void clickJS(By by, String elemname) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebElement elem = findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
			ExReporter.log(LogStatus.ERROR, "Elements not located in the page: " + elemname);
		}
	}

	public static void clickJS(WebElement elem, String elemname) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
			ExReporter.log(LogStatus.ERROR, "Elements not located in the page: " + elemname);
		}
	}

	public static void clickJSNoError(By by, String elemname) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebElement elem = driver.findElement(by);// findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
		}
	}

	public static void click(By by, String elemname) {
		try {
			WebElement elem = findElement(by);
			elem.click();
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "Elements not located in the page");
		}
	}

	public static void clickNoError(By by, String elemname) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			Thread.sleep(10000);
			driver.findElement(by).click();
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
		}
	}

	public static void click(WebElement elem) {
		try {
			elem.click();
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page");
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "Elements not located in the page");
		}
	}

	public static void click(WebElement elem, String elementname) {
		try {
			elem.click();
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page :" + elementname);
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "Elements not located in the page: " + elementname);
		}
	}

	public static Boolean ObjectExist(By LocatorValue) {
		Boolean ObjectExist = null;
		WebDriver driver = DriverFactory.getCurrentDriver();		
		waitVisibleNoError(LocatorValue, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
		try {
			Thread.sleep(1500);
			if (driver.findElement(LocatorValue).isDisplayed()) {
				ObjectExist = true;
			} else
				ObjectExist = false;
		} catch (Exception E) {
			ObjectExist = false;
		}
		return ObjectExist;
	}

	public static void sendKeys(By by, String keysToSend) {
		try {
			WebElement t = findElement(by);
			t.sendKeys(keysToSend);
			Thread.sleep(1000);
			ExReporter.log(LogStatus.PASS, "Text is entered successfully :" + keysToSend);
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "Text not entered successfully");
		}
	}

	public static void sendKeys(WebElement elem, String keysToSend) {
		try {
			elem.sendKeys(keysToSend);
			ExReporter.log(LogStatus.PASS, "Text is entered successfully :" + keysToSend);
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "Text not entered successfully");
		}
	}

	public static void waitClick(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not clickable");
		}
	}
	
	public static void waitClickContinue(By by,By by1) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.presenceOfElementLocated(by1));
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(by1));
			waitloop : for(int i =0; i < 10 ; i++){
			if(driver.findElement(by).isDisplayed()){
				MethodDef.click(by);
				ExReporter.log(LogStatus.PASS, "Transaction Post Cut off Time");
				break waitloop;
			}else if(driver.findElement(by1).isDisplayed()){
				ExReporter.log(LogStatus.PASS, "Transaction within Cut off Time");
				break waitloop;
			}
			Thread.sleep(1000);
			}
		} catch (Exception e) {
			ExReporter.log(LogStatus.PASS, "Element Not Located in Page");
		}
	}

	public static void waitVisible(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not visible");
		}
	}

	public static void waitVisible(By by, int sec) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, sec);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not visible");
		}
	}

	public static void waitVisible(By by, String name) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			ExReporter.log(LogStatus.PASS, "Element is visible :" + name);
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not visible :" + name);
		}
	}

	public static void waitInVisible(By by, String name) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			ExReporter.log(LogStatus.PASS, "Element not visible :" + name);
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element is visible :" + name);
		}
	}
	
	public static void waitClickable(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not clickable");
		}
	}

	public static void waitClickable(By by, String elemname) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			ExReporter.log(LogStatus.WARNING, "Element not clickable: " + elemname);
		}
	}
	
	public static void waitPresent(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			ExReporter.log(LogStatus.WARNING, "Element not clickable" );
		}
	}

	public static boolean waitVisibleNoError(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			// ExReporter.log(LogStatus.ERROR, "Element not visible");
			return false;
		}
	}

	public static void waitVisibleNoError(By by, int sec) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, sec);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			// ExReporter.log(LogStatus.ERROR, "Element not visible");
		}
	}

	public static void waitForAttribute(By by, String attr, String value) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.attributeContains(by, attr, value));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element has not switched to the correct attribute to continue");
		}
	}

	public static void waitClickableNoError(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// ExReporter.log(LogStatus.ERROR, "Element not visible");
		}
	}

	public static void waitClickableNoError(By by, int time) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// ExReporter.log(LogStatus.ERROR, "Element not visible");
		}
	}

	public static void waitVisible(WebElement elem) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOf(elem));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not visible");
		}
	}

	public static void waitVisible(WebElement elem, String name) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOf(elem));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not visible :" + name);
		}
	}

	public static void clearText(By by) {
		WebDriver driver = DriverFactory.getCurrentDriver();
		driver.findElement(by).clear();
	}

	public static void clearText(WebElement elem) {
		elem.clear();
	}

	public static void waitClickable(WebElement elem) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOf(elem));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not clickable");
		}
	}

	public static void waitClickable(WebElement elem, String elemname) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOf(elem));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not clickable: " + elemname);
		}
	}

	public static void moveClick(By by, String elemname) {
		if (!ConfigProvider.getConfig("Browser").equalsIgnoreCase("SAFARI")) {
			WebDriver driver = DriverFactory.getCurrentDriver();
			Actions action = new Actions(driver);
			action.moveToElement(findElement(by)).click().build().perform();
		} else {
			click(by);
		}
	}
	
	public static void moveClick(WebElement elem, String elemname) {
		if (!ConfigProvider.getConfig("Browser").equalsIgnoreCase("SAFARI")) {
			WebDriver driver = DriverFactory.getCurrentDriver();
			Actions action = new Actions(driver);
			action.moveToElement(elem).click().build().perform();
		} else {
			click(elem);
		}
	}

	public static void moveToElement(By by, String elemname) {
		if (!ConfigProvider.getConfig("Browser").equalsIgnoreCase("SAFARI")) {
			WebDriver driver = DriverFactory.getCurrentDriver();
			Actions action = new Actions(driver);
			action.moveToElement(findElement(by)).perform();
		}
	}
	
	public static void moveToElement(WebElement elem, String elemname) {
		if (!ConfigProvider.getConfig("Browser").equalsIgnoreCase("SAFARI")) {
			WebDriver driver = DriverFactory.getCurrentDriver();
			Actions action = new Actions(driver);
			action.moveToElement(elem).perform();
		}
	}

	public static void moveToElementNoLog(WebElement elem) {
		try {
			if (!ConfigProvider.getConfig("Browser").equalsIgnoreCase("SAFARI")) {
				WebDriver driver = DriverFactory.getCurrentDriver();
				Actions action = new Actions(driver);
				action.moveToElement(elem).perform();
			}
		} catch (Exception E) {

		}
	}

	public static void moveToElementClickNoFail(By by, String elemname) {
		if (!ConfigProvider.getConfig("Browser").equalsIgnoreCase("SAFARI")) {
			WebDriver driver = DriverFactory.getCurrentDriver();
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(by)).click().build().perform();
		} else {
			clickNoError(by, elemname);
		}
	}

	public static String getText(By by) {
		try {
			return findElement(by).getText();
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "UNABLE TO GET TEXT FROM ELEMENT");
		}
		return null;
	}
	
	public static String getTextNoError(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			return driver.findElement(by).getText();
		} catch (Exception E) {
			//ExReporter.log(LogStatus.FAIL, "UNABLE TO GET TEXT FROM ELEMENT");
		}
		return null;
	}

	public static String getText(By by, String elem) {
		try {
			return findElement(by).getText();
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "UNABLE TO GET TEXT FROM ELEMENT: " + elem);
		}
		return null;
	}

	public static void assertContainsData(By by) {
		waitVisible(by);
		try {
			WebElement elem = findElement(by);
			if (!elem.getText().isEmpty())
				ExReporter.log(LogStatus.PASS, "Data is populated in the Element");
			else
				ExReporter.log(LogStatus.FAIL, "Data is not populated in the Element");
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Error in accessing data");
		}
	}
	
	public static void assertContainsData(By by, String string) {
		try {
			WebElement elem = findElement(by);
			if (elem.getText().isEmpty())
				ExReporter.log(LogStatus.FAIL, "Element " + elem + "has no data");
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error accessing element data");
		}
	}
	
	public static boolean assertContainsNoError(WebElement elem, String text) {
		try {
			if (elem.getText().contains(text)) {
				ExReporter.log(LogStatus.PASS, "Text is populated in the Element: " + text);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void assertEquals(By by, String text) {
		waitVisible(by);
		try {
			WebElement elem = findElement(by);
			if (elem.getText().equals(text))
				ExReporter.log(LogStatus.PASS, "Text is populated in the Element: " + text);
			else
				ExReporter.log(LogStatus.FAIL, "Text is not populated in the Element: " + text);
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Error in accessing Text");
		}
	}

	public static boolean assertContains(By by, String text) {
		try {
			WebElement elem = findElement(by);
			try {
				moveToElementNoLog(elem);
			} catch (Exception e) {

			}
			if (elem.getText().contains(text)) {
				ExReporter.log(LogStatus.PASS,
						"Text is populated in the Element: " + text + " ||actual|| " + elem.getText());
				return true;
			} else {
				ExReporter.log(LogStatus.FAIL,
						"Text is not populated in the Element: " + text + " ||actual|| " + elem.getText());
				return false;
			}
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
		}
		return false;
	}
	
	public static boolean assertContainsOnAttribute(By by, String text, String Attribute) {
		try {			
			WebElement elem = findElement(by);
			String attrbdata=elem.getAttribute(Attribute);
			try {
				moveToElementNoLog(elem);
			} catch (Exception e) {

			}
			if (attrbdata.contains(text)) {
				ExReporter.log(LogStatus.PASS,"Text is populated in the Attribute");
				return true;
			} else {
				ExReporter.log(LogStatus.FAIL,"Text is not populated in the Attribute");
				return false;
			}
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
		}
		return false;
	}

	public static boolean assertElemsContains(By by, String text) {
		try {
			List<WebElement> elems = findElements(by);
			for (WebElement elem : elems) {
				try {
					moveToElementNoLog(elem);
				} catch (Exception e) {

				}
				if (elem.getText().contains(text)) {
					ExReporter.log(LogStatus.PASS,
							"Text is populated in the Element: " + text + " ||actual|| " + elem.getText());
					return true;
				}
			}
			ExReporter.log(LogStatus.FAIL, "Text is not populated in the Elements: " + text);
			return false;
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
		}
		return false;
	}

	public static boolean assertElemsContainsWarn(By by, String text) {
		try {
			List<WebElement> elems = findElements(by);
			for (WebElement elem : elems) {
				try {
					moveToElementNoLog(elem);
				} catch (Exception e) {

				}
				if (elem.getText().contains(text)) {
					ExReporter.log(LogStatus.PASS,
							"Text is populated in the Element: " + text + " ||actual|| " + elem.getText());
					return true;
				}
			}
			ExReporter.log(LogStatus.WARNING, "Text is not populated in the Elements: " + text);
			return false;
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
		}
		return false;
	}

	public static boolean assertInnerHTMLContains(By by, String text) {
		try {
			WebElement elem = findElement(by);
			if (elem.getAttribute("innerHTML").contains(text)) {
				ExReporter.log(LogStatus.PASS,
						"Text is populated in the Element: " + text + " ||actual|| " + elem.getAttribute("innerHTML"));
				return true;
			} else {
				ExReporter.log(LogStatus.FAIL, "Text is not populated in the Element: " + text + " ||actual|| "
						+ elem.getAttribute("innerHTML"));
				return false;
			}
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
		}
		return false;
	}

	public static boolean assertInnerHTMLContainsNoError(By by, String text) {
		try {
			WebElement elem = findElement(by);
			if (elem.getAttribute("innerHTML").contains(text)) {
				ExReporter.log(LogStatus.PASS,
						"Text is populated in the Element: " + text + " ||actual|| " + elem.getAttribute("innerHTML"));
				return true;
			} else {
				ExReporter.log(LogStatus.PASS, "Text is not populated in the Element: " + text + " ||actual|| "
						+ elem.getAttribute("innerHTML"));
				return false;
			}
		} catch (Exception e) {
			ExReporter.log(LogStatus.PASS, "Error in accessing Text : " + e.getMessage());
		}
		return false;
	}

	public static boolean assertInnerHTMLContainsNoError(WebElement elem, String text) {
		try {
			if (elem.getAttribute("innerHTML").contains(text)) {
				ExReporter.log(LogStatus.PASS,
						"Text is populated in the Element: " + text + " ||actual|| " + elem.getAttribute("innerHTML"));
				return true;
			} else {
				ExReporter.log(LogStatus.PASS, "Text is not populated in the Element: " + text + " ||actual|| "
						+ elem.getAttribute("innerHTML"));
				return false;
			}
		} catch (Exception e) {
			ExReporter.log(LogStatus.PASS, "Error in accessing Text : " + e.getMessage());
		}
		return false;
	}

	public static boolean assertContains(WebElement elem, String text) {
		try {
			if (elem.getText().contains(text)) {
				ExReporter.log(LogStatus.PASS, "Text is populated in the Element: " + text);
				return true;
			} else {
				ExReporter.log(LogStatus.FAIL, "Text is not populated in the Element: " + text);
				return false;
			}
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
		}
		return false;
	}

	public static void assertContains(By by, String text, String passmsg) {
		try {
			WebElement elem = findElement(by);
			if (elem.getText().contains(text))
				ExReporter.log(LogStatus.PASS, passmsg + " - Text is populated in the Element: " + text);
			else
				ExReporter.log(LogStatus.FAIL, passmsg + " - Text is not populated in the Element: " + text);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
		}
	}


	public static void scrollDown() {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(0, 700);");
		} catch (Exception E) {

		}
	}
	
	public static void flipUp() {
		try{
		WebDriver driver = DriverFactory.getCurrentDriver();
		((ContextAware) driver).context("NATIVE_APP"); // driver -> your android
														// driver
		Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * 0.80);
		int endY = 0;
		int startX = size.width / 2;
		String type=driver.getClass().toString();
		if(type.contains("IOSDriver"))
		((IOSDriver) driver).swipe(startX, startY, startX, endY, 1000);
		else
			((AndroidDriver) driver).swipe(startX, startY, startX, endY, 1000);
		switchToWebview();
	}catch (Exception e) {
		ExReporter.log(LogStatus.ERROR, "Screen Flip Failed");
	}
	}
	
	public static void scrollPayees(By by) {
		try{
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, 1);

			payeeloop :for(int i=0;i<10;i++){
				try{
					driver.findElement(by).click();
					break payeeloop;
				}catch(Exception e){
					System.out.println(e);
					((ContextAware) driver).context("NATIVE_APP"); // driver -> your android
					Dimension size = driver.manage().window().getSize();
					int startY = (int) (size.height * 0.80);
					int endY = 0;
					int startX = size.width / 2;
					String type=driver.getClass().toString();
					if(type.contains("IOSDriver"))
						((IOSDriver) driver).swipe(startX, startY, startX, endY, 1000);
					else
						((AndroidDriver) driver).swipe(startX, startY, startX, endY, 1000);
					switchToWebview();
				}

			}

		}catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Screen Flip Failed");
		}
	}
	
	public static void flipUpHalf() {
		try{
		WebDriver driver = DriverFactory.getCurrentDriver();
		((ContextAware) driver).context("NATIVE_APP"); // driver -> your android
														// driver
		Dimension size = driver.manage().window().getSize();
		int startY = (int) (size.height * 0.80);
		int endY = (int) (size.height * 0.50);
		int startX = size.width / 2;
		String type=driver.getClass().toString();
		if(type.contains("IOSDriver"))
		((IOSDriver) driver).swipe(startX, startY, startX, endY, 1000);
		else
			((AndroidDriver) driver).swipe(startX, startY, startX, endY, 1000);
		switchToWebview();
	}catch (Exception e) {
		ExReporter.log(LogStatus.ERROR, "Screen Flip Failed");
	}
	}
	
	public static void scrollValues () {
		try{

			WebDriver driver = DriverFactory.getCurrentDriver();

				((ContextAware) driver).context("NATIVE_APP"); // driver -> your android
				// driver
				Dimension size = driver.manage().window().getSize();
				int startY = (int) (size.height * 0.85);
				int endY = (int) (size.height * 0.60);
				int startX = size.width / 2;
				String type=driver.getClass().toString();
				if(type.contains("IOSDriver"))
					((IOSDriver) driver).swipe(startX, startY, startX, endY, 1000);
				else
					((AndroidDriver) driver).swipe(startX, startY, startX, endY, 1000);
				switchToWebview();
			
		}catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Scroll Values Failed");
		}
	}
	

	public static void waitWebElement(WebElement elem) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(elem));
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Element not clickable");
		}
	}
	
	public static void exchangeratevalidation(String Currency) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			MethodDef.click(FundsTransferPage.transactionFrequency());
			CommonDef.waitElement(FundsTransferPage.cancelButton());
			driver.findElement(FundsTransferPage.cancelButton()).click();
			
			// wait for and Get the Exchange rate
			CommonDef.waitElement(FundsTransferPage.exchangeRate());
			String value = driver.findElement(FundsTransferPage.exchangeRate()).getText().toString();
			String r1[] = value.split("=");
			String exRate[] = r1[1].split(" ");
			System.out.println(exRate[0]);
		//	Utilities.fStorein(Driver.TD_File, SCName, TestDataBinder, "Exhange_Rate", exRate[0]);
			if(exRate[0].equalsIgnoreCase(ProjectConfig.getPropertyValue(Currency)))
				ExReporter.log(LogStatus.PASS, "Exchange Rate Validation Passed");
			else
				ExReporter.log(LogStatus.PASS, "Exchange Rate Validation Failed");
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Exchange Rate Validation Failed");
		}
	}

	public static void valuePrint(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			String value = driver.findElement(by).getText().toString();
			System.out.println(value);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
	}
	
	public static String messageValidation(By by) {
		String Validation = null;
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			String value = driver.findElement(by).getText().toString();
			//System.out.println(value);
			if(value.contains("failed"))
				Validation = "fail";
			else
				Validation = "pass";
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
		return Validation;
	}
	
	public static void Writevalue(By by, By by1) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			String value = driver.findElement(by).getText().toString();
			String value1 = driver.findElement(by1).getText().toString();
			String value2 = TestData.getConfig("DataBinding");
			System.out.println(value);
			Object[][] writeData = {
	                {value2, value, value1},
	        };
			writeDynamicValue(writeData);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
	}
	
	public static void WritevalueSelfAccounts(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			String value = driver.findElement(by).getText().toString();
			String value2 = TestData.getConfig("DataBinding");
			Object[][] writeData = {
	                {value2, value},
	        };
			writeDynamicValue(writeData);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
	}
	
	public static void Writevalue(By by, By by1,String rate, String charge) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			String value = driver.findElement(by).getText().toString();
			String value1 = driver.findElement(by1).getText().toString();
			String value2 = TestData.getConfig("DataBinding");
			System.out.println(value);
			Object[][] writeData = {
	                {value2, value, value1, charge, rate},
	        };
			writeDynamicValue(writeData);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
	}
	
	public static void writeTransactionValues(String value,String scenario, String Column) {
		try {
			Object[][] writeData = {
	                {value},
	        };
			storeTransactionValues(writeData,scenario,Column);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
	}
	
	public static void WritevalueSelfAccounts(By by,String rate) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			String value = driver.findElement(by).getText().toString();
			String value2 = TestData.getConfig("DataBinding");
			Object[][] writeData = {
	                {value2, value, "-", "-", rate},
	        };
			writeDynamicValue(writeData);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
	}
	
	public static void WritevalueSelfAccounts(String ref,String rate) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			//String value = driver.findElement(by).getText().toString();
			String value2 = TestData.getConfig("DataBinding");
			Object[][] writeData = {
	                {value2, ref, "-", "-", rate},
	        };
			writeDynamicValue(writeData);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
	}
	
	public static void switchToNative() {
		WebDriver driver = DriverFactory.getCurrentDriver();
		((ContextAware) driver).context("NATIVE_APP");
	}

	public static void switchToWebview() {
		WebDriver driver = DriverFactory.getCurrentDriver();
		Set<String> contextNames = ((ContextAware) driver).getContextHandles();
		for (String contextName : contextNames) {
			//System.out.println(contextName);
			if (contextName.startsWith("WEBVIEW")) {
				((ContextAware) driver).context(contextName);
			}
		}
	}
	
	public static void waitElement(By by) {
		WebDriver driver = DriverFactory.getCurrentDriver();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public static void balanceLoadingWaitOutsideUae(By by) throws InterruptedException {
		//WebDriver driver = DriverFactory.getCurrentDriver();
		waitloop : for(int i =0 ; i<2;i++){
			try{
				//WebElement el = driver.findElement(SendMoneyOutsideUaePage.availableBalance(Currency));
				waitElement(by);
				break waitloop;
			}catch(Exception e){
				System.out.println(e);
				Thread.sleep(1500);
				
			}
		}
	}
	
	public static void balanceLoadingWait(By by) throws InterruptedException {
		//WebDriver driver = DriverFactory.getCurrentDriver();
		waitloop : for(int i =0 ; i<2;i++){
			try{
				//WebElement el = driver.findElement(SendMoneyOutsideUaePage.availableBalance(Currency));
				waitElement(by);
				break waitloop;
			}catch(Exception e){
				System.out.println(e);
				Thread.sleep(1500);
				
			}
		}
	}
	
	public static void fromAccountWait(By by) throws InterruptedException {
		//WebDriver driver = DriverFactory.getCurrentDriver();
		waitloop : for(int i =0 ; i<2;i++){
			try{
				//WebElement el = driver.findElement(SendMoneyOutsideUaePage.availableBalance(Currency));
				waitElement(by);
				break waitloop;
			}catch(Exception e){
				System.out.println(e);
				Thread.sleep(1500);
			}
		}
	}
	
	
	public static void findElementsClick(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			List<WebElement> elm = driver.findElements(by);
			for (WebElement s : elm) {
				clickJS(s);
			}
		} catch (Exception E) {
			ExReporter.log(LogStatus.ERROR, "Elements not located in the page");
		}
	}
	
	public static int findElementsCount(By by){
		int itmcnt=0;
		try{
			WebDriver driver = DriverFactory.getCurrentDriver();
			List<WebElement> elm = driver.findElements(by);			
			for (WebElement s : elm) {
				itmcnt=itmcnt+1;
			}
		}catch(Exception E){
			ExReporter.log(LogStatus.ERROR, "Elements not located in the page");
		}
		return itmcnt;
	}

	public static void clickJS(WebElement elem) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			ExReporter.log(LogStatus.PASS, "Element successfully clicked in the page");
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "Elements not located in the page");
		}
	}

	public static void isDisplayed(By by, String cont) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			waitVisible(by);
			WebElement element = driver.findElement(by);
			element.isDisplayed();
			ExReporter.log(LogStatus.PASS, "Element Available in the page " + cont);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Element not Available in the page " + e.getMessage());
		}
	}

	public static String getAttribute(By by, String attribute) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebElement element = driver.findElement(by);
			return element.getAttribute(attribute);
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Error getting attribure value of an element " + e.getMessage());
			return null;
		}
	}

	public static String getAttribute(WebElement element, String attribute) {
		try {
			return element.getAttribute(attribute);
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Error getting attribure value of an element " + e.getMessage());
			return null;
		}
	}

	public static void assertEquals(String attribute, String value) {
		try {
			if (attribute.equalsIgnoreCase(value))
				ExReporter.log(LogStatus.PASS, "Element has the correct attribute");
			else
				ExReporter.log(LogStatus.FAIL, "Element dooesn't has the correct attribure");
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Error getting attribure value of an element " + e.getMessage());
		}
	}

	public static void clickJquery(String query) {
		WebDriver driver = DriverFactory.getCurrentDriver();
		((JavascriptExecutor) driver).executeScript("return jQuery('" + query + "').get(0)");
	}

	public static void scollToPageEnd() {
		WebDriver driver = DriverFactory.getCurrentDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public static void switchNewWindow() {
		WebDriver driver = DriverFactory.getCurrentDriver();
		try {
			Set<String> newwindows = driver.getWindowHandles();
			String CurWindow = (String) newwindows.toArray()[newwindows.size() - 1];
			driver.switchTo().window(CurWindow);
		} catch (Exception E) {
			ExReporter.log(LogStatus.FAIL, "Unable to Navigate New Window : " + E.getMessage());
		}
	}

	public static void switch_Window() {
		WebDriver driver = DriverFactory.getCurrentDriver();

		try {
			Set<String> win = driver.getWindowHandles();
			int i = 0;
			for (String w : win) {
				if (i == 1) {
					driver.switchTo().window(w);
					ExReporter.log(LogStatus.PASS, "Switch window");
					break;
				}
				if (i == 0) {
					driver.switchTo().window(w);
					// String parent=w;
					i = i + 1;
					// ExReporter.log(LogStatus.PASS, "Switch window");
				}

			}

		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Unable Switch the Window: " + e.getMessage());
		}
	}

	public static void brokenLinkValidation(String URL) {
		// System.out.println("inside broken link");
		int response = getResponseCode(URL);
		if (response == 200) {
			// System.out.println("working 200");
			ExReporter.logNoScreen(LogStatus.PASS, URL + "-URL is working");
		} else {
			// System.out.println("not working NOT 200");
			ExReporter.logNoScreen(LogStatus.FAIL, URL + "-URL is not working");
		}
	}

	public static int getResponseCode(String urlString) {
		try {
			URL u = new URL(urlString);
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if (h.getResponseCode() == 301 || h.getResponseCode() == 302 || h.getResponseCode() == 307) {
				String newUrl = h.getHeaderField("Location");
				u = new URL(newUrl);
				h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
			}
			return h.getResponseCode();
		} catch (MalformedURLException e) {
			ExReporter.log(LogStatus.FAIL, "MalformedURLException" + e.getMessage());
			return -1;
		} catch (IOException e) {
			ExReporter.log(LogStatus.FAIL, "MalformedURLException" + e.getMessage());
		}
		return 0;
	}

	public static void assertContainsData(WebElement web, String string) {
		try {
			if (web.getText().isEmpty())
				ExReporter.log(LogStatus.FAIL, "Element " + web + "has no data");
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error accessing element data");
		}
	}

	public static void reloadPage() {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			driver.navigate().refresh();
			waitForPageLoad();
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "Error reloading page");
		}
	}
	public static void dropdown(By by, String keysToSend) {
		try {

		WebElement t = findElement(by);
		Select sel= new Select(t);
		sel.selectByVisibleText(keysToSend);
		Thread.sleep(1500);
		ExReporter.log(LogStatus.PASS, "Text is entered successfully :" + keysToSend);
		} catch (Exception E) {
		ExReporter.log(LogStatus.FAIL, "Text not entered successfully");
		}
		}
	 
	public static void beneficiaryValidation(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait =  new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
			ExReporter.log(LogStatus.PASS, "Beneficiary is Valid");
		} catch (Exception e) {
			ExReporter.log(LogStatus.ERROR, "Beneficiary is Invalid");
		}
	}
	
	public static void handleCustomAmount(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait =  new WebDriverWait(driver, 7);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			if(driver.findElement(by).isDisplayed()){
				MethodDef.click(by);
				System.out.println("test");
				ExReporter.log(LogStatus.PASS, "Custom Amount Selected");
			}
		} catch (Exception e) {
			//ExReporter.log(LogStatus.PASS, "Beneficiary is Invalid");
			System.out.println("catch");
		}
	}
	
	public static void waitClickOK(By by, By by1) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.presenceOfElementLocated(by1));
			Thread.sleep(1000);
			waitloop : for(int i=0 ; i<10 ; i++){
				if(driver.findElement(by).isDisplayed()){
				MethodDef.click(by);
				ExReporter.log(LogStatus.PASS, "Ok Button is clicked");
				break waitloop;
				}else if(driver.findElement(by).isDisplayed()){
					ExReporter.log(LogStatus.PASS, "Ok Button is not Available");
					break waitloop;
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			ExReporter.log(LogStatus.PASS, "Element not Available");
		}
	}
	
	public static void WritevalueServiceRequest(String value) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			String value2 = TestData.getConfig("DataBinding");
			Object[][] writeData = {
	                {value2, value, "-", "-", "-"},
	        };
			writeDynamicValue(writeData);
		} catch (Exception e) {
			ExReporter.log(LogStatus.FAIL, "No Such Element to Print");
		}
	}
	
	
	public static void writeDynamicValue(Object[][] writeObject) throws IOException {
		String read = System.getProperty("Report_Path_Dynamic")+"/DynamicValues.xls";
		System.out.println(read);
		
		//Create an object of File class to open xlsx file
        File file =    new File(read);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook writeBook = null;
        
        writeBook = new HSSFWorkbook(inputStream);
        Sheet writeSheet = writeBook.getSheet("ExecutionValues");
        
        //Get the Row Count
        int rowNum = writeSheet.getLastRowNum()-writeSheet.getFirstRowNum();
        System.out.println(rowNum);
        
        for (Object[] aBook : writeObject) {
            Row row = writeSheet.createRow(++rowNum);
             
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(columnCount++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
         
         
        try (FileOutputStream outputStream = new FileOutputStream(read)) {
        	writeBook.write(outputStream);
        }catch(Exception e){
        	System.out.println(e);
        }
        writeBook.close();
        
	}
	
	public synchronized static void storeTransactionValues(Object[][] writeObject, String ScenarioId,String value) throws IOException {
		String read = System.getProperty("Report_Path_Dynamic")+"/DynamicValues.xls";
		System.out.println(read);
		
		//Create an object of File class to open xlsx file
        File file =    new File(read);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook writeBook = null;
        
        writeBook = new HSSFWorkbook(inputStream);
        Sheet writeSheet = writeBook.getSheet("ExecutionValues");
        
        //Get the Row Count
       int writeRow;
        int rowNum = writeSheet.getLastRowNum();
        searchRowLoop: for (writeRow=0; writeRow <rowNum; writeRow++){
        	Row row = writeSheet.getRow(writeRow);
        	Cell cell = row.getCell(0);
        	String temp = cell.getStringCellValue();
        	if(temp.equalsIgnoreCase(ScenarioId)){
        		System.out.println(writeRow);
        		break searchRowLoop;
        	}
        }
        int writeColumn;
      //Get the Column Count
            Row row = writeSheet.getRow(0);
            searchColumnLoop : for(writeColumn=0;writeColumn<10;writeColumn++){
            	Cell cell = row.getCell(writeColumn);
            	String temp = cell.getStringCellValue();
            	if(temp.equalsIgnoreCase(value)){
            		System.out.println(writeColumn);
            		break searchColumnLoop;
            	}
            }

            //Write the values
            for (Object[] aBook : writeObject) {
                Row rowValue = writeSheet.getRow(writeRow);
                 
                for (Object field : aBook) {
                    Cell cell = rowValue.getCell(writeColumn);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
                 
            }
         
        try (FileOutputStream outputStream = new FileOutputStream(read)) {
        	writeBook.write(outputStream);
        }catch(Exception e){
        	System.out.println(e);
        }
        writeBook.close();
        
	}
	
	public static void writeMasterReport(Object[][] writeObject) throws IOException {
		String read = System.getProperty("Report_Path_Dynamic")+"/DynamicValues.xls";
		
		//Create an object of File class to open xlsx file
        File file =    new File(read);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook writeBook = null;
        
        writeBook = new HSSFWorkbook(inputStream);
        Sheet writeSheet = writeBook.getSheet("MasterReport");
        
        //Get the Row Count
        int rowNum = writeSheet.getLastRowNum()-writeSheet.getFirstRowNum();
        //System.out.println(rowNum);
        
        for (Object[] aBook : writeObject) {
            Row row = writeSheet.createRow(++rowNum);
             
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(columnCount++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
         
         
        try (FileOutputStream outputStream = new FileOutputStream(read)) {
        	writeBook.write(outputStream);
        }catch(Exception e){
        	System.out.println(e);
        }
        writeBook.close();
        
	}
	
	
}
