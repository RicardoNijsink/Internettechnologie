package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
	private String name;
	private String password;
	private ArrayList<Message> messages = new ArrayList<Message>();
	private boolean isLocked = false;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		messages.add(new Message("kaas.nl", "imand anders", "mij", "tset", dateFormat.format(date)));
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	public int getMessageCount() {
		int messageCount = 0;
		for(Message m : messages){
			messageCount++;
		}
		return messageCount;
	}
	
	public int getTotalMessageLength() {
		int totalMessageLength = 0;
		for(Message m : messages){
			totalMessageLength += m.getLength();
		}
		return totalMessageLength;
	}
	
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	public void lockUser() {
		this.isLocked = true;
	}
	
	public void unlockUser() {
		//TODO delete shit
		this.isLocked = false;
	}
	
	public Message getMessage(int i){
		return messages.get(i);
	}

}
