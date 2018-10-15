package Tests;

import Core.DataObject;
import Core.Initializer;
import ModelHandeling.TexturedModelStructure;
/** A basic test to see if the engine works with no data provided. This is
 *  the bare minimum of code for a "game" to launch.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class Test_1 {
	
	private static Initializer init; 	
	
	/** Starts execution.
	 * @param args terminal arguments
	*/
	public static void test() {
		//Initialize the game.
		init = new Initializer();
		
		//Setup DataObject.
		DataObject object = new DataObject();
		
		//start the loop.
		init.start(object);
	}
}
