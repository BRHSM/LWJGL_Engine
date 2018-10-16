package GraphicsEngine;

import ModelHandeling.BasicModel;

/** A basic shader for rendering shapes.
 * 
 * @author user
 * @see BasicModel
 * @see AbstractShader
 */
public class BasicModelShader extends AbstractShader{
	/** Filename of the vertex shader.
	 */
    private static final String VERTEX_FILE = "BasicModelShader.vs";
    /** Filename of the fragment shader.
	 */
    private static final String FRAGMENT_FILE = "BasicModelShader.fs";
 
    /** Create a new BasicShader.
     */
    public BasicModelShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    /** Bind the attributes of the shader.
     */
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }

	@Override
    /** Get all uniform locations. (does nothing).
     */
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub
		
	}
     
     
}
