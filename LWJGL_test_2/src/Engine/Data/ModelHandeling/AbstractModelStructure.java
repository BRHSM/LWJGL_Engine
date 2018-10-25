package Engine.Data.ModelHandeling;

/** Extend this class for a new modelStructure.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class AbstractModelStructure {
	/** The vertices of a model.
	 */
	private float[] vertices;
	
	/** The index order in which the vertices are rendered.
	 */
	private int[] indexes;
	
	/** The message ID of the model.
	 */
	private int id;

	/** Create a new AbstractModel
	 * 
	 * @param vertices The array of vertices.
	 * @param indexes The array of indexes.
	 */
	public AbstractModelStructure(float[] vertices, int[] indexes, int id) {
		this.vertices = vertices;
		this.indexes = indexes;
	}
	
	/** Get the vertices array.
	 * 
	 * @return The vertices array.
	 */
	public float[] getVertices() {
		return vertices;
	}

	/** Get the indexes array.
	 * 
	 * @return The indexes array.
	 */
	public int[] getIndexes() {
		return indexes;
	}
	
	public int getId() {
		return id;
	}
}
