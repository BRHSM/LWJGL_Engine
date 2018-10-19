package Engine.OptionManager;

import java.util.ArrayList;

public class GraphicOptions extends AbstractOptions{
	//values
	private static String windowHeight;
	private static String windowWidth;
	private static String windowAlignment;
	private static String windowFullscreen;
	private static String windowFov;
	private static String windowNearPlane;
	private static String windowFarPlane;
	private static String frameCap;
	private static String useProjectionMatrix;
	private static String useViewMatrix;
	
	//keys
	public static final String WINDOWHEIGHT_KEY = "windowHeight";
	public static final String WINDOWWIDTH_KEY = "windowWidth";
	public static final String WINDOWALLIGNMENT_KEY = "windowAlignment";
	public static final String WINDOWFULLSCREEN_KEY = "windowFullscreen";
	public static final String WINDOWFOV_KEY = "windowFov";
	public static final String WINDOWNEARPLANE_KEY = "windowNearPlane";
	public static final String WINDOWFARPLANE_KEY = "windowFarPlane";
	public static final String FRAMECAP_KEY = "frameCap";
	public static final String USEPROJECTIONMARTRIX_KEY = "useProjectionMatrix";
	public static final String USEVIEWMATRIX_KEY = "useViewMatrix";

	private static ArrayList<String> keyList;
	
	public static void loadFromFile(String filename) {
		OptionReader loader = new OptionReader(filename);
		
		keyList = new ArrayList<String>();
		
		//load properties
		windowHeight = loader.getProperty(WINDOWHEIGHT_KEY);
		windowWidth = loader.getProperty(WINDOWWIDTH_KEY);
		windowAlignment = loader.getProperty(WINDOWALLIGNMENT_KEY);
		windowFullscreen = loader.getProperty(WINDOWFULLSCREEN_KEY);
		windowFov = loader.getProperty(WINDOWFOV_KEY);
		windowNearPlane = loader.getProperty(WINDOWNEARPLANE_KEY);
		windowFarPlane = loader.getProperty(WINDOWFARPLANE_KEY);
		frameCap = loader.getProperty(FRAMECAP_KEY);
		useProjectionMatrix = loader.getProperty(USEPROJECTIONMARTRIX_KEY);
		useViewMatrix = loader.getProperty(USEVIEWMATRIX_KEY);
		
		keyList.add(WINDOWHEIGHT_KEY);
		keyList.add(WINDOWWIDTH_KEY);
		keyList.add(WINDOWALLIGNMENT_KEY);
		keyList.add(WINDOWFULLSCREEN_KEY);
		keyList.add(WINDOWFOV_KEY);
		keyList.add(WINDOWNEARPLANE_KEY);
		keyList.add(WINDOWFARPLANE_KEY);
		keyList.add(FRAMECAP_KEY);
		keyList.add(USEPROJECTIONMARTRIX_KEY);
		keyList.add(USEVIEWMATRIX_KEY);
		
		properties.put(WINDOWHEIGHT_KEY,windowHeight);
		properties.put(WINDOWWIDTH_KEY,windowWidth);
		properties.put(WINDOWALLIGNMENT_KEY,windowAlignment);
		properties.put(WINDOWFULLSCREEN_KEY,windowFullscreen);
		properties.put(WINDOWFOV_KEY,windowFov);
		properties.put(WINDOWNEARPLANE_KEY,windowNearPlane);
		properties.put(WINDOWFARPLANE_KEY,windowFarPlane);
		properties.put(FRAMECAP_KEY,frameCap);	
		properties.put(USEPROJECTIONMARTRIX_KEY,useProjectionMatrix);	
		properties.put(USEVIEWMATRIX_KEY,useViewMatrix);	

	}
	
	public static boolean isInKeyList(String propertyKey) {
		return keyList.contains(propertyKey);
	}
}
