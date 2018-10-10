package Exceptions;

/** Exception thrown when an option is loaded that does not exist.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class OptionNotFoundException extends AbstractException{
	/** Create a new OptionNotFoundException.
	 */
	public OptionNotFoundException() {
		super("test_4", "testLong_4");
	}
}
