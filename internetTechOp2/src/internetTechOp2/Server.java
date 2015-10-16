package internetTechOp2;

import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import model.Message;
import model.PopModel;
import model.User;

public class Server extends ServerSocket {

	private PopModel model;
	
	public Server(int port) throws IOException {
		super(port);
		model = new PopModel();
		while (true) {
			System.out.println("pre connect");
			Socket socket = this.accept();
			System.out.println("post connect");
			ClientThread ct = new ClientThread(socket);
			ct.start(); // start de thread en roept run() aan. Gebruik hier niet
			// run(): dan wordt de code in de huidige thread gedraaid.

		}
	}

	public class ClientThread extends Thread {
		private Socket socket;
		private PrintWriter writer = null;
		private BufferedReader reader = null;
		private User user;
		private boolean running = true;
		
		public ClientThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				writer = new PrintWriter(socket.getOutputStream());
				
				System.out.println("test");
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				AuthState();
				
			} catch (IOException e) {
				
			}
		}
		
		
		private void AuthState(){
			writer.println("+OK POP3 server ready");
			writer.flush();
				
				String input;
				try {
					while(running){
					input = reader.readLine();
					if (input != null) {
					System.out.println("input="+input);
					if (input.equals("QUIT")) {
						writer.println("+OK dewey POP3 server signing off");
						writer.flush();
						running = false;
					} else if (input.equals("AUTH")){
						
						writer.println("+OK user please");
						writer.flush();
						
					}else if (input.startsWith("USER")) {
						String user = "";
						Scanner in = new Scanner(input);
						in.next();
						if (in.hasNext()) {
							user = in.next();
							writer.println("+OK pass please");
							writer.flush();
							System.out.println(user);
							input = reader.readLine();
							System.out.println("input="+input);

							if (input.startsWith("PASS")) {
								String pass = "";
								in = new Scanner(input);
								in.next();
								if (in.hasNext()) {
									
									pass = in.next();
									System.out.println(pass);
									if (Server.this.model.getUser(user)!=null){
										
										if (Server.this.model.getUser(user).getPassword().equals(pass)){
											writer.println("+OK welcome back");
											writer.flush();
											
											this.user=Server.this.model.getUser(user);
											this.user.lockUser();
											TransactionState();
										}else{
											writer.println("-ERR pass incorrect");
											writer.flush();
										}
									}else{
										writer.println("-ERR username incorrect");
										writer.flush();
									}
									
								}
							}

							// TODO rest
						} else {
							System.out.println("userempty");
							writer.println("-ERR user empty");
							writer.flush();
						}
					}else if (input.equals("CAPA")){
						System.out.println("capa found");
						writer.println("-ERR u wot m8");
						writer.flush();
					}else{
						writer.println("-ERR u wot m8");
						writer.flush();
					}
				}

			}
			System.out.println("uit de lus");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		}
		
		
		private void TransactionState(){
			writer.println("+OK POP3 server ready");
			writer.flush();
			boolean inTheLoop = true;
			
				
				String input;
				try {
					while(inTheLoop&&running){
					input = reader.readLine();
					if (input != null) {
					System.out.println("input="+input);
					if (input.equals("QUIT")) {
						writer.println("+OK dewey POP3 server signing off");
						writer.flush();
						user.deleteMessages();
						inTheLoop = false;
					}else if (input.equals("STAT")){
						System.out.println( user.getMessageCount());
						System.out.println( user.getTotalMessageLength());
						writer.println("+OK "+ user.getMessageCount()+" "+ user.getTotalMessageLength());
						writer.flush();
						System.out.println("test");
					}else if (input.equals("LIST")){
						writer.println("+OK "+ user.getMessageCount()+" messages ("+ user.getTotalMessageLength()+" octets)");
						int i = 1;
						for (Message m : user.getMessages()){
							writer.println(i+" "+ m.getLength());
							i++;
						}
						writer.println(".");
						writer.flush();
					}else if (input.contains("RETR")){
						try{
						int i = Integer.parseInt(input.substring(4).trim());
						Message message = user.getMessage(i-1);
						System.out.println(message.getMessage());
						writer.println("+OK "+ message.getLength()+ " octets");
						writer.println(message.getMessageHeader());
						writer.println("\n");
						writer.println(message.getMessage());
						writer.println(".");
						writer.flush();
						}
						catch(NumberFormatException e){
							writer.println("-ERR nietes");
							writer.flush();
						}
					}else if (input.contains("DELE")){
						try{
							int i = Integer.parseInt(input.substring(4).trim());
							user.getMessage(i-1).delete();
							writer.println("+OK DELETED");
							writer.flush();
						}catch(NumberFormatException e){
							writer.println("-ERR nietes");
							writer.flush();
						}
					}else{
						writer.println("-ERR nietes");
						writer.flush();
					}
				}else{
					inTheLoop= false;
				}

			}
			System.out.println("uit de lus");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		}
	}

}

