package server;

import java.io.PrintWriter;

public class ClientInfo {
	private PrintWriter writer;
	private String username;
	
	
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
