package Engine.Data.OptionManager;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/** Class with the code which is used to read Language files.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class LanguageReader {
	
	/** The properties inside the language file
	 */
	Properties configFile;
   
	/** Create a new LanguageReader ready to read from the file given in filename 
	 *  (No need to add file extention as .lang is added to final string.)
	 *  Only reads files in RESLanguageFiles folder.
	 * 
	 * @param filename The name of the language file.
	 */
   	public LanguageReader(String filename){
   		configFile = new java.util.Properties();
   		try {
   			FileInputStream stream = new FileInputStream(new File("RES/RESLanguageFiles/" + filename + ".lang"));
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

   	/** Close the LanguageReader.
   	 */
	public void close() {
		//clear the properties. 
		configFile.clear();
	}
}