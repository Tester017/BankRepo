package mav.bank.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class ExcelReader {

	private XMLCreator xmlCreator;
	@SuppressWarnings("unused")
	private FileInputStream fileInputStream;
	private static Map<String, Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>> testsComb = new HashMap<String, Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>>();
	private static Map<String, Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>> testsCombMob = new HashMap<String, Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>>();
	private static Map<String, Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>> testsCombBs = new HashMap<String, Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>>();
	
	private static JSONObject arrayDataComb = new JSONObject();
	private static JSONObject arrayDataCombMob = new JSONObject();
	private static JSONObject arrayDataCombBs = new JSONObject();
	
	public ExcelReader(XMLCreator xmlCreator) {
		this.xmlCreator = xmlCreator;
	}

	public ExcelReader(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public void readData() {
		Workbook file;
		String label;
		List<String> labelnames = new ArrayList<String>();
		Map<String, Integer> indextemplate = new HashMap<String, Integer>();
		Map<Integer, String> platform = new HashMap<Integer, String>();
		Map<Integer, String> os = new HashMap<Integer, String>();
		Map<Integer, String> browname = new HashMap<Integer, String>();
		Map<Integer, String> version = new HashMap<Integer, String>();

		try {
			System.out.println("Reading excel");
			System.out.println(System.getProperty("ResSuite")+" Daniel test");
			file = WorkbookFactory.create(new FileInputStream(System.getProperty("ResSuite") + "/ScenarioSheet.xls"));
			Sheet sheet = file.getSheet("ScenariosPicker");
			int browCount = 0, mBrowCount = 0;
			for (Row row : sheet) {
				Cell firstcell = row.getCell(0);
				if (firstcell == null) {
					continue;
				}
				label = firstcell.getStringCellValue();

				switch (label) {
				case "TEST-Indicator":
					List<String> template = Arrays.asList("TEST-Indicator", "ClassName", "Run", "ScenarioID",
							"TestCaseID", "DataBinding", "Keyword", "ScreenName");
					try {
					for (Cell cell : row) {
						int column_index = cell.getColumnIndex();
						String column_name = cell.getStringCellValue();
						labelnames.add(column_name);
						// System.out.println("Column name: " + column_name);
						if (!template.contains(column_name)) {
							String[] currvalue = column_name.split("_");
							platform.put(column_index, currvalue[0]);
							os.put(column_index, currvalue[1]);
							browname.put(column_index, currvalue[2]);
							version.put(column_index, currvalue[3]);
						} else {
							indextemplate.put(column_name, column_index);
						}
					}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

					break;
				case "TEST":
					System.out.println("Daniel from TEST");
					try {
					int runindex = indextemplate.get("Run");
					String classnm = row.getCell(indextemplate.get("ClassName")).getStringCellValue();
					String keyword = row.getCell(indextemplate.get("Keyword")).getStringCellValue();
					String data = row.getCell(indextemplate.get("DataBinding")).getStringCellValue();
					String brows="";
					System.out.println(classnm);
					System.out.println(keyword);
					System.out.println(data);
					System.out.println(brows);

					if (row.getCell(runindex).getStringCellValue().equalsIgnoreCase("YES")) {
						for (Integer t : platform.keySet()) {

							if (row.getCell(t) == null)
								continue;	
							

							if (row.getCell(t).getStringCellValue().equalsIgnoreCase("YES")
									&& platform.get(t).equals("Browser")) {
								brows="Browser";
								browCount++;
								datacomb(classnm, keyword, data, platform.get(t), os.get(t), browname.get(t),
										version.get(t));
							} else if (row.getCell(t).getStringCellValue().equalsIgnoreCase("YES")
									&& platform.get(t).equals("BrowserStack")) {
								brows="BrowserStack";
								browCount++;
								datacombbs(classnm, keyword, data, platform.get(t), os.get(t), browname.get(t),
										version.get(t));
							}else if (!row.getCell(t).getStringCellValue().equalsIgnoreCase("NO")
									&& platform.get(t).equals("mBrowser")
									&& row.getCell(t).getStringCellValue().length() > 0) {
								mBrowCount++;
								for (String mobSplit : row.getCell(t).getStringCellValue().split(",")) {
									datacombmob(classnm, keyword, data, platform.get(t), os.get(t), browname.get(t),
											version.get(t), mobSplit);
								}

							}
						}
						if (browCount > 0) {
							browCount = 0;
							if (brows.equals("BrowserStack")) {
								xmlCreator.writeTestNGBS(DesktopDataCombBuilder.testNGComb(classnm, keyword, data));
							}else {
								xmlCreator.writeTestNG(DesktopDataCombBuilder.testNGComb(classnm, keyword, data));
							}
							//xmlCreator.writeTestNG(DesktopDataCombBuilder.testNGComb(classnm, keyword, data));
							
						}						
						// xmlCreator.writeTestNG(MobBrowDataCombBuilder.testNGMobComb(classnm,
						// keyword, data));
					}
					} catch (Exception e) {
						System.out.println(e.getLocalizedMessage());
					}
					break;
				}
			}
			arrayDataComb = new JSONObject(testsComb);
			String filepath = System.getProperty("ResSuite") + "/" + "combination.json";
			try (FileWriter json = new FileWriter(filepath)) {
				json.write(arrayDataComb.toString());
				json.flush();
				json.close();
			}
			arrayDataCombMob = new JSONObject(testsCombMob);
			String filepathMob = System.getProperty("ResSuite") + "/" + "combinationMob.json";
			try (FileWriter json = new FileWriter(filepathMob)) {
				json.write(arrayDataCombMob.toString());
				json.flush();
				json.close();
			}
			arrayDataCombBs = new JSONObject(testsCombBs);
			String filepathBs = System.getProperty("ResSuite") + "/" + "combinationBS.json";
			try (FileWriter json = new FileWriter(filepathBs)) {
				json.write(arrayDataCombBs.toString());
				json.flush();
				json.close();
			}

			if (mBrowCount > 0)
				getBrowCombination(filepathMob);
			// xmlCreator.writeTestNG(arrayDataCombMob);

		} catch (Exception E) {
			Assert.fail("Error reading data from the Input File" + E.getMessage());
		}

	}

	private void getBrowCombination(String filepathMob) {
		try {

			File file = new File(filepathMob);
			FileReader fr = new FileReader(file);
			CusJSONParser.JSONParser(fr);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void datacombmob(String classnm, String keyword, String data, String plat, String os, String brow,
			String version, String mobSplit) {
		Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>> testmap = new HashMap<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>();
		Map<String, Map<String, ArrayList<Map<String, String>>>> devicemap = new HashMap<String, Map<String, ArrayList<Map<String, String>>>>();
		Map<String, ArrayList<Map<String, String>>> datamap = new HashMap<String, ArrayList<Map<String, String>>>();
		ArrayList<Map<String, String>> comblist = new ArrayList<Map<String, String>>();

		testmap = testsCombMob.computeIfAbsent(plat,
				clsNm -> new HashMap<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>());
		devicemap = testmap.computeIfAbsent(classnm,
				mtdNm -> new HashMap<String, Map<String, ArrayList<Map<String, String>>>>());
		datamap = devicemap.computeIfAbsent(mobSplit, dtname -> new HashMap<String, ArrayList<Map<String, String>>>());
		comblist = datamap.computeIfAbsent(keyword, plt -> new ArrayList<Map<String, String>>());

		Map<String, String> combmap = new HashMap<String, String>();

		combmap.put("Classname", classnm);
		combmap.put("Testname", keyword);
		combmap.put("Data", data);
		combmap.put("Platform", plat);
		combmap.put("OS", os);
		combmap.put("Browser", brow);
		combmap.put("Version", version);
		combmap.put("Device", mobSplit);
		comblist.add(combmap);
	}
	
	private void datacombbs(String classnm, String keyword, String data, String plat, String os, String brow,
			String version) {

		Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>> testmap = new HashMap<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>();
		Map<String, Map<String, ArrayList<Map<String, String>>>> devicemap = new HashMap<String, Map<String, ArrayList<Map<String, String>>>>();
		Map<String, ArrayList<Map<String, String>>> datamap = new HashMap<String, ArrayList<Map<String, String>>>();
		ArrayList<Map<String, String>> comblist = new ArrayList<Map<String, String>>();

		testmap = testsCombBs.computeIfAbsent(plat,
				clsNm -> new HashMap<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>());
		devicemap = testmap.computeIfAbsent(classnm,
				mtdNm -> new HashMap<String, Map<String, ArrayList<Map<String, String>>>>());
		datamap = devicemap.computeIfAbsent(data, dtname -> new HashMap<String, ArrayList<Map<String, String>>>());
		comblist = datamap.computeIfAbsent(keyword, plt -> new ArrayList<Map<String, String>>());

		Map<String, String> combmap = new HashMap<String, String>();

		combmap.put("Classname", classnm);
		combmap.put("Testname", keyword);
		combmap.put("Data", data);
		combmap.put("Platform", plat);
		combmap.put("OS", os);
		combmap.put("Browser", brow);
		combmap.put("Version", version);
		//combmap.put("Device", mobSplit);
		comblist.add(combmap);
	}

	private void datacomb(String classnm, String keyword, String data, String plat, String os, String brow,
			String version) {

		Map<String, Map<String, Map<String, ArrayList<Map<String, String>>>>> testmap = new HashMap<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>();
		Map<String, Map<String, ArrayList<Map<String, String>>>> devicemap = new HashMap<String, Map<String, ArrayList<Map<String, String>>>>();
		Map<String, ArrayList<Map<String, String>>> datamap = new HashMap<String, ArrayList<Map<String, String>>>();
		ArrayList<Map<String, String>> comblist = new ArrayList<Map<String, String>>();

		testmap = testsComb.computeIfAbsent(plat,
				clsNm -> new HashMap<String, Map<String, Map<String, ArrayList<Map<String, String>>>>>());
		devicemap = testmap.computeIfAbsent(classnm,
				mtdNm -> new HashMap<String, Map<String, ArrayList<Map<String, String>>>>());
		datamap = devicemap.computeIfAbsent(data, dtname -> new HashMap<String, ArrayList<Map<String, String>>>());
		comblist = datamap.computeIfAbsent(keyword, plt -> new ArrayList<Map<String, String>>());

		Map<String, String> combmap = new HashMap<String, String>();

		combmap.put("Classname", classnm);
		combmap.put("Testname", keyword);
		combmap.put("Data", data);
		combmap.put("Platform", plat);
		combmap.put("OS", os);
		combmap.put("Browser", brow);
		combmap.put("Version", version);
		comblist.add(combmap);
	}

	public static JSONArray getBrowCombination(String suiteName, String className, String methodName,
			String testXMLName) {
		try {
			File file;
			if (suiteName.equals("Browser"))
				file = new File(System.getProperty("ResSuite") + "/combination.json");
			else if (suiteName.equals("BrowserStack"))
				file = new File(System.getProperty("ResSuite") + "/combinationBS.json");			
			else
				file = new File(System.getProperty("ResSuite") + "/combinationMob.json");
			FileReader fr = new FileReader(file);
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(fr);
			JSONArray method;
			//System.out.println(suiteName + className + testXMLName +methodName);
			if (suiteName.equals("Browser"))
				method = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get(suiteName))
						.get(className)).get(testXMLName)).get(methodName);
			else if (suiteName.equals("BrowserStack"))
				method = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get(suiteName))
						.get(className)).get(testXMLName)).get(methodName);
			else
				method = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get(suiteName))
						.get(className)).get(testXMLName)).get(methodName);
			// System.out.println("METHOD EXCELREADER : " + method);
			return method;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Map<String, String>> getTestDataCombination(String methodName, String testXMLName) {
		try {
			FileInputStream file = new FileInputStream(System.getProperty("ResData"));
			ExcelReader read = new ExcelReader(file);
			Workbook wb = WorkbookFactory.create(new FileInputStream(System.getProperty("ResData")));// +
																										// "/ScenarioSheet.xlsx"));
			Sheet sheet = wb.getSheet(methodName);
			Map<Integer, String> data = read.getDataColIndex(sheet, methodName);
			ArrayList<Map<Integer, String>> comb = read.getDataCombination(sheet, testXMLName);
			System.out.println("getting the data value ");
			ArrayList<Map<String, String>> dataComb = new ArrayList<Map<String, String>>();
			for (Map<Integer, String> tec : comb) {
				Map<String, String> matrix = new HashMap<String, String>();
				for (int i : tec.keySet()) {
					// System.out.println(data.get(i) + "-" + tec.get(i));
					matrix.put(data.get(i), tec.get(i));
				}
				dataComb.add(matrix);
			}
			return dataComb;
		} catch (Exception e) {
			ExReporter.log(LogStatus.FATAL, "DATA COMBINATION computation failure for test: " + testXMLName);
		}
		return null;
	}

	public static ArrayList<Map<String, String>> getTestDataCombination(String methodName, ArrayList<String> dataArr) {
		try {
			FileInputStream file = new FileInputStream(System.getProperty("ResData"));// +
																						// "/ScenarioSheet.xlsx");
			ExcelReader read = new ExcelReader(file);
			Workbook wb = WorkbookFactory.create(new FileInputStream(System.getProperty("ResData")));// +
																										// "/ScenarioSheet.xlsx"));
			Sheet sheet = wb.getSheet(methodName);
			Map<Integer, String> data = read.getDataColIndex(sheet, methodName);
			ArrayList<Map<String, String>> dataComb = new ArrayList<Map<String, String>>();
			for (String testXMLName : dataArr) {
				ArrayList<Map<Integer, String>> comb = read.getDataCombination(sheet, testXMLName);
				// System.out.println("DATA" + data);
				// System.out.println("COMB" + comb);
				Map<String, String> matrix = new HashMap<String, String>();
				for (Map<Integer, String> tec : comb) {
					for (int i : tec.keySet()) {
						// System.out.println(data.get(i) + "-" + tec.get(i));
						matrix.put(data.get(i), tec.get(i));
					}
				}
				dataComb.add(matrix);
			}
			return dataComb;
		} catch (Exception e) {
			// ExReporter.log(LogStatus.FATAL, "DATA COMBINATION computation
			// failure for test: " + testXMLName);
			e.printStackTrace();
		}
		return null;
	}

	private ArrayList<Map<Integer, String>> getDataCombination(Sheet sheet, String testXMLName) {

		ArrayList<Map<Integer, String>> comb = new ArrayList<Map<Integer, String>>();

		for (Row row : sheet) {
			if (row.getRowNum() == 0) {
				continue;
			}

			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(testXMLName)) {
				Map<Integer, String> curRow;
				curRow = getCurrentRow(row, testXMLName);
				comb.add(curRow);
			}
		}
		// System.out.println("INSIDE COMB :" + comb);
		return comb;
	}

	private Map<Integer, String> getCurrentRow(Row row, String testXMLName) {
		Map<Integer, String> data = new HashMap<Integer, String>();
		try {
			for (Cell cell : row) {

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					data.put(cell.getColumnIndex(), cell.getStringCellValue());
					break;
				/*
				 * case Cell.CELL_TYPE_FORMULA: data.put(cell.getColumnIndex(),
				 * cell.getCellFormula()); break;
				 */
				default:
					data.put(cell.getColumnIndex(), cell.getStringCellValue());
					break;
				}
			}
			return data;
		} catch (Exception e) {
			data.put(99, "test");
			return data;
		}
	}

	private Map<Integer, String> getDataColIndex(Sheet sheet, String methodName) {
		try {
			Map<Integer, String> data = new HashMap<Integer, String>();
			System.out.println("getDataCollection index::sheet"+sheet +" Method name"+methodName);
			boolean indexrow = true;

			for (Row row : sheet) {
				if (indexrow) {
					for (Cell cell : row) {
						data.put(cell.getColumnIndex(), cell.getStringCellValue());
					}
					return data;
				}
			}
			return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
