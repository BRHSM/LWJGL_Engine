package InputHandeling;

import static org.lwjgl.glfw.GLFW.*;

/** Class which handles key events.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class KeyStrokeHandler {
	
	/** Boolean used to check if key has been pressed the frame before. 
	 */
	private boolean space_key = false;
	
	/** Triggered every game tick. Keyboard event handeling should happen here.
	 *  (Currently holds 2 examples of single press detection and continues detection.)
	 */
	public void update() {
		//Single press detection of spacebar.
		if(KeyboardHandler.keys[GLFW_KEY_SPACE]) {
			if(!space_key)
				System.out.println("test");
			space_key = true;
			
		} else {
			space_key = false;
		}
		//continues press detection of A key.
		if(KeyboardHandler.keys[GLFW_KEY_A]) {
			System.out.println("A");
		} 
	}
}
