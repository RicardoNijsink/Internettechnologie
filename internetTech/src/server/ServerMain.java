package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {
	public static int PORTNUMER = 2000;

	public static void main(String[] args) {
		Model model = new Model();
		try {
			Server ontvangstsocket = new Server(PORTNUMER, model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
