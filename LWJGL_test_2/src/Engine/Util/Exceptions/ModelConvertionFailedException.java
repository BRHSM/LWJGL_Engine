package Engine.Util.Exceptions;

/** Exception which is thrown if a modelStructure fails to convert to a model.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractException
 */
public class ModelConvertionFailedException extends AbstractException{
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = 7205187134815355299L;

	/** Create a new ModelConvertionFailedException
	 */
	public ModelConvertionFailedException() {
		super("[ERROR]: A ModelStructure was unable to be converted", "[ERROR]: The ModelConverter was unable to convert one ore more ModelStructures, make sure all ModelStructures added to the DataObject conaint the right information.");
	}
}
