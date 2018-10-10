package OptionManager;

import java.util.Properties;

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

	public static void loadFromFile(String filename) {
		OptionReader loader = new OptionReader(filename);
		//load properties
		windowHeight = loader.getProperty(WINDOWHEIGHT_KEY);
		windowWidth = loader.getProperty(WINDOWWIDTH_KEY);
		windowAlignment = loader.getProperty(WINDOWALLIGNMENT_KEY);
		windowFullscreen = loader.getProperty(WINDOWFULLSCREEN_KEY);
		frameCap = loader.getProperty(FRAMECAP_KEY);
		
		properties.put(WINDOWHEIGHT_KEY,windowHeight);
		properties.put(WINDOWWIDTH_KEY,windowWidth);
		properties.put(WINDOWALLIGNMENT_KEY,windowAlignment);
		properties.put(WINDOWFULLSCREEN_KEY,windowFullscreen);
		properties.put(FRAMECAP_KEY,frameCap);	
	}
}
