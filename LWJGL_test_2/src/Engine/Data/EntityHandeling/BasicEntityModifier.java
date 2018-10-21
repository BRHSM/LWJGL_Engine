package Engine.Data.EntityHandeling;

import org.lwjglx.util.vector.Vector3f;

public class BasicEntityModifier {
	
	private Vector3f dposition;
	private float drx;
	private float dry;
	private float drz;
	private float dscale;
	
	public BasicEntityModifier(Vector3f dposition, float drx, float dry, float drz, float dscale) {
		this.dposition = dposition;
		this.drx = drx;
		this.dry = dry;
		this.drz = drz;
		this.dscale = dscale;
	}

	public Vector3f getDposition() {
		return dposition;
	}

	public float getDrx() {
		return drx;
	}

	public float getDry() {
		return dry;
	}

	public float getDrz() {
		return drz;
	}

	public float getDscale() {
		return dscale;
	}

	@Override
	public String toString() {
		return "BasicEntityModifier [dposition=" + dposition + ", drx=" + drx + ", dry=" + dry + ", drz=" + drz + ", dscale=" + dscale + "]";
	}
	
}
