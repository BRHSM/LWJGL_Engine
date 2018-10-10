package OptionManager;

import java.util.Properties;

public class CurrentLanguage extends AbstractOptions{
	//values
	private static String title;
	
	//keys
	public static final String TITLE_KEY = "window_title";

	public static void loadFromFile(String filename) {
		LanguageReader loader = new LanguageReader(filename);
		//load properties
		title = loader.getProperty(TITLE_KEY);
		
		properties.put(TITLE_KEY,title);
	}
}
