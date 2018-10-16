package Engine.OptionManager;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class AbstractOptions {
	public static volatile Properties properties;
	
	public static void setupProperties() {
		if(properties == null)
			properties = new Properties();
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public String toString() {
		Map<String, String> treeMap = new TreeMap(properties);
		return treeMap.toString();
	}

	public void setProperty(String optionKey, String value) {
		properties.put(optionKey, value);
		
	}

	public Properties getProperties() {
		return properties;
	}
}
