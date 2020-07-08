package mav.bank.framework;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SyncMap {

	public static Map<String, String> loginObj = Collections
			.synchronizedMap(new HashMap<String, String>());

	public static void init(Map<String, String> configMap) {
		loginObj = configMap;
	}
	
	public static Map<String, String> currvalues() {
		return loginObj;
	}

	public static void setAvailable(String key) {
		loginObj.put(key, "yes-" + key);
	}
	
	public static void availableStat(String key) {
		System.out.println(loginObj.get(key));//loginObj.put(key, "yes-" + key);
	}

	public static void setAvailable(String key, Boolean split) {
		if (split)
			key = key.split("-")[1];
		loginObj.put(key, "yes-" + key);
	}

	public static void setUnAvailable(String key) {
		// checkAvailableSync(key);
		key = key.split("-")[1];
		loginObj.put(key, "no-" + key);
	}

	public static synchronized String getAvailable() {
		// System.out.println("OBJECT " + loginObj);
		for (String i : loginObj.keySet()) {
			System.out.println("Indiv " + loginObj.get(i));
			if (loginObj.get(i).contains("yes")) {
				SyncMap.setUnAvailable(loginObj.get(i));// .split("-")[1]);
				//System.out.println("OBJECT " + loginObj);
				return loginObj.get(i);
			}
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getAvailable();
	}

	public static String checkAvailable(String user) {
		//System.out.println("Inside Availability");
		if (loginObj.get(user).contains("yes")) {
			//System.out.println("Available");
			SyncMap.setUnAvailable(loginObj.get(user));
			//System.out.println("OBJECT " + loginObj);
			return loginObj.get(user);
		} else {
			//System.out.println("Not Available");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			checkAvailable(user);
		}
		return loginObj.get(user);
	}

	public static synchronized String checkAvailableSync(String user) {
		//System.out.println("Inside Availability");
		if (loginObj.get(user).contains("yes")) {
			//System.out.println("Available");
			SyncMap.setUnAvailable(loginObj.get(user));
			//System.out.println("OBJECT " + loginObj);
			return loginObj.get(user);
		} else {
			//System.out.println("Not Available");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			checkAvailable(user);
		}
		return loginObj.get(user);
	}

	public static boolean checkAvailableNew(String user) {
		boolean availability = false;
		if (loginObj.get(user).contains("yes")) {
			availability = synchSetUnavailable(user);
			return availability;
		} else
			return false;
	}

	private static synchronized boolean synchSetUnavailable(String key) {
		if (loginObj.get(key).contains("yes")) {
			//System.out.println("KEY" + key);
			loginObj.put(key, "no-" + loginObj.get(key).split("-")[1]);
			return true;
		} else
			return false;
	}

}
