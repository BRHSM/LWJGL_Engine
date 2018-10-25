package Tests.Tests;

import Engine.Core.Core.DataObject;
import Engine.Core.Core.IDGenerator;
import Engine.Core.Core.Initializer;
import Engine.Data.ModelHandeling.TexturedModelStructure;
/** Testing the texturedModel.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class Test_3 {
	
	private static Initializer init; 	
	
	/** Starts execution.
	 * @param args terminal arguments
	*/
	public static void test() {
		init = new Initializer();
		
		IDGenerator generator = new IDGenerator();
		
		//create a basic model. 
		float[] vertices = {
			    -0.5f, 0.5f, 0f,
			    -0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    0.5f, 0.5f, 0f,
			  };
		//create a basic model index list.
		int[] indexes = {
				//left Bot
				0,1,3,
				//right top
				3,1,2
		};
		
		float[] textureCoordinates = {
				0f,0f,
				0f,1f,
				1f,1f,
				1f,0f
		};
		
		int id = generator.generateID();
		
		//setup model loader. 
		TexturedModelStructure modelStructure = new TexturedModelStructure(vertices, textureCoordinates, indexes, id);
		
		//Setup DataObject.
		DataObject object = new DataObject();
		object.addModel(modelStructure);
		
		init.start(object);
	}
}
