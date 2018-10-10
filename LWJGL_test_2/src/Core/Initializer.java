package Core;

import Exceptions.ExceptionThrower;
import Exceptions.InternalErrorException;

/** This class holds the code used to launch the engine's threads.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class Initializer {
	/** Thread object used for rendering
	*/
	private RenderThread thread;

	/** Method used to start various threads in order for the game to work.
	*/
	public void start(DataObject dataObject) {
		thread = new RenderThread(dataObject);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stop();
	}
	/** Method called when the game stops(naturally). 
	 * 
	 */
	public void stop() {
		
	}
}
