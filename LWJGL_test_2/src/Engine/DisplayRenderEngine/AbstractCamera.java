package Engine.DisplayRenderEngine;

import org.lwjglx.input.Keyboard;
import org.lwjglx.util.vector.Vector3f;

public abstract class AbstractCamera {
     
    private Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;
     
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
