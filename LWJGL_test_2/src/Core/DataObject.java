package Core;

import java.util.ArrayList;

import GraphicsEngine.AbstractShader;
import GraphicsEngine.BasicModelShader;
import GraphicsEngine.TexturedModelShader;
import ModelHandeling.AbstractModelStructure;
import ModelHandeling.ModelList;
import OptionManager.CurrentLanguage;
import OptionManager.EngineOptions;
import OptionManager.GraphicOptions;
import OptionManager.OptionHandler;
import UtilClasses.StringBreaker;

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
	public static final AbstractShader DEFAULT_BASIC_SHADER = new BasicModelShader();
	
	/** The default TextureShader.
	 */
	public static final AbstractShader DEFAULT_TEXTURE_SHADER = new TexturedModelShader();
	
	/** The BasicShader to use.
	 */
	private BasicModelShader basicShader;
	
	/** The TextureShader to use.
	 */
	private TexturedModelShader textureShader;
	/**List of ModelStructures to load on startup. 
	 */
	ArrayList<AbstractModelStructure> modelStructureList = new ArrayList<AbstractModelStructure>();
	
	/** Creates a clean DataObject
	 */
	public DataObject() {
		basicShader = (BasicModelShader) DEFAULT_BASIC_SHADER;
		textureShader = (TexturedModelShader) DEFAULT_TEXTURE_SHADER;
		OptionHandler.setupOptions();
		
		OptionHandler.addOptionFile(OptionHandler.GRAPHIC_OPTION_ID, new GraphicOptions(), "GraphicOptions");
		OptionHandler.loadOptionListFromFile(OptionHandler.GRAPHIC_OPTION_ID, OptionHandler.GRAPHIC_OPTION_TYPE);
		
		OptionHandler.addOptionFile(OptionHandler.ENGINE_OPTION_ID, new EngineOptions(), "EngineOptions");
		OptionHandler.loadOptionListFromFile(OptionHandler.ENGINE_OPTION_ID, OptionHandler.ENGINE_OPTION_TYPE);
		
		OptionHandler.addOptionFile(OptionHandler.CURRENT_LANGUAGE_ID, new CurrentLanguage(), OptionHandler.getProperty(EngineOptions.MAINLANGUAGE_KEY, OptionHandler.ENGINE_OPTION_ID));
		OptionHandler.loadOptionListFromFile(OptionHandler.CURRENT_LANGUAGE_ID, OptionHandler.CURRENT_LANGUAGE_TYPE);
		
		//print all options
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
			System.out.println(StringBreaker.breakString("[DEBUG]: all loaded options: " + OptionHandler.getAllOptions(), "\n", 125));
		}
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
			if(shader instanceof BasicModelShader) {
				this.basicShader = (BasicModelShader)shader;
			} else {
				//TODO: Throw Exception
			}
		if (shaderType == TEXTURE_SHADER)
			if(shader instanceof TexturedModelShader) {
				this.textureShader = (TexturedModelShader)shader;
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
	public BasicModelShader getBasicShader() {
		if(basicShader == DEFAULT_BASIC_SHADER)
			System.out.println("[DEBUG]: No BasicShader loaded, using default shader" + DEFAULT_BASIC_SHADER.toString());
		return basicShader;
	}

	/** Get the TextureShader.
	 * 
	 * @return the TextureShader.
	 */
	public TexturedModelShader getTextureShader() {
		if(textureShader == DEFAULT_TEXTURE_SHADER)
			System.out.println("[DEBUG]: No TextureShader loaded, using default shader" + DEFAULT_TEXTURE_SHADER.toString());
		return textureShader;
	}
	
	
}
