package Engine.Graphics.Cameras;
/** The default camera which does not support viewMatrices. 
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractCamera
 */
public class StaticCamera extends AbstractCamera{
	@Override
    /** Move the camera. (In this case does nothing).
     */
	public void move() {}
	
	@Override
	/** Get a class description
	 * 
	 * @return The string describing the class
	 */
	public String toString() {
		return "StaticCamera [position=" + position + ", pitch=" + pitch + ", yaw=" + yaw + ", roll=" + roll + "]";
	}
}
