package Core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import OptionManager.EngineOptions;
import OptionManager.OptionHandler;
import UtilClasses.DoublePrintStream;

/** This class holds the code used to launch the engine's threads.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see RenderThread
*/
public class Initializer {
	/** Thread object used for rendering
	*/
	private RenderThread thread;

	/** Method used to start various threads in order for the game to work.
	*/
	public void start(DataObject dataObject) {
	    FileOutputStream file;
	    if(OptionHandler.getProperty(EngineOptions.DEBUGLOGTOFILE, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
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
		if(OptionHandler.getProperty(EngineOptions.WRITEBACKONEXIT_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
			System.out.println("[DEBUG]: Writing back options");
			OptionHandler.writeBackOptions();
		}
	}
}
