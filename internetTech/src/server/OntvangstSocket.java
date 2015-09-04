package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class OntvangstSocket extends ServerSocket {

	public OntvangstSocket(int port) throws IOException {
		super(port);
		while (true) {
			Socket socket = this.accept();
			System.out.println("kaas");
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
			BufferedReader reader;
			//while (true) {
				try {
					reader = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					String line = reader.readLine();
					System.out.println("berichtgelezen?");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}
	
			// 1. Wacht op berichten van de client.
			// 2. Stuur berichten van de clients door naar de andere
			// clients. (Broadcast)
		}
	}

}
