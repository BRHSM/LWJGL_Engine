package GraphicsEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
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
		vertexShaderID = loadShader(this.vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(this.fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		// attach shaders to program
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		// Link the program.
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
        	//error reading file.
            e.printStackTrace();
            System.exit(-1);
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
            System.exit(-1);
        }
        //Return shader ID.
        return shaderID;
    }

	@Override
	public String toString() {
		return "  [vertexFile=" + vertexFile + ", fragmentFile=" + fragmentFile + "]";
	}
	
	
}
