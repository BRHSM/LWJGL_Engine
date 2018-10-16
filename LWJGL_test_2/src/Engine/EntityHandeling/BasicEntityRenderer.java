package Engine.EntityHandeling;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjglx.util.vector.Matrix4f;

import Engine.Exceptions.ExceptionThrower;
import Engine.Exceptions.ModelInvalidException;
import Engine.Exceptions.ShaderIncompatableException;
import Engine.GraphicsEngine.AbstractShader;
import Engine.GraphicsEngine.BasicEntityShader;
import Engine.Math.MatrixMaths;
import Engine.ModelHandeling.BasicModel;

/** Class used to render an AbstractEntity with a BasicModel as it's model.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see BasicModel
 */
public class BasicEntityRenderer extends AbstractEntityRenderer {
	/** RPrepare the renderer for rendering the entity.
	 */
	public void prepare() {
		GL11.glClearColor(0,0,0,1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	/** Render a textured model to the screen.
	 *
	 * @param model the model to render.
	 */
	
	// create for each model.
	public void render (AbstractEntity entity, AbstractShader shader) {
		if(shader instanceof BasicEntityShader) {
			if(entity.getModel() instanceof BasicModel) {
				BasicModel model = (BasicModel)entity.getModel();
				GL30.glBindVertexArray(model.getVaoID());
				GL20.glEnableVertexAttribArray(0);
				Matrix4f transformationMatrix = MatrixMaths.createTransformationMatrix(entity.getPosition(), entity.getRx(), entity.getRy(), entity.getRz(), entity.getScale());
				((BasicEntityShader)shader).loadTransformationMatrix(transformationMatrix);
				GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, model.getVaoID());
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
				GL20.glDisableVertexAttribArray(0);
				GL30.glBindVertexArray(0);
			} else {
				ExceptionThrower.throwException(new ModelInvalidException());
			}
		} else {
			ExceptionThrower.throwException(new ShaderIncompatableException(shader.toString()));
		}
	}
}