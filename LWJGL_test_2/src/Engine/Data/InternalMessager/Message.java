package Engine.Data.InternalMessager;

public class Message {
	private int identifier;
	private Object[] data;
	
	public Message(int identifier, Object[] data2) {
		super();
		this.identifier = identifier;
		this.data = data2;
	}

	public int getIdentifier() {
		return identifier;
	}

	public Object[] getData() {
		return data;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public void setData(Object[] data) {
		this.data = data;
	}	
}
