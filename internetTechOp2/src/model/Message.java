package model;

public class Message {
	private String message;
	private int octets;
	
	public Message(String message) {
		this.message = message;
		this.octets = message.getBytes().length;
	}

	public String getMessage() {
		return message;
	}

	public int getLength() {
		return octets;
	}

}
