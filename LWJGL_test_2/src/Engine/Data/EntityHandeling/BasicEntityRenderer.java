package Engine.Data.EntityHandeling;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjglx.util.vector.Matrix4f;

import Engine.Data.ModelHandeling.BasicModel;
import Engine.Data.ModelHandeling.TexturedModel;
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

	
	
}
