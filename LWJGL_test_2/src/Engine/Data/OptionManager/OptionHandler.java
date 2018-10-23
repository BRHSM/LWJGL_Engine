package Engine.Data.OptionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.OptionNotFoundException;
import Engine.Util.Util.SortedProperties;

/** Class which handles all options. This class is used to read and write to option files and the loaded 
 *  options in memory.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractOptions
 * @see CurrentLanguage
 * @see EngineOptions
 * @see GraphicOptions
*/
public class OptionHandler {
	private static HashMap<Integer, AbstractOptions> optionList;
	private static HashMap<Integer, String> optionFilename;
	
	//OptionFIleID's (Starting from 101 to 199)
	public static final int GRAPHIC_OPTION_ID = 101;
	public static final int ENGINE_OPTION_ID = 102;
	public static final int CURRENT_LANGUAGE_ID = 103;
	public static final int RUNTIME_OPTIONS_ID = 199;
	
	//OptionFileTypes (Starting from 201 to 299)
	public static final int GRAPHIC_OPTION_TYPE = 201;
	public static final int ENGINE_OPTION_TYPE = 202;
	public static final int CURRENT_LANGUAGE_TYPE = 203;
	public static final int RUNTIME_OPTIONS_TYPE = 299;
	
	public static void setupOptions() {
		optionList = new HashMap<Integer, AbstractOptions>();
		optionFilename = new HashMap<Integer, String>();
	}
	
	/** Add an option file to the list of files to load on init.
	 * 
	 * @param id the ID of the options
	 * @param options An instance of options to use.
	 * @param filename The name of the file.
	 */
	public static void addOptionFile(int id, AbstractOptions options, String filename) {
		AbstractOptions.setupProperties();
		optionList.put(id, options);
		optionFilename.put(id, filename);
	}
	
	public static void addRuntimeOptions() {
		optionList.put(RUNTIME_OPTIONS_ID, new RuntimeOptions());
	}
	
	/** Get a property for a given fileID and optionkey.
	 * 
	 * @param optionKey The key of the option.
	 * @param optionFileID The ID of the file.
	 * @return The value of the option as a string.
	 */
	public static String getProperty(String optionKey, int optionFileID) {
		String property = optionList.get(optionFileID).getProperty(optionKey);
		if(property == null | property.isEmpty())
			ExceptionThrower.throwException(new OptionNotFoundException(optionKey));
		return property;
	}
	
	/** Set a property in memory
	 * 
	 * @param optionKey The key of the option.
	 * @param optionFileID The ID of the file.
	 * @param value The value of the option as a string.
	 */
	public static void setProperty(String optionKey, int optionFileID, String value) {
		optionList.get(optionFileID).setProperty(optionKey,value);
	}
	
	/** Notify the option class to load from it's file.
	 * 
	 * @param id The ID of the class to notify.
	 * @param type The type of options the class contains.
	 */
	public static void loadOptionListFromFile(int id, int type) {
		if(type == GRAPHIC_OPTION_TYPE)
			GraphicOptions.loadFromFile(optionFilename.get(id));
		if(type == ENGINE_OPTION_TYPE)
			EngineOptions.loadFromFile(optionFilename.get(id));
		if(type == CURRENT_LANGUAGE_TYPE)
			CurrentLanguage.loadFromFile(optionFilename.get(id));
		if(type == RUNTIME_OPTIONS_TYPE)
			RuntimeOptions.load();
	}
	/** Get all options in string format.
	 * 
	 * @return a string of all loaded options.
	 */
	public static String getAllOptions() {
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = optionList.entrySet().iterator();
	    @SuppressWarnings("unchecked")
		Entry<String, AbstractOptions> pair = (Entry<String, AbstractOptions>)it.next();
	    sb.append(pair.getValue().toString() + "\n");
		return sb.toString();
	}

	/** Writeback all options to their respective file.
	 */
	public static void writeBackOptions() {
		AbstractOptions options = optionList.get(101);
		@SuppressWarnings("unchecked")
		Enumeration<String> enums = (Enumeration<String>) options.getProperties().propertyNames();
		Properties engineOptions = new Properties();
		Properties graphicsOptions = new Properties();
		SortedProperties engineOptionsSorted = new SortedProperties();
		SortedProperties graphicsOptionsSorted = new SortedProperties();
	    while (enums.hasMoreElements()) {
		    String key = enums.nextElement();
		    String value = options.getProperties().getProperty(key);
		    if(GraphicOptions.isInKeyList(key)) {
		    	graphicsOptions.put(key, value);
		    	if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
		    		System.out.println(String.format("         + Writing:                          %-20s = %-40s  to: RES/RESConfigFiles/GraphicOptions.cfg", key, value));
			} else if(EngineOptions.isInKeyList(key)) {
				engineOptions.put(key, value);
				if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
					System.out.println(String.format("         + Writing:                          %-20s = %-40s  to: RES/RESConfigFiles/EngineOptions.cfg", key, value));
			} else {
				if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
					System.out.println(String.format("         - Encountered Language Attribute:   %-20s = %-40s ", key, value));
			}
	    }
	    
	    try {
	    	engineOptionsSorted.putAll(engineOptions);
	    	graphicsOptionsSorted.putAll(graphicsOptions);
	    	engineOptionsSorted.store(new FileOutputStream(new File("RES/RESConfigFiles/EngineOptions.cfg")), null);
	    	graphicsOptionsSorted.store(new FileOutputStream(new File("RES/RESConfigFiles/GraphicOptions.cfg")), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
