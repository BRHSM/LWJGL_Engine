package Exceptions;

/** Exception which is thrown if a renderer is loaded without a shader.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ShaderNotFoundException extends AbstractException{
	/** Create a new ShaderNotFoundException
	 */
	public ShaderNotFoundException() {
		super("test", "testLong");
	}
}
