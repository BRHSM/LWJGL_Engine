package Engine.Util.Exceptions;

import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.OptionHandler;
/** Abstract class used by other exceptions to build upon.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractException
 */
public class AbstractException extends Exception{
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = 591345334521026008L;
	/** The general message for the exception.
	 */
	private String message;
	/** A longer message containing more information about the exception.
	 */
	private String messageLong;
	
	/** Create a new AbstractException.
	 * 
	 * @param message The short message.
	 * @param messageLong The longer message.
	 */
	public AbstractException(String message, String messageLong) {
		this.message = message;
		this.messageLong = messageLong;
		this.debugOnCreate();
	}
	/** Show the debug message after creating the exception.
	 */
	private void debugOnCreate() {
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
			if(OptionHandler.getProperty(EngineOptions.DEBUGLONGEXCEPTIONS_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
				//show longer message
				System.out.println(this.message);
			} else {
				//show shorter message
				System.out.println(this.messageLong);
			}
			if(OptionHandler.getProperty(EngineOptions.DEBUGSTACKTRACE_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
				//show StackTrace
				System.out.println(this.getStackTrace());
			}
		}
	}
}
