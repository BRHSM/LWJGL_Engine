package Engine.Data.OptionManager;

import java.util.ArrayList;

import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.OptionInvalidException;

public class RuntimeOptions extends AbstractOptions {
	//keys
	public static final String USESVIEWMATRIX_KEY = "usesViewMatrix";
	public static final String USESPROJECTIONMATRIX_KEY = "usesProjectionMatrix";
	//defaults
	private static final String USESVIEWMATRIX_DEFAULT = "false";
	private static final String USESPROJECTIONMATRIX_DEFAULT = "false";
	
	public static void load () {
		addProperty(USESVIEWMATRIX_KEY, USESVIEWMATRIX_DEFAULT, OptionValidator.BOOLEAN_OPTION);
		addProperty(USESPROJECTIONMATRIX_KEY, USESPROJECTIONMATRIX_DEFAULT, OptionValidator.BOOLEAN_OPTION);
	}
	
	/** Add a property to the propertylist
	 * 
	 * @param key the key for the property to add.
	 */
	protected static void addProperty(String key, String value, int validationKey) {
		String tmp = value;
		if(OptionValidator.validateOption(tmp, validationKey)) {
			options.put(key, tmp);
			validationKeys.put(key, new Integer(validationKey));
		} else {
			ExceptionThrower.throwException(new OptionInvalidException(key));
		}
	}
}
