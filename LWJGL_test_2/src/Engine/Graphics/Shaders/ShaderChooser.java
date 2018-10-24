package Engine.Graphics.Shaders;

import Engine.Data.OptionManager.EngineOptions;
import Engine.Data.OptionManager.GraphicOptions;
import Engine.Data.OptionManager.OptionHandler;
import Engine.Data.OptionManager.RuntimeOptions;

public class ShaderChooser {
	public static final int VERTEX_TYPE = 301;
	public static final int FRAGMENT_TYPE = 302;
	
	public static String getUsableShaderFileName(int type, boolean isModelShader) {
		//load custom set shader.
		if(OptionHandler.getProperty(EngineOptions.SHADERUSECUSTOM_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
			if(type == VERTEX_TYPE) {
				return OptionHandler.getProperty(EngineOptions.SHADERCUSTOMVERTEXNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".vs";
			}
			if(type == FRAGMENT_TYPE) {
				return OptionHandler.getProperty(EngineOptions.SHADERCUSTOMFRAGMENTNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".fs";
			}
		} else {
			//load shaders in automatic mode.
			if(OptionHandler.getProperty(EngineOptions.SHADERAUTOSELECT_KEY, OptionHandler.ENGINE_OPTION_ID).equals("true")) {
				if(!isModelShader) {
					if(OptionHandler.getProperty(RuntimeOptions.USESVIEWMATRIX_KEY, OptionHandler.RUNTIME_OPTIONS_ID).equals("true")) {
						if(type == VERTEX_TYPE) {
							return OptionHandler.getProperty(EngineOptions.SHADERVINAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".vs";
						}
						if(type == FRAGMENT_TYPE) {
							return OptionHandler.getProperty(EngineOptions.SHADERVINAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".fs";
						}
					}
					if(OptionHandler.getProperty(RuntimeOptions.USESPROJECTIONMATRIX_KEY, OptionHandler.RUNTIME_OPTIONS_ID).equals("true")) {
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
			//manual loading.
			} else {
				//load entityshaders
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
				//load defaults
				if(type == VERTEX_TYPE) {
					return OptionHandler.getProperty(EngineOptions.SHADERDEFAULTNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".vs";
				}
				if(type == FRAGMENT_TYPE) {
					return OptionHandler.getProperty(EngineOptions.SHADERDEFAULTNAME_KEY, OptionHandler.ENGINE_OPTION_ID) + ".fs";
				}
			}
		}
		return null;
	}
}
