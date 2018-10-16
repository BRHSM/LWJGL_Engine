package Engine.InputHandeling;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

/** Class which contains the listener for keyboard events.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class KeyboardHandler extends GLFWKeyCallback{

	/** Array of booleans which are true if key is pressed, false otherwise.
	 */
	public static boolean[] keys = new boolean[256];
	
	/**
	 * The listener behavior if a key press is detected.
	 */
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key]= (action == GLFW.GLFW_RELEASE) ? false : true;
	}
}
