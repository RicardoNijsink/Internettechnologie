package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Model {

	private ArrayList<ClientInfo> clients;
	
	public Model() {
		clients = new ArrayList<ClientInfo>();
	}

	public void addClient(PrintWriter writer, String username) {
		clients.add(new ClientInfo(writer, username));
	}

	public ArrayList<ClientInfo> getClients() {
		ArrayList<ClientInfo> copiedClients = new ArrayList<ClientInfo>();

		for (ClientInfo c : clients) {
			copiedClients.add(c);
		}

		return copiedClients;
	}

	public void removeClient(String username) {
		try {
			for (ClientInfo c : clients) {
				if (c.getUsername().equals(username)) {
					clients.remove(c);
				}
			}
		} catch (ConcurrentModificationException e) {

		}
	}
}
