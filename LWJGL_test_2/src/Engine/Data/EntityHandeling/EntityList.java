package Engine.Data.EntityHandeling;

import java.util.ArrayList;

import Engine.Data.ModelHandeling.AbstractModel;
import Engine.Data.ModelHandeling.AbstractModelStructure;
import Engine.Data.ModelHandeling.BasicModel;
import Engine.Data.ModelHandeling.BasicModelLoader;
import Engine.Data.ModelHandeling.BasicModelStructure;
import Engine.Data.ModelHandeling.ModelList;
import Engine.Data.ModelHandeling.ModelTexture;
import Engine.Data.ModelHandeling.TexturedModel;
import Engine.Data.ModelHandeling.TexturedModelLoader;
import Engine.Data.ModelHandeling.TexturedModelStructure;
import Engine.Util.Exceptions.ExceptionThrower;
import Engine.Util.Exceptions.ModelsAlreadyConvertedException;

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
				AbstractEntity entity = new AbstractEntity(model, entityStructure.getPosition(), entityStructure.getRx(), entityStructure.getRy(), entityStructure.getRz(), entityStructure.getScale(), entityStructure.getEntityModifier());
				entities.add(entity);
			}
			if(modelStructure instanceof TexturedModelStructure) {
				BasicModel model = texturedModelLoader.loadToVAO(modelStructure.getVertices(), ((TexturedModelStructure) modelStructure).getTextureCoordinates(), modelStructure.getIndexes());
				ModelTexture texture = new ModelTexture(texturedModelLoader.loadTexture(((TexturedModelStructure)modelStructure).getTextureName()));
				TexturedModel texturedModel = new TexturedModel(model,texture);
				AbstractEntity entity = new AbstractEntity(texturedModel, entityStructure.getPosition(), entityStructure.getRx(), entityStructure.getRy(), entityStructure.getRz(), entityStructure.getScale(), entityStructure.getEntityModifier());
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
