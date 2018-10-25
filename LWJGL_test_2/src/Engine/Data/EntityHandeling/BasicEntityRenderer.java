package Engine.Data.EntityHandeling;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjglx.util.vector.Matrix4f;

import Engine.Data.ModelHandeling.BasicModel;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Graphics.Cameras.AbstractCamera;
import Engine.Graphics.DisplayEngine.DisplayManager;
import Engine.Graphics.Shaders.AbstractShader;
import Engine.Graphics.Shaders.BasicEntityShader;
import Engine.Graphics.Shaders.TexturedEntityShader;
import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.ModelInvalidException;
import Engine.Util.Exceptions.ShaderIncompatableException;
import Engine.Util.Math.MatrixMaths;

/** Class used to render an AbstractEntity with a BasicModel as it's model.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see BasicModel
 */
public class BasicEntityRenderer extends AbstractEntityRenderer {
	/** The aspect ratio of the screen.
	 */
	private float aspectRatio;
	/** The field of view of the screen.
	 */
	private float fov;
	/** The closest plane on the screen. 
	 */
	private float nearPlane;
	/** The farthest plane on the screen. 
	 */
	private float farPlane;
	
	/** RPrepare the renderer for rendering the entity.
	 */
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0,0,0,1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	/** Render a textured model to the screen.
	 *
	 * @param model the model to render.
	 */
	
	// create for each model.
	public void render (AbstractEntity entity, AbstractShader shader, AbstractCamera camera) {
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
	
	/** Setup the shader's projection matrix for rendering.
	 * 
	 * @param shader The shader to use.
	 */
	public void setup(AbstractShader shader) {
		nearPlane = Float.parseFloat(OptionHandler.getProperty(GraphicOptions.WINDOWNEARPLANE_KEY, OptionHandler.GRAPHIC_OPTION_ID));
		farPlane = Float.parseFloat(OptionHandler.getProperty(GraphicOptions.WINDOWFARPLANE_KEY, OptionHandler.GRAPHIC_OPTION_ID));
		aspectRatio = DisplayManager.getWidth() / (float)DisplayManager.getHeight();
		fov = Float.parseFloat(OptionHandler.getProperty(GraphicOptions.WINDOWFOV_KEY, OptionHandler.GRAPHIC_OPTION_ID));
		((BasicEntityShader)shader).loadProjectionMatrix(MatrixMaths.createProjectionMatrix(aspectRatio, fov, nearPlane, farPlane));
	}
	
	/** Load the camera to use by the shader.
	 * 
	 * @param shader The shader to use the camera for.
	 * @param camera The camera to use.
	 */
	public void loadCamera(AbstractShader shader, AbstractCamera camera) {
		((BasicEntityShader)shader).loadViewMatrix(MatrixMaths.createViewMatrix(camera));
	}
}
