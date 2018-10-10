package Exceptions;

/** Exception which is thrown if a model is invalid.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ModelInvalidException extends AbstractException{
	public ModelInvalidException() {
		super("test_3", "testLong_3");
	}
}
