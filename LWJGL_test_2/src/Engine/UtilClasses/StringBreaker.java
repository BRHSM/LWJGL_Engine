package Engine.UtilClasses;

public class StringBreaker {
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
}
