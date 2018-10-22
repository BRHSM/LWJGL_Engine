package Engine.IO.KeyboardHandeling;

import org.lwjgl.glfw.GLFW;

import Engine.Util.Exceptions.AbstractException;

/** This class contains a list of constants used to identify keys.
 * 
 * @author Bram Steenbergen
 * @version 1.0
 * @since 1.0
 */
public class Keys {
	//Number keys
	/** The 0 key on the keyboard.
	 */
	public static final int KEY_0 = GLFW.GLFW_KEY_0;
	/** The 1 key on the keyboard.
	 */
	public static final int KEY_1 = GLFW.GLFW_KEY_1;
	/** The 2 key on the keyboard.
	 */
	public static final int KEY_2 = GLFW.GLFW_KEY_2;
	/** The 3 key on the keyboard.
	 */
	public static final int KEY_3 = GLFW.GLFW_KEY_3;
	/** The 4 key on the keyboard.
	 */
	public static final int KEY_4 = GLFW.GLFW_KEY_4;
	/** The 5 key on the keyboard.
	 */
	public static final int KEY_5 = GLFW.GLFW_KEY_5;
	/** The 6 key on the keyboard.
	 */
	public static final int KEY_6 = GLFW.GLFW_KEY_6;
	/** The 7 key on the keyboard.
	 */
	public static final int KEY_7 = GLFW.GLFW_KEY_7;
	/** The 8 key on the keyboard.
	 */
	public static final int KEY_8 = GLFW.GLFW_KEY_8;
	/** The 9 key on the keyboard.
	 */
	public static final int KEY_9 = GLFW.GLFW_KEY_9;
	
	//letter keys
	/** The A key on the keyboard.
	 */
	public static final int KEY_A = GLFW.GLFW_KEY_A;
	/** The B key on the keyboard.
	 */
	public static final int KEY_B = GLFW.GLFW_KEY_B;
	/** The C key on the keyboard.
	 */
	public static final int KEY_C = GLFW.GLFW_KEY_C;
	/** The D key on the keyboard.
	 */
	public static final int KEY_D = GLFW.GLFW_KEY_D;
	/** The E key on the keyboard.
	 */
	public static final int KEY_E = GLFW.GLFW_KEY_E;
	/** The F key on the keyboard.
	 */
	public static final int KEY_F = GLFW.GLFW_KEY_F;
	/** The G key on the keyboard.
	 */
	public static final int KEY_G = GLFW.GLFW_KEY_G;
	/** The H key on the keyboard.
	 */
	public static final int KEY_H = GLFW.GLFW_KEY_H;
	/** The I key on the keyboard.
	 */
	public static final int KEY_I = GLFW.GLFW_KEY_I;
	/** The J key on the keyboard.
	 */
	public static final int KEY_J = GLFW.GLFW_KEY_J;
	/** The K key on the keyboard.
	 */
	public static final int KEY_K = GLFW.GLFW_KEY_K;
	/** The L key on the keyboard.
	 */
	public static final int KEY_L = GLFW.GLFW_KEY_L;
	/** The M key on the keyboard.
	 */
	public static final int KEY_M = GLFW.GLFW_KEY_M;
	/** The N key on the keyboard.
	 */
	public static final int KEY_N = GLFW.GLFW_KEY_N;
	/** The O key on the keyboard.
	 */
	public static final int KEY_O = GLFW.GLFW_KEY_O;
	/** The P key on the keyboard.
	 */
	public static final int KEY_P = GLFW.GLFW_KEY_P;
	/** The Q key on the keyboard.
	 */
	public static final int KEY_Q = GLFW.GLFW_KEY_Q;
	/** The R key on the keyboard.
	 */
	public static final int KEY_R = GLFW.GLFW_KEY_R;
	/** The S key on the keyboard.
	 */
	public static final int KEY_S = GLFW.GLFW_KEY_S;
	/** The T key on the keyboard.
	 */
	public static final int KEY_T = GLFW.GLFW_KEY_T;
	/** The U key on the keyboard.
	 */
	public static final int KEY_U = GLFW.GLFW_KEY_U;
	/** The V key on the keyboard.
	 */
	public static final int KEY_V = GLFW.GLFW_KEY_V;
	/** The W key on the keyboard.
	 */
	public static final int KEY_W = GLFW.GLFW_KEY_W;
	/** The X key on the keyboard.
	 */
	public static final int KEY_X = GLFW.GLFW_KEY_X;
	/** The Y key on the keyboard.
	 */
	public static final int KEY_Y = GLFW.GLFW_KEY_Y;
	/** The Z key on the keyboard.
	 */
	public static final int KEY_Z = GLFW.GLFW_KEY_Z;
	
