package model;

import java.util.ArrayList;

public class User {
	private String name;
	private String password;
	private ArrayList<Message> messages;
	private boolean isLocked = false;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
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
		this.isLocked = false;
	}

}
