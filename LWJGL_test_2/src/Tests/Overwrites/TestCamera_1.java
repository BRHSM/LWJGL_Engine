package Tests.Overwrites;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.input.Keyboard;

import Engine.Graphics.Cameras.AbstractCamera;
import Engine.IO.KeyboardHandeling.KeyStrokeHandler;
import Engine.IO.KeyboardHandeling.Keys;

public class TestCamera_1 extends AbstractCamera{
	 public void move(){
	        if(KeyStrokeHandler.isKeyDown(Keys.KEY_W)){
	            position.z-=0.02f;
	        }
	        if(KeyStrokeHandler.isKeyDown(Keys.KEY_D)){
	            position.x+=0.02f;
	        }
	        if(KeyStrokeHandler.isKeyDown(Keys.KEY_A)){
	            position.x-=0.02f;
	        }
	    }
}
