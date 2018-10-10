package ModelHandeling;

/** A structure class for the TextureModel.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see TextureModel
 *
 */
public class TexturedModelStructure extends AbstractModelStructure{
	
	/** Array of coordinates for the texture.
	 */
	private float[] textureCoordinates;
	/** The name of the file of the texture.
	 */
	private String textureName;
	
	/** Create a new TextureModelStructure containing a placeholder texture.
	 * 
	 * @param vertices The vertices of the structure.
	 * @param textureCoordinates The textureCoordinates of the structure.
	 * @param indexes The index order for rendering vertices.
	 */
	public TexturedModelStructure(float[] vertices, float[] textureCoordinates, int[] indexes) {
		super(vertices, indexes);
		this.textureCoordinates = textureCoordinates;
		this.textureName = "Placeholder_Texture";
	}
	/** Create a new TextureModelStructure containing a custom texture.
	 * 
	 * @param vertices The vertices of the structure.
	 * @param textureCoordinates The textureCoordinates of the structure.
	 * @param indexes The index order for rendering vertices.
	 * @param textureName Name of the texture file.
	 */
	public TexturedModelStructure(float[] vertices, float[] textureCoordinates, int[] indexes, String textureName) {
		super(vertices, indexes);
		this.textureCoordinates = textureCoordinates;
		this.textureName = textureName;
	}
	/** Get the textureCoordinates.
	 * 
	 * @return the textureCoordinates.
	 */
	public float[] getTextureCoordinates() {
		return textureCoordinates;
	}
	
	/** Get the textureName.
	 * 
	 * @return the textureName.
	 */
	public String getTextureName() {
		return textureName;
	}
	
}
