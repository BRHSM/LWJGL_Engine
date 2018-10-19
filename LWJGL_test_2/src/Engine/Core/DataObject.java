package Engine.Core;

import java.util.ArrayList;

import Engine.EntityHandeling.AbstractEntityStructure;
import Engine.EntityHandeling.EntityList;
import Engine.GraphicsEngine.AbstractShader;
import Engine.GraphicsEngine.BasicEntityShader;
import Engine.GraphicsEngine.BasicModelShader;
import Engine.GraphicsEngine.TexturedEntityShader;
import Engine.GraphicsEngine.TexturedModelShader;
import Engine.ModelHandeling.AbstractModelStructure;
import Engine.ModelHandeling.ModelList;
import Engine.OptionManager.CurrentLanguage;
import Engine.OptionManager.EngineOptions;
import Engine.OptionManager.GraphicOptions;
import Engine.OptionManager.OptionHandler;
import Engine.UtilClasses.StringBreaker;

/** Object wich contains all data for the engine to work. 
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 * @see AbstractShader
 * @see AbstractModelStructure
 * @see ModelList
 */
public class DataObject {
	
	//Shader ID values
	/** ID value for BasicModelShaders.
	 */
	public static final int BASIC_MODEL_SHADER = 301;
	/** ID value for TextureShaders.
	 */
	public static final int TEXTURED_MODEL_SHADER = 302;
	/** ID value for BasicShaders.
	 */
	public static final int BASIC_ENTITY_SHADER = 303;
	/** ID value for TextureShaders.
	 */
	public static final int TEXTURED_ENTITY_SHADER = 304;
	
	/** The default BasicShader.
	 */
	public static final AbstractShader DEFAULT_BASIC_MODEL_SHADER = new BasicModelShader();
	/** The default TextureShader.
	 */
	public static final AbstractShader DEFAULT_TEXTURED_MODEL_SHADER = new TexturedModelShader();
	/** The default BasicShader.
	 */
	public static final AbstractShader DEFAULT_BASIC_ENTITY_SHADER = new BasicEntityShader();
	/** The default TextureShader.
	 */
	public static final AbstractShader DEFAULT_TEXTURED_ENTITY_SHADER = new TexturedEntityShader();
	
	/** The BasicShader to use.
	 */
	private BasicModelShader basicModelShader;
	/** The TextureShader to use.
	 */
	private TexturedModelShader texturedModelShader;
	/** The BasicShader to use.
	 */
	private BasicEntityShader basicEntityShader;
	/** The TextureShader to use.
	 */
	private TexturedEntityShader texturedEntityShader;
	
	/**List of ModelStructures to load on startup. 
	 */
	ArrayList<AbstractModelStructure> modelStructureList = new ArrayList<AbstractModelStructure>();
	/**List of EntityStructures to load on startup. 
	 */
	ArrayList<AbstractEntityStructure> entityStructureList = new ArrayList<AbstractEntityStructure>();
	
