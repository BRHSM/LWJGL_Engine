package Engine.Graphics.Shaders;

import org.lwjgl.opengl.GL20;
import org.lwjglx.util.vector.Matrix4f;

import Engine.Data.ModelHandeling.TexturedModel;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.ShaderIncompatableException;

/** This class contains a basic shader for textured models.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see TexturedModel
 * @see AbstractShader
 */
public class BasicEntityShader extends AbstractShader{
    
    /** The location for the TransformationMatrix.
     */
    private int transformationMatrixLocation;
    /** The location of the projectionMatrix. 
     */
    private int projectionMatrixLocation;
    /** The location of the viewMatrix. 
     */
    private int viewMatrixLocation;    
    
protected int programIDTextured;
	
	/** The ID of the vertes shader.
	 */
	protected int vertexShaderIDTextured;
	/** The ID of the fragment shader.
	 */
	protected int fragmentShaderIDTextured;
	
	/* Filename of the shader.
	 */
	protected String vertexFileTextured;
	
	/* Filename of the shader.
	 */
	protected String fragmentFileTextured;
    
    /** Create a new BasicEntityShader.
     */
    public BasicEntityShader() {
        super();        
    }
    
    /** Loads the shader in openGL so it can be used.
	 * 
	 * @param subPathBasic the SubPath of the folder for the shaders inside the main shaders folder.
	 * @param vertexFileBasic The filename of the vertex shader.
	 * @param fragmentFileBasic The filename of the fragment SHader
	 */
	public void setupShader(String subPathBasic, String vertexFileBasic, String fragmentFileBasic, String subPathTextured) {
		this.vertexFileBasic = vertexFileBasic;
		this.fragmentFileBasic = fragmentFileBasic;
		this.vertexFileTextured = vertexFileBasic;
		this.fragmentFileTextured = fragmentFileBasic;
		//Get the ID's
		if(!getFileExtention(vertexFileBasic).equals("vs")) {
			ExceptionThrower.throwException(new ShaderIncompatableException(vertexFileBasic));
		}
		if(!getFileExtention(fragmentFileBasic).equals("fs")) {
			ExceptionThrower.throwException(new ShaderIncompatableException(fragmentFileBasic));
		}
		if(!getFileExtention(vertexFileTextured).equals("vs")) {
			ExceptionThrower.throwException(new ShaderIncompatableException(vertexFileTextured));
		}
		if(!getFileExtention(fragmentFileTextured).equals("fs")) {
			ExceptionThrower.throwException(new ShaderIncompatableException(fragmentFileTextured));
		}
		vertexShaderIDBasic = loadShader(this.vertexFileBasic, GL20.GL_VERTEX_SHADER, subPathBasic);
		fragmentShaderIDBasic = loadShader(this.fragmentFileBasic, GL20.GL_FRAGMENT_SHADER, subPathBasic);
		programIDBasic = GL20.glCreateProgram();
		
		vertexShaderIDTextured = loadShader(this.vertexFileTextured, GL20.GL_VERTEX_SHADER, subPathTextured);
		fragmentShaderIDTextured = loadShader(this.fragmentFileTextured, GL20.GL_FRAGMENT_SHADER, subPathTextured);
		programIDTextured = GL20.glCreateProgram();
		// attach shaders to program
		GL20.glAttachShader(programIDBasic, vertexShaderIDBasic);
		GL20.glAttachShader(programIDBasic, fragmentShaderIDBasic);
		
		GL20.glAttachShader(programIDTextured, vertexShaderIDTextured);
		GL20.glAttachShader(programIDTextured, fragmentShaderIDTextured);
		// Link the program.
		bindAttributes();
		GL20.glLinkProgram(programIDBasic);
		GL20.glLinkProgram(programIDTextured);
		// Validate the program. 
		GL20.glValidateProgram(programIDBasic);
		GL20.glValidateProgram(programIDTextured);
		
		getAllUniformLocations();
		
		int linkStatusBasic = GL20.glGetProgrami(programIDBasic, GL20.GL_LINK_STATUS);
		System.out.println("         Link status 1:   " + toString() + " : " +  linkStatusBasic);
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
			System.out.println("         Loaded shader 1: " + toString() + " from subfolder: " + subPathBasic);
		}
		int linkStatusTextured = GL20.glGetProgrami(programIDTextured, GL20.GL_LINK_STATUS);
		System.out.println("         Link status 2:   " + toString() + " : " +  linkStatusTextured);
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
			System.out.println("         Loaded shader 2: " + toString() + " from subfolder: " + subPathTextured);
		}

	}
	
    public void start(){
    	if(type == 0) {
    		GL20.glUseProgram(programIDBasic);
    	}
    	if(type == 1) {
    		GL20.glUseProgram(programIDTextured);
    	}
    }
    
    
    /** Bind the attributes of the shader.
     */
    protected void bindAttributes() {
    	//bind position.
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinate");
    }

	@Override
    /** Get all uniform locations.
     */
	protected void getAllUniformLocations() {
		transformationMatrixLocation = super.getUniformLocation("transformationMatrix");
		projectionMatrixLocation = super.getUniformLocation("projectionMatrix");
		viewMatrixLocation = super.getUniformLocation("viewMatrix");	
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
	
	public void setType(int type) {
		this.type = type;
	}
}