	//Function keys
	/** The F1 key on the keyboard.
	 */
	public static final int KEY_F1  = GLFW.GLFW_KEY_F1;
	/** The F2 key on the keyboard.
	 */
	public static final int KEY_F2  = GLFW.GLFW_KEY_F2;
	/** The F3 key on the keyboard.
	 */
	public static final int KEY_F3  = GLFW.GLFW_KEY_F3;
	/** The F4 key on the keyboard.
	 */
	public static final int KEY_F4  = GLFW.GLFW_KEY_F4;
	/** The F5 key on the keyboard.
	 */
	public static final int KEY_F5  = GLFW.GLFW_KEY_F5;
	/** The F6 key on the keyboard.
	 */
	public static final int KEY_F6  = GLFW.GLFW_KEY_F6;
	/** The F7 key on the keyboard.
	 */
	public static final int KEY_F7  = GLFW.GLFW_KEY_F7;
	/** The F8 key on the keyboard.
	 */
	public static final int KEY_F8  = GLFW.GLFW_KEY_F8;
	/** The F9 key on the keyboard.
	 */
	public static final int KEY_F9  = GLFW.GLFW_KEY_F9;
	/** The F10 key on the keyboard.
	 */
	public static final int KEY_F10 = GLFW.GLFW_KEY_F10;
	/** The F11 key on the keyboard.
	 */
	public static final int KEY_F11 = GLFW.GLFW_KEY_F11;
	/** The F12 key on the keyboard.
	 */
	public static final int KEY_F12 = GLFW.GLFW_KEY_F12;
	/** The F13 key on the keyboard.
	 */
	public static final int KEY_F13 = GLFW.GLFW_KEY_F13;
	/** The F14 key on the keyboard.
	 */
	public static final int KEY_F14 = GLFW.GLFW_KEY_F14;
	/** The F15 key on the keyboard.
	 */
	public static final int KEY_F15 = GLFW.GLFW_KEY_F15;
	/** The F16 key on the keyboard.
	 */
	public static final int KEY_F16 = GLFW.GLFW_KEY_F16;
	/** The F17 key on the keyboard.
	 */
	public static final int KEY_F17 = GLFW.GLFW_KEY_F17;
	/** The F18 key on the keyboard.
	 */
	public static final int KEY_F18 = GLFW.GLFW_KEY_F18;
	/** The F19 key on the keyboard.
	 */
	public static final int KEY_F19 = GLFW.GLFW_KEY_F19;
	/** The F20 key on the keyboard.
	 */
	public static final int KEY_F20 = GLFW.GLFW_KEY_F20;
	/** The F21 key on the keyboard.
	 */
	public static final int KEY_F21 = GLFW.GLFW_KEY_F21;
	/** The F22 key on the keyboard.
	 */
	public static final int KEY_F22 = GLFW.GLFW_KEY_F22;
	/** The F23 key on the keyboard.
	 */
	public static final int KEY_F23 = GLFW.GLFW_KEY_F23;
	/** The F24 key on the keyboard.
	 */
	public static final int KEY_F24 = GLFW.GLFW_KEY_F24;
	/** The F25 key on the keyboard.
	 */
	public static final int KEY_F25 = GLFW.GLFW_KEY_F25;
	
	//arrow keys
	/** The up key on the keyboard.
	 */
	public static final int KEY_ARROW_UP    = GLFW.GLFW_KEY_UP;
	/** The down key on the keyboard.
	 */
	public static final int KEY_ARROW_DOWN  = GLFW.GLFW_KEY_DOWN;
	/** The left key on the keyboard.
	 */
	public static final int KEY_ARROW_LEFT  = GLFW.GLFW_KEY_LEFT;
	/** The right key on the keyboard.
	 */
	public static final int KEY_ARROW_RIGHT = GLFW.GLFW_KEY_RIGHT;
	
	//numberpad keys
	/** The 0 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_0        = GLFW.GLFW_KEY_KP_0;
	/** The 1 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_1        = GLFW.GLFW_KEY_KP_1;
	/** The 2 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_2        = GLFW.GLFW_KEY_KP_2;
	/** The 3 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_3        = GLFW.GLFW_KEY_KP_3;
	/** The 4 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_4        = GLFW.GLFW_KEY_KP_4;
	/** The 5 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_5        = GLFW.GLFW_KEY_KP_5;
	/** The 6 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_6        = GLFW.GLFW_KEY_KP_6;
	/** The 7 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_7        = GLFW.GLFW_KEY_KP_7;
	/** The 8 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_8        = GLFW.GLFW_KEY_KP_8;
	/** The 9 key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_9        = GLFW.GLFW_KEY_KP_9;
	/** The + key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_ADD      = GLFW.GLFW_KEY_KP_ADD;
	/** The . key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_DECIMAL  = GLFW.GLFW_KEY_KP_DECIMAL;
	/** The / key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_DIVIDE   = GLFW.GLFW_KEY_KP_DIVIDE;
	/** The ENTER key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_ENTER    = GLFW.GLFW_KEY_KP_ENTER;
	/** The = key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_EQUAL    = GLFW.GLFW_KEY_KP_EQUAL;
	/** The * key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_MULTIPLY = GLFW.GLFW_KEY_KP_MULTIPLY;
	/** The - key on the keyboard's numberpad.
	 */
	public static final int KEY_KP_SUBTRACT = GLFW.GLFW_KEY_KP_SUBTRACT;
	
