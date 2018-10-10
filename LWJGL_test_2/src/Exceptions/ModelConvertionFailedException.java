package Exceptions;

/** Exception which is thrown if a modelStructure fails to convert to a model.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ModelConvertionFailedException extends AbstractException{
	/** Create a new ModelConvertionFailedException
	 */
	public ModelConvertionFailedException() {
		super("test_4", "testLong_4");
	}
}
