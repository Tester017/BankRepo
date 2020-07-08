package mav.bank.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;

public class ProjectConfig {

	public static Properties prop = new Properties();
	public static String filepath;
	static {
		try {
			filepath = System.getProperty("ResSuite");
			System.out.println(filepath);
			System.out.println("Aftr filepath");
			filepath="./Bank-TestSetup";
			if (filepath == null) {
				filepath = "projectconfig.properties";
				try{
					InputStream resourceStream = Thread.currentThread().getContextClassLoader()
							.getResourceAsStream(filepath);
					prop.load(resourceStream);
				} catch (Exception E) {
					Assert.fail(E.getMessage());
				}
			} else {
				System.out.println(filepath);
				filepath = filepath + File.separator + "projectconfig.properties";
				prop.load(new FileInputStream(filepath));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}

}
