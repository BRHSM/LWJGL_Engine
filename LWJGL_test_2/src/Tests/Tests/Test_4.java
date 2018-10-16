package Tests.Tests;

import Engine.Core.DataObject;
import Engine.Core.Initializer;
import Engine.ModelHandeling.TexturedModelStructure;
import Engine.OptionManager.EngineOptions;
import Engine.OptionManager.OptionHandler;
/** Testing the ability to change options during execution.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class Test_4 {
	
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
		
		float[] textureCoordinates = {
				0f,0f,
				0f,1f,
				1f,1f,
				1f,0f
		};
		
		//setup modle loader. 
		TexturedModelStructure modelStructure = new TexturedModelStructure(vertices, textureCoordinates, indexes);
		
		//Setup DataObject.
		DataObject object = new DataObject();
		OptionHandler.setProperty(EngineOptions.DEBUGENABLED_KEY, OptionHandler.ENGINE_OPTION_ID, "0");
		object.addModel(modelStructure);
		
		init.start(object);
	}
}
