package mav.bank.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import mav.bank.library.functions.CommonDef;

public class ExReporter {

	private static ExtentReports extentD;
	private static ExtentReports extentDwss;
	private static ExtentReports extentM;
	private static ExtentReports extentMwss;

	public ExtentTest testParent;
	public ExtentTest testParentWss;
	public static String reportPath;
	public static String Report_Path_Dynamic;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testwss = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<Boolean> isPassed = new ThreadLocal<Boolean>();

	public static int passCountD = 0;
	public static int failCountD = 0;
	public static int passCountM = 0;
	public static int failCountM = 0;

	
	
	public static Map<String, ExReporter> classReportMap = new HashMap<String, ExReporter>();

	public static void initReport(String suiteName) {
		String path = ProjectConfig.getPropertyValue("SSPath");
		String fileName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
		new File(path + "Report_" + fileName).mkdirs();
		reportPath = path + "Report_" + fileName;
		System.setProperty("Report_Path", reportPath);
		if (suiteName.equalsIgnoreCase("Browser")) {
			extentD = new ExtentReports(reportPath + "/Browser-Browser-Results.htm", true);
			extentDwss = new ExtentReports(reportPath + "/Browser-Browser-Results-withScreens.htm", true);
		} else {
			extentM = new ExtentReports(reportPath + "/Mobile-Browser-Results.htm", true);
			extentMwss = new ExtentReports(reportPath + "/Mobile-Browser-Results-withScreens.htm", true);
		}
	}

	public ExReporter(String testName, String description, String suiteName) {
		initParent(testName, description, suiteName);
	}

	public ExReporter(String testName, String suiteName) {
		initParent(testName, suiteName);
	}

	public static void log(LogStatus logStatus, String stepName) {
		test.get().log(logStatus, stepName);
		String path = "";
		String screenPass = ProjectConfig.getPropertyValue("screenPass");
		String screenFail = ProjectConfig.getPropertyValue("screenFail");
		String screenWarn = ProjectConfig.getPropertyValue("screenWarn");

		switch (logStatus) {
		case PASS:
			if (screenPass.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(logStatus, stepName + testwss.get().addScreenCapture(path));
			} else
				testwss.get().log(logStatus, stepName);
			break;
		case FAIL:
			if (screenFail.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(logStatus, stepName + testwss.get().addScreenCapture(path));
			} else
				testwss.get().log(logStatus, stepName);
			isPassed.set(false);
			// Assert.fail();
			break;
		case INFO:
		case SKIP:
			testwss.get().log(logStatus, stepName);
			break;
		case WARNING:
			if (screenWarn.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(logStatus, stepName + testwss.get().addScreenCapture(path));
			} else
				testwss.get().log(logStatus, stepName);
			break;
		case ERROR:
			if (screenFail.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(LogStatus.FAIL, stepName + testwss.get().addScreenCapture(path));
			} else
				testwss.get().log(LogStatus.FAIL, stepName);
			isPassed.set(false);
			Assert.fail();
			break;
		case UNKNOWN:
			isPassed.set(false);
			logStatus = LogStatus.FAIL;
			if (screenWarn.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(logStatus, stepName + testwss.get().addScreenCapture(path));
			} else {
				testwss.get().log(logStatus, stepName + " ");
			}
			Assert.fail();
			break;
		case FATAL:
			isPassed.set(false);
			testwss.get().log(logStatus, stepName);
			// System.out.println(isPassed.get());
			Assert.fail();
			break;
		default:
			break;
		}

	}

	public static void logNoScreen(LogStatus logStatus, String stepName) {
		test.get().log(logStatus, stepName);
		switch (logStatus) {
		case PASS:
			testwss.get().log(logStatus, stepName);
			break;
		case FAIL:
			testwss.get().log(logStatus, stepName);
			isPassed.set(false);
			// Assert.fail();
			break;
		case INFO:
		case SKIP:
			testwss.get().log(logStatus, stepName);
			break;
		case WARNING:
			testwss.get().log(logStatus, stepName);
			break;
		case ERROR:
			testwss.get().log(LogStatus.FAIL, stepName);
			isPassed.set(false);
			Assert.fail();
			break;
		case UNKNOWN:
			isPassed.set(false);
			logStatus = LogStatus.FAIL;
			testwss.get().log(logStatus, stepName + " ");
			Assert.fail();
			break;
		case FATAL:
			isPassed.set(false);
			testwss.get().log(logStatus, stepName);
			// System.out.println(isPassed.get());
			Assert.fail();
			break;
		default:
			break;
		}

	}

