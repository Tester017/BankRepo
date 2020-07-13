package mav.bank.alltestpack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import mav.bank.driver.DriverFactory;
import mav.bank.framework.ConfigProvider;
import mav.bank.framework.ExReporter;
import mav.bank.framework.ExcelReader;
import mav.bank.framework.SyncMap;
import mav.bank.framework.TestData;
import mav.bank.listeners.TestJiraListener;

/**	
* Class Name			:	RAKTestNGMethods
* Use					:	Class for all the Testng Methods that are to be executed before and after the execution of suite, Class and Methods
* Designed By			:	surya kumar
* Date Last Modified	:	20-Aug-2016
*/

@Listeners(TestJiraListener.class)
public class BankTestNGMethods {

	public ExReporter classReport;
	public final static Logger logger = Logger.getLogger(System.getProperty("ResSuite"));

	/**	
	* Method Name			:	beforeSuite 
	* Use					:	Function for execution of certain methods and initialization of Extent reports before the execution of a suite- 
	* Return Type			:	Has no Return Type and Input is from Test Data Sheets
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*/
	
	@BeforeSuite(alwaysRun = true)
	public static void beforeSuite(ITestContext ctx) throws FileNotFoundException, IOException {
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		logger.info("TEST PACK EXECUTION STARTED for TESTNG Suite... :" + suiteName);
		System.out.println(suiteName);
		ExReporter.initReport(suiteName);
		ExReporter.createDynamicValuesExcel();
	}

	/**	
	* Method Name			:	afterSuite 
	* Use					:	Function for execution of certain methods and closure of Extent reports after the execution of a suite
	* Return Type			:	Has no Return Type and Input is from Test Data Sheets
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*/
	@AfterSuite(alwaysRun = true)
	public static void afterSuite(ITestContext ctx) throws MalformedURLException, IOException {

		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		logger.info("TEST PACK EXECUTION ENDED for TESTNG Suite... :" + suiteName);
		ExReporter.endReport(suiteName);

	}

	/**	
	* Method Name			:	beforeClass 
	* Use					:	Function for execution of methods Extent Reports Initialization before the execution of a class
	* Return Type			:	Has no Return Type and Input is from Test Data Sheets
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*/
	@BeforeClass(alwaysRun = true)
	public void beforeClass(ITestContext ctx) {
		String className = this.getClass().getSimpleName();
		
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
	

		String testName = ctx.getCurrentXmlTest().getName();
	
		logger.info("Starting TestNG Class  for SUITE..." + suiteName + "... :" + className);
		if (suiteName.equalsIgnoreCase("browser"))
			classReport = new ExReporter(testName, "-", suiteName);
		
		else
			classReport = new ExReporter(className + "-" + testName, testName, suiteName);
		
	}

	/**	
	* Method Name			:	afterClass 
	* Use					:	Function for execution of methods and Extent Reports Closure after the execution of a class 
	* Return Type			:	Has no Return Type and Input is from Test Data Sheets
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*/
	@AfterClass(alwaysRun = true)
	public void afterClass(ITestContext ctx) {
		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		String className = this.getClass().getSimpleName();
		logger.info("Ended TestNG Class  for SUITE..." + suiteName + "... :" + className);
		classReport.endParent(suiteName);
	}

	/**	
	* Method Name			:	beforeMethod 
	* Use					:	Function for execution of methods and Extent Reports and Test Data Initialization before the execution of a Method 
	* Return Type			:	Has no Return Type and Input is from Test Data Sheets
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*/
	@SuppressWarnings("unchecked")
	@BeforeMethod(alwaysRun = true)
	public static void beforeMethod(Object[] testArgs, ITestContext ctx, Method method) throws IOException {

		String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		ConfigProvider.init((Map<String, String>) testArgs[0]);
		TestData.init((Map<String, String>) testArgs[1]);
		logger.info("Executing SUITE ::: " + suiteName + " ||| METHOD ::: " + method.getName() + " @ "
				+ ConfigProvider.getConfig("Data") + " ||| on Browser ::: " + ConfigProvider.getConfig("Browser") + "-"
				+ ConfigProvider.getConfig("Version"));
		ExReporter.createTransactionRow();
	}

