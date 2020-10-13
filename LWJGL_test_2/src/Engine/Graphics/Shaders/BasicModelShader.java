package Engine.Graphics.Shaders;

import Engine.Data.ModelHandeling.BasicModel;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.OptionHandler;

/** A basic shader for rendering shapes.
 * 
 * @author user
 * @see BasicModel
 * @see AbstractShader
 */
public class BasicModelShader extends AbstractShader{
 
    /** Create a new BasicShader.
     */
    public BasicModelShader() {
    	super();        
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
		return;
	}
     
     
}