	public void initParent(String testName, String description, String suiteName) {
		if ("Browser".equalsIgnoreCase(suiteName)) {
			testParent = extentD.startTest(testName, description);
			testParentWss = extentDwss.startTest(testName, description);
		} else {
			testParent = extentM.startTest(testName, description);
			testParentWss = extentMwss.startTest(testName, description);
		}
	}

	public void initParent(String testName, String suiteName) {
		if ("Browser".equalsIgnoreCase(suiteName)) {
			testParent = extentD.startTest(testName);
			testParentWss = extentDwss.startTest(testName);
		} else {
			testParent = extentM.startTest(testName);
			testParentWss = extentMwss.startTest(testName);
		}
	}

	public static void initTest(String testName, String desc) {

		isPassed.set(true);
		test.set(getextent().startTest(testName, desc));
		testwss.set(getextentwss().startTest(testName, desc));
	}

	public static void assignCatogory(String Catogory) {
		test.get().assignCategory(Catogory);
		testwss.get().assignCategory(Catogory);
	}

	public void endParent(String suiteName) {
		if ("Browser".equalsIgnoreCase(suiteName)) {
			extentD.endTest(testParent);
			extentDwss.endTest(testParentWss);
		} else {
			extentM.endTest(testParent);
			extentMwss.endTest(testParentWss);
		}
	}

	public static void endReport(String suiteName) {
		if ("Browser".equalsIgnoreCase(suiteName)) {
			extentD.flush();
			extentDwss.flush();
			extentD.close();
			extentDwss.close();
		} else {
			extentM.flush();
			extentMwss.flush();
			extentM.close();
			extentMwss.close();

		}
	}

	private static ExtentReports getextent() {
		switch (ConfigProvider.getConfig("Platform").toUpperCase()) {
		case "BROWSER":
			return extentD;
		default:
			return extentM;
		}
	}

	private static ExtentReports getextentwss() {
		switch (ConfigProvider.getConfig("Platform").toUpperCase()) {
		case "BROWSER":
			return extentDwss;
		default:
			return extentMwss;
		}
	}

	public void appendParent() {
		testParent.appendChild(test.get());
		testParentWss.appendChild(testwss.get());
		// System.out.println(isPassed.get());
		if (ConfigProvider.getConfig("Platform").equalsIgnoreCase("Browser")) {
			if (isPassed.get()) {
				passCountD = passCountD + 1;
			} else {
				failCountD = failCountD + 1;
			}
		} else {
			if (isPassed.get()) {
				passCountM = passCountM + 1;
			} else {
				failCountM = failCountM + 1;
			}
		}
		test.remove();
		testwss.remove();
		isPassed.remove();

	}

	public static void createDynamicValuesExcel() throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("ExecutionValues");
      //HSSFSheet sheet1 = workbook.createSheet("MasterReport");
		String path = ProjectConfig.getPropertyValue("DynamicValuesPath");
		String fileName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
		new File(path + "Report_" + fileName).mkdirs();
		Report_Path_Dynamic = path + "Report_" + fileName;
		String dest = Report_Path_Dynamic+"/DynamicValues.xls";
		System.setProperty("Report_Path_Dynamic", Report_Path_Dynamic);
		
		Object[][] bookData = {
                {"Test Case ID","Device Name", "Transaction Reference", "Core Banking Reference", "Transaction Charges", "Transaction Exchange Rate", "Test Case Status","Transaction Status","Time Stamp"},
        };
 
		
        int rowCount = 0;
         
