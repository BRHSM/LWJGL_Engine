package EntityHandeling;

import GraphicsEngine.AbstractShader;

public abstract class AbstractEntityRenderer {
	public abstract void prepare();
	public abstract void render(AbstractEntity model, AbstractShader shader);
}
