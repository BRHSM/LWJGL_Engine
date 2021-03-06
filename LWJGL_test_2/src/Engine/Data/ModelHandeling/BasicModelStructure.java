package Engine.Data.ModelHandeling;

/** Implementation of AbstractModelStructure used for BasicModels.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractModelStructure
 */
public class BasicModelStructure extends AbstractModelStructure{
	
	/** Create a new BasicModelStructure.
	 * 
	 * @param vertices The vertices of the model.
	 * @param indexes The index order for rendering the vertices.
	 */
	public BasicModelStructure(float[] vertices, int[] indexes, int id) {
		super(vertices, indexes, id);
	}
}
