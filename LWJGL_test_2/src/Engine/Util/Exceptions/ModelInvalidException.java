package Engine.Util.Exceptions;

/** Exception which is thrown if a model is invalid.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractException
 */
public class ModelInvalidException extends AbstractException{
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = 7663022092720906005L;

	/** Create a new ModelInvalidException.
	 */
	public ModelInvalidException() {
		super("[ERROR]: An invalid model was added", "[ERROR]: an invalid model was loaded and it cannot be rendered. Check if all your models are correct.");
	}
}