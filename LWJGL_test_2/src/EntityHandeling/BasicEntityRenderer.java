package EntityHandeling;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjglx.util.vector.Matrix4f;

import Exceptions.ExceptionThrower;
import Exceptions.ModelInvalidException;
import Exceptions.ShaderIncompatableException;
import GraphicsEngine.AbstractShader;
import GraphicsEngine.BasicShader;
import GraphicsEngine.UniformTestShader;
import Math.MatrixMaths;
import ModelHandeling.BasicModel;
import ModelHandeling.TexturedModel;

public class BasicEntityRenderer extends AbstractEntityRenderer {
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
		if(shader instanceof UniformTestShader) {
			if(entity.getModel() instanceof BasicModel) {
				BasicModel model = (BasicModel)entity.getModel();
				GL30.glBindVertexArray(model.getVaoID());
				GL20.glEnableVertexAttribArray(0);
				Matrix4f transformationMatrix = MatrixMaths.createTransformationMatrix(entity.getPosition(), entity.getRx(), entity.getRy(), entity.getRz(), entity.getScale());
				((UniformTestShader)shader).loadTransformationMatrix(transformationMatrix);
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
