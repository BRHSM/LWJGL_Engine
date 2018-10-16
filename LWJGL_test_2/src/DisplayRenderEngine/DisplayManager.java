package DisplayRenderEngine;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.opengl.GL;
import Core.DataObject;
import Exceptions.ExceptionThrower;
import Exceptions.InternalErrorException;
import Exceptions.ShaderNotFoundException;
import GraphicsEngine.AbstractShader;
import GraphicsEngine.BasicModelShader;
import GraphicsEngine.TexturedModelShader;
import InputHandeling.KeyboardHandler;
import ModelHandeling.AbstractModel;
import ModelHandeling.BasicModel;
import ModelHandeling.BasicModelRenderer;
import ModelHandeling.ModelList;
import ModelHandeling.TexturedModel;
import ModelHandeling.TexturedModelRenderer;

/** This class holds the code for showing the window to the screen. 
 *  @author Bram Steenbergen
 *  @version 1.0
 *  @since 1.0
 *  @see WindowLoader
 *  @see BasicModelRenderer
 *  @see TexturedModelRenderer
 *  @see AbstractShader
 *  @see BasicModelShader
 *  @see TexturedModelShader
 *  @see ModelList
*/
public class DisplayManager {
		
	/** Long variable which holds the window address. 
	 */
	private long window;
	
	/** A renderer for basic models. 
	 */
	private BasicModelRenderer modelBasicRenderer;
	
	/** A renderer for Textured models.
	 */
	private TexturedModelRenderer modelTexturedRenderer;
	
	/** A loader for the window. 
	 */
	private WindowLoader windowLoader;
	
	/** The currently loaded shader.
	 */
	private AbstractShader currentShader;
	
	/** A shader for BasicModels.
	 */
	private BasicModelShader basicShader;
	
	/** A shader for TexturedModels.
	 */
	private TexturedModelShader textureShader;
	
	/** A list of models to render on startup.
	 */
	private ModelList modelList;
	
	/** Create a prepared window and add the models to it. After the window is prepared and
	 *  populated, It's shown to the screen
	 * 
	 *  @param keyboarHandler The listener for keyboard events
	 */
	public void createDisplay(KeyboardHandler keyboardHandler, DataObject object) {
		//initialize glfw
		if(!glfwInit()) {
			ExceptionThrower.throwException(new InternalErrorException());
		} 
		
		//get a new window loader
		windowLoader = new WindowLoader();
		
		modelList = object.getModelLists();
		
		//setup the window with the window loader. 
		window = windowLoader.setupWindow(keyboardHandler);
		
		glfwMakeContextCurrent(window);
		
		//prepare GL 
		GL.createCapabilities();
		
		//Setup Renderers
		modelBasicRenderer = new BasicModelRenderer();
		modelTexturedRenderer = new TexturedModelRenderer();
		
		//setup shaders
		basicShader = object.getBasicShader();
		textureShader = object.getTextureShader();
		if(basicShader == null || textureShader == null) {
			ExceptionThrower.throwException(new ShaderNotFoundException(basicShader, textureShader));
		}
		
		basicShader.setupShader();
		textureShader.setupShader();
		
		//convert ModelStructures to models
		modelList.ConvertToModels();
		
		//Show the window.
		glfwShowWindow(window);
		
	}
	
	/** Update the screen to the next frame. 
	 */
	public void updateDisplay() {
		ArrayList<AbstractModel> modelList;
		//prepare the model renderers.
		modelBasicRenderer.prepare();
		modelTexturedRenderer.prepare();
		
		//handle basic models. 
		modelList = this.modelList.getModels();
		
		//scroll through models.
		for (AbstractModel model : modelList) {
			if(model instanceof BasicModel) {
				currentShader = basicShader;
				//Start shader program.
				currentShader.start();
				//render the model.
				modelBasicRenderer.render((BasicModel)model);
				//Stop shader program.
				currentShader.stop();
			}
			if (model instanceof TexturedModel) {
				//handle basic models. 
				currentShader = textureShader;
				//Start shader program.
				currentShader.start();
				//render the model.
				modelTexturedRenderer.render((TexturedModel)model);
				//Stop shader program.
				currentShader.stop();
			}
		}
		
		//Swap the front and back buffers of window.
		glfwSwapBuffers(window);
		
	}
	
	/** Notify the DiplayManager that a game tick has passed. 
	 */
	public void updateFromThread() {
		//Process all pending events.
		glfwPollEvents();
	}
	
	/** Notify that the screen is closed. 
	 */
	public void cancelDisplay() {
		currentShader.cleanUp();
		windowLoader.cleanUp();
		GL.setCapabilities(null);
		Callbacks.glfwFreeCallbacks(window);
		glfwTerminate();
	}

	/** Check if window needs to be closed. 
	 * 
	 * @return true if window needs to be closed, false otherwise
	 */
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
}
