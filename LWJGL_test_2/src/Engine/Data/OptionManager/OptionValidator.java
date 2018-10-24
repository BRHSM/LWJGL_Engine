package Engine.Data.OptionManager;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class OptionValidator {
	public static final int BOOLEAN_OPTION = 401;
	public static final int INTEGER_OPTION = 402;
	public static final int FLOATINGPOINT_OPTION = 403;
	public static final int CHARARRAY_OPTION = 404;
	public static final int TEXT_OPTION = 405;
	public static final int FOLDERPATH_OPTION = 406;
	public static final int ANY_OPTION = 499;
	
	public static boolean validateOption(String value, int type) {
		if(type == BOOLEAN_OPTION) {
			if(value.equals("true"))
				return true;
			if(value.equals("false"))
				return true;
			return false;
		}
		if(type == INTEGER_OPTION) {
			return isNumeric(value);
		}
		if(type == FLOATINGPOINT_OPTION) {
			return isFloatingPoint(value);
		}
		if(type == CHARARRAY_OPTION) {
			return value.matches("[a-zA-Z]+");
		}
		if(type == TEXT_OPTION) {
			return !value.matches(".*\\d+.*");
		}
		if(type == FOLDERPATH_OPTION) {
			return isFolderPath(value);
		}
		if(type == ANY_OPTION) {
			return true;
		}
		return false;
	}
	
	private static boolean isNumeric(String str){
		try {  
		    int d = Integer.parseInt(str);  
		} catch(NumberFormatException nfe) {  
		    return false;  
		}  
		return true;  
	}
	
	private static boolean isFloatingPoint(String str){
		try {  
		    float d = Float.parseFloat(str);  
		} catch(NumberFormatException nfe) {  
		    return false;  
		}  
		return true;  
	}
	
	private static boolean isFolderPath(String str) {
		try {
		    Paths.get(str);
		} catch (InvalidPathException | NullPointerException ex) {
		    return false;
		}
		return true;
	}
}
