package mav.bank.framework;

import java.util.Map;

public class TestData {

	private static ThreadLocal<Map<String, String>> testDataMap = new ThreadLocal<Map<String, String>>();

	public static void init(Map<String, String> configMap) {
		TestData.testDataMap.set(configMap);
	}

	public static String getConfig(String key) {
		return TestData.testDataMap.get().get(key);
	}

	public static void endThreadLocal() {
		testDataMap.remove();
	}
}
