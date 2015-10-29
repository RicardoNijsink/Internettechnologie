package model;

import java.util.ArrayList;

/**
 * Class voor het bijhouden van alle gebruikers
 * @author Ricardo
 *
 */
public class PopModel {
	private ArrayList<User> users = new ArrayList<>();
	
	/**
	 * Constructor van het model
	 * Hier worden alle gebruikers aan het model toegevoegd
	 */
	public PopModel() {
		users.add(new User("ikke@kaas.com", "ikke"));
		users.add(new User("jij@kaas.com", "jij"));
	}

	public void addUser(String name, String password) {
		users.add(new User(name, password));
	}
	
	/**
	 * Methode voor het ophalen van alle gebruikers 
	 * @return De arraylist met alle gebruikers
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * Methode voor het ophalen van een gebruiker aan de hand van de naam
	 * @param name De naam van de op te halen gebruiker
	 * @return De gevonden gebruiker. Anders null.
	 */
	public User getUser(String name) {
		User user = null;
		for(User u : users){
			if(u.getName().equals(name)){
				user = u;
			}
		}
		return user;
	}

}
