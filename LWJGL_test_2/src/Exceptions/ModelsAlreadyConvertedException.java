package Exceptions;

/** Exceptions which gets thrown when a modelStructure is added to the modelStructure list after it's converted
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ModelsAlreadyConvertedException extends AbstractException{
	/** Create a new ModelsAlreadyConvertedException
	 */
	public ModelsAlreadyConvertedException() {
		super("test_4", "testLong_4");
	}
}
