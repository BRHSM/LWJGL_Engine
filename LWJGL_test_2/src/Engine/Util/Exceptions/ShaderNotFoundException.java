package Engine.Util.Exceptions;

import Engine.Graphics.Shaders.AbstractShader;
import Engine.Graphics.Shaders.BasicEntityShader;
import Engine.Graphics.Shaders.BasicModelShader;
import Engine.Graphics.Shaders.TexturedEntityShader;
import Engine.Graphics.Shaders.TexturedModelShader;

/** Exception which is thrown if a renderer is loaded without a shader.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractException
 */
public class ShaderNotFoundException extends AbstractException{
	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = -7065358176229778999L;
	/** Create a new ShaderNotFoundException
	 * 
	 * @param texturedEntityShader 
	 * @param basicEntityShader 
	 */
	public ShaderNotFoundException(BasicModelShader basicModelShader, TexturedModelShader texturedModelShader, BasicEntityShader basicEntityShader, TexturedEntityShader texturedEntityShader) {
		super("[ERROR]: One or more shaders failed to load.", "[ERROR]: One or more shaders failed to load while the DisplayManager tried to load them. \n         BasicModelShader: " + getIfNull(basicModelShader) + "\n         texturedModelShader: " + getIfNull(texturedModelShader) + "\n         basicEntityShader: " + getIfNull(basicEntityShader) + "\n         texturedEntityShader: " + getIfNull(texturedEntityShader) + "\n");
	}
	/** Get a null string if the shader is undefined.
	 * 
	 * @param shader the shader
	 * @return null or the properties of the shader.
	 */
	private static String getIfNull(AbstractShader shader) {
		return shader != null ? shader.toString() : "null";
	}
}
