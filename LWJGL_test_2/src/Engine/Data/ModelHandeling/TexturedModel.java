package Engine.Data.ModelHandeling;
/** A Model with a texture on it.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractModel
 * @see ModelTexture
 * @see BasicModel
 */
public class TexturedModel extends AbstractModel{
	
	/** The texture for the model
	 */
	private ModelTexture texture;
	
	/** Create a TextureModel from a BasicModel and a ModelTexture.
	 * 
	 * @param model The basicModel.
	 * @param texture The Texture.
	 */
	public TexturedModel(BasicModel model, ModelTexture texture) {
		super(model.getVaoID(), model.getVertexCount());
		this.texture = texture;
	}
	
	/** Create a TexturedModel from ID, vertexCount and ModelTexture.
	 * 
	 * @param vaoID The model ID.
	 * @param vertexCount The number of vertices of the model.
	 * @param texture The texture for the model.
	 */
	public TexturedModel(int vaoID, int vertexCount, ModelTexture texture) {
		super(vaoID, vertexCount);
		this.texture = texture;
	}
	
	/** Get the texture of the model.
	 * 
	 * @return The texture for the model
	 */
	public ModelTexture getTexture() {
		return texture;
	}	
}
