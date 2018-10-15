package GraphicsEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjglx.util.vector.Matrix4f;
import org.lwjglx.util.vector.Vector3f;

import Exceptions.ExceptionThrower;
import Exceptions.InternalErrorException;
import Exceptions.ShaderIncompatableException;
/** A generic shader program class which can be etended to create by other shaders.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractShader {
	
	/** The ID of the shader program.
	 */
	private int programID;
	
	/** The ID of the vertes shader.
	 */
	private int vertexShaderID;
	/** The ID of the fragment shader.
	 */
	private int fragmentShaderID;
	
	/* Filename of the shader.
	 */
	private String vertexFile;
	
	/* Filename of the shader.
	 */
	private String fragmentFile;
	private static FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
	
	/** Create a nrw GenericSsaderProfram for the given vertex- and fragmentshader's
	 *  filename. (Shader files should be placed in the RESShaderFiles folder)
	 * 
	 * @param vertexFile The filename of the vertex shader.
	 * @param fragmentFile The filename of the fragment SHader
	 */
	public AbstractShader(String vertexFile, String fragmentFile) {
		this.vertexFile = vertexFile;
		this.fragmentFile = fragmentFile;

	}
	
	/** Loads the shader in openGL so it can be used.
	 */
	public void setupShader() {
		//Get the ID's
		if(!getFileExtention(vertexFile).equals("vs")) {
			ExceptionThrower.throwException(new ShaderIncompatableException(vertexFile));
		}
		if(!getFileExtention(fragmentFile).equals("fs")) {
			ExceptionThrower.throwException(new ShaderIncompatableException(fragmentFile));
		}
		vertexShaderID = loadShader(this.vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(this.fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		// attach shaders to program
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		// Link the program.
		bindAttributes();
		GL20.glLinkProgram(programID);
		// Validate the program. 
		GL20.glValidateProgram(programID);
	}
	
	/** Start the shader.
	 */
    public void start(){
        GL20.glUseProgram(programID);
    }
    
    /** Stop the shader.
     */
    public void stop(){
        GL20.glUseProgram(0);
    }
    
    protected int getUniformLocation(String uniformName) {
    	return GL20.glGetUniformLocation(programID, uniformName);
    }
    
    protected abstract void getAllUniformLocations();
    
    protected void loadFloat(int location, float value) {
    	GL20.glUniform1f(location, value);
    }
    
    protected void loadVector(int location, Vector3f value) {
    	GL20.glUniform3f(location, value.x, value.y, value.z);
    }
    
    protected void loadBoolean(int location, boolean value) {
    	float toLoad = 0;
    	if(value) {
    		toLoad = 1;
    	}
    	GL20.glUniform1f(location, toLoad);
    }
    
    protected void loadMatrix(int location, Matrix4f matrix) {
    	matrix.store(buffer);
    	buffer.flip();
    	GL20.glUniformMatrix4fv(location, false, buffer);
    }
    
    /** Remove the shader.
     */
    public void cleanUp(){
    	// Stop shader before cleanup.
        stop();
        // Detach shaders from program.
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        // Delete shaders.
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        // Delete program. 
        GL20.glDeleteProgram(programID);
    }
    
    /** Method to override for attibute binding.
     */
    protected abstract void bindAttributes();
    
    /** Bind a single attribute 
     */
    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }
    /** Load a shader from a shader file
     * 
     * @param file the filename of the shader
     * @param type the type of the shader
     * @return the ID of the shader
     */
	private static int loadShader(String file, int type){
		//create a string builder.
        StringBuilder shaderSource = new StringBuilder();
        //read the file and store in shaderSource.
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
        	ExceptionThrower.throwException(new InternalErrorException());
        }
        //get shader ID.
        int shaderID = GL20.glCreateShader(type);
        //create shader from source.
        GL20.glShaderSource(shaderID, shaderSource);
        //compile shader.
        GL20.glCompileShader(shaderID);
        //check for errors.
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("[ERROR]: Could not compile shader!");
            ExceptionThrower.throwException(new InternalErrorException());
        }
        //Return shader ID.
        return shaderID;
    }

	@Override
	public String toString() {
		return "  [vertexFile=" + vertexFile + ", fragmentFile=" + fragmentFile + "]";
	}
	
	private String getFileExtention(String path) {
		String extension = "";

		int i = path.lastIndexOf('.');
		int p = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));

		if (i >= p) {
		    extension = path.substring(i+1);
		}
		return extension;
	}
	
	
}
