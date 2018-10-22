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
	private float[] vertices;
	/** The textureCoordinates of the model.
	 */
	private float[] textureCoords;
	/** The normals of the model.
	 */
	private float[] normals;
	/** The indices of the model.
	 */
	private int[] indices;
	/** The texture file of the model.
	 */
	private String texture;
	/** The furthest point of the model.
	 */
	private float furthestPoint;
	
	/** Create an OBJModel.
	 * 
	 * @param verticesArray The vertices of the model.
	 * @param texturesArray The textureCoordinates of the model.
	 * @param normalsArray The normals of the model.
	 * @param indicesArray The indices of the model.
	 */
	public OBJModel(float[] verticesArray, float[] texturesArray, float[] normalsArray, int[] indicesArray, float furthestPoint) {
		this.vertices = verticesArray;
		this.textureCoords = texturesArray;
		this.normals = normalsArray;
		this.indices = indicesArray;
		this.furthestPoint = furthestPoint;
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
		return new BasicModelStructure(vertices, indices);
		
	}

	/** Convert the oBJModel into a TexturedModelStructure.
	 * 
	 * @return A TexturedModelStructure version of the model.
	 */
	public TexturedModelStructure convertToTexturedModelStructure() {
		return new TexturedModelStructure(vertices, textureCoords, indices, texture);
		
	}
}
	
	
