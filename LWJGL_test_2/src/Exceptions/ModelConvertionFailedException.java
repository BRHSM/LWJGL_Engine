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
		super("[ERROR]: A ModelStructure was unable to be converted", "[ERROR]: The ModelConverter was unable to convert one ore more ModelStructures, make sure all ModelStructures added to the DataObject conaint the right information.");
	}
}
