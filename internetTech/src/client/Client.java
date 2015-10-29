package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Class voor de afhandeling van de communicatie aan de clientkant
 * @author Ricardo
 *
 */
public class Client {
	private String SERVER_ADDRESS = "localhost"; 
	private String username;
	
	public Client(){
		try {
			Socket socket = new Socket(SERVER_ADDRESS, 110);
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream);
			ServerThread ct = this.new ServerThread(socket.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner in = new Scanner(System.in);
			
			//kiezen van een username
			System.out.println("enter username: ");
			boolean usernameNotAccepted = true;
			
			while (usernameNotAccepted) {
				username = in.nextLine();
				if(username.contains("\"")) {
					System.out.println("username can not contain <\">, try again");
				} 
				else {
					writer.println(username);
					writer.flush();
					String returnValue = reader.readLine();
					if(returnValue.equals("1")){
						usernameNotAccepted = false;
					}
					else{
						System.out.println("username already taken");
					}
				}
			}
			
			ct.start();
			System.out.println("your username = "+username);
			System.out.println("to send a message put qoutes around it");
			System.out.println("to send a private message put pm <usermame> in front of the message");
			System.out.println("to log out type \"log out\"");
			//start sending messages
			boolean notStoped = true;
			
			while(notStoped){
				String input = in.nextLine();
				if(input.equals("log out")){
					socket.close();
					notStoped=false;
				} 
				else{
					writer.println(" " + input);
					writer.flush();
				}
			}

		} catch(IOException e) {
			if(e instanceof SocketException){
				System.out.println("loged out");
			}
			else{
			e.printStackTrace();
			}
		}
	}

	/**
	 * Thread voor het luisteren naar input.
	 * Nadat er input is gegeven, wordt deze geprint.
	 * @author auke
	 *
	 */
	public class ServerThread extends Thread {
		private InputStream inputStream;

		public ServerThread(InputStream inputStream) {
			this.inputStream =inputStream;
		}

		public void run() {
			BufferedReader reader;

			try {
				reader = new BufferedReader(new InputStreamReader(
						inputStream));
				while(true) {
					String line = reader.readLine();
					System.out.println(line);
				}
			} catch(IOException e) {
				if (!(e instanceof SocketException)){
					e.printStackTrace();
				}
			}
		}
	}
	
}
