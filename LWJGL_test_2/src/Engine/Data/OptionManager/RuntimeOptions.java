package Engine.Data.OptionManager;

import java.util.ArrayList;

public class RuntimeOptions extends AbstractOptions {
	//keys
	private static final String USESVIEWMATRIX_KEY = "usesViewMatrix";
	//defaults
	private static final String USESVIEWMATRIX_DEFAULT = "false";
	
	public static void load () {
		addProperty(USESVIEWMATRIX_KEY, USESVIEWMATRIX_DEFAULT);
	}
	
	/** Add a property to the propertylist
	 * 
	 * @param key the key for the property to add.
	 */
	protected static void addProperty(String key, String defaultValue) {
		String tmp = defaultValue;
		properties.put(key, tmp);
	}
}
