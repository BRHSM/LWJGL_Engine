package Engine.Data.ModelHandeling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.Log;

import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Util.Util.NullLogSystem;

/** Class used to load textured models into memory in order for them to render.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractModelLoader
 * @see TexturedModel
*/
public class TexturedModelLoader extends AbstractModelLoader { 
	/** List of VAO's loaded. 
	 */
	private List<Integer> vaos = new ArrayList<Integer>();
	/** List of VBO's loaded. 
	 */
	private List<Integer> vbos = new ArrayList<Integer>();
	/** List of textures loaded.
	 */
	private List<Integer> textures = new ArrayList<Integer>();
	
	/** Load a model to a VAO.
	 * 
	 * @param positions the vertices of the model.
	 * @param positions the texture layer of the model.
	 * @param indexes the array of indexes.
	 * @return a RawModel with model info (ID, VerticesCount).
	 */
	public BasicModel loadToVAO(float[] positions, float[] textureCoordinates, int[] indexes, int id) {
		int vaoID = createVAO();
		bindIndexBuffer(indexes);
		storeInList(0, 3, positions);
		storeInList(1, 2, textureCoordinates);
		unbindVAO();
		return new BasicModel(vaoID, indexes.length, id);
	}
	
	/** Load a texture from a given png file.
	 *  (files need to be placed in the "RESTextures" folder and the filename passed must
	 *   not contain the file extention ".png" as that is allready added within this method.)
	 * 
	 * @param the filename of the texture
	 * @return The ID of the texture
	 */
	public int loadTexture(String file) {
		Log.setLogSystem(new NullLogSystem()); 
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(OptionHandler.getProperty(EngineOptions.PATHTEXTURES_KEY, OptionHandler.ENGINE_OPTION_ID) + file + ".png"));
		} catch (FileNotFoundException e) {
			// TODO Handle File Error
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Handle IO Error
			e.printStackTrace();
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	/** Unload the lists from memory.
	 */
	public void cleanUp() {
		for(int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture : textures) {
			GL11.glDeleteTextures(texture);
		}
	}
	
	/** Create a new VAO and add it to the list.
	 * 
	 * @return the ID of the VAO
	 */
	protected int createVAO() {
		int vaoID =  GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	/** Store the VAO inside a VBO.
	 * 
	 * @param attributeID The VAO's ID.
	 * @param data The vertices data.
	 */
	protected void storeInList(int attributeID, int coordinateSize, float[] data) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeID, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/** Clear the bound VAO.
	 */
	protected void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	/** Binds an index buffer to a model.
	 * 
	 * @param indexes An array of indexes to bind. 
	 */
	protected void bindIndexBuffer(int[] indexes) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = convertToIntBuffer(indexes);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	/** Converts an integer array to an IntBuffer.
	 * 
	 * @param data The data to convert.
	 * @return The converted data.
	 */
	private IntBuffer convertToIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	/** Convert  a float array to FloatBuffer data.
	 * 
	 * @param data the data to convert.
	 * @return the converted data.
	 */
	/*
	private FloatBuffer convertToFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	*/
}
