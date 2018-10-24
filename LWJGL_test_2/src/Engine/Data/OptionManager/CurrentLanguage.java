package Engine.Data.OptionManager;

import java.util.ArrayList;

import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.OptionInvalidException;

/** Class which handels all properties of the currently loaded language file.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractOptions
 * @see LanguageReader
 */
public class CurrentLanguage extends AbstractOptions{
	//keys
	public static final String TITLE_KEY = "window_title";
	
	/** The list of keys loaded by this class.
	 */
	private static ArrayList<String> keyList;
	/** A loader for language files.
	 */
	private static LanguageReader loader;
	
	/** Load the current language from a language file.
	 * 
	 * @param filename The file to load.
	 */
	public static void loadFromFile(String filename) {
		loader = new LanguageReader(filename);
		
		keyList = new ArrayList<String>();
		//load properties
		addProperty(TITLE_KEY,OptionValidator.TEXT_OPTION);
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
	protected static void addProperty(String key, int validationKey) {
		String tmp = loader.getProperty(key);
		if(OptionValidator.validateOption(tmp, validationKey)) {
			keyList.add(key);
			options.put(key, tmp);
			validationKeys.put(key, new Integer(validationKey));
		} else {
			ExceptionThrower.throwException(new OptionInvalidException(key));
		}
	}
}
