package internetTechOp2;

import java.io.IOException;

/**
 * Class voor het aanmaken van de server
 * @author Ricardo
 *
 */
public class ServerMain {
	public static int PORTNUMER = 110;

	public static void main(String[] args) {
		try {
			Server ontvangstsocket = new Server(PORTNUMER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
