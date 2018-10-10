package Core;

import java.util.ArrayList;

import GraphicsEngine.AbstractShader;
import GraphicsEngine.BasicShader;
import GraphicsEngine.TextureShader;
import ModelHandeling.AbstractModelStructure;
import ModelHandeling.ModelList;
import OptionManager.CurrentLanguage;
import OptionManager.EngineOptions;
import OptionManager.GraphicOptions;
import OptionManager.OptionHandler;

/** Object wich contains all data for the engine to work. 
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see ModelList
 */
public class DataObject {
	
	//Shader ID values
	/** ID value for BasicShaders.
	 */
	public static final int BASIC_SHADER = 101;
	/** ID value for TextureShaders.
	 */
	public static final int TEXTURE_SHADER = 102;
	
	/** The default BasicShader.
	 */
	public static final AbstractShader DEFAULT_BASIC_SHADER = new BasicShader();
	
	/** The default TextureShader.
	 */
	public static final AbstractShader DEFAULT_TEXTURE_SHADER = new TextureShader();
	
	/** The BasicShader to use.
	 */
	private BasicShader basicShader;
	
	/** The TextureShader to use.
	 */
	private TextureShader textureShader;
	/**List of ModelStructures to load on startup. 
	 */
	ArrayList<AbstractModelStructure> modelStructureList = new ArrayList<AbstractModelStructure>();
	
	/** Creates a clean DataObject
	 */
	public DataObject() {
		basicShader = (BasicShader) DEFAULT_BASIC_SHADER;
		textureShader = (TextureShader) DEFAULT_TEXTURE_SHADER;
		OptionHandler.setupOptions();
		
		OptionHandler.addOptionFile(OptionHandler.GRAPHIC_OPTION_ID, new GraphicOptions(), "GraphicOptions");
		OptionHandler.loadOptionListFromFile(OptionHandler.GRAPHIC_OPTION_ID, OptionHandler.GRAPHIC_OPTION_TYPE);
		
		OptionHandler.addOptionFile(OptionHandler.ENGINE_OPTION_ID, new EngineOptions(), "EngineOptions");
		OptionHandler.loadOptionListFromFile(OptionHandler.ENGINE_OPTION_ID, OptionHandler.ENGINE_OPTION_TYPE);
		
		OptionHandler.addOptionFile(OptionHandler.CURRENT_LANGUAGE_ID, new CurrentLanguage(), OptionHandler.getProperty(EngineOptions.MAINLANGUAGE_KEY, OptionHandler.ENGINE_OPTION_ID));
		OptionHandler.loadOptionListFromFile(OptionHandler.CURRENT_LANGUAGE_ID, OptionHandler.CURRENT_LANGUAGE_TYPE);
	}
	
	/** Add a ModelStructure to the modelStructureList.
	 * 
	 * @param model The model to add to the list.
	 */
	public void addModel(AbstractModelStructure modelStructure) {
		modelStructureList.add(modelStructure);
	}
	
	/** Set the shader to use for a specified modeltype.
	 * 
	 * @param shaderType The type of shader you want to use.
	 * @param shader The shader you want to use.
	 */
	public void setShader(int shaderType, AbstractShader shader) {
		if (shaderType == BASIC_SHADER)
			if(shader instanceof BasicShader) {
				this.basicShader = (BasicShader)shader;
			} else {
				//TODO: Throw Exception
			}
		if (shaderType == TEXTURE_SHADER)
			if(shader instanceof TextureShader) {
				this.textureShader = (TextureShader)shader;
			} else {
				//TODO: Throw Exception
			}
	}
	
	/** Get the ModelList.
	 * 
	 * @return the ModelList
	 */
	public ModelList getModelLists() {
		return new ModelList(modelStructureList);
	}

	/** Get the BasicShader.
	 * 
	 * @return the BasicShader.
	 */
	public BasicShader getBasicShader() {
		if(basicShader == DEFAULT_BASIC_SHADER)
			System.out.println("[DEBUG]: No BasicShader loaded, using default shader" + DEFAULT_BASIC_SHADER.toString());
		return basicShader;
	}

	/** Get the TextureShader.
	 * 
	 * @return the TextureShader.
	 */
	public TextureShader getTextureShader() {
		if(textureShader == DEFAULT_TEXTURE_SHADER)
			System.out.println("[DEBUG]: No TextureShader loaded, using default shader" + DEFAULT_TEXTURE_SHADER.toString());
		return textureShader;
	}
	
	
}
