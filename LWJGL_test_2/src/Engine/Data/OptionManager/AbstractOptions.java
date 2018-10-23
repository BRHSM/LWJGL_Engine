package Engine.Data.OptionManager;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/** An abstract representation of an option file used by other option file classes.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class AbstractOptions {
	/** The list of properties loaded.
	 */
	public static volatile Properties properties;
	
	/** Setup the propertylist.
	 */
	public static void setupProperties() {
		if(properties == null)
			properties = new Properties();
	}
	
	/** Get a property for a certain key.
	 * 
	 * @param key The key of the property
	 * @return The value for that key.
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	/** Get all options as a string.
	 */
	public String toString() {
		Map<String, String> treeMap = new TreeMap(properties) {
			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder();
				sb.append("{\n");
				@SuppressWarnings("unchecked")
				Enumeration<String> enums = (Enumeration<String>) properties.propertyNames();
				while (enums.hasMoreElements()) {
					String key = enums.nextElement();
					String value = properties.getProperty(key);
					sb.append("         " + key + "=" + value + "\n");
				}
				sb.append("}");
				return sb.toString();
			}
		};
		return treeMap.toString();
	}

	/** Overwrite a loaded property.
	 * 
	 * @param optionKey The key of the property to overwrite.
	 * @param value The value to set for the property.
	 */
	public void setProperty(String optionKey, String value) {
		properties.put(optionKey, value);
		
	}

	/** Get the property value.
	 * 
	 * @return the property list.
	 */
	public Properties getProperties() {
		return properties;
	}
}
