package Exceptions;

/** Class used to throw custom exceptions.
 * 
 * @author Bram Steenebergen
 * @version 1.0
 * @since 1.0
 * @see AbstractException
 */
public class ExceptionThrower {
	/** Throw an exception.
	 * @param e The exception to throw.
	 */
	public static void throwException(AbstractException e) {
		System.exit(-1);
	}
}
