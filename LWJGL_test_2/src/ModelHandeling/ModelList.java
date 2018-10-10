package ModelHandeling;

import java.util.ArrayList;

import Exceptions.ExceptionThrower;
import Exceptions.ModelsAlreadyConvertedException;

/** Class used to hold all the models before and after convertion from ModelStructure.
 * 
 * @author Bram Steenebrgen
 * @version 1.0
 * @since 1.0
 * @see AbstractModelStructure
 * @see AbstractModel
 */
public class ModelList {
	
	/** List of modelStructures.
	 */
	ArrayList<AbstractModelStructure> modelStructures = new ArrayList<AbstractModelStructure>();
	/** List of models.
	 */
	ArrayList<AbstractModel> models = new ArrayList<AbstractModel>();
	
	/** Create a new empty model list.
	 */
	private boolean isConverted = false;
	
	public ModelList() {
		//create Model List
	}
	
	/** Create a copy of a modelStructureList from ArrayList.
	 * 
	 * @param modelStructureList the list to copy modelStructures from from.
	 */
	public ModelList(ArrayList<AbstractModelStructure> modelStructureList) {
		this.modelStructures = modelStructureList;
	}
	
	/** Convert the ModelStructures to models.
	 */
	public void ConvertToModels() {
		// create ModelLoaders.
		BasicModelLoader basicModelLoader = new BasicModelLoader();
		TexturedModelLoader texturedModelLoader = new TexturedModelLoader();
		
		isConverted = true;
		
		//Loop through modelStructureList and add models to models(the list) via their respective loaders.  
		for(AbstractModelStructure modelStructure: this.modelStructures) {
			if(modelStructure instanceof BasicModelStructure) {
				BasicModel model = basicModelLoader.loadToVAO(modelStructure.getVertices(), modelStructure.getIndexes());
				models.add(model);
			}
			if(modelStructure instanceof TexturedModelStructure) {
				BasicModel model = texturedModelLoader.loadToVAO(modelStructure.getVertices(), ((TexturedModelStructure) modelStructure).getTextureCoordinates(), modelStructure.getIndexes());
				ModelTexture texture = new ModelTexture(texturedModelLoader.loadTexture(((TexturedModelStructure)modelStructure).getTextureName()));
				TexturedModel texturedModel = new TexturedModel(model,texture);
				models.add(texturedModel);
			}
		}
		//Clear ModelStructure List
		modelStructures.clear();
	}
	/** Add a modelStructure to the list.
	 * 
	 * @param modelStructure The modelStructure to add.
	 */
	public void addModelStructure(AbstractModelStructure modelStructure) {
		if(isConverted) {
			ExceptionThrower.throwException(new ModelsAlreadyConvertedException());
		}
		modelStructures.add(modelStructure);
	}
	
	/** Get the list of modelStructures.
	 * 
	 * @return The list of modelStructures.
	 */
	public ArrayList<AbstractModelStructure> getModelStructures() {
		return modelStructures;
	}

	/** Get the list of modelStructures.
	 * 
	 * @return The list of modelStructures.
	 */
	public ArrayList<AbstractModel> getModels() {
		return models;
	}
}
