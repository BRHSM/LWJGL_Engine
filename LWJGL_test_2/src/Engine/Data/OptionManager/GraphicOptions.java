package Engine.Data.OptionManager;

import java.util.ArrayList;

public class GraphicOptions extends AbstractOptions{
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
	
	private static OptionReader loader;
	
	public static void loadFromFile(String filename) {
		loader = new OptionReader(filename);
		
		keyList = new ArrayList<String>();
		//load properties
		
		addProperty(WINDOWHEIGHT_KEY);
		addProperty(WINDOWWIDTH_KEY);
		addProperty(WINDOWALLIGNMENT_KEY);
		addProperty(WINDOWFULLSCREEN_KEY);
		addProperty(WINDOWFOV_KEY);
		addProperty(WINDOWNEARPLANE_KEY);
		addProperty(WINDOWFARPLANE_KEY);
		addProperty(FRAMECAP_KEY);
		addProperty(USEPROJECTIONMARTRIX_KEY);
		addProperty(USEVIEWMATRIX_KEY);
	}
	
	public static boolean isInKeyList(String propertyKey) {
		return keyList.contains(propertyKey);
	}
	
	protected static void addProperty(String key) {
		String tmp = loader.getProperty(key);
		keyList.add(key);
		properties.put(key, tmp);
	}
}
