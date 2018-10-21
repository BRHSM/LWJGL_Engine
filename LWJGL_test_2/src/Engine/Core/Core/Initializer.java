package Engine.Core.Core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Util.Util.DoublePrintStream;

/** This class holds the code used to launch the engine's threads.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see RenderThread
 * @see DataObject
*/
public class Initializer {
	/** Thread object used for rendering
	*/
	private RenderThread thread;

	/** Method used to start various threads in order for the game to work.
	*/
	public void start(DataObject dataObject) {
	    FileOutputStream file;
	    if(OptionHandler.getProperty(EngineOptions.DEBUGLOGTOFILE_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1") && OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
			try {
			    Calendar cal = Calendar.getInstance();
			    StringBuilder sb = new StringBuilder();
			    sb.append(OptionHandler.getProperty(EngineOptions.PATHDEVELOPMENTFILES_KEY, OptionHandler.ENGINE_OPTION_ID));
			    sb.append("log_" + cal.get(Calendar.DAY_OF_MONTH) + "-");
			    sb.append((cal.get(Calendar.MONTH) + 1) + "-");
			    sb.append(cal.get(Calendar.YEAR) + "_");
			    
			    sb.append(cal.get(Calendar.HOUR) + ";");
			    sb.append(cal.get(Calendar.MINUTE) + ";");
			    sb.append(cal.get(Calendar.SECOND) + ".txt");
				
				//TODO: setup log file to have correct dat/time formad and add it to the correct folder.
				file = new FileOutputStream(sb.toString());
				DoublePrintStream dps = new DoublePrintStream(file, System.out);
			    System.setOut(dps);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}	   
	    }
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
		//writeback options
		if(OptionHandler.getProperty(EngineOptions.WRITEBACKONEXIT_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
			if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
				System.out.println("[DEBUG]: Writing back options: ");
			OptionHandler.writeBackOptions();
		}
	}
}
