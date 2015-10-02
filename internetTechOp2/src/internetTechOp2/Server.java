package internetTechOp2;

import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server extends ServerSocket {

	public Server(int port) throws IOException {
		super(port);
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

		public ClientThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				writer.println("+OK POP3 server ready");
				writer.flush();
				System.out.println("test");
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				boolean inTheLoop = true;
				while(inTheLoop){
					
					String input = reader.readLine();
					
					if (input != null) {
						System.out.println("input="+input);
						if (input.equals("QUIT")) {
							writer.println("+OK dewey POP3 server signing off");
							writer.flush();
							inTheLoop = false;
						} else if (input.startsWith("USER")) {
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
						}
					}

				}
				System.out.println("uit de lus");
				
			} catch (IOException e) {
				
			}
		}
	}

}

