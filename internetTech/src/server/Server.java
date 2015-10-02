package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server extends ServerSocket {

	public Server(int port, Model model) throws IOException {
		super(port);
		while (true) {
			Socket socket = this.accept();
			//System.out.println("kaas");
			ClientThread ct = new ClientThread(socket, model);
			ct.start(); // start de thread en roept run() aan. Gebruik hier niet
			// run(): dan wordt de code in de huidige thread gedraaid.

		}
	}

	public class ClientThread extends Thread {
		private Socket socket;
		private Model model;

		public ClientThread(Socket socket, Model model) {
			this.socket = socket;
			this.model = model;
		}

		public void run() {
			BufferedReader reader;

			String username = null;
			try {
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				// checking if username is valid
				boolean usernameAccepted = false;
				while (!usernameAccepted) {
					username = reader.readLine();
					boolean usernameInUse = false;
					for (ClientInfo c : model.getClients()) {
						if (c.getUsername().equals(username)) {
							usernameInUse = true;
						}
					}
					if (!usernameInUse) {
						usernameAccepted = true;
						writer.println("1");
						writer.flush();
					} else {
						writer.println("0");
						writer.flush();
					}
				}
				if (username != null) {
					model.addClient(writer, username);
					while (true) {
						String line = reader.readLine();
						if (line==null){
							SocketException e = new SocketException();
							throw e;
						}
						Scanner in = new Scanner(line);

						in.useDelimiter("\"");
						if (in.hasNext()) {
							String premessage = in.next();

							if (in.hasNext()) {
								String message = in.next();
								while (in.hasNext()) {
									message = message + "\"" + in.next();
								}

								// -------
								while (premessage.startsWith(" ")) {
									premessage = premessage.substring(1,
											premessage.length());
								}
								while (premessage.endsWith(" ")) {
									premessage = premessage.substring(0,
											premessage.length() - 1);
								}
								// private message
								if (premessage.startsWith("pm <")
										&& premessage.endsWith(">")) {
									String pmUsername = premessage.substring(4,
											premessage.length() - 1);
									boolean foundUser = false;

									for (ClientInfo c : model.getClients()) {
										if (c.getUsername().equals(pmUsername)) {
											foundUser = true;
											PrintWriter writer2 = c.getWriter();
											writer2.println(username
													+ " wispered to you: "
													+ message);
											writer2.flush();

											writer.println("you wispered to "
													+ pmUsername + ": "
													+ message);
											writer.flush();
										}
									}
									if (!foundUser) {
										writer.println("user not found");
										writer.flush();
									}
									// algemene message
								} else {
									for (ClientInfo c : model.getClients()) {
										if (c.getUsername().equals(username)) {
											writer.println("you" + " : "
													+ message);
											writer.flush();
										} else {
											c.getWriter().println(username + " : "
													+ message);
											c.getWriter().flush();
										}
									}
								}

								// ------
							} else {
								writer.println("invalid message");
								writer.flush();
							}
						} else {
							writer.println("invalid message");
							writer.flush();
						}

					}
				}else{
					System.out.println("unreacheble code");
				}
			} catch (IOException e) {
				if (e instanceof SocketException){
					if (username!=null){
						model.removeClient(username);
					}
				}else{
				}
			}

			// 1. Wacht op berichten van de client.
			// 2. Stuur berichten van de clients door naar de andere
			// clients. (Broadcast)
		}
	}

}
