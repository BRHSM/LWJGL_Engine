package ModelHandeling;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/** Class used to load textured models into memory in order for them to render.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class BasicModelLoader {
	/** List of VAO's loaded. 
	 */
	private List<Integer> vaos = new ArrayList<Integer>();
	/** List of VBO's loaded. 
	 */
	private List<Integer> vbos = new ArrayList<Integer>();
	
	/** Load a model to a VAO.
	 * 
	 * @param positions the vertices of the model.
	 * @param indexes the array of indexes.
	 * @return a RawModel with model info (ID, VerticesCount).
	 */
	public BasicModel loadToVAO(float[] positions, int[] indexes) {
		int vaoID = createVAO();
		bindIndexBuffer(indexes);
		storeInList(0, 3, positions);
		unbindVAO();
		return new BasicModel(vaoID, indexes.length);
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
	}
	
	/** Create a new VAO and add it to the list.
	 * 
	 * @return the ID of the VAO
	 */
	private int createVAO() {
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
	private void storeInList(int attributeID, int coordinateSize, float[] data) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeID, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/** Clear the bound VAO.
	 */
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	/** Binds an index buffer to a model.
	 * 
	 * @param indexes An array of indexes to bind. 
	 */
	private void bindIndexBuffer(int[] indexes) {
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
