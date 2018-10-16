package OptionManager;

import java.util.ArrayList;
import java.util.Properties;

public class CurrentLanguage extends AbstractOptions{
	//values
	private static String title;
	
	//keys
	public static final String TITLE_KEY = "window_title";

	private static ArrayList<String> keyList;
	
	public static void loadFromFile(String filename) {
		LanguageReader loader = new LanguageReader(filename);
		
		keyList = new ArrayList<String>();
		//load properties
		title = loader.getProperty(TITLE_KEY);
		
		keyList.add(TITLE_KEY);
		
		properties.put(TITLE_KEY,title);
	}
	
	public static boolean isInKeyList(String propertyKey) {
		return keyList.contains(propertyKey);
	}
}
