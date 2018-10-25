package Engine.Data.EntityHandeling;

import org.lwjglx.util.vector.Matrix4f;
import org.lwjglx.util.vector.Vector3f;

import Engine.Data.ModelHandeling.AbstractModel;
import Engine.Data.ModelHandeling.AbstractModelStructure;
import Engine.Util.Math.MatrixMaths;
/** This class holds basic information to generate an entity.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class AbstractEntityStructure {
	/** The ModelStructure containing model information.
	 */
	private AbstractModelStructure modelStructure;
	/** position of the model.
	 */
	private Vector3f position;
	/** X rotation of the model.
	 */
	private float rx;
	/** Y rotation of the model.
	 */
	private float ry;
	/** Z rotation of the model.
	 */
	private float rz;
	/** Scale of the model.
	 */
	private float scale;
	/** The message identifier of the entity
	 */
	private int id;
	/** The modifier of the model.
	 */
	private BasicEntityModifier modifier;
	
	/** Create an AbstractEntityStructure.
	 * 
	 * @param modelStructure The modelstructure.
	 * @param position The position of the model.
	 * @param rx The x rotation of the model.
	 * @param ry The y rotation of the model.
	 * @param rz The z rotation of the model.
	 * @param scale The scale of the model.
	 */
	public AbstractEntityStructure(AbstractModelStructure modelStructure, Vector3f position, float rx, float ry, float rz, float scale, int id) {
		this.modelStructure = modelStructure;
		this.position = position;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.scale = scale;
		this.id = id;
		modifier = new BasicEntityModifier(new Vector3f(0,0,0),0,0,0,0);
	}
	
	/** Get the ModelStructure.
	 * 
	 * @return The ModelStructure.
	 */
	public AbstractModelStructure getModelStructure() {
		return modelStructure;
	}

	/** Get the Position.
	 * 
	 * @return The Position.
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

	/** Get the Scale.
	 * 
	 * @return The Scale.
	 */
	public float getScale() {
		return scale;
	}
	
	/** Get the ID.
	 * 
	 * @return The ID.
	 */
	public int getID() {
		return id;
	}
	
	/** Set the EntityModifier.
	 * 
	 * @param modifier The EntityModifier.
	 */
	public void setEntityModifier(BasicEntityModifier modifier) {
		this.modifier = modifier;
	}
	
	/** Get the EntityModifier.
	 * 
	 * @return The EntityModifier.
	 */
	public BasicEntityModifier getEntityModifier() {
		return modifier;
	}
}
