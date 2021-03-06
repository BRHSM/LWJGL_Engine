package Engine.Graphics.DisplayEngine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import Engine.Data.OptionManager.CurrentLanguage;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.IO.KeyboardHandeling.KeyboardHandler;
import Engine.Util.Exceptions.InternalErrorException;

/** Class with the code which is used to prepare a window for the DisplayManager to show.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class WindowLoader {
	
	private long window;
	/** Setup a window for the DisplayManager to use.
	 * 
	 * @param keyboardHandler The keyboardHandler which listens for keyboard input. 
	 * @return The address of the window
	 * @throws InternalErrorException 
	 */
	public long setupWindow(KeyboardHandler keyboardHandler) throws InternalErrorException {
		//Initialize an OptionReader and LanguageReader.
		String title = OptionHandler.getProperty(CurrentLanguage.TITLE_KEY, OptionHandler.CURRENT_LANGUAGE_ID);
		
		//Get Dimension Properties from options. 
		int width  = Integer.parseInt(OptionHandler.getProperty(GraphicOptions.WINDOWWIDTH_KEY, OptionHandler.GRAPHIC_OPTION_ID));
		int height = Integer.parseInt(OptionHandler.getProperty(GraphicOptions.WINDOWHEIGHT_KEY, OptionHandler.GRAPHIC_OPTION_ID));
		
		//set window hints (debug mode, major version, minor version, resieable).
		GLFW.glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		GLFW.glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		//Initialize the window address. 
		window = glfwCreateWindow(width,
		 		  				  height,
		 		  				  title,
		 		  				  Integer.parseInt(OptionHandler.getProperty(GraphicOptions.WINDOWFULLSCREEN_KEY, OptionHandler.GRAPHIC_OPTION_ID)),
		 		  				  0);
		//check for errors.
		if (window == NULL) {
			throw new InternalErrorException();
		}
		//success
		
		//get video mode and set window position accordingly.
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width)/2 , (vidmode.height() - height)/2);
		
		//add the keyboard listener
		glfwSetKeyCallback(window, keyboardHandler);
		
		//prepare the window for loading models.
		glfwMakeContextCurrent(window);
		
		//return the window address.
		return window;
	}

	/** Method used to cleanup the window.
	 */
	public void cleanUp() {
		
		
	}
}
