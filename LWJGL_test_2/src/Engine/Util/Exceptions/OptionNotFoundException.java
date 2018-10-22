package Engine.Util.Exceptions;

/** Exception thrown when an option is loaded that does not exist.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractException
 */
public class OptionNotFoundException extends AbstractException{
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = 6927911958588561999L;

	/** Create a new OptionNotFoundException.
	 * 
	 * @param key The key of the invalid option.
	 */
	public OptionNotFoundException(String key) {
		super("[ERROR]: An option value was requested wich does not exist.", "[ERROR]: The option for key: " + key + " does not exist, make sure the option is loaded and the key exists in the right file. ");
	}
}
