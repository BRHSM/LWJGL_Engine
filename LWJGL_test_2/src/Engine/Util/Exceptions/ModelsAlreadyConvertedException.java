package Engine.Util.Exceptions;

/** Exceptions which gets thrown when a modelStructure is added to the modelStructure list after it's converted
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractException
 */
public class ModelsAlreadyConvertedException extends AbstractException{
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = 597166699425158244L;

	/** Create a new ModelsAlreadyConvertedException
	 */
	public ModelsAlreadyConvertedException() {
		super("[ERROR]: A ModelStructure was added after the list was converted. ", "[ERROR]: ModelStructures can only be added before the ModelStructures are converted into models. Make sure only to convert to models after the last ModelStructure was added.");
	}
}
