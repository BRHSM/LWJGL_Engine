package Exceptions;

/** Exception which is thrown if a shader is loaded which is incompattable with the renderer.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ShaderIncompatableException extends AbstractException{
	/** Create a new ShaderIncompatableException
	 * @param filename the name of the errored shader file.
	 */
	public ShaderIncompatableException(String filename) {
		super("[ERROR]: An incompatable shader file was loaded. ", "[ERROR]: The file: " + filename + " is incompattable with the shader type it's assigned to. Make sure the extention is correct.");
	}
}
