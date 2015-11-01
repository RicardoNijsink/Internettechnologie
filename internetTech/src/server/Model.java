package server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Class voor het bijhouden van alle clients
 * @author Ricardo
 *
 */
public class Model {
	private ArrayList<ClientInfo> clients;
	
	/**
	 * Constructor van het model.
	 * Hier wordt de lijst voor het opslaan van de clients aangemaakt.
	 */
	public Model() {
		clients = new ArrayList<ClientInfo>();
	}

	/**
	 * Methode voor het toevoegen van een client
	 * @param writer De writer van de client
	 * @param username De gebruikersnaam van de client
	 */
	public void addClient(PrintWriter writer, String username) {
		clients.add(new ClientInfo(writer, username));
	}

	/**
	 * Methode voor het ophalen van de lijst met clients
	 * @return De arraylist met alle clients
	 */
	public ArrayList<ClientInfo> getClients() {
		ArrayList<ClientInfo> copiedClients = new ArrayList<ClientInfo>();

		for(ClientInfo c : clients) {
			copiedClients.add(c);
		}

		return copiedClients;
	}

	/**
	 * Methode voor het verwijderen van een client
	 * @param username De gebruikersnaam van de client
	 */
	public void removeClient(String username) {
		try {
			for(ClientInfo c : clients){
				if(c.getUsername().equals(username)){
					clients.remove(c);
				}
			}
		} catch(ConcurrentModificationException e) {
			e.printStackTrace();
		}
	}
}
