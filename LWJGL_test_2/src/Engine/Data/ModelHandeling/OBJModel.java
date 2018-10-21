package Engine.Data.ModelHandeling;

import java.util.ArrayList;

import org.lwjglx.util.vector.Vector2f;
import org.lwjglx.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class OBJModel {
	private ArrayList<Vector3f> vertices;
	private ArrayList<Vector2f> textureCoords;
	private ArrayList<Vector3f> normals;
	private ArrayList<Integer> indices;
	private Texture texture;
	
	public OBJModel(ArrayList<Vector3f> vertices, ArrayList<Vector2f> textureCoords, ArrayList<Vector3f> normals, ArrayList<Integer> indices) {
		this.vertices = vertices;
		this.textureCoords = textureCoords;
		this.normals = normals;
		this.indices = indices;
	}
	
	public void loadTexture(Texture texture) {
		this.texture = texture;
	}
	
	public BasicModelStructure convertToBasicModelStructure() {
		return null;
		
	}
	
	public TexturedModelStructure convertToTexturedModelStructure() {
		return null;
		
	}
}
	
	
