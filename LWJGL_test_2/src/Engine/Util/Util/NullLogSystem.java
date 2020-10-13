package Engine.Util.Util;

import org.newdawn.slick.util.LogSystem;
/** A class used to disable the log output of Slick. This class contains only empty methods and is therefore not documented.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class NullLogSystem implements LogSystem {

	@Override
	public void debug(String arg0) {
		return;
	}

	@Override
	public void error(Throwable arg0) {
		return;
		
	}

	@Override
	public void error(String arg0) {
		return;
		
	}

	@Override
	public void error(String arg0, Throwable arg1) {
		return;
		
	}

	@Override
	public void info(String arg0) {
		return;
		
	}

	@Override
	public void warn(String arg0) {
		return;
		
	}

	@Override
	public void warn(String arg0, Throwable arg1) {
		return;
		
	}
}
