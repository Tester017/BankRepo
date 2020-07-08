package mav.bank.framework;

import java.util.HashMap;
import java.util.Map;

public class DesktopDataCombBuilder {

	public static Map<String, String> testNGComb(String classnm, String keyword,
			String data) {
		Map<String, String> combmap = new HashMap<String, String>();
		combmap.put("Classname", classnm);
		combmap.put("Testname", keyword);
		combmap.put("Data", data);
		return combmap;
	}

}
