package model;

public class Message {
	private String date;
	private String from;
	private String subject;
	private String to;
	
	private String message;
	private int octets;
	private boolean deleted;
	
	
	public Message(String message, String from, String to, String subject, String date) {
		this.from=from;
		this.to=to;
		this.subject=subject;
		this.date=date;
		this.message = message;
		this.octets = message.getBytes().length;
	}

	public String getMessage() {
		return message;
	}

	public int getLength() {
		return octets;
	}
	
	public String getMessageHeader (){
		return "Date: "+date+"\nFrom: "+from+"\nSubject: "+subject+"\nTo: "+to+"\nContent-Type: text/plain";
	}
	
	public void delete(){
		deleted=true;
	}
	
	public boolean isDeleted(){
		return deleted;
	}

}
