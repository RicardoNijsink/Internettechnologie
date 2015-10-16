package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class voor het bijhouden van de gegevens van een gebruiker
 * @author Ricardo
 *
 */
public class User {
	private String name;
	private String password;
	private ArrayList<Message> messages = new ArrayList<Message>();
	private boolean isLocked = false;
	
	/**
	 * Constructor van een gebruiker
	 * @param name De naam van de gebruiker
	 * @param password Het wachtwoord van de gebruiker
	 */
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		messages.add(new Message("kaas.nl", "imand anders", "mij", "tset", dateFormat.format(date)));
		messages.add(new Message("kaas.nl", "imand anders", "mij", "tset2", dateFormat.format(date)));
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
	
	/**
	 * Methode voor het ophalen van het aantal berichten
	 * @return Het aantal berichten van de gebruiker
	 */
	public int getMessageCount() {
		int messageCount = 0;
		for(Message m : messages){
			messageCount++;
		}
		return messageCount;
	}
	
	/**
	 * Methode voor het ophalen van de lengte van een bericht
	 * @return De totale lengte van een bericht
	 */
	public int getTotalMessageLength() {
		int totalMessageLength = 0;
		
		for(Message m : messages){
			totalMessageLength += m.getLength();
		
		}
		return totalMessageLength;
	}
	
	/**
	 * Methode voor het toevoegen van een bericht
	 * @param message Het toe te voegen bericht
	 */
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	/**
	 * Methode voor het locken van een gebruiker aan de server
	 */
	public void lockUser() {
		this.isLocked = true;
	}
	
	/**
	 * Methode voor het unlocken van een gebruiker van de server
	 */
	public void unlockUser() {
		//TODO delete shit
		this.isLocked = false;
	}
	
	/**
	 * Methode voor het ophalen van een bericht aan de hand van de positie
	 * @param pos De positie van het bericht
	 * @return Het gevonden bericht. Anders null.
	 */
	public Message getMessage(int pos){
		Message m = null;
		m = messages.get(pos);
		return m;
	}
	
	/**
	 * Methode voor het verwijderen van berichten die gemarkeerd zijn om verwijderd te worden
	 */
	public void deleteMessages(){
		for (Message m : messages){
			if (m.isDeleted()){
				messages.remove(m);
			}
		}
	}
	
	/**
	 * Methode voor het verwijderen van alle markeringen
	 */
	public void unmark (){
		for (Message m : messages){
			if (m.isDeleted()){
				m.unMark();
			}
		}
	}

}
