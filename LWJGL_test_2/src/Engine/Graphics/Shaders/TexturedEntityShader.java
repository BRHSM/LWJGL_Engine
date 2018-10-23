package Engine.Graphics.Shaders;

import org.lwjglx.util.vector.Matrix4f;

import Engine.Data.ModelHandeling.TexturedModel;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;

/** This class contains a basic shader for textured models.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see TexturedModel
 * @see AbstractShader
 */
public class TexturedEntityShader extends AbstractShader{

    /** The location for the TransformationMatrix.
     */
    private int transformationMatrixLocation;
    /** The location of the projectionMatrix. 
     */
    private int projectionMatrixLocation;
    /** The location of the viewMatrix. 
     */
    private int viewMatrixLocation;
    
    /** Create a new TexturedEntityShader
     */
    public TexturedEntityShader() {
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
    /** Get all uniform locations.
     */
	protected void getAllUniformLocations() {
		transformationMatrixLocation = super.getUniformLocation("transformationMatrixTextured");
		projectionMatrixLocation = super.getUniformLocation("projectionMatrixTextured");
		viewMatrixLocation = super.getUniformLocation("viewMatrixTextured");		
	}
    
	/** Load the transformationMatrix.
	 * 
	 * @param matrix the transformationMatrix to load
	 */
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(transformationMatrixLocation, matrix);
	}
	
	/** Load a projectionMatrix.
	 * 
	 * @param matrix The projectionMatrix to load.
	 */
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.start();
		super.loadMatrix(projectionMatrixLocation, matrix);
		super.stop();
	}
	
	/** Load a viewMatrix.
	 * 
	 * @param matrix The viewMatrix to load.
	 */
	public void loadViewMatrix(Matrix4f matrix) {
		super.start();
		super.loadMatrix(viewMatrixLocation, matrix);
		super.stop();
	}
}
