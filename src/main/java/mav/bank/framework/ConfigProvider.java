package mav.bank.framework;

import java.util.Map;

public class ConfigProvider {
	private static ThreadLocal<Map<String, String>> configMap = new ThreadLocal<Map<String, String>>();

	public static void init(Map<String, String> configMap) {
		ConfigProvider.configMap.set(configMap);
	}

	public static String getConfig(String key) {
		return ConfigProvider.configMap.get().get(key);
	}

	public static void endThreadLocal() {
		configMap.remove();
	}
}