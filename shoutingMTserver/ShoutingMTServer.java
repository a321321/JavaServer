package shoutingMTserver;

import java.net.ServerSocket;
import java.net.Socket;

public class ShoutingMTServer {
	public static final int PORT = 7789;
	private static final int maxnrofConnections=7;
	public static shoutingMTserver.TelSemafoor mijnSemafoor = new shoutingMTserver.TelSemafoor(maxnrofConnections);
	
	
	public static void main(String[] args) {
		Socket connection;
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.err.println("MT Server started..bring on the load, to a maximum of: " + maxnrofConnections);
			
			while (true) {
				connection = server.accept();		
				System.err.println("New connection accepted..handing it over to worker thread");
				Thread worker = new Thread(new shoutingMTserver.Worker(connection));
				worker.start();
			}
		}

		catch (java.io.IOException ioe) { }
	}
}
