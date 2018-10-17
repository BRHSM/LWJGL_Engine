package Engine.Core;

import Engine.DisplayRenderEngine.DisplayManager;
import Engine.InputHandeling.KeyStrokeHandler;
import Engine.InputHandeling.KeyboardHandler;
import Engine.OptionManager.EngineOptions;
import Engine.OptionManager.GraphicOptions;
import Engine.OptionManager.OptionHandler;
/** Thread which handles the rendering of the screen.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see DisplayManager
 * @see KeyStrokeHandler
*/
public class RenderThread extends Thread{
	/** main display field which holds the main game window.
	 */
	DisplayManager displayManager;
	/** A keyStrokeHandler for handeling keyboard input.
	 */
	KeyStrokeHandler keyStrokeHandeler;
	
	/** Boolean used to check if game is running
	*/
	private Boolean running = true;
	
	/** The DataObject used for rendering the game.
	*/
	private DataObject object;
	
	/** integer representation of boolean used to check if game is in debug mode
	*/
	private int isDebug;
	
	/** integer representation of boolean used to check if game should report average
	 *  load time or a load time for each frame. 
	*/
	private int avgLoadtime;
	
	/** Creates an new empty RenderThread.
	 */
	public RenderThread(DataObject object) {
		super();
		this.object = object;
	}
	
	/** Method with holds main game loop. Executes the game loop untill it's told to stop. 
	 */
	public void run() {
		//Initialize an OptionReader
		// Memory version of "debugEnabled" in GraphicOptions.cfg
		isDebug = Integer.parseInt(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID));
		avgLoadtime = Integer.parseInt(OptionHandler.getProperty(EngineOptions.DEBUGAVGLOADTIME_KEY, OptionHandler.ENGINE_OPTION_ID));
		//call Init
		init();
		
		if(isDebug == 1)
			System.out.println("\n[DEBUG]: Starting main loop.");
		
		//Initialize Timevariable
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = Integer.parseInt(OptionHandler.getProperty(GraphicOptions.FRAMECAP_KEY, OptionHandler.GRAPHIC_OPTION_ID));
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		long gameTime;
		long lastFpsTime = 0;
		long now;
		long updateLength;
		long cicles = 0;
		int fps = 0;
		double loadtimePerSec = 0d;
		double totalLoadTime = 0d;
		double delta;
		 
		//Start Game Loop
		while(running) {
			//calculate current time. 
			now = System.nanoTime();
	        updateLength = now - lastLoopTime;
	        lastLoopTime = now;
	        delta = updateLength / ((double)OPTIMAL_TIME);
			
	        //calculate last FPS time. 
	        lastFpsTime += updateLength;
	        if(lastFpsTime >= 1000000000){
	            lastFpsTime = 0;
	            //display info if in debug mode.
	            if(isDebug == 1) {
	            	if(avgLoadtime == 1) {
	            		//calculate and show avarage load time
	            		System.out.println("[DEBUG]: Avarage load time: " + (double)Math.round(loadtimePerSec / fps * 100000d) / 100000d);
	            	}
	            	//show FPS
	            	System.out.println("[DEBUG]: fps: " + fps);
	            }
	            
	            //Reset load time avarage. 
	            loadtimePerSec = 0;
	            //set fps back to 0 for new second.
	            fps = 0;
	        }
	        //update the game and render the screen. 
			update();
			render();
			cicles++;
			
			try{
				//add 1 frame to fps.
				fps++;
				//calculate time before next time and sleep. 
	            gameTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
	            Thread.sleep(gameTime);
	        }catch(Exception e){}
			
			//Show\calculate Loadtime if in debug mode.
			if(isDebug == 1) {
				if(avgLoadtime == 1) {
					//show avarage load time per second.
					loadtimePerSec = loadtimePerSec + (double)Math.round(delta * 100000d) / 100000d;
				}else {
					// Show loadtime for each frame
					System.out.println("[DEBUG]: LoadTIme: " + (double)Math.round(delta * 100000d) / 100000d);
				}
				if(OptionHandler.getProperty(EngineOptions.DEBUGTOTAVGLOADTIME_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
				//add to total load time.
				totalLoadTime = totalLoadTime + (double)Math.round(delta * 100000d) / 100000d;
				}
			}
				
			//end gameloop on close. 
			if(displayManager.shouldClose()) {
				//end the game.
				running = false;
				if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
					if(OptionHandler.getProperty(EngineOptions.DEBUGTOTAVGLOADTIME_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
						System.out.println("\n[DEBUG]: Game ran with a total avarage load time of: " + Math.round((totalLoadTime / new Double(cicles)) * 100000d) / 100000d + " over " + cicles + " cicles\n");
			}
		}
		//clean everything. 
		end();
	}
	
	/** Initializing before the game loop.
	*/
	private void init() {

		keyStrokeHandeler = new KeyStrokeHandler();
		displayManager = new DisplayManager();
		displayManager.createDisplay(new KeyboardHandler(), object);
		if(isDebug == 1) {
			//Initialize an OptionReader and LanguageReader.
			System.out.println("[DEBUG]: Language file loaded: " + OptionHandler.getProperty(EngineOptions.PATHLANGUAGEFILE_KEY, OptionHandler.ENGINE_OPTION_ID) +  OptionHandler.getProperty(EngineOptions.MAINLANGUAGE_KEY, OptionHandler.ENGINE_OPTION_ID));
		}
	}

	/** Update variables before rendering. 
	*/
	private void update() {
		//TODO: maybe?? instead of updating in this thread, updating should use a separate thread.
		keyStrokeHandeler.update();
		displayManager.updateFromThread();
	}

	/** Render the screen. 
	*/
	private void render() {
		displayManager.updateDisplay();
	}
	
	/** Stuff to do after game loops ends.  
	*/
	private void end() {
		displayManager.cancelDisplay();
		
	}
}
