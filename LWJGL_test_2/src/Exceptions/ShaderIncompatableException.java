package Exceptions;

/** Exception which is thrown if a shader is loaded which is incompattable with the renderer.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ShaderIncompatableException extends AbstractException{
	/** serialVersionUID
	 */
	private static final long serialVersionUID = -3239293508000941724L;

	/** Create a new ShaderIncompatableException
	 * @param filename the name of the errored shader file.
	 */
	public ShaderIncompatableException(String filename) {
		super("[ERROR]: An incompatable shader was loaded. ", "[ERROR]: The shader: " + filename + " is incompattable with the shader type it's assigned to. Make sure the extention is correct.");
	}
}
