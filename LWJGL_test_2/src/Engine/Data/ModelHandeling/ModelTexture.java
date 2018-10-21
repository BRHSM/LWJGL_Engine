package Engine.Data.ModelHandeling;
/** Class used to store textures for TexturedModels
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see TexturedModel
 */
public class ModelTexture {

	/** The ID of the texture.
	 */
	private int textureID;
	
	/** Create a new ModelTexture.
	 * 
	 * @param textureID The ID of the texture
	 */
	public ModelTexture(int textureID) {
		this.textureID = textureID; 
	}

	/** Get the ID of the texture.
	 * 
	 * @return The ID of the texture
	 */
	public int getTextureID() {
		return textureID;
	}

	/** Set the ID of the texture.
	 * 
	 * @param textureID The ID of the texture
	 */
	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}
	
	
	
}
