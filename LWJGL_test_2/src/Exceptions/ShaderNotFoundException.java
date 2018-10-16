package Exceptions;

import GraphicsEngine.AbstractShader;
import GraphicsEngine.BasicModelShader;
import GraphicsEngine.TexturedModelShader;

/** Exception which is thrown if a renderer is loaded without a shader.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @see 1.0
 */
public class ShaderNotFoundException extends AbstractException{
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = -7065358176229778999L;
	/** Create a new ShaderNotFoundException
	 */
	public ShaderNotFoundException(BasicModelShader basicShader, TexturedModelShader textureShader) {
		super("[ERROR]: One or more shaders failed to load.", "[ERROR]: One or more shaders failed to load while the DisplayManager tried to load them. \n         BasicShader: " + getIfNull(basicShader) + "\n         textureShader: " + getIfNull(textureShader) + "\n");
	}
	/** Get a null string if the shader is undefined.
	 * @param shader the shader
	 * @return null or the properties of the shader.
	 */
	private static String getIfNull(AbstractShader shader) {
		return shader != null ? shader.toString() : "null";
	}
}
