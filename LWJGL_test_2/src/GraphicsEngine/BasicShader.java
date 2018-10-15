package GraphicsEngine;

import ModelHandeling.BasicModel;

/** A basic shader for rendering shapes.
 * 
 * @author user
 * @see BasicModel
 * @see AbstractShader
 */
public class BasicShader extends AbstractShader{
	/** Filename of the vertex shader.
	 */
    private static final String VERTEX_FILE = "src/RESShaderFiles/BasicVertexShader.vs";
    /** Filename of the fragment shader.
	 */
    private static final String FRAGMENT_FILE = "src/RESShaderFiles/BasicFragmentShader.fs";
 
    /** Create a new BasicShader.
     */
    public BasicShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    /** Bind the attributes of the shader.
     */
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }

	@Override
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub
		
	}
     
     
}