	/** Creates a clean DataObject
	 */
	public DataObject() {
		basicModelShader = (BasicModelShader) DEFAULT_BASIC_MODEL_SHADER;
		texturedModelShader = (TexturedModelShader) DEFAULT_TEXTURED_MODEL_SHADER;
		basicEntityShader = (BasicEntityShader) DEFAULT_BASIC_ENTITY_SHADER;
		texturedEntityShader = (TexturedEntityShader) DEFAULT_TEXTURED_ENTITY_SHADER;
		OptionHandler.setupOptions();
		
		//load graphic options
		OptionHandler.addOptionFile(OptionHandler.GRAPHIC_OPTION_ID, new GraphicOptions(), "GraphicOptions");
		OptionHandler.loadOptionListFromFile(OptionHandler.GRAPHIC_OPTION_ID, OptionHandler.GRAPHIC_OPTION_TYPE);
		
		//load engine options
		OptionHandler.addOptionFile(OptionHandler.ENGINE_OPTION_ID, new EngineOptions(), "EngineOptions");
		OptionHandler.loadOptionListFromFile(OptionHandler.ENGINE_OPTION_ID, OptionHandler.ENGINE_OPTION_TYPE);
		
		//load current language
		OptionHandler.addOptionFile(OptionHandler.CURRENT_LANGUAGE_ID, new CurrentLanguage(), OptionHandler.getProperty(EngineOptions.MAINLANGUAGE_KEY, OptionHandler.ENGINE_OPTION_ID));
		OptionHandler.loadOptionListFromFile(OptionHandler.CURRENT_LANGUAGE_ID, OptionHandler.CURRENT_LANGUAGE_TYPE);
		
		//print all options if debug.
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
			System.out.println(StringBreaker.breakString("[DEBUG]: all loaded options: " + OptionHandler.getAllOptions(), "\n", 125));
		}
	}
	
	/** Add a modelStructure to the modelStructureList.
	 * 
	 * @param model The modelStructure to add to the list.
	 */
	public void addModel(AbstractModelStructure modelStructure) {
		modelStructureList.add(modelStructure);
	}
	
	/** Add a entityStructure to the entityStructureList.
	 * 
	 * @param model The entityStructure to add to the list.
	 */
	public void addEntity(AbstractEntityStructure entityStructure) {
		entityStructureList.add(entityStructure);
		
	}
	
	/** Set the shader to use for a specified modeltype.
	 * 
	 * @param shaderType The type of shader you want to use.
	 * @param shader The shader you want to use.
	 */
	public void setShader(int shaderType, AbstractShader shader) {
		if (shaderType == BASIC_MODEL_SHADER)
			if(shader instanceof BasicModelShader) {
				this.basicModelShader = (BasicModelShader)shader;
			} else {
				//TODO: Throw Exception
			}
		if (shaderType == TEXTURED_MODEL_SHADER)
			if(shader instanceof TexturedModelShader) {
				this.texturedModelShader = (TexturedModelShader)shader;
			} else {
				//TODO: Throw Exception
			}
		if (shaderType == BASIC_ENTITY_SHADER)
			if(shader instanceof BasicEntityShader) {
				this.basicEntityShader = (BasicEntityShader)shader;
			} else {
				//TODO: Throw Exception
			}
		if (shaderType == TEXTURED_ENTITY_SHADER)
			if(shader instanceof TexturedEntityShader) {
				this.texturedEntityShader = (TexturedEntityShader)shader;
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
	
	/** Get the ModelList.
	 * 
	 * @return the ModelList
	 */
	public EntityList getEntityLists() {
		return new EntityList(entityStructureList);
	}

	/** Get the BasicShader.
	 * 
	 * @return the BasicShader.
	 */
	public BasicModelShader getBasicModelShader() {
		if(basicModelShader == DEFAULT_BASIC_MODEL_SHADER && OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
			System.out.println("         No BasicModelShader loaded, using default shader" + DEFAULT_BASIC_MODEL_SHADER.toString());
		return basicModelShader;
	}

	/** Get the TextureShader.
	 * 
	 * @return the TextureShader.
	 */
	public TexturedModelShader getTexturedModelShader() {
		if(texturedModelShader == DEFAULT_TEXTURED_MODEL_SHADER && OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
			System.out.println("         No TexturedModelShader loaded, using default shader" + DEFAULT_TEXTURED_MODEL_SHADER.toString());
		return texturedModelShader;
	}	
	
	/** Get the BasicShader.
	 * 
	 * @return the BasicShader.
	 */
	public BasicEntityShader getBasicEntityShader() {
		if(basicEntityShader == DEFAULT_BASIC_ENTITY_SHADER && OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
			System.out.println("         No BasicEntityShader loaded, using default shader" + DEFAULT_BASIC_ENTITY_SHADER.toString());
		return basicEntityShader;
	}

	/** Get the TextureShader.
	 * 
	 * @return the TextureShader.
	 */
	public TexturedEntityShader getTexturedEntityShader() {
		if(texturedEntityShader == DEFAULT_TEXTURED_ENTITY_SHADER && OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
			System.out.println("         No TexturedEntityShader loaded, using default shader" + DEFAULT_TEXTURED_ENTITY_SHADER.toString());
		return texturedEntityShader;
	}
}
