package Exceptions;

/** Exception which is thrown if an internal error occures. These errors are not 
 * related to user input but rather to a non functional code within the engine itself.
 * If you get this Eception please report it.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class InternalErrorException extends AbstractException{
	/** Create a new InternalErrorException.
	 */
	public InternalErrorException() {
		super("test_4", "testLong_4");
	}
}
