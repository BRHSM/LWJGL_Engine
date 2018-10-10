package Exceptions;

/** Exception which is thrown if a model is invalid.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ModelInvalidException extends AbstractException{
	public ModelInvalidException() {
		super("[ERROR]: An invalid model was added", "[ERROR]: an invalid model was loaded and it cannot be rendered. Check if all your models are correct.");
	}
}
