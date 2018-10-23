package Engine.Graphics.Shaders;

import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;

public class ShaderChooser {
	public static final int VERTEX_TYPE = 301;
	public static final int FRAGMENT_TYPE = 302;
	
	public static String getUsableShaderFileName(int type, boolean isModelShader) {
		if(OptionHandler.getProperty(EngineOptions.SHADERUSECUSTOM_KEY, OptionHandler.ENGINE_OPTION_ID).equals("1")) {
			if(type == VERTEX_TYPE) {
				return OptionHandler.getProperty(EngineOptions.SHADERCUSTOMVERTEXNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".vs";
			}
			if(type == FRAGMENT_TYPE) {
				return OptionHandler.getProperty(EngineOptions.SHADERCUSTOMFRAGMENTNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".fs";
			}
		} else {
			if(!isModelShader) {
				if(OptionHandler.getProperty(GraphicOptions.USEVIEWMATRIX_KEY, OptionHandler.GRAPHIC_OPTION_ID).equals("true")) {
					if(type == VERTEX_TYPE) {
						return OptionHandler.getProperty(EngineOptions.SHADERVINAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".vs";
					}
					if(type == FRAGMENT_TYPE) {
						return OptionHandler.getProperty(EngineOptions.SHADERVINAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".fs";
					}
				}
				if(OptionHandler.getProperty(GraphicOptions.USEPROJECTIONMARTRIX_KEY, OptionHandler.GRAPHIC_OPTION_ID).equals("true")) {
					if(type == VERTEX_TYPE) {
						return OptionHandler.getProperty(EngineOptions.SHADERPRNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".vs";
					}
					if(type == FRAGMENT_TYPE) {
						return OptionHandler.getProperty(EngineOptions.SHADERPRNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".fs";
					}
				}
			}
			if(type == VERTEX_TYPE) {
				return OptionHandler.getProperty(EngineOptions.SHADERDEFAULTNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".vs";
			}
			if(type == FRAGMENT_TYPE) {
				return OptionHandler.getProperty(EngineOptions.SHADERDEFAULTNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".fs";
			}
		}
		return null;
	}
}
