package Engine.ModelHandeling;

/** This class contains the information for a model.
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
*/
public class BasicModel extends AbstractModel{	
	/** Create a model for given parameters.
	 * 
	 * @param vaoID The model ID.
	 * @param vertexCount The number of vertices of the model.
	 */
	public BasicModel(int vaoID, int vertexCount) {
		super(vaoID, vertexCount);
	}	
}
