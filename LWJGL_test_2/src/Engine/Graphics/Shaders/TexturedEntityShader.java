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
    /** The location of useProjectionMatrix. 
     */
    private int useProjectionMatrixLocation;
    /** The location of useViewMatrix. 
     */
    private int useViewMatrixLocation;
    
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
		transformationMatrixLocation = super.getUniformLocation("transformationMatrix");
		projectionMatrixLocation = super.getUniformLocation("projectionMatrix");
		useProjectionMatrixLocation = super.getUniformLocation("useProjectionMatrix");
		viewMatrixLocation = super.getUniformLocation("viewMatrix");
		useViewMatrixLocation = super.getUniformLocation("useViewMatrix");		
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
	
	/** Load useProjectionMatrix.
	 */
	public void loadUseProjectionMatrix() {
		super.start();
		super.loadBoolean(useProjectionMatrixLocation, Boolean.parseBoolean(OptionHandler.getProperty(GraphicOptions.USEPROJECTIONMARTRIX_KEY, OptionHandler.GRAPHIC_OPTION_ID)));
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
	
	/** Load useViewMatrix.
	 */
	public void loadUseViewMatrix() {
		super.start();
		super.loadBoolean(useViewMatrixLocation, Boolean.parseBoolean(OptionHandler.getProperty(GraphicOptions.USEVIEWMATRIX_KEY, OptionHandler.GRAPHIC_OPTION_ID)));
		super.stop();
	}
}
