package Engine.Data.ModelHandeling;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.InternalErrorException;

/** Class used to rendering textured models to the screen.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class BasicModelRenderer extends AbstractModelRenderer{
	
	/** prepare the window for rendering.
	 */
	public void prepare() {
		GL11.glClearColor(0,0,0,1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	/** Render a textured model to the screen.
	 *
	 * @param model the model to render.
	 */
	public void render (AbstractModel modelBasic) {
		if(modelBasic instanceof BasicModel) {
			BasicModel model = (BasicModel)modelBasic;
			GL30.glBindVertexArray(model.getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, model.getVaoID());
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			GL20.glDisableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
		} else {
			ExceptionThrower.throwException(new InternalErrorException());
		}
	}
}
