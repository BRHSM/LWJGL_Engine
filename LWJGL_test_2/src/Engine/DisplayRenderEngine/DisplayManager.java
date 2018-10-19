package Engine.DisplayRenderEngine;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import Engine.Core.DataObject;
import Engine.EntityHandeling.AbstractEntity;
import Engine.EntityHandeling.BasicEntityRenderer;
import Engine.EntityHandeling.EntityList;
import Engine.EntityHandeling.TexturedEntityRenderer;
import Engine.Exceptions.ExceptionThrower;
import Engine.Exceptions.InternalErrorException;
import Engine.Exceptions.ShaderNotFoundException;
import Engine.GraphicsEngine.AbstractShader;
import Engine.GraphicsEngine.BasicEntityShader;
import Engine.GraphicsEngine.BasicModelShader;
import Engine.GraphicsEngine.TexturedEntityShader;
import Engine.GraphicsEngine.TexturedModelShader;
import Engine.InputHandeling.KeyboardHandler;
import Engine.ModelHandeling.AbstractModel;
import Engine.ModelHandeling.BasicModel;
import Engine.ModelHandeling.BasicModelRenderer;
import Engine.ModelHandeling.ModelList;
import Engine.ModelHandeling.TexturedModel;
import Engine.ModelHandeling.TexturedModelRenderer;
import Engine.OptionManager.EngineOptions;
import Engine.OptionManager.OptionHandler;

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
	private static long window;
	
	/** A renderer for basic models. 
	 */
	private BasicModelRenderer basicModelRenderer;
	/** A renderer for Textured models.
	 */
	private TexturedModelRenderer texturedModelRenderer;
	/** A renderer for basic models. 
	 */
	private BasicEntityRenderer basicEntityRenderer;
	/** A renderer for Textured models.
	 */
	private TexturedEntityRenderer texturedEntityRenderer;
	
	/** A loader for the window. 
	 */
	private WindowLoader windowLoader;
	
	/** The currently loaded shader.
	 */
	private AbstractShader currentShader;
	
	/** A shader for BasicModels.
	 */
	private BasicModelShader basicModelShader;
	/** A shader for TexturedModels.
	 */
	private TexturedModelShader texturedModelShader;
	/** A shader for BasicModels.
	 */
	private BasicEntityShader basicEntityShader;
	/** A shader for TexturedModels.
	 */
	private TexturedEntityShader texturedEntityShader;
	
	/** A list of models to render on startup.
	 */
	private ModelList modelList;

	/** A list of entities to render on startup.
	 */
	private EntityList entityList;

	private AbstractCamera camera;
	
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
		entityList = object.getEntityLists();
		
		//setup the window with the window loader. 
		window = windowLoader.setupWindow(keyboardHandler);
		
		camera = object.getCamera();
		
		glfwMakeContextCurrent(window);
		
		//prepare GL 
		GL.createCapabilities();
		
		//Setup Renderers
		basicModelRenderer = new BasicModelRenderer();
		texturedModelRenderer = new TexturedModelRenderer();
		basicEntityRenderer = new BasicEntityRenderer();
		texturedEntityRenderer = new TexturedEntityRenderer();
		
		//create shaders
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
			System.out.println("\n[DEBUG]: Loading shader files: ");
		
		basicModelShader = object.getBasicModelShader();
		texturedModelShader = object.getTexturedModelShader();
		basicEntityShader = object.getBasicEntityShader();
		texturedEntityShader = object.getTexturedEntityShader();
		
		System.out.println();
		
		//check if shaders exist.
		if(basicModelShader == null || texturedModelShader == null || basicEntityShader == null || texturedEntityShader == null) {
			ExceptionThrower.throwException(new ShaderNotFoundException(basicModelShader, texturedModelShader, basicEntityShader, texturedEntityShader));
		}
		
		//Setup Shaders
		basicModelShader.setupShader();
		texturedModelShader.setupShader();
		basicEntityShader.setupShader();
		texturedEntityShader.setupShader();
		
		basicEntityRenderer.setup(basicEntityShader);
		texturedEntityRenderer.setup(texturedEntityShader);
		
		//convert ModelStructures to models
		modelList.ConvertToModels();
		entityList.convertToEntities();
		
		//Show the window.
		GL11.glEnable(GL11.GL_DEPTH_TEST);  
		glfwShowWindow(window);
		
	}
	
	/** Update the screen to the next frame. 
	 */
	public void updateDisplay() {
		ArrayList<AbstractModel> modelList;
		ArrayList<AbstractEntity> entityList;
		//prepare the model renderers.
		basicModelRenderer.prepare();
		texturedModelRenderer.prepare();
		basicEntityRenderer.prepare();
		texturedEntityRenderer.prepare();

		camera.move();
		
		basicEntityRenderer.loadCamera(basicEntityShader, camera);
		texturedEntityRenderer.loadCamera(texturedEntityShader, camera);
		
		//handle basic models. 
		modelList = this.modelList.getModels();
		entityList = this.entityList.getEntities();
		
		//scroll through models.
		for (AbstractModel model : modelList) {
			if(model instanceof BasicModel) {
				currentShader = basicModelShader;
				//Start shader program.
				currentShader.start();
				//render the model.
				basicModelRenderer.render((BasicModel)model);
				//Stop shader program.
				currentShader.stop();
			}
			if (model instanceof TexturedModel) {
				//handle basic models. 
				currentShader = texturedModelShader;
				//Start shader program.
				currentShader.start();
				//render the model.
				texturedModelRenderer.render((TexturedModel)model);
				//Stop shader program.
				currentShader.stop();
			}
		}
		
		for (AbstractEntity entity : entityList) {
			if(entity.getModel() instanceof BasicModel) {
				currentShader = basicEntityShader;
				//Start shader program.
				currentShader.start();
				//render the model.
				basicEntityRenderer.render(entity, currentShader, camera);
				//Stop shader program.
				currentShader.stop();
			}
			if (entity.getModel() instanceof TexturedModel) {
				//handle basic models. 
				currentShader = texturedEntityShader;
				//Start shader program.
				currentShader.start();
				//render the model.
				texturedEntityRenderer.render(entity, currentShader, camera);
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
		glfwPollEvents();
		for(AbstractEntity entity : entityList.getEntities())
			entity.update();
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

	public static int getHeight() {
		IntBuffer w = BufferUtils.createIntBuffer(4);
		IntBuffer h = BufferUtils.createIntBuffer(4);
		glfwGetWindowSize(window, w, h);
		return h.get(0);
	}
	
	public static int getWidth() {
		IntBuffer w = BufferUtils.createIntBuffer(4);
		IntBuffer h = BufferUtils.createIntBuffer(4);
		glfwGetWindowSize(window, w, h);
		return w.get(0);
	}
}
