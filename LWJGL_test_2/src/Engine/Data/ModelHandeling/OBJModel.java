package Engine.Data.ModelHandeling;

import java.util.ArrayList;

import org.lwjglx.util.vector.Vector2f;
import org.lwjglx.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
/** This class contains a model in it's .obj file format and allows for convention to other model types.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see OBJLoader
 * @see BasicModel
 * @see TexturedModel
*/
public class OBJModel {
	/** The vertices of the model.
	 */
	private ArrayList<Vector3f> vertices;
	/** The textureCoordinates of the model.
	 */
	private ArrayList<Vector2f> textureCoords;
	/** The normals of the model.
	 */
	private ArrayList<Vector3f> normals;
	/** The indices of the model.
	 */
	private ArrayList<Integer> indices;
	/** The texture of the model.
	 */
	private Texture texture;
	
	/** Create an OBJModel.
	 * 
	 * @param vertices The vertices of the model.
	 * @param textureCoords The textureCoordinates of the model.
	 * @param normals The normals of the model.
	 * @param indices The indices of the model.
	 */
	public OBJModel(ArrayList<Vector3f> vertices, ArrayList<Vector2f> textureCoords, ArrayList<Vector3f> normals, ArrayList<Integer> indices) {
		this.vertices = vertices;
		this.textureCoords = textureCoords;
		this.normals = normals;
		this.indices = indices;
	}
	
	/** Set the texture to the model.
	 * 
	 * @param texture The texture to load.
	 */
	public void loadTexture(Texture texture) {
		this.texture = texture;
	}
	
	/** Convert the oBJModel into a BasicModelStructure.
	 * 
	 * @return A BasicModelStructure version of the model.
	 */
	public BasicModelStructure convertToBasicModelStructure() {
		return null;
		
	}
	
	/** Convert the oBJModel into a TexturedModelStructure.
	 * 
	 * @return A TexturedModelStructure version of the model.
	 */
	public TexturedModelStructure convertToTexturedModelStructure() {
		return null;
		
	}
}
	
	
