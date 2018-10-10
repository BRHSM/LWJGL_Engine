package Exceptions;

/** Exception which is thrown if a shader is loaded which is incompattable with the renderer.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ShaderIncompatableException extends AbstractException{
	/** Create a new ShaderIncompatableException
	 */
	public ShaderIncompatableException() {
		super("test_2", "testLong_2");
	}
}
