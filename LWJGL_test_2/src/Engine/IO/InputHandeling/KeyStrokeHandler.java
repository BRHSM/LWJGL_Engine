package Engine.IO.InputHandeling;

/** Class which handles key events.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class KeyStrokeHandler {
	public static boolean isKeyDown(int key) {
		if(KeyboardHandler.keys[key])
			return true;
		return false;
	}
}