	/**	
	* Method Name			:	afterMethod 
	* Use					:	Function for execution of methods and Extent Reports and Test Data closure after the execution of a Method 
	* Return Type			:	Has no Return Type and Input is from Test Data Sheets
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	*/
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context, Method method, ITestResult result) {
		String suiteName = context.getCurrentXmlTest().getSuite().getName();
		String status = getResultString(result);
		logger.info("RESULT SUITE ::: " + suiteName + " ||| METHOD ::: " + method.getName() + " @ "
				+ ConfigProvider.getConfig("Data") + " ||| on Browser ::: " + ConfigProvider.getConfig("Browser") + "-"
				+ ConfigProvider.getConfig("Version") + " ||| RESULT ::: " + status);
		classReport.appendParent();
		DriverFactory.closeDriver();
		ConfigProvider.endThreadLocal();
		TestData.endThreadLocal();
	}

	/**	
	* Method Name			:	getResultString 
	* Use					:	To store the Value of the Test Execution result and returns the value to the called function
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	* @return executionStatus the String value to be returned to the called function and to store the execution result
	* @param The result value passed from the called function based on which the return value is set
	*/
	private String getResultString(ITestResult result) {
		String executionStatus = null;
		int execStatus = result.getStatus();
		if (execStatus == ITestResult.SUCCESS) {
			executionStatus = "PASS";
		} else if (execStatus == ITestResult.FAILURE) {
			executionStatus = "FAIL";
		} else if (execStatus == ITestResult.SKIP) {
			executionStatus = "SKIP";
		}
		return executionStatus;
	}

	private void releaseUser(String user) {
		try {
			user = TestData.getConfig(user);
			// SyncMap.availableStat(user);
			if (!user.equalsIgnoreCase("n/a"))
				SyncMap.setAvailable(user);
			// SyncMap.availableStat(user);
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("unchecked")
	@Parameters({ "TEST" })
	@DataProvider(name = "TestDataParallel", parallel = true)
	public static Object[][] GlobalTestData(Method method, ITestContext context, ITestNGMethod met2) {
		return testDataComputer(method, context, met2);
	}

	@DataProvider(name = "TestDataSerial", parallel = false)
	public static Object[][] GlobalTestDataNoParallel(Method method, ITestContext context, ITestNGMethod met2) {
		return testDataComputer(method, context, met2);
	}

	/**	
	* Method Name			:	testDataComputer 
	* Use					:	To create an object of 2-D array that is used as test data inputter for browser, mobile or browserstack
	* Designed By			:	surya kumar
	* Date Last Modified	:	20-Aug-2017
	* @return Object the 2-D String object to be returned to the called function and to store the test data used for execution in array
	* @param Method, ITestContext and ITestNGMethod
	*/
	@SuppressWarnings("unchecked")
	private static Object[][] testDataComputer(Method method, ITestContext context, ITestNGMethod met2) {
		
		System.out.println("**********************************************");

		System.out.println("Daniel from testDataComputer");
		System.out.println("**********************************************");
		String className = method.getDeclaringClass().getSimpleName();
		String methodName = method.getName();

		String suiteName = context.getCurrentXmlTest().getSuite().getName();
		System.out.println("DATA PRO SUITE NAME : " + suiteName);
		String testXMLName = "";
		if (suiteName.equals("Browser"))
			testXMLName = (context.getCurrentXmlTest().getName()).split("-")[1];
		else if (suiteName.equals("BrowserStack"))
			testXMLName = (context.getCurrentXmlTest().getName()).split("-")[1];
		else if (suiteName.equals("mBrowser"))
			testXMLName = context.getCurrentXmlTest().getName();

		ArrayList<Map<String, String>> browData = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> dataComb = new ArrayList<Map<String, String>>();

		ArrayList<String> dataArr = new ArrayList<String>();

		System.out.println("DATA PRO DATA NAME: " + testXMLName);
		JSONArray browcomb = ExcelReader.getBrowCombination(suiteName, className, methodName, testXMLName);
		if (browcomb != null) {
			for (int i = 0; i < browcomb.size(); i++) {
				if (suiteName.equals("mBrowser")) {
					if (i == 0)
						browData.add((Map<String, String>) browcomb.get(i));
					Map<String, String> test = new HashMap<String, String>();
					test = (Map<String, String>) browcomb.get(i);
					dataArr.add(test.get("Data"));
				} else if (suiteName.equals("Browser")) {
					browData.add((Map<String, String>) browcomb.get(i));
				} else if (suiteName.equals("BrowserStack")) {
					browData.add((Map<String, String>) browcomb.get(i));
				}
			}
		}
		System.out.println("DATA PROV TEST DATA: " + browData);
		if (suiteName.equals("Browser"))
			dataComb = ExcelReader.getTestDataCombination(methodName, testXMLName);
		else if (suiteName.equals("BrowserStack"))
			dataComb = ExcelReader.getTestDataCombination(methodName, testXMLName);
		else if (suiteName.equals("mBrowser"))
			dataComb = ExcelReader.getTestDataCombination(methodName, dataArr);
		System.out.println("DATACOMB" + dataComb);
		int i = 0;
		int count = browData.size() * dataComb.size();

		Object[][] test = new Object[count][2];

		for (Map<String, String> elem : browData) {
			for (Map<String, String> entryD : dataComb) {
				test[i][0] = elem;
				test[i][1] = entryD;
				i++;
			}
		}

		return test;
	}

}
