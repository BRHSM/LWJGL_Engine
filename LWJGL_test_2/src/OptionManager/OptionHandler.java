package OptionManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import Exceptions.ExceptionThrower;
import Exceptions.OptionNotFoundException;

public class OptionHandler {
	private static HashMap<Integer, AbstractOptions> optionList;
	private static HashMap<Integer, String> optionFilename;
	
	//OptionFIleID's (Starting from 101 to 199)
	public static final int GRAPHIC_OPTION_ID = 101;
	public static final int ENGINE_OPTION_ID = 102;
	public static final int CURRENT_LANGUAGE_ID = 103;
	
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
}
