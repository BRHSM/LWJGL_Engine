package ModelHandeling;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/** Class used to rendering textured models to the screen.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class TexturedModelRenderer {
	
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
	public void render (TexturedModel texturedModel) {
		TexturedModel model = texturedModel;
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, model.getVaoID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
