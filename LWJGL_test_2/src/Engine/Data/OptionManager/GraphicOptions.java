package Engine.Data.OptionManager;

import java.util.ArrayList;

/** Class which handels graphics options.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractOptions
 * @see OptionReader
 */
public class GraphicOptions extends AbstractOptions{
	//keys
	public static final String WINDOWHEIGHT_KEY = "windowHeight";
	public static final String WINDOWWIDTH_KEY = "windowWidth";
	public static final String WINDOWALLIGNMENT_KEY = "windowAlignment";
	public static final String WINDOWFULLSCREEN_KEY = "windowFullscreen";
	public static final String WINDOWFOV_KEY = "windowFov";
	public static final String WINDOWNEARPLANE_KEY = "windowNearPlane";
	public static final String WINDOWFARPLANE_KEY = "windowFarPlane";
	public static final String FRAMECAP_KEY = "frameCap";
	public static final String USEPROJECTIONMARTRIX_KEY = "useProjectionMatrix";
	public static final String USEVIEWMATRIX_KEY = "useViewMatrix";

	/** The list of keys loaded by this class.
	 */
	private static ArrayList<String> keyList;
	/** A loader for option files.
	 */
	private static OptionReader loader;
	
	/** Load the graphics options from a config.
	 * 
	 * @param filename The file to load.
	 */
	public static void loadFromFile(String filename) {
		loader = new OptionReader(filename);
		
		keyList = new ArrayList<String>();
		//load properties
		
		addProperty(WINDOWHEIGHT_KEY);
		addProperty(WINDOWWIDTH_KEY);
		addProperty(WINDOWALLIGNMENT_KEY);
		addProperty(WINDOWFULLSCREEN_KEY);
		addProperty(WINDOWFOV_KEY);
		addProperty(WINDOWNEARPLANE_KEY);
		addProperty(WINDOWFARPLANE_KEY);
		addProperty(FRAMECAP_KEY);
		addProperty(USEPROJECTIONMARTRIX_KEY);
		addProperty(USEVIEWMATRIX_KEY);
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
