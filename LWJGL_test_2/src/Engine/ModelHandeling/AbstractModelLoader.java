package Engine.ModelHandeling;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModelLoader {
	/** List of VAO's loaded. 
	 */
	protected List<Integer> vaos = new ArrayList<Integer>();
	/** List of VBO's loaded. 
	 */
	protected List<Integer> vbos = new ArrayList<Integer>();
	
	public abstract void cleanUp();
	
	protected abstract int createVAO();
	
	protected abstract void storeInList(int attributeID, int coordinateSize, float[] data);
	
	protected abstract void unbindVAO();
	
	protected abstract void bindIndexBuffer(int[] indexes);
}
