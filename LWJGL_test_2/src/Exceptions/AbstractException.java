package Exceptions;

import OptionManager.EngineOptions;
import OptionManager.OptionHandler;
/** Abstract class used by other exceptions to build upon.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 */
public class AbstractException extends Exception{
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
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
			if(OptionHandler.getProperty(EngineOptions.DEBUGLONGEXCEPTIONS_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
				//show longer message
				System.out.println(this.message);
			} else {
				//show shorter message
				System.out.println(this.messageLong);
			}
			if(OptionHandler.getProperty(EngineOptions.DEBUGSTACKTRACE_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
				//show StackTrace
				System.out.println(this.getStackTrace());
			}
		}
	}
}
