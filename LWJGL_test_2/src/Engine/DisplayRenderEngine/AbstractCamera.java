package Engine.DisplayRenderEngine;

import org.lwjglx.input.Keyboard;
import org.lwjglx.util.vector.Vector3f;

public abstract class AbstractCamera {
     
    protected Vector3f position = new Vector3f(0,0,0);
    protected float pitch;
    protected float yaw;
    protected float roll;
     
    public AbstractCamera(){}
     
    public abstract void move();
 
    public Vector3f getPosition() {
        return position;
    }
 
    public float getPitch() {
        return pitch;
    }
 
    public float getYaw() {
        return yaw;
    }
 
    public float getRoll() {
        return roll;
    }
     
     
 
}
