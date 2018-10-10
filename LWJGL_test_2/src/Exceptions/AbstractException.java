package Exceptions;

import OptionManager.EngineOptions;
import OptionManager.OptionHandler;

public class AbstractException extends Exception{
	
	private String message;
	private String messageLong;
	
	public AbstractException(String message, String messageLong) {
		this.message = message;
		this.messageLong = messageLong;
		this.debugOnCreate();
	}
	public String getMessage() {
		return message;
	}
	public String getMessageLong() {
		return messageLong;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setMessageLong(String messageLong) {
		this.messageLong = messageLong;
	}
	public void debugOnCreate() {
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
