package Engine.Core.Core;

import java.util.ArrayList;

import Engine.Data.EntityHandeling.AbstractEntityStructure;
import Engine.Data.EntityHandeling.EntityList;
import Engine.Data.InternalMessager.Messager;
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
import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.OptionDisabledButStillUsedException;
import Engine.Util.Exceptions.ShaderIncompatableException;
import Engine.Util.Util.StringBreaker;

/** Object wich contains all data for the engine to work 
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
	
	/** The default BasicModelShaders.
	 */
	public static AbstractShader DEFAULT_BASIC_MODEL_SHADER;
	/** The default TexturedModelShaders.
	 */
	public static AbstractShader DEFAULT_TEXTURED_MODEL_SHADER;
	/** The default TexturedModelShaders.
	 */
	public static AbstractShader DEFAULT_ENTITY_SHADER;
	
	/** The TexturedModelShaders to use.
	 */
	private BasicModelShader basicModelShader;
	/** The TexturedModelShaders to use.
	 */
	private TexturedModelShader texturedModelShader;
	/** The TexturedEntityShaders to use.
	 */
	private BasicEntityShader entityShader;
	
	/**List of ModelStructures to load on startup. 
	 */
	private ArrayList<AbstractModelStructure> modelStructureList = new ArrayList<AbstractModelStructure>();
	/**List of EntityStructures to load on startup. 
	 */
	private ArrayList<AbstractEntityStructure> entityStructureList = new ArrayList<AbstractEntityStructure>();
	
	private Messager messager;
	private AbstractUpdater updater;
	
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
		DEFAULT_ENTITY_SHADER = new BasicEntityShader();
		
		basicModelShader = (BasicModelShader) DEFAULT_BASIC_MODEL_SHADER;
		texturedModelShader = (TexturedModelShader) DEFAULT_TEXTURED_MODEL_SHADER;
		entityShader = (BasicEntityShader) DEFAULT_ENTITY_SHADER;
		
		messager = new Messager();
		
		//set default camera
		camera = new StaticCamera();
		
		updater = new AbstractUpdater();
		
		
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
	
	/** Get the ModelList.
	 * 
	 * @return the ModelList
	 */
	public ModelList getModelLists() {
		ModelList modelList = new ModelList(modelStructureList);
		messager.setModelsList(modelList);
		return modelList;
	}
	
	/** Get the ModelList.
	 * 
	 * @return the ModelList
	 */
	public EntityList getEntityLists() {
		EntityList modelList = new EntityList(entityStructureList);
		messager.setEntitiesList(modelList);
		return modelList;
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
	public BasicEntityShader getEntityShader() {
		return entityShader;
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
		if(OptionHandler.getProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
			System.out.println("[DEBUG]: setting camera: " + camera.toString());
		}
		OptionHandler.setProperty(RuntimeOptions.USESVIEWMATRIX_KEY, OptionHandler.RUNTIME_OPTIONS_ID, "true");
		if(OptionHandler.getProperty(EngineOptions.SHADERAUTOSELECT_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true") && OptionHandler.getProperty(EngineOptions.SHADERUSECUSTOM_KEY, OptionHandler.ENGINE_OPTION_ID).equals("false"))
			if(OptionHandler.getProperty(GraphicOptions.USEVIEWMATRIX_KEY, OptionHandler.ENGINE_OPTION_ID).equals("false"))
				ExceptionThrower.throwException(new OptionDisabledButStillUsedException());
		this.camera = camera;
	}
	
	public Messager getMessagerInctance() {
		return messager;
	}
	
	public AbstractUpdater getUpdaterInctance() {
		return updater;
	}
	
	public void setUpdaterInctance(AbstractUpdater updater) {
		this.updater = updater;
	}
}
