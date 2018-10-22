package Engine.Data.OptionManager;

import java.util.ArrayList;

/** Class which handels engine options.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractOptions
 * @see OptionReader
 */
public class EngineOptions extends AbstractOptions{
	//keys
	public static final String DEBUGENABLED_KEY = "debugEnabled";
	public static final String DEBUGAVGLOADTIME_KEY = "debugAvgLoadtime";
	public static final String DEBUGTOTAVGLOADTIME_KEY = "debugTotAvgLoadtime";
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
	
	/** The list of keys loaded by this class.
	 */
	private static ArrayList<String> keyList;
	/** A loader for option files.
	 */
	private static OptionReader loader;
	
	/** Load the engine options from a config.
	 * 
	 * @param filename The file to load.
	 */
	public static void loadFromFile(String filename) {		
		loader = new OptionReader(filename);
		
		keyList = new ArrayList<String>();
		
		//load properties
		addProperty(DEBUGENABLED_KEY);
		addProperty(DEBUGAVGLOADTIME_KEY);
		addProperty(DEBUGTOTAVGLOADTIME_KEY);
		addProperty(DEBUGLONGEXCEPTIONS_KEY);
		addProperty(DEBUGSTACKTRACE_KEY);
		addProperty(DEBUGLOGTOFILE_KEY);
		addProperty(SHOWSHADERUSED_KEY);
		addProperty(MAINLANGUAGE_KEY);
		addProperty(PATHDEVELOPMENTFILES_KEY);
		addProperty(PATHLANGUAGEFILE_KEY);
		addProperty(PATHMODELS_KEY);
		addProperty(PATHSHADERFILES_KEY);
		addProperty(PATHTEXTURES_KEY);
		addProperty(WRITEBACKONEXIT_KEY);
	}
	
	/** Check if a key is in the keylist for this class.
	 * 
	 * @param propertyKey The key to check.
	 * @return true if the key is in the list, false otherwise.
	 */
	public static boolean isInKeyList(String propertyKey) {
		return keyList.contains(propertyKey);
	}
	
	/** Add a property to the propertylist
	 * 
	 * @param key the key for the property to add.
	 */
	protected static void addProperty(String key) {
		String tmp = loader.getProperty(key);
		keyList.add(key);
		properties.put(key, tmp);
	}
}

