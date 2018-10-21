package Engine.Data.ModelHandeling;

import java.util.ArrayList;
import java.util.List;

import Engine.Graphics.GraphicsEngine.AbstractShader;
/** An abstract loader class for models.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractShader
 *
 */
public abstract class AbstractModelLoader {
	/** List of VAO's loaded. 
	 */
	protected List<Integer> vaos = new ArrayList<Integer>();
	/** List of VBO's loaded. 
	 */
	protected List<Integer> vbos = new ArrayList<Integer>();
	
	/** Clean up the loader.
	 */
	public abstract void cleanUp();
	
	/** Create a VAO. 
	 * 
	 * @return the VAO's ID.
	 */
	protected abstract int createVAO();
	
	/** Store the VAO inside a VBO.
	 * 
	 * @param attributeID The VAO's ID.
	 * @param coordinateSize the size of the coordinates.
	 * @param data The vertices data.
	 */
	protected abstract void storeInList(int attributeID, int coordinateSize, float[] data);
	
	/** Clear the bound VAO.
	 */
	protected abstract void unbindVAO();
	
	/** Binds an index buffer to a model.
	 * 
	 * @param indexes An array of indexes to bind. 
	 */
	protected abstract void bindIndexBuffer(int[] indexes);
}
