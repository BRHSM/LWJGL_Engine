package Engine.Data.InternalMessager;

import java.util.ArrayList;

import Engine.Data.EntityHandeling.AbstractEntity;
import Engine.Data.EntityHandeling.EntityList;
import Engine.Data.ModelHandeling.AbstractModel;
import Engine.Data.ModelHandeling.ModelList;

public class Messager {
	
	private ModelList modelsList;
	private EntityList entitiesList;
	
	public Messager() {
	}
	
	public void sendMessage(int type, Object[] data, int ID) {
		for(AbstractModel model : modelsList.getModels()) {
			if(model.getID() == ID) {
				model.sendMessage(new Message(type, data));
				return;
			}
		}
		for(AbstractEntity entity : entitiesList.getEntities()) {
			if(entity.getID() == ID) {
				entity.sendMessage(new Message(type, data));
				return;
			}
		}
	}
	
	public void sendMessage(int type, int ID) {
		for(AbstractModel model : modelsList.getModels()) {
			if(model.getID() == ID) {
				model.sendMessage(new Message(type, null));
				return;
			}
		}
		for(AbstractEntity entity : entitiesList.getEntities()) {
			if(entity.getID() == ID) {
				entity.sendMessage(new Message(type, null));
				return;
			}
		}
	}

	public void setModelsList(ModelList modelList) {
		this.modelsList = modelList;
	}

	public void setEntitiesList(EntityList modelList) {
		this.entitiesList = modelList;
	}
}
