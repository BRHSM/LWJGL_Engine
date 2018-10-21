package Engine.Graphics.GraphicsEngine;

import org.lwjglx.util.vector.Matrix4f;

import Engine.Data.ModelHandeling.TexturedModel;
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
	/** Filename of the vertex shader.
	 */
    private static final String VERTEX_FILE = "TexturedEntityShader.vs";
    /** Filename of the fragment shader.
     */
    private static final String FRAGMENT_FILE = "TexturedEntityShader.fs";
    /** The location for the TransformationMatrix.
     */
    private int transformationMatrixLocation;
    
    private int projectionMatrixLocation;
    
    private int viewMatrixLocation;
    
    private int useProjectionMatrixLocation;
    
    private int useViewMatrixLocation;
    
    /** Create a new TexturedEntityShader
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
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.start();
		super.loadMatrix(projectionMatrixLocation, matrix);
		super.stop();
	}
	
	public void loadUseProjectionMatrix() {
		super.start();
		super.loadBoolean(useProjectionMatrixLocation, Boolean.parseBoolean(OptionHandler.getProperty(GraphicOptions.USEPROJECTIONMARTRIX_KEY, OptionHandler.GRAPHIC_OPTION_ID)));
		super.stop();
	}
	
	public void loadViewMatrix(Matrix4f matrix) {
		super.start();
		super.loadMatrix(viewMatrixLocation, matrix);
		super.stop();
	}
	
	public void loadUseViewMatrix() {
		super.start();
		super.loadBoolean(useViewMatrixLocation, Boolean.parseBoolean(OptionHandler.getProperty(GraphicOptions.USEVIEWMATRIX_KEY, OptionHandler.GRAPHIC_OPTION_ID)));
		super.stop();
	}
}
