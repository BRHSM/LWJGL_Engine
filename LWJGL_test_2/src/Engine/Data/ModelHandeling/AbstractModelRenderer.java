package Engine.Data.ModelHandeling;

/** An abstract renderer for models.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public abstract class AbstractModelRenderer {
	/** Prepare the model to render.
	 */
	public abstract void prepare();
	/** Render a model to the display.
	 * 
	 * @param model The model to render.
	 */
	public abstract void render(AbstractModel model);
}
