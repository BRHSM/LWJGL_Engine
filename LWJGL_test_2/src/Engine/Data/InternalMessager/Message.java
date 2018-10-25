package Engine.Data.InternalMessager;

public class Message {
	private int identifier;
	private byte[] data;
	
	public Message(int identifier, byte[] data) {
		super();
		this.identifier = identifier;
		this.data = data;
	}

	public int getIdentifier() {
		return identifier;
	}

	public byte[] getData() {
		return data;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public void setData(byte[] data) {
		this.data = data;
	}	
}
