package server;

import java.io.IOException;
import java.net.ServerSocket;

public class main {
	public static int PORTNUMER = 2000;

	public static void main(String[] args) {
		try {
			OntvangstSocket ontvangstsocket = new OntvangstSocket(PORTNUMER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
