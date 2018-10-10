package OptionManager;

import java.util.Properties;

public class AbstractOptions {
	public static volatile Properties properties;
	
	public static void setupProperties() {
		if(properties == null)
			properties = new Properties();
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
