package Engine.Data.ModelHandeling;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
	/** The texture file of the model.
	 */
	private String texture;
	
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
	 * @param texture The texture file to load.
	 */
	public void loadTexture(String texture) {
		this.texture = texture;
	}
	
	/** Convert the oBJModel into a BasicModelStructure.
	 * 
	 * @return A BasicModelStructure version of the model.
	 */
	public BasicModelStructure convertToBasicModelStructure() {
		return new BasicModelStructure(Vec3fToFloatBuffer(vertices), toIntArray(indices));
		
	}

	/** Convert the oBJModel into a TexturedModelStructure.
	 * 
	 * @return A TexturedModelStructure version of the model.
	 */
	public TexturedModelStructure convertToTexturedModelStructure() {
		return new TexturedModelStructure(Vec3fToFloatBuffer(vertices), Vec2fToFloatBuffer(textureCoords), toIntArray(indices), texture);
		
	}
	/** Convert a list of vectors containing 3 floats in a floatArray.
	 * 
	 * @param vecList The vectorlist to convert
	 * @return A floatArray version of the vectorList
	 */
	public float[] Vec3fToFloatBuffer(ArrayList<Vector3f> vecList) {
		float[] array = new float[vecList.size() * 3];
		int i = 0;
		for(Vector3f vec : vecList) {
			array[i++] = vec.x;
			array[i++] = vec.y;
			array[i++] = vec.z;
		}
		return array;
	}
	
	/** Convert a list of vectors containing 2 floats in a floatArray.
	 * 
	 * @param vecList The vectorlist to convert
	 * @return A floatArray version of the vectorList
	 */
	public float[] Vec2fToFloatBuffer(ArrayList<Vector2f> vecList) {
		float[] array = new float[vecList.size() * 2];
		int i = 0;
		for(Vector2f vec : vecList) {
			array[i++] = vec.x;
			array[i++] = vec.y;
		}
		return array;
	}
	
	/** Convert an arraylist of Integer to an array of int
	 * 
	 * @param indices The array to convert.
	 * @return The IntArray.
	 */
	private int[] toIntArray(ArrayList<Integer> indices) {
		Integer[] tmp = Arrays.copyOf(indices.toArray(), indices.size(), Integer[].class);
		int[] array = new int[indices.size()];
		int i = 0;
		for(Integer tmpInt : tmp) {
			array[i] = tmp[i].intValue();
			i++;
		}
		return array;
	}
}
	
	
