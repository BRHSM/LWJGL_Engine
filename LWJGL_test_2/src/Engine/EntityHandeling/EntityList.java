package Engine.EntityHandeling;

import java.util.ArrayList;

import Engine.Exceptions.ExceptionThrower;
import Engine.Exceptions.ModelsAlreadyConvertedException;
import Engine.ModelHandeling.AbstractModel;
import Engine.ModelHandeling.AbstractModelStructure;
import Engine.ModelHandeling.BasicModel;
import Engine.ModelHandeling.BasicModelLoader;
import Engine.ModelHandeling.BasicModelStructure;
import Engine.ModelHandeling.ModelList;
import Engine.ModelHandeling.ModelTexture;
import Engine.ModelHandeling.TexturedModel;
import Engine.ModelHandeling.TexturedModelLoader;
import Engine.ModelHandeling.TexturedModelStructure;

public class EntityList {
	
	/** List of modelStructures.
	 */
	ArrayList<AbstractEntityStructure> entityStructures = new ArrayList<AbstractEntityStructure>();
	/** List of models.
	 */
	ArrayList<AbstractEntity> entities = new ArrayList<AbstractEntity>();
	/** boolean to tell if the entityStructures are converted.
	 */
	private boolean isConverted = false;
	
	public EntityList(ArrayList<AbstractEntityStructure> entityStructures) {
		this.entityStructures = entityStructures;
	}
	
	public void convertToEntities() {
		BasicModelLoader basicModelLoader = new BasicModelLoader();
		TexturedModelLoader texturedModelLoader = new TexturedModelLoader();
		
		isConverted = true;
		
		for(AbstractEntityStructure entityStructure: this.entityStructures) {
			AbstractModelStructure modelStructure = entityStructure.getModelStructure();
			if(modelStructure instanceof BasicModelStructure) {
				BasicModel model = basicModelLoader.loadToVAO(modelStructure.getVertices(), modelStructure.getIndexes());
				AbstractEntity entity = new AbstractEntity(model, entityStructure.getPosition(), entityStructure.getRx(), entityStructure.getRy(), entityStructure.getRz(), entityStructure.getScale());
				entities.add(entity);
			}
			if(modelStructure instanceof TexturedModelStructure) {
				BasicModel model = texturedModelLoader.loadToVAO(modelStructure.getVertices(), ((TexturedModelStructure) modelStructure).getTextureCoordinates(), modelStructure.getIndexes());
				ModelTexture texture = new ModelTexture(texturedModelLoader.loadTexture(((TexturedModelStructure)modelStructure).getTextureName()));
				TexturedModel texturedModel = new TexturedModel(model,texture);
				AbstractEntity entity = new AbstractEntity(texturedModel, entityStructure.getPosition(), entityStructure.getRx(), entityStructure.getRy(), entityStructure.getRz(), entityStructure.getScale());
				entities.add(entity);
			}
		}
	}
	
	public void addModelStructure(AbstractEntityStructure entityStructure) {
		if(isConverted) {
			ExceptionThrower.throwException(new ModelsAlreadyConvertedException());
		}
		entityStructures.add(entityStructure);
	}

	public ArrayList<AbstractEntity> getEntities() {
		return entities;
	}
}
