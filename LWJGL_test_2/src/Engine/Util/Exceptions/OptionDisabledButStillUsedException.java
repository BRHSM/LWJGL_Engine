package Engine.Util.Exceptions;

/** Exception thrown when an option is loaded with a value different than it's type.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractException
 */
public class OptionDisabledButStillUsedException extends AbstractException{
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = 6927911958588561999L;

	/** Create a new OptionInvalidException.
	 * 
	 * @param key The key of the invalid option.
	 */
	public OptionDisabledButStillUsedException() {
		super("[ERROR]: An option value was used while disallowed.", "[ERROR]: An .");
	}
}
