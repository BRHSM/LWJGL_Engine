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
import Engine.Data.OptionManager.RuntimeOptions;
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
	public static AbstractShader DEFAULT_BASIC_MODEL_SHADER;
	/** The default TexturedModelShaders.
	 */
	public static AbstractShader DEFAULT_TEXTURED_MODEL_SHADER;
	/** The default TexturedModelShaders.
	 */
	public static AbstractShader DEFAULT_BASIC_ENTITY_SHADER;
	/** The default TexturedEntityShaders.
	 */
	public static AbstractShader DEFAULT_TEXTURED_ENTITY_SHADER;
	
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
		
		//load runtime options.
		OptionHandler.addRuntimeOptions();
		OptionHandler.loadOptionListFromFile(OptionHandler.RUNTIME_OPTIONS_ID, OptionHandler.RUNTIME_OPTIONS_TYPE);
		
		//print all options if debug.
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
			System.out.println(StringBreaker.breakStringBehindString("[DEBUG]: all loaded options: \n" + OptionHandler.getAllOptions(), "\n", ","));
		}
		
		//set default shaders.
		DEFAULT_BASIC_MODEL_SHADER = new BasicModelShader();
		DEFAULT_TEXTURED_MODEL_SHADER = new TexturedModelShader();
		DEFAULT_BASIC_ENTITY_SHADER = new BasicEntityShader();
		DEFAULT_TEXTURED_ENTITY_SHADER = new TexturedEntityShader();
		
		basicModelShader = (BasicModelShader) DEFAULT_BASIC_MODEL_SHADER;
		texturedModelShader = (TexturedModelShader) DEFAULT_TEXTURED_MODEL_SHADER;
		basicEntityShader = (BasicEntityShader) DEFAULT_BASIC_ENTITY_SHADER;
		texturedEntityShader = (TexturedEntityShader) DEFAULT_TEXTURED_ENTITY_SHADER;
		
		//set default camera
		camera = new StaticCamera();
		
		
		if(basicModelShader == DEFAULT_BASIC_MODEL_SHADER && OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true"))
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
		return basicModelShader;
	}

	/** Get the TextureShader.
	 * 
	 * @return the TextureShader.
	 */
	public TexturedModelShader getTexturedModelShader() {
		return texturedModelShader;
	}	
	
	/** Get the BasicShader.
	 * 
	 * @return the BasicShader.
	 */
	public BasicEntityShader getBasicEntityShader() {
		return basicEntityShader;
	}

	/** Get the TextureShader.
	 * 
	 * @return the TextureShader.
	 */
	public TexturedEntityShader getTexturedEntityShader() {
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
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true"))
			System.out.println("[DEBUG]: setting camera: " + camera.toString());
		OptionHandler.setProperty(RuntimeOptions.USESVIEWMATRIX_KEY, OptionHandler.RUNTIME_OPTIONS_ID, "true");
		this.camera = camera;
	}
}
