package Engine.IO.KeyboardHandeling;

/** Class which handles key events.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class KeyStrokeHandler {
	/** Function used to see if a key is pressed.
	 * 
	 * @param key the key you want to check
	 * @return true if the key is pressed, false otherwise.
	 */
	public static boolean isKeyDown(int key) {
		if(KeyboardHandler.keys[key])
			return true;
		return false;
	}
}
