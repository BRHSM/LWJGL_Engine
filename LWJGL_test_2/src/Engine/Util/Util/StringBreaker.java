package Engine.Util.Util;
/** Class used to introduce breaklines into strings.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class StringBreaker {
	/** Break the string every X characters and insert a string in each break.
	 * 
	 * @param text The string to break
	 * @param insert The break token.
	 * @param period The interval of insertion.
	 * @return The broken string.
	 */
	public static String breakString(String text, String insert, int period){
	    StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length()/period)+1);
	    int index = 0;
	    String prefix = "";
	    while (index < text.length())
	    {
	        builder.append(prefix);
	        prefix = insert;
	        builder.append(text.substring(index, Math.min(index + period, text.length())));
	        index += period;
	    }
	    return builder.toString();
	}
	
	/** Break the string behind every character and insert a string in each break.
	 * 
	 * @param text The string to break
	 * @param insert The break token.
	 * @param divide the string to break behind
	 * @return The broken string.
	 */
	public static String breakStringBehindString(String text, String insert, String divider) {
		return text.replace(divider, divider + insert);
	}
}
