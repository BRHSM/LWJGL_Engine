package OptionManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import Exceptions.ExceptionThrower;
import Exceptions.InternalErrorException;
import Exceptions.OptionNotFoundException;

public class OptionHandler {
	private static HashMap<Integer, AbstractOptions> optionList;
	private static HashMap<Integer, String> optionFilename;
	
	public static final int ALL_IDS = 100;
	//OptionFIleID's (Starting from 101 to 199)
	public static final int GRAPHIC_OPTION_ID = 101;
	public static final int ENGINE_OPTION_ID = 102;
	public static final int CURRENT_LANGUAGE_ID = 103;
	
	//OptionFileTypes (Starting from 201 to 299)
	public static final int GRAPHIC_OPTION_TYPE = 201;
	public static final int ENGINE_OPTION_TYPE = 202;
	public static final int CURRENT_LANGUAGE_TYPE = 203;
	
	public static void setupOptions() {
		optionList = new HashMap<Integer, AbstractOptions>();
		optionFilename = new HashMap<Integer, String>();
	}
	
	public static void addOptionFile(int id, AbstractOptions options, String filename) {
		options.setupProperties();
		optionList.put(id, options);
		optionFilename.put(id, filename);
	}
	
	public static String getProperty(String optionKey, int optionFileID) {
		String property = optionList.get(optionFileID).getProperty(optionKey);
		if(property == null | property.isEmpty())
			ExceptionThrower.throwException(new OptionNotFoundException(optionKey));
		return property;
	}
	
	public static void setProperty(String optionKey, int optionFileID, String value) {
		optionList.get(optionFileID).setProperty(optionKey,value);
	}
	
	public static void loadOptionListFromFile(int id, int type) {
		if(type == GRAPHIC_OPTION_TYPE)
			GraphicOptions.loadFromFile(optionFilename.get(id));
		if(type == ENGINE_OPTION_TYPE)
			EngineOptions.loadFromFile(optionFilename.get(id));
		if(type == CURRENT_LANGUAGE_TYPE)
			CurrentLanguage.loadFromFile(optionFilename.get(id));
	}

	public static String getAllOptions() {
		StringBuilder sb = new StringBuilder();
		Iterator it = optionList.entrySet().iterator();
	    Entry pair = (Entry)it.next();
	    sb.append(pair.getValue().toString() + "\n");
		return sb.toString();
	}

	public static void writeBackOptions() {
		AbstractOptions options = optionList.get(101);
		Enumeration<String> enums = (Enumeration<String>) options.getProperties().propertyNames();
		Properties engineOptions = new Properties();
		Properties GraphicsOptions = new Properties();
	    while (enums.hasMoreElements()) {
		    String key = enums.nextElement();
		    String value = options.getProperties().getProperty(key);
		    if(GraphicOptions.isInKeyList(key)) {
		    	GraphicsOptions.put(key, value);
		    	if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
		    		System.out.println("         + Writing: " + key + " = " + value + " to: RES/RESConfigFiles/GraphicOptions.cfg");
			} else if(EngineOptions.isInKeyList(key)) {
				engineOptions.put(key, value);
				if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
					System.out.println("         + Writing: " + key + " = " + value + " to: RES/RESConfigFiles/EngineOptions.cfg");
			} else {
				if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
					System.out.println("         - Encountered Language Attribute: " + key + " = " + value);
			}
	    }
	    
	    try {
			engineOptions.store(new FileOutputStream(new File("RES/RESConfigFiles/EngineOptions.cfg")), null);
			GraphicsOptions.store(new FileOutputStream(new File("RES/RESConfigFiles/GraphicOptions.cfg")), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
