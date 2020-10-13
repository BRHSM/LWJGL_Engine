package Engine.Graphics.Shaders;

import Engine.Data.ModelHandeling.TexturedModel;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.OptionHandler;

/** This class contains a basic shader for textured models.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see TexturedModel
 * @see AbstractShader
 */
public class TexturedModelShader extends AbstractShader{
 
    /** Create a new TexturedModelShader
     */
    public TexturedModelShader() {
    	super();        
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
		return;
	}
     
     
}
