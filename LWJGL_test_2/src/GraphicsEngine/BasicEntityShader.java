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
public class BasicEntityShader extends AbstractShader{
	/** Filename of the vertex shader.
	 */
    private static final String VERTEX_FILE = "src/RESShaderFiles/BasicEntityShader.vs";
    /** Filename of the fragment shader.
     */
    private static final String FRAGMENT_FILE = "src/RESShaderFiles/BasicEntityShader.fs";
    /** The location for the TransformationMatrix.
     */
    private int location;
    /** Create a new BasicEntityShader.
     */
    public BasicEntityShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
    
    @Override
    /** Bind the attributes of the shader.
     */
    protected void bindAttributes() {
    	//bind position.
        super.bindAttribute(0, "position");
    }

	@Override
    /** Get all uniform locations.
     */
	protected void getAllUniformLocations() {
		location = super.getUniformLocation("transformationMatrix");
		// TODO Auto-generated method stub
		
	}
    
	/** Load the transformationMatrix.
	 * 
	 * @param matrix the transformationMatrix to load
	 */
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location, matrix);
	}
     
}
