package Engine.Data.OptionManager;

import java.util.ArrayList;

public class CurrentLanguage extends AbstractOptions{
	//keys
	public static final String TITLE_KEY = "window_title";

	private static ArrayList<String> keyList;
	
	private static LanguageReader loader;
	
	public static void loadFromFile(String filename) {
		loader = new LanguageReader(filename);
		
		keyList = new ArrayList<String>();
		//load properties
		addProperty(TITLE_KEY);
	}
	
	public static boolean isInKeyList(String propertyKey) {
		return keyList.contains(propertyKey);
	}
	
	protected static void addProperty(String key) {
		String tmp = loader.getProperty(key);
		keyList.add(key);
		properties.put(key, tmp);
	}
}
