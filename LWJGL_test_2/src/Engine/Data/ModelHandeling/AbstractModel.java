package Engine.Data.ModelHandeling;

import Engine.Graphics.Shaders.AbstractShader;

/** An abstract model.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractModel {
	/** The model ID.
	 */
	private int vaoID;
	/** The number of vertices of the model.
	 */
	private int vertexCount;
	
	/** Create a model for given parameters.
	 * 
	 * @param vaoID The model ID.
	 * @param vertexCount The number of vertices of the model.
	 */
	public AbstractModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}
	
	/** Get the model ID.
	 * 
	 * @return The model ID.
	 */
	public int getVaoID() {
		return vaoID;
	}
	
	/** Set the model ID.
	 * 
	 * @param vaoID The model ID
	 */
	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}

	/** Get the vertex count.
	 * 
	 * @return The vertex count.
	 */
	public int getVertexCount() {
		return vertexCount;
	}

	/** Set the vertex count.
	 * 
	 * @param vaoID The vertex count.
	 */
	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

}
