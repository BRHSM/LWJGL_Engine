package Tests.Tests;

import org.lwjglx.util.vector.Vector3f;

import Engine.Core.Core.AbstractUpdater;
import Engine.Core.Core.DataObject;
import Engine.Core.Core.IDGenerator;
import Engine.Core.Core.Initializer;
import Engine.Data.EntityHandeling.AbstractEntityStructure;
import Engine.Data.EntityHandeling.BasicEntityModifier;
import Engine.Data.InternalMessager.MessageIdentiriers;
import Engine.Data.ModelHandeling.OBJLoader;
import Engine.Data.ModelHandeling.OBJModel;
import Engine.Data.ModelHandeling.TexturedModelStructure;
import Engine.IO.KeyboardHandeling.KeyStrokeHandler;
import Engine.IO.KeyboardHandeling.Keys;
import Tests.Overwrites.TestCamera_1;
/** A basic test to see if the engine works with no data provided. This is
 *  the bare minimum of code for a "game" to launch.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class Test_9 {
	
	private static Initializer init; 	
	
	/** Starts execution.
	 * @param args terminal arguments
	*/
	public static void test() {
		init = new Initializer();
		
		//Setup DataObject.
		DataObject object = new DataObject();
		
		IDGenerator generator = new IDGenerator();
		
		int id = generator.generateID();
		int id2 = generator.generateID();
		
		//setup modle loader. 
		OBJModel tmpModel = OBJLoader.loadOBJ("stall", id);
		tmpModel.loadTexture("stallTexture");
		TexturedModelStructure modelStructure = tmpModel.convertToTexturedModelStructure();
		AbstractEntityStructure entityStructure = new AbstractEntityStructure(modelStructure, new Vector3f(0, -2, -50), 0, 0, 0, 1, id2);
		entityStructure.setEntityModifier(new BasicEntityModifier(new Vector3f(0,0,0),0,1,0,0));
		
		object.addEntity(entityStructure);
		object.setCamera(new TestCamera_1());
		object.setUpdaterInctance(new AbstractUpdater() {
			@Override
			public void update() {
				if(KeyStrokeHandler.isKeyDown(Keys.KEY_0))
					object.getMessagerInctance().sendMessage(MessageIdentiriers.TOGGLE_UPDATE, id2);
			}
		});
		
		init.start(object);
	}
}
