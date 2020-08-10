package messages;

import server.message;

public class userValid extends message{

	
	private static final long serialVersionUID = 1L;
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public userValid(String none, int value) {
		super(none);
		this.value = value;
	}

}
