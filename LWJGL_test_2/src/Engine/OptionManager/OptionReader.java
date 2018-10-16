package Engine.OptionManager;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/** Class with the code which is used to read configuration files.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class OptionReader {
	
	/** The properties inside the configuration file
	 */
	Properties configFile;
   
	/** Create a new OptionReader ready to read from the file given in filename 
	 *  (No need to add file extention as .cfg is added to final string.)
	 *  Only reads files in RESConfigFiles folder.
	 * 
	 * @param filename The name of the file.
	 */
   	public OptionReader(String filename){
   		configFile = new java.util.Properties();
   		try {
   			FileInputStream stream = new FileInputStream(new File("RES/RESConfigFiles/" + filename + ".cfg"));
   			configFile.load(stream);
   		}catch(Exception eta){
   			eta.printStackTrace();
   		}
   	}
 
   	/** Get the value of a property for a given key.
   	 * 
   	 * @param key The key of the property you want.
   	 * @return the value of the property as String.
   	 */
   	public String getProperty(String key) {
   		String value = this.configFile.getProperty(key);
   		return value;
   	}

   	/** Close the OptionReader.
   	 */
	public void close() {
		//clear the properties. 
		configFile.clear();
	}
}