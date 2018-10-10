package OptionManager;

import java.util.Properties;

public class EngineOptions extends AbstractOptions{
	//values
	private static String debugEnabled;
	private static String debugAvgLoadtime;
	private static String debugLongExceptions;
	private static String debugStackTrace;
	private static String showShaderUsed;
	private static String mainLanguage;
	
	//keys
	public static final String DEBUGENABLED_KEY = "debugEnabled";
	public static final String DEBUGAVGLOADTIME_KEY = "debugAvgLoadtime";
	public static final String DEBUGLONGEXCEPTIONS_KEY = "debugLongExceptions";
	public static final String DEBUGSTACKTRACE_KEY = "debugStackTrace";
	public static final String SHOWSHADERUSED_KEY = "showShaderUsed";
	public static final String MAINLANGUAGE_KEY = "mainLanguage";
	
	public static void loadFromFile(String filename) {		
		OptionReader loader = new OptionReader(filename);
		//load properties
		debugEnabled = loader.getProperty(DEBUGENABLED_KEY);
		debugAvgLoadtime = loader.getProperty(DEBUGAVGLOADTIME_KEY);
		debugLongExceptions = loader.getProperty(DEBUGLONGEXCEPTIONS_KEY);
		debugStackTrace = loader.getProperty(DEBUGSTACKTRACE_KEY);
		showShaderUsed = loader.getProperty(SHOWSHADERUSED_KEY);
		mainLanguage = loader.getProperty(MAINLANGUAGE_KEY);
		
		properties.put(DEBUGENABLED_KEY, debugEnabled);
		properties.put(DEBUGAVGLOADTIME_KEY, debugAvgLoadtime);
		properties.put(DEBUGLONGEXCEPTIONS_KEY, debugLongExceptions);
		properties.put(DEBUGSTACKTRACE_KEY, debugStackTrace);
		properties.put(SHOWSHADERUSED_KEY, showShaderUsed);
		properties.put(MAINLANGUAGE_KEY, mainLanguage);
	}
}

