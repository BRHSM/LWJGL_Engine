package Engine.Graphics.Cameras;

import org.lwjglx.input.Keyboard;
import org.lwjglx.util.vector.Vector3f;
/** Class which handels all properties of the currently loaded language file.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractCamera {
    /** The position of the camera. 
     */
    protected Vector3f position = new Vector3f(0,0,0);
    /** The pitch of the camera. 
     */
    protected float pitch;
    /** The yaw of the camera. 
     */
    protected float yaw;
    /** The roll of the camera. 
     */
    protected float roll;
    
    /** Create a new AbstractCamera.
     */
    public AbstractCamera(){}
    
    /** Move the camera.
     */
    public abstract void move();
    
    /** Get the camera position.
     * 
     * @return The camera position.
     */
    public Vector3f getPosition() {
        return position;
    }
 
    /** Get the camera pitch.
     * 
     * @return The camera pitch.
     */
    public float getPitch() {
        return pitch;
    }
 
    /** Get the camera yaw.
     * 
     * @return The camera yaw.
     */
    public float getYaw() {
        return yaw;
    }
 
    /** Get the camera roll.
     * 
     * @return The camera roll.
     */
    public float getRoll() {
        return roll;
    }
     
     
 
}
