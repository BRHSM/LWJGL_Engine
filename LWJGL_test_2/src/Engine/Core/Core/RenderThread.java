package Engine.Core.Core;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Graphics.DisplayEngine.DisplayManager;
import Engine.IO.KeyboardHandeling.KeyStrokeHandler;
import Engine.IO.KeyboardHandeling.KeyboardHandler;
import Engine.Util.Exceptions.InternalErrorException;
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
	private String isDebug;
	
	/** integer representation of boolean used to check if game should report average
	 *  load time or a load time for each frame. 
	*/
	private String avgLoadtime;
	
	/** Creates an new empty RenderThread.
	 */
	public RenderThread(DataObject object) {
		super();
		this.object = object;
		isDebug = OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID);
		avgLoadtime = OptionHandler.getProperty(EngineOptions.DEBUGAVGLOADTIME_KEY, OptionHandler.ENGINE_OPTION_ID);
	}
	
	/** Method with holds main game loop. Executes the game loop untill it's told to stop. 
	 */
	public void run() {
		//call Init
		try {
			init();
			
			if(isDebug.equals("true"))
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
		            if(isDebug.equals("true")) {
		            	if(avgLoadtime.equals("true")) {
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
				update(object.getUpdaterInctance());
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
				if(isDebug.equals("true")) {
					if(avgLoadtime.equals("true")) {
						//show avarage load time per second.
						loadtimePerSec = loadtimePerSec + (double)Math.round(delta * 100000d) / 100000d;
					}else {
						// Show loadtime for each frame
						System.out.println("[DEBUG]: LoadTIme: " + (double)Math.round(delta * 100000d) / 100000d);
					}
					if(OptionHandler.getProperty(EngineOptions.DEBUGTOTAVGLOADTIME_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
					//add to total load time.
					totalLoadTime = totalLoadTime + (double)Math.round(delta * 100000d) / 100000d;
					}
				}
					
				//end gameloop on close. 
				if(displayManager.shouldClose()) {
					//end the game.
					running = false;
					if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true"))
						if(OptionHandler.getProperty(EngineOptions.DEBUGTOTAVGLOADTIME_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true"))
							System.out.println("\n[DEBUG]: Game ran with a total avarage load time of: " + Math.round((totalLoadTime / new Double(cicles)) * 100000d) / 100000d + " over " + cicles + " cicles\n");
				}
			}
		} catch (InternalErrorException e1) { }
		
		//clean everything. 
		end();
	}
	
	/** Initializing before the game loop.
	 * @throws InternalErrorException 
	*/
	private void init() throws InternalErrorException {

		keyStrokeHandeler = new KeyStrokeHandler();
		displayManager = new DisplayManager();
		displayManager.createDisplay(new KeyboardHandler(), object);
		if(isDebug.equals("true")) {		    
			System.out.println("[DEBUG]: Language file loaded: " + OptionHandler.getProperty(EngineOptions.PATHLANGUAGEFILE_KEY, OptionHandler.ENGINE_OPTION_ID) +  OptionHandler.getProperty(EngineOptions.MAINLANGUAGE_KEY, OptionHandler.ENGINE_OPTION_ID));
		}
	}

	/** Update variables before rendering. 
	 * @param abstractUpdater 
	*/
	private void update(AbstractUpdater abstractUpdater) {
		displayManager.updateFromThread();
		abstractUpdater.update();
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
