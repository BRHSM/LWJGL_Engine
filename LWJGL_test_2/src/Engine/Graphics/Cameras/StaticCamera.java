package Engine.Graphics.Cameras;
/** Instance of a camera without movement. 
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
}
