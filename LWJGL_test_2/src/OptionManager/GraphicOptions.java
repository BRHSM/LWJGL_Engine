package OptionManager;

import java.util.ArrayList;

public class GraphicOptions extends AbstractOptions{
	//values
	private static String windowHeight;
	private static String windowWidth;
	private static String windowAlignment;
	private static String windowFullscreen;
	private static String frameCap;
	
	//keys
	public static final String WINDOWHEIGHT_KEY = "windowHeight";
	public static final String WINDOWWIDTH_KEY = "windowWidth";
	public static final String WINDOWALLIGNMENT_KEY = "windowAlignment";
	public static final String WINDOWFULLSCREEN_KEY = "windowFullscreen";
	public static final String FRAMECAP_KEY = "frameCap";

	private static ArrayList<String> keyList;
	
	public static void loadFromFile(String filename) {
		OptionReader loader = new OptionReader(filename);
		
		keyList = new ArrayList<String>();
		
		//load properties
		windowHeight = loader.getProperty(WINDOWHEIGHT_KEY);
		windowWidth = loader.getProperty(WINDOWWIDTH_KEY);
		windowAlignment = loader.getProperty(WINDOWALLIGNMENT_KEY);
		windowFullscreen = loader.getProperty(WINDOWFULLSCREEN_KEY);
		frameCap = loader.getProperty(FRAMECAP_KEY);
		
		keyList.add(WINDOWHEIGHT_KEY);
		keyList.add(WINDOWWIDTH_KEY);
		keyList.add(WINDOWALLIGNMENT_KEY);
		keyList.add(WINDOWFULLSCREEN_KEY);
		keyList.add(FRAMECAP_KEY);
		
		properties.put(WINDOWHEIGHT_KEY,windowHeight);
		properties.put(WINDOWWIDTH_KEY,windowWidth);
		properties.put(WINDOWALLIGNMENT_KEY,windowAlignment);
		properties.put(WINDOWFULLSCREEN_KEY,windowFullscreen);
		properties.put(FRAMECAP_KEY,frameCap);	
	}
	
	public static boolean isInKeyList(String propertyKey) {
		return keyList.contains(propertyKey);
	}
}
