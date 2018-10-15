package GraphicsEngine;

import ModelHandeling.TexturedModel;

/** This class contains a basic shader for textured models.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see TexturedModel
 * @see AbstractShader
 */
public class TexturedModelShader extends AbstractShader{
	/** Filename of the vertex shader.
	 */
    private static final String VERTEX_FILE = "src/RESShaderFiles/TextureModelShader.vs";
    /** Filename of the fragment shader.
     */
    private static final String FRAGMENT_FILE = "src/RESShaderFiles/TextureModelShader.fs";
 
    /** Create a new TexturedModelShader
     */
    public TexturedModelShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
    
    @Override
    /** Bind the attributes of the shader.
     */
    protected void bindAttributes() {
    	//bind position.
        super.bindAttribute(0, "position");
        //bind textures.
        super.bindAttribute(1, "textureCoordinate");
    }

	@Override
    /** Get all uniform locations. (does nothing).
     */
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub
		
	}
     
     
}
