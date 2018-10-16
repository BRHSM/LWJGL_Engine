package Engine.EntityHandeling;

import org.lwjglx.util.vector.Vector3f;

import Engine.ModelHandeling.AbstractModelStructure;

public class AbstractEntityStructure {
	private AbstractModelStructure modelStructure;
	private Vector3f position;
	private float rx;
	private float ry;
	private float rz;
	private float scale;
	
	public AbstractEntityStructure(AbstractModelStructure modelStructure, Vector3f position, float rx, float ry, float rz, float scale) {
		this.modelStructure = modelStructure;
		this.position = position;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.scale = scale;
	}

	public AbstractModelStructure getModelStructure() {
		return modelStructure;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getRx() {
		return rx;
	}

	public float getRy() {
		return ry;
	}

	public float getRz() {
		return rz;
	}

	public float getScale() {
		return scale;
	}
}
