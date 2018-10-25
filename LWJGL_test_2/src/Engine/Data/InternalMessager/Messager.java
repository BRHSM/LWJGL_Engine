package Engine.Data.InternalMessager;

import java.util.ArrayList;

import Engine.Data.EntityHandeling.AbstractEntity;
import Engine.Data.ModelHandeling.AbstractModel;

public class Messager {
	
	private ArrayList<AbstractModel> modelsList;
	private ArrayList<AbstractEntity> entitiesList;
	
	public Messager(ArrayList<AbstractModel> modelsList, ArrayList<AbstractEntity> entitiesList) {
		this.modelsList = modelsList;
		this.entitiesList = entitiesList;
	}
	
	public void sendMessage(int type, byte[] data, int ID) {
		for(AbstractModel model : modelsList) {
			if(model.getID = ID) {
				model.sendMessage(new Message(type, data));
				return;
			}
		}
		for(AbstractEntity entity : entitiesList) {
			if(entity.getID = ID) {
				entity.sendMessage(new Message(type, data));
				return;
			}
		}
	}
	
	public void sendMessage(int type, int ID) {
		for(AbstractModel model : modelsList) {
			if(model.getID = ID) {
				model.sendMessage(new Message(type, null));
				return;
			}
		}
		for(AbstractEntity entity : entitiesList) {
			if(entity.getID = ID) {
				entity.sendMessage(new Message(type, null));
				return;
			}
		}
	}
}
