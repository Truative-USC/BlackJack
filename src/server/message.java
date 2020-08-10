package server;

import java.io.Serializable;

public class message implements Serializable  {
	public static final long serialVersionUID = 1;
	private String message;
	public message(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
