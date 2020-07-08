package mav.bank.framework;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CusJSONParser {

	public static void JSONParser(FileReader fr) {
		JSONTokener tokener = new JSONTokener(fr);
		JSONObject root = new JSONObject(tokener);
		System.out.println("JSON org :" + root.getJSONObject("mBrowser"));

		for (Object key : root.keySet()) {
			System.out.println("Classification KEY: " + key);
			JSONObject classNm = (JSONObject) root.get((String) key);

			for (String ckey : classNm.keySet()) {
				System.out.println("Class KEY: " + ckey);
				// XMLCreator.writeMobClass(test, ckey);
				JSONObject devNm = (JSONObject) classNm.get(ckey);

				// System.out.println("DEVICE NAME: " + devNm);
				for (String dkey : devNm.keySet()) {
					System.out.println("DEVICES KEY: " + dkey);
					Map<Integer, String> methodMap = new HashMap<Integer, String>();
					JSONObject device = (JSONObject) devNm.get(dkey);
					List<String> mNames = new ArrayList<String>();
					for (String mkey : device.keySet()) {
						System.out.println("Methods KEY: " + mkey);
						JSONArray method = (JSONArray) device.get(mkey);
						for (int i = 0; i < method.length(); i++) {
							System.out.println("ARR: " + method.get(i));
							JSONObject obj = method.getJSONObject(i);
							System.out.println(obj.getString("Data"));
							methodMap.put(i, i + "~" + mkey + "~" + obj.getString("Data"));
							mNames.add(mkey);
							// XMLCreator.writeTestTagNew(dkey, ckey, mkey,
							// obj.getString("Data"));
						}
						//XMLCreator.writeTestTag(dkey, ckey, mNames);
					}
					XMLCreator.writeTestTag(dkey, ckey, mNames);
					
				}
			}
		}

	}
}