        for (Object[] aBook : bookData) {
        	CellStyle style = workbook.createCellStyle();
        	Font font = workbook.createFont();
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            style.setFont(font);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            Row row = sheet.createRow(rowCount++);
            
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellStyle(style);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
        
    /*    for (Object[] aBook : report) {
            Row row = sheet1.createRow(0);
             
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(columnCount++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }*/
         
         
        try (FileOutputStream outputStream = new FileOutputStream(dest)) {
            workbook.write(outputStream);
        }
        workbook.close();
	}
	
	
	public synchronized static void createTransactionRow() throws IOException {
		String read = System.getProperty("Report_Path_Dynamic")+"/DynamicValues.xls";
		System.out.println(read);
		
		//Create an object of File class to open xlsx file
        File file =    new File(read);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook writeBook = null;
        
        writeBook = new HSSFWorkbook(inputStream);
        Sheet writeSheet = writeBook.getSheet("ExecutionValues");
        String dataBinding = TestData.getConfig("DataBinding");
        String devicename = ConfigProvider.getConfig("Device");
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        Object[][] bookData = {
                {dataBinding, devicename,"-", "-", "-", "-","-", "Fail/After Cut off Time",time},
        };
 
        int rowNum = writeSheet.getLastRowNum();
         
        for (Object[] aBook : bookData) {
            
            CellStyle style = writeBook.createCellStyle();
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            Row row = writeSheet.createRow(++rowNum);
            int columnCount = 0;
             
            for (Object field : aBook) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellStyle(style);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
             
        }
        for(int i=0; i <9 ; i++)
        writeSheet.autoSizeColumn(i);

        try (FileOutputStream outputStream = new FileOutputStream(read)) {
        	writeBook.write(outputStream);
        }catch(Exception e){
        	System.out.println(e);
        }
        writeBook.close();
	}
	
/*	
	public static void masterReport()throws MalformedURLException, IOException {

		String masterPath = System.getProperty("Report_Path").replace(".", "/Users/mac/Desktop/Framework/RAK Bank");
		Source source = new Source(new URL("file://"+masterPath+"/Mobile-Browser-Results.htm"));
		source.fullSequentialParse();
		
		//For Getting Error Results
		List<Element> errorList = source.getAllElementsByClass("displayed error node-1x");
		for (Element element : errorList) {
			//System.out.println(errorList.size());
			if (element.getAttributes() != null) {
				List<Element> result = element.getAllElementsByClass("test-status label outline capitalize error");
				List<Element> scn_id = element.getAllElementsByClass("test-node-name");
				
				String Scn = ((Element) scn_id.get(0)).getContent().toString();
				String Scenario[]=Scn.split("-");

				String report = ((Element) result.get(0)).getContent().toString();
				Object[][] writeData = {
						{Scenario[1],report},
				};
				CommonDef.writeMasterReport(writeData);
			}
		}
		
		//For Getting Pass List
		List<Element> passlist = source.getAllElementsByClass("displayed pass node-1x");
		for (Element element : passlist) {
			if (element.getAttributes() != null) {
				List<Element> result = element.getAllElementsByClass("test-status label outline capitalize pass");
				List<Element> scn_id = element.getAllElementsByClass("test-node-name");
				String Scn = ((Element) scn_id.get(0)).getContent().toString();
				String Scenario[]=Scn.split("-");

				String report = ((Element) result.get(0)).getContent().toString();
				Object[][] writeData = {
						{Scenario[1],report},
				};
				CommonDef.writeMasterReport(writeData);
			}
		}
		
		//For Getting Fail List
		List<Element> faillist = source.getAllElementsByClass("displayed fail node-1x");
		for (Element element : faillist) {
			if (element.getAttributes() != null) {
				List<Element> result = element.getAllElementsByClass("test-status label outline capitalize fail");
				List<Element> scn_id = element.getAllElementsByClass("test-node-name");
				String Scn = ((Element) scn_id.get(0)).getContent().toString();
				String Scenario[]=Scn.split("-");

				String report = ((Element) result.get(0)).getContent().toString();
				Object[][] writeData = {
						{Scenario[1],report},
				};
				CommonDef.writeMasterReport(writeData);
			}
		}

		
	}
		
	*/
}
