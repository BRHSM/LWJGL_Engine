package Engine.EntityHandeling;

import Engine.DisplayRenderEngine.AbstractCamera;
import Engine.GraphicsEngine.AbstractShader;
/** An abstract renderer class for entities.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractShader
 *
 */
public abstract class AbstractEntityRenderer {
	/** Prepare the screen for rendering.
	 */
	public abstract void prepare();
	/** Render an entity to the screen using a given shader.
	 * 
	 * @param entity The entity to render.
	 * @param shader The shader to use for rendering.
	 */
	public abstract void render(AbstractEntity entity, AbstractShader shader, AbstractCamera camera);
}
