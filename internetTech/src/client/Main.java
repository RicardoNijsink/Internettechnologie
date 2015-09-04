package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
	private static String SERVER_ADDRESS = "localhost"; 

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(SERVER_ADDRESS, server.main.PORTNUMER);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			// Verstuur een regel tekst
			PrintWriter writer = new PrintWriter(outputStream);
			writer.println( "kaas" );
			writer.flush(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
