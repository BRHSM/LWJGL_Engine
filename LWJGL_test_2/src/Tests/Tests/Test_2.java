package Tests.Tests;

import Engine.Core.DataObject;
import Engine.Core.Initializer;
import Engine.ModelHandeling.BasicModelStructure;
/** Testing the BasicModel.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class Test_2 {
	
	private static Initializer init; 	
	
	/** Starts execution.
	 * @param args terminal arguments
	*/
	public static void test() {
		init = new Initializer();
		
		//create a basic model (Trapezoid). 
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
		
		//setup model loader. 
		BasicModelStructure modelStructure = new BasicModelStructure(vertices, indexes);
		
		//Setup DataObject.
		DataObject object = new DataObject();
		object.addModel(modelStructure);
		
		init.start(object);
	}
}

