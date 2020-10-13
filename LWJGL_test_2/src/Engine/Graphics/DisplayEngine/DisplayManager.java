package Engine.Graphics.DisplayEngine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import Engine.Core.Core.DataObject;
import Engine.Data.EntityHandeling.AbstractEntity;
import Engine.Data.EntityHandeling.AbstractEntityRenderer;
import Engine.Data.EntityHandeling.BasicEntityRenderer;
import Engine.Data.EntityHandeling.EntityList;
import Engine.Data.EntityHandeling.TexturedEntityRenderer;
import Engine.Data.ModelHandeling.AbstractModel;
import Engine.Data.ModelHandeling.BasicModel;
import Engine.Data.ModelHandeling.BasicModelRenderer;
import Engine.Data.ModelHandeling.ModelList;
import Engine.Data.ModelHandeling.TexturedModel;
import Engine.Data.ModelHandeling.TexturedModelRenderer;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Graphics.Cameras.AbstractCamera;
import Engine.Graphics.Shaders.AbstractShader;
import Engine.Graphics.Shaders.BasicEntityShader;
import Engine.Graphics.Shaders.BasicModelShader;
import Engine.Graphics.Shaders.ShaderChooser;
import Engine.Graphics.Shaders.TexturedEntityShader;
import Engine.Graphics.Shaders.TexturedModelShader;
import Engine.IO.KeyboardHandeling.KeyboardHandler;
import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.InternalErrorException;
import Engine.Util.Exceptions.ShaderNotFoundException;

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
	/** A renderer for entities.
	 */
	private AbstractEntityRenderer abstractEntityRenderer;
	
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
	private BasicEntityShader entityShader;
	
	/** A list of models to render on startup.
	 */
	private ModelList modelList;

	/** A list of entities to render on startup.
	 */
	private EntityList entityList;

	/** The loaded camera.
	 */
	private AbstractCamera camera;
	
	/** Create a prepared window and add the models to it. After the window is prepared and
	 *  populated, It's shown to the screen
	 * 
	 *  @param keyboarHandler The listener for keyboard events
	 * @throws InternalErrorException 
	 */
	public void createDisplay(KeyboardHandler keyboardHandler, DataObject object) throws InternalErrorException {
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
		abstractEntityRenderer = new BasicEntityRenderer();
		
		//create shaders
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")){
			System.out.println("[DEBUG]: OS name: " + System.getProperty("os.name"));
		    System.out.println("[DEBUG]: OS version: " + System.getProperty("os.version"));
		    System.out.println("[DEBUG]: LWJGL version: " + org.lwjgl.Sys.getVersion());
		    System.out.println("[DEBUG]: OpenGL version: " + glGetString(GL_VERSION));
			
			System.out.println("\n[DEBUG]: Loading shader files: ");
			
		}
		
		basicModelShader = object.getBasicModelShader();
		texturedModelShader = object.getTexturedModelShader();
		entityShader = object.getEntityShader();
		
		//check if shaders exist.
		if(basicModelShader == null || texturedModelShader == null || entityShader == null ) {
			ExceptionThrower.throwException(new ShaderNotFoundException(basicModelShader, texturedModelShader, entityShader));
		}
		
		//Setup Shaders
		basicModelShader.setupShader(OptionHandler.getProperty(EngineOptions.SUBPATHBASICMODELSHADER_KEY, OptionHandler.ENGINE_OPTION_ID), ShaderChooser.getUsableShaderFileName(ShaderChooser.VERTEX_TYPE, true), ShaderChooser.getUsableShaderFileName(ShaderChooser.FRAGMENT_TYPE, true));
		texturedModelShader.setupShader(OptionHandler.getProperty(EngineOptions.SUBPATHTEXTUREDMODELSHADER_KEY, OptionHandler.ENGINE_OPTION_ID), ShaderChooser.getUsableShaderFileName(ShaderChooser.VERTEX_TYPE, true), ShaderChooser.getUsableShaderFileName(ShaderChooser.FRAGMENT_TYPE, true));
		entityShader.setupShader(OptionHandler.getProperty(EngineOptions.SUBPATHBASICENTITYSHADER_KEY, OptionHandler.ENGINE_OPTION_ID), ShaderChooser.getUsableShaderFileName(ShaderChooser.VERTEX_TYPE, false), ShaderChooser.getUsableShaderFileName(ShaderChooser.FRAGMENT_TYPE, false), OptionHandler.getProperty(EngineOptions.SUBPATHTEXTUREDENTITYSHADER_KEY, OptionHandler.ENGINE_OPTION_ID));
		
		abstractEntityRenderer.setup(entityShader, 0);
		abstractEntityRenderer.setup(entityShader, 1);
		
		//convert ModelStructures to models
		modelList.ConvertToModels();
		entityList.convertToEntities();
		
		//Show the window. 
		glfwShowWindow(window);
		
	}
	
	/** Update the screen to the next frame. 
	 */
	public void updateDisplay() {
		ArrayList<AbstractModel> modelList;
		ArrayList<AbstractEntity> entityList;
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0,0,0,1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		camera.move();
		
		abstractEntityRenderer.loadCamera(entityShader, camera, 0);
		abstractEntityRenderer.loadCamera(entityShader, camera, 1);
		
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
			currentShader = entityShader;
			//Start shader program.
			currentShader.start();
			//render the model.
			abstractEntityRenderer.render(entity, (BasicEntityShader)currentShader, camera);
			//Stop shader program.
			currentShader.stop();
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

	/** Get the height of the display
	 * 
	 * @return The height of the display.
	 */
	public static int getHeight() {
		IntBuffer w = BufferUtils.createIntBuffer(4);
		IntBuffer h = BufferUtils.createIntBuffer(4);
		glfwGetWindowSize(window, w, h);
		return h.get(0);
	}
	
	/** Get the width of the display
	 * 
	 * @return The width of the display.
	 */
	public static int getWidth() {
		IntBuffer w = BufferUtils.createIntBuffer(4);
		IntBuffer h = BufferUtils.createIntBuffer(4);
		glfwGetWindowSize(window, w, h);
		return w.get(0);
	}
}
