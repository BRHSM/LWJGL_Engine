package Engine.Data.EntityHandeling;

import org.lwjglx.util.vector.Vector3f;

import Engine.Data.ModelHandeling.AbstractModel;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Data.OptionManager.RuntimeOptions;
import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.OptionDisabledButStillUsedException;
/** Class used to represent an entity. This allows a model to be transposed using 
 *  a position and a rotation value for each axes as well as a scale value which
 *  scales the model in all axis.
 *  
 *  This basicEntity can use any model which extends the AbstractModel class.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractModel
 *
 */
public class AbstractEntity {
	
	/** The model to load in the entity.
	 */
	private AbstractModel model;
	/** A vector which represents the position transformation of the model.
	 */
	private Vector3f position;
	/** The X rotation of the model.
	 */
	private float rx;
	/** The Y rotation of the model.
	 */
	private float ry;
	/** The Z rotation of the model.
	 */
	private float rz;
	/** The scale of the model.
	 */
	private float scale;
	
	private BasicEntityModifier modifier;
	/** Create a new AbstractEntity.
	 * 
	 * @param model The model
	 * @param position The position transformation.
	 * @param rx The X rotation.
	 * @param ry The Y rotation.
	 * @param rz The Z rotation.
	 * @param scale the scale transformation.
	 */
	public AbstractEntity(AbstractModel model, Vector3f position, float rx, float ry, float rz, float scale, BasicEntityModifier modifier) {
		this.model = model;
		this.position = position;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.scale = scale;
		this.modifier = modifier;
		if(position.z != 0) {
			OptionHandler.setProperty(RuntimeOptions.USESPROJECTIONMATRIX_KEY, OptionHandler.RUNTIME_OPTIONS_ID, "true");
			if(OptionHandler.getProperty(EngineOptions.SHADERAUTOSELECT_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true") && OptionHandler.getProperty(EngineOptions.SHADERUSECUSTOM_KEY, OptionHandler.ENGINE_OPTION_ID).equals("false"))
				if(OptionHandler.getProperty(GraphicOptions.USEPROJECTIONMARTRIX_KEY, OptionHandler.ENGINE_OPTION_ID).equals("false"))
					ExceptionThrower.throwException(new OptionDisabledButStillUsedException());
		}
		
	}
	
	/** Update the entity.
	 */
	public void update() {
		increasePosition(modifier.getDposition().getX(),modifier.getDposition().getY(),modifier.getDposition().getZ());
		increaseRotation(modifier.getDrx(), modifier.getDry(), modifier.getDrz());
		increaseScale(modifier.getDscale());
	}
	
	/** Increase the position transformation of the model. (Negative values decrease the values).
	 * 
	 * @param dx The X transformation change.
	 * @param dy The Y transformation change.
	 * @param dz The Z transformation change.
	 */
	private void increasePosition(float dx, float dy, float dz) {
		position.x += dx;
		position.y += dy;
		position.z += dz;
		
	}
	
	/** Increase the rotation of the model. (Negative values decrease the values).
	 * 
	 * @param drx The X rotation change.
	 * @param dry The Y rotation change.
	 * @param drz The Z rotation change.
	 */
	private void increaseRotation(float drx, float dry, float drz) {
		rx += drx;
		ry += dry;
		rz += drz;
	}
	
	/** Increase the scale of the model. (Negative values decrease the values).
	 * 
	 * @param dscale The scale change.
	 */
	private void increaseScale(float dscale) {
		scale += dscale;
	}
	
	/** Get the model.
	 * 
	 * @return The model.
	 */
	public AbstractModel getModel() {
		return model;
	}
	
	/** Get the position.
	 * 
	 * @return The position vector.
	 */
	public Vector3f getPosition() {
		return position;
	}
	
	/** Get the X rotation.
	 * 
	 * @return The X rotation.
	 */
	public float getRx() {
		return rx;
	}
	
	/** Get the Y rotation.
	 * 
	 * @return The Y rotation.
	 */
	public float getRy() {
		return ry;
	}
	
	/** Get the Z rotation.
	 * 
	 * @return The Z rotation.
	 */
	public float getRz() {
		return rz;
	}
	
	/** Get the scale.
	 * 
	 * @return The scale.
	 */
	public float getScale() {
		return scale;
	}
}
