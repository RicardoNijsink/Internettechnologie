package server;

import java.io.PrintWriter;

/**
 * Class voor het bijhouden van de informatie van de client
 * @author Ricardo
 *
 */
public class ClientInfo {
	private PrintWriter writer;
	private String username;
	
	/**
	 * Constructor van een client met informatie meegegeven.
	 * @param writer De writer van de client
	 * @param username De gebruikersnaam van de client
	 */
	public ClientInfo(PrintWriter writer, String username) {
		super();
		this.writer = writer;
		this.username = username;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public String getUsername() {
		return username;
	}

}
