package GraphicsEngine;

import org.lwjglx.util.vector.Matrix4f;

import ModelHandeling.TexturedModel;

/** This class contains a basic shader for textured models.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see TexturedModel
 * @see AbstractShader
 */
public class TexturedEntityShader extends AbstractShader{
	/** Filename of the vertex shader.
	 */
    private static final String VERTEX_FILE = "src/RESShaderFiles/UniformTestShader.vs";
    /** Filename of the fragment shader.
     */
    private static final String FRAGMENT_FILE = "src/RESShaderFiles/UniformTestShader.fs";
    
    private int location;
    /** Create a new TextureShader
     */
    public TexturedEntityShader() {
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
	protected void getAllUniformLocations() {
		location = super.getUniformLocation("transformationMatrix");
		// TODO Auto-generated method stub
		
	}
     
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location, matrix);
	}
     
}