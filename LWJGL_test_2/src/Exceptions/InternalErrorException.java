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
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = -4397681718258939178L;

	/** Create a new InternalErrorException.
	 */
	public InternalErrorException() {
		super("[ERROR]: An internal error occured. Please report this error and it's conditions.", "[ERROR]: An error occured in the core of the engine. This is a bug and this should be reported. Please collect as much information as possible about the error and report it.");
	}
}
