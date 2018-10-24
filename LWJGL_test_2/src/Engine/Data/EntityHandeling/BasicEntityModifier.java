package Engine.Data.EntityHandeling;

import org.lwjglx.util.vector.Vector3f;

import Engine.Data.ModelHandeling.AbstractModel;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Data.OptionManager.RuntimeOptions;
import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.OptionDisabledButStillUsedException;

/** 
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractModel
 *
 */
public class BasicEntityModifier {
	
	/** the change in position on every frame.
	 */
	private Vector3f dposition;
	/** the change in X rotation on every frame.
	 */
	private float drx;
	/** the change in Y rotation on every frame.
	 */
	private float dry;
	/** the change in Z rotation on every frame.
	 */
	private float drz;
	/** the change in scale on every frame.
	 */
	private float dscale;
	
	/** Create a new BasicEntityModifier.
	 * 
	 * @param dposition The change in position for each frame.
	 * @param drx The change in X rotation for each frame.
	 * @param dry The change in Y rotation for each frame.
	 * @param drz The change in Z rotation for each frame.
	 * @param dscale The change in scale for each frame.
	 */
	public BasicEntityModifier(Vector3f dposition, float drx, float dry, float drz, float dscale) {
		this.dposition = dposition;
		this.drx = drx;
		this.dry = dry;
		this.drz = drz;
		this.dscale = dscale;
		if(dposition.z != 0) {
			OptionHandler.setProperty(RuntimeOptions.USESPROJECTIONMATRIX_KEY, OptionHandler.RUNTIME_OPTIONS_ID, "true");
			if(OptionHandler.getProperty(EngineOptions.SHADERAUTOSELECT_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true") && OptionHandler.getProperty(EngineOptions.SHADERUSECUSTOM_KEY, OptionHandler.ENGINE_OPTION_ID).equals("false"))
				if(OptionHandler.getProperty(GraphicOptions.USEPROJECTIONMARTRIX_KEY, OptionHandler.ENGINE_OPTION_ID).equals("false"))
					ExceptionThrower.throwException(new OptionDisabledButStillUsedException());
		}
	}

	/** Get the change in position.
	 * 
	 * @return The change in position for each frame.
	 */
	public Vector3f getDposition() {
		return dposition;
	}

	/** Get the change in X rotation.
	 * 
	 * @return The change in X rotation for each frame.
	 */
	public float getDrx() {
		return drx;
	}

	/** Get the change in Y rotation.
	 * 
	 * @return The change in Y rotation for each frame.
	 */
	public float getDry() {
		return dry;
	}

	/** Get the change in Z rotation.
	 * 
	 * @return The change in Z rotation for each frame.
	 */
	public float getDrz() {
		return drz;
	}

	/** Get the change in scale.
	 * 
	 * @return The change in scale for each frame.
	 */
	public float getDscale() {
		return dscale;
	}

	@Override
	/** Get the classString.
	 * 
	 * @return A string describing the modifier.
	 */
	public String toString() {
		return "BasicEntityModifier [dposition=" + dposition + ", drx=" + drx + ", dry=" + dry + ", drz=" + drz + ", dscale=" + dscale + "]";
	}
	
}
