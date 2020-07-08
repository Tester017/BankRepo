package mav.bank.framework;

import java.util.Map;

public class TestDataProvider {

	private static ThreadLocal<Map<String, String>> testDataMap = new ThreadLocal<Map<String, String>>();

	public static void init(Map<String, String> configMap) {
		TestDataProvider.testDataMap.set(configMap);
	}

	public static String getConfig(String key) {
		return TestDataProvider.testDataMap.get().get(key);
	}

	public static void endThreadLocal() {
		testDataMap.remove();
	}
}