	//other keys
	/** The ' key on the keyboard.
	 */
	public static final int KEY_APOSTROPHE   = GLFW.GLFW_KEY_APOSTROPHE;
	/** The \ key on the keyboard.
	 */
	public static final int KEY_BACKSLASH    = GLFW.GLFW_KEY_BACKSLASH;
	/** The BACKSPACE key on the keyboard.
	 */
	public static final int KEY_BACKSPACE    = GLFW.GLFW_KEY_BACKSPACE;
	/** The CAPS_LOCK key on the keyboard.
	 */
	public static final int KEY_CAPS_LOCK    = GLFW.GLFW_KEY_CAPS_LOCK;
	/** The , key on the keyboard.
	 */
	public static final int KEY_COMMA        = GLFW.GLFW_KEY_COMMA;
	/** The DELETE key on the keyboard.
	 */
	public static final int KEY_DELETE       = GLFW.GLFW_KEY_DELETE;
	/** The END key on the keyboard.
	 */
	public static final int KEY_END          = GLFW.GLFW_KEY_END;
	/** The ENTER key on the keyboard.
	 */
	public static final int KEY_ENTER        = GLFW.GLFW_KEY_ENTER;
	/** The = key on the keyboard.
	 */
	public static final int KEY_EQUAL        = GLFW.GLFW_KEY_EQUAL;
	/** The ESCAPE key on the keyboard.
	 */
	public static final int KEY_ESCAPE       = GLFW.GLFW_KEY_ESCAPE;
	/** The ^ key on the keyboard.
	 */
	public static final int KEY_GRAVE_ACCENT = GLFW.GLFW_KEY_GRAVE_ACCENT;
	/** The HOME key on the keyboard.
	 */
	public static final int KEY_HOME         = GLFW.GLFW_KEY_HOME;
	/** The INSERT key on the keyboard.
	 */
	public static final int KEY_INSERT       = GLFW.GLFW_KEY_INSERT;
	/** The LAST key on the keyboard.
	 */
	public static final int KEY_LAST         = GLFW.GLFW_KEY_LAST;
	/** The [ key on the keyboard.
	 */
	public static final int KEY_L_BRACKET    = GLFW.GLFW_KEY_LEFT_BRACKET;
	/** The LEFT_CONTROL key on the keyboard.
	 */
	public static final int KEY_L_CONTROL    = GLFW.GLFW_KEY_LEFT_CONTROL;
	/** The LEFT_SHIFT key on the keyboard.
	 */
	public static final int KEY_L_SHIFT      = GLFW.GLFW_KEY_LEFT_SHIFT;
	/** The < key on the keyboard.
	 */
	public static final int KEY_L_SUPER      = GLFW.GLFW_KEY_LEFT_SUPER;
	/** The MENU key on the keyboard.
	 */
	public static final int KEY_MENU         = GLFW.GLFW_KEY_MENU;
	/** The NUM_LOCK key on the keyboard.
	 */
	public static final int KEY_NUM_LOCK     = GLFW.GLFW_KEY_NUM_LOCK;
	/** The PAGE_DOWN key on the keyboard.
	 */
	public static final int KEY_PAGE_DOWN    = GLFW.GLFW_KEY_PAGE_DOWN;
	/** The PAGE_UP key on the keyboard.
	 */
	public static final int KEY_PAGE_UP      = GLFW.GLFW_KEY_PAGE_UP;
	/** The PAUSE key on the keyboard.
	 */
	public static final int KEY_PAUSE        = GLFW.GLFW_KEY_PAUSE;
	/** The . key on the keyboard.
	 */
	public static final int KEY_PERIOD       = GLFW.GLFW_KEY_PERIOD;
	/** The PRINT_SCREEN key on the keyboard.
	 */
	public static final int KEY_PRINT_SCREEN = GLFW.GLFW_KEY_PRINT_SCREEN;
	/** The ] key on the keyboard.
	 */
	public static final int KEY_R_BRACKET    = GLFW.GLFW_KEY_RIGHT_BRACKET;
	/** The RIGHT_CONTROL key on the keyboard.
	 */
	public static final int KEY_R_CONTROL    = GLFW.GLFW_KEY_RIGHT_CONTROL;
	/** The RIGHT_SHIFT key on the keyboard.
	 */
	public static final int KEY_R_SHIFT      = GLFW.GLFW_KEY_RIGHT_SHIFT;
	/** The > key on the keyboard.
	 */
	public static final int KEY_R_SUPER      = GLFW.GLFW_KEY_RIGHT_SUPER;
	/** The SCROLL_LOCK key on the keyboard.
	 */
	public static final int KEY_SCROLL_LOCK  = GLFW.GLFW_KEY_SCROLL_LOCK;
	/** The ; key on the keyboard.
	 */
	public static final int KEY_SEMICOLON    = GLFW.GLFW_KEY_SEMICOLON;
	/** The / key on the keyboard.
	 */
	public static final int KEY_SLASH        = GLFW.GLFW_KEY_SLASH;
	/** The SPACE key on the keyboard.
	 */
	public static final int KEY_SPACE        = GLFW.GLFW_KEY_SPACE;
	/** The TAB key on the keyboard.
	 */
	public static final int KEY_TAB          = GLFW.GLFW_KEY_TAB;
}
