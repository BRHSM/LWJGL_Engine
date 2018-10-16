package OptionManager;

import java.util.ArrayList;

public class EngineOptions extends AbstractOptions{
	//values
	private static String debugEnabled;
	private static String debugAvgLoadtime;
	private static String debugLongExceptions;
	private static String debugStackTrace;
	private static String debugLogToFile;
	private static String showShaderUsed;
	private static String mainLanguage;
	private static String pathDevelopmentFiles;
	private static String pathLanguageFiles;
	private static String pathModels;
	private static String pathShaderFiles;
	private static String pathTextures;
	private static String WritebackOnExit;
	
	//keys
	public static final String DEBUGENABLED_KEY = "debugEnabled";
	public static final String DEBUGAVGLOADTIME_KEY = "debugAvgLoadtime";
	public static final String DEBUGLONGEXCEPTIONS_KEY = "debugLongExceptions";
	public static final String DEBUGSTACKTRACE_KEY = "debugStackTrace";
	public static final String DEBUGLOGTOFILE_KEY = "debugLogToFile";
	public static final String SHOWSHADERUSED_KEY = "showShaderUsed";
	public static final String MAINLANGUAGE_KEY = "mainLanguage";
	public static final String PATHDEVELOPMENTFILES_KEY = "pathDevelopmentFiles";
	public static final String PATHLANGUAGEFILE_KEY = "pathLanguageFiles";
	public static final String PATHMODELS_KEY = "pathModels";
	public static final String PATHSHADERFILES_KEY = "pathShaderFiles";
	public static final String PATHTEXTURES_KEY = "pathTextures";
	public static final String WRITEBACKONEXIT_KEY = "WritebackOnExit";
	
	private static ArrayList<String> keyList;
	
	public static void loadFromFile(String filename) {		
		OptionReader loader = new OptionReader(filename);
		
		keyList = new ArrayList<String>();
		
		//load properties
		debugEnabled 			= loader.getProperty(DEBUGENABLED_KEY);
		debugAvgLoadtime 		= loader.getProperty(DEBUGAVGLOADTIME_KEY);
		debugLongExceptions 	= loader.getProperty(DEBUGLONGEXCEPTIONS_KEY);
		debugStackTrace 		= loader.getProperty(DEBUGSTACKTRACE_KEY);
		debugLogToFile 			= loader.getProperty(DEBUGLOGTOFILE_KEY);
		showShaderUsed 			= loader.getProperty(SHOWSHADERUSED_KEY);
		mainLanguage 			= loader.getProperty(MAINLANGUAGE_KEY);
		pathDevelopmentFiles 	= loader.getProperty(PATHDEVELOPMENTFILES_KEY);
		pathLanguageFiles 		= loader.getProperty(PATHLANGUAGEFILE_KEY);
		pathModels 				= loader.getProperty(PATHMODELS_KEY);
		pathShaderFiles 		= loader.getProperty(PATHSHADERFILES_KEY);
		pathTextures 			= loader.getProperty(PATHTEXTURES_KEY);
		WritebackOnExit 		= loader.getProperty(WRITEBACKONEXIT_KEY);
		
		keyList.add(DEBUGENABLED_KEY);
		keyList.add(DEBUGAVGLOADTIME_KEY);
		keyList.add(DEBUGLONGEXCEPTIONS_KEY);
		keyList.add(DEBUGSTACKTRACE_KEY);
		keyList.add(DEBUGLOGTOFILE_KEY);
		keyList.add(SHOWSHADERUSED_KEY);
		keyList.add(MAINLANGUAGE_KEY);
		keyList.add(PATHDEVELOPMENTFILES_KEY);
		keyList.add(PATHLANGUAGEFILE_KEY);
		keyList.add(PATHMODELS_KEY);
		keyList.add(PATHSHADERFILES_KEY);
		keyList.add(PATHTEXTURES_KEY);
		keyList.add(WRITEBACKONEXIT_KEY);
		
		properties.put(DEBUGENABLED_KEY, debugEnabled);
		properties.put(DEBUGAVGLOADTIME_KEY, debugAvgLoadtime);
		properties.put(DEBUGLONGEXCEPTIONS_KEY, debugLongExceptions);
		properties.put(DEBUGSTACKTRACE_KEY, debugStackTrace);
		properties.put(DEBUGLOGTOFILE_KEY, debugLogToFile);
		properties.put(SHOWSHADERUSED_KEY, showShaderUsed);
		properties.put(MAINLANGUAGE_KEY, mainLanguage);
		properties.put(PATHDEVELOPMENTFILES_KEY, pathDevelopmentFiles);
		properties.put(PATHLANGUAGEFILE_KEY, pathLanguageFiles);
		properties.put(PATHMODELS_KEY, pathModels);
		properties.put(PATHSHADERFILES_KEY, pathShaderFiles);
		properties.put(PATHTEXTURES_KEY, pathTextures);
		properties.put(WRITEBACKONEXIT_KEY, WritebackOnExit);
	}
	
	public static boolean isInKeyList(String propertyKey) {
		return keyList.contains(propertyKey);
	}
}

