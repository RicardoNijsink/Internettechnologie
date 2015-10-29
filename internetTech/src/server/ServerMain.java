package server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Class waarin de server en het bijbehorende model worden aangemaakt
 * @author Ricardo
 *
 */
public class ServerMain {
	public static int PORTNUMER = 2000;

	public static void main(String[] args) {
		Model model = new Model();
		try {
			Server ontvangstsocket = new Server(PORTNUMER, model);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
