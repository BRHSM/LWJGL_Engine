package Engine.Core.Core;

import java.util.ArrayList;

import Engine.Data.EntityHandeling.AbstractEntityStructure;
import Engine.Data.EntityHandeling.EntityList;
import Engine.Data.ModelHandeling.AbstractModelStructure;
import Engine.Data.ModelHandeling.ModelList;
import Engine.Data.OptionManager.CurrentLanguage;
import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Graphics.Cameras.AbstractCamera;
import Engine.Graphics.Cameras.StaticCamera;
import Engine.Graphics.Shaders.AbstractShader;
import Engine.Graphics.Shaders.BasicEntityShader;
import Engine.Graphics.Shaders.BasicModelShader;
import Engine.Graphics.Shaders.TexturedEntityShader;
import Engine.Graphics.Shaders.TexturedModelShader;
import Engine.Util.Util.StringBreaker;

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
	/** ID value for TexturedModelShaders.
	 */
	public static final int TEXTURED_MODEL_SHADER = 302;
	/** ID value for BasicEntityShaders.
	 */
	public static final int BASIC_ENTITY_SHADER = 303;
	/** ID value for TexturedEntityShaders.
	 */
	public static final int TEXTURED_ENTITY_SHADER = 304;
	
	/** The default BasicModelShaders.
	 */
	public static final AbstractShader DEFAULT_BASIC_MODEL_SHADER = new BasicModelShader();
	/** The default TexturedModelShaders.
	 */
	public static final AbstractShader DEFAULT_TEXTURED_MODEL_SHADER = new TexturedModelShader();
	/** The default TexturedModelShaders.
	 */
	public static final AbstractShader DEFAULT_BASIC_ENTITY_SHADER = new BasicEntityShader();
	/** The default TexturedEntityShaders.
	 */
	public static final AbstractShader DEFAULT_TEXTURED_ENTITY_SHADER = new TexturedEntityShader();
	
	/** The TexturedModelShaders to use.
	 */
	private BasicModelShader basicModelShader;
	/** The TexturedModelShaders to use.
	 */
	private TexturedModelShader texturedModelShader;
	/** The TexturedEntityShaders to use.
	 */
	private BasicEntityShader basicEntityShader;
	/** The TexturedEntityShaders to use.
	 */
	private TexturedEntityShader texturedEntityShader;
	
	/**List of ModelStructures to load on startup. 
	 */
	private ArrayList<AbstractModelStructure> modelStructureList = new ArrayList<AbstractModelStructure>();
	/**List of EntityStructures to load on startup. 
	 */
	private ArrayList<AbstractEntityStructure> entityStructureList = new ArrayList<AbstractEntityStructure>();
	
	/** The camera to use;
	 */
	private AbstractCamera camera; 
	
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
		
		//set default camera
		camera = new StaticCamera();
		
		
		if(basicModelShader == DEFAULT_BASIC_MODEL_SHADER && OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1"))
			System.out.println("[DEBUG]: Default camera loaded: " + camera.toString());
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
	
	/** Get the camera.
	 * 
	 * @return the camera.
	 */
	public AbstractCamera getCamera() {
		return camera;
	}

	/** Set the camera.
	 * 
	 * @param camera The camera to set.
	 */
	public void setCamera(AbstractCamera camera) {
		this.camera = camera;
	}
}
