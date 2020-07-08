package mav.bank.framework;

import java.util.HashMap;
import java.util.Map;

public class MobBrowDataCombBuilder {

	public static Map<String, String> testNGMobComb(String classnm, String keyword, String data, String device) {
		Map<String, String> combmap = new HashMap<String, String>();
		combmap.put("Classname", classnm);
		combmap.put("Testname", keyword);
		combmap.put("Data", data);
		combmap.put("Device", device);
		return combmap;
	}
}
