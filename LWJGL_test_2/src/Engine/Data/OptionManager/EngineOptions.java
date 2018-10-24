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
	public static final String DEBUGSHOWOPTIONUPDATE_KEY = "debugShowOptionUpdate";
	public static final String DEBUGLOGTOFILE_KEY = "debugLogToFile";
	public static final String DEBUGSHOWSHADERUSED_KEY = "debugShowShaderUsed";
	public static final String MAINLANGUAGE_KEY = "mainLanguage";
	public static final String PATHDEVELOPMENTFILES_KEY = "pathDevelopmentFiles";
	public static final String PATHLANGUAGEFILE_KEY = "pathLanguageFiles";
	public static final String PATHMODELS_KEY = "pathModels";
	public static final String PATHSHADERFILES_KEY = "pathShaderFiles";
	public static final String PATHTEXTURES_KEY = "pathTextures";
	public static final String WRITEBACKONEXIT_KEY = "WritebackOnExit";
	public static final String SUBPATHBASICENTITYSHADER_KEY = "subPathBasicEntityShader";
	public static final String SUBPATHBASICMODELSHADER_KEY = "subPathBasicModelShader";
	public static final String SUBPATHTEXTUREDENTITYSHADER_KEY = "subPathTexturedEntityShader";
	public static final String SUBPATHTEXTUREDMODELSHADER_KEY = "subPathTexturedModelShader";
	public static final String SHADERUSECUSTOM_KEY = "shaderUseCustom";
	public static final String SHADERCUSTOMVERTEXNAME_KEY = "shaderCustomVertexName";
	public static final String SHADERCUSTOMFRAGMENTNAME_KEY = "shaderCustomFragmentName";
	public static final String SHADERDEFAULTNAME_KEY = "shaderDefaultName";
	public static final String SHADERPRNAME_KEY = "shaderPRName";
	public static final String SHADERVINAME_KEY = "shaderVIName";
	public static final String SHADERLINAME_KEY = "shaderLIName";
	public static final String SHADERAUTOSELECT_KEY = "shaderAutoSelect";
	
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
		addProperty(DEBUGSHOWOPTIONUPDATE_KEY);
		addProperty(DEBUGLOGTOFILE_KEY);
		addProperty(DEBUGSHOWSHADERUSED_KEY);
		addProperty(MAINLANGUAGE_KEY);
		addProperty(PATHDEVELOPMENTFILES_KEY);
		addProperty(PATHLANGUAGEFILE_KEY);
		addProperty(PATHMODELS_KEY);
		addProperty(PATHSHADERFILES_KEY);
		addProperty(PATHTEXTURES_KEY);
		addProperty(WRITEBACKONEXIT_KEY);
		addProperty(SUBPATHBASICENTITYSHADER_KEY);
		addProperty(SUBPATHBASICMODELSHADER_KEY);
		addProperty(SUBPATHTEXTUREDENTITYSHADER_KEY);
		addProperty(SUBPATHTEXTUREDMODELSHADER_KEY);
		addProperty(SHADERUSECUSTOM_KEY);
		addProperty(SHADERCUSTOMVERTEXNAME_KEY);
		addProperty(SHADERCUSTOMFRAGMENTNAME_KEY);
		addProperty(SHADERDEFAULTNAME_KEY);
		addProperty(SHADERPRNAME_KEY);
		addProperty(SHADERVINAME_KEY);
		addProperty(SHADERLINAME_KEY);
		addProperty(SHADERAUTOSELECT_KEY);
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

