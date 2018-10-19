package Tests.Tests;

import org.lwjglx.util.vector.Vector3f;

import Engine.Core.DataObject;
import Engine.Core.Initializer;
import Engine.EntityHandeling.AbstractEntityStructure;
import Engine.EntityHandeling.BasicEntityModifier;
import Engine.ModelHandeling.TexturedModelStructure;
/** A basic test to see if the engine works with no data provided. This is
 *  the bare minimum of code for a "game" to launch.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 *
 */
public class Test_6 {
	
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
		AbstractEntityStructure entityStructure = new AbstractEntityStructure(modelStructure, new Vector3f(0, 0, -1), 0, 0, 0, 1);
		entityStructure.setEntityModifier(new BasicEntityModifier(new Vector3f(0,0,-0.01f),0,0,0,0));
		
		//Setup DataObject.
		DataObject object = new DataObject();
		object.addEntity(entityStructure);
		
		init.start(object);
	}
}
