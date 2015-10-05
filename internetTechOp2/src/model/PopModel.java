package model;

import java.util.ArrayList;

public class PopModel {
	private ArrayList<User> users = new ArrayList<>();
	
	public void addUser(String name, String password) {
		users.add(new User(name, password));
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public User getUser(String name) {
		User user = null;
		for(User u : users){
			if(u.getName() == name){
				user = u;
			}
		}
		return user;
	}

}