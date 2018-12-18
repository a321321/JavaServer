package shoutingMTserver;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.net.*;
import java.io.*;
//import java.util.concurrent.*;

class Worker implements Runnable
{
	private Socket clientSocket;

	public Worker(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		try {
			String s;
			System.err.println("New worker thread started");
			
			//lets check if we already accepted maximum number of connections
			ShoutingMTServer.mijnSemafoor.probeer();

			int red = -1;
			byte[] buffer = new byte[5*1024]; // a read buffer of 5KiB
			byte[] redData;
			StringBuilder clientData = new StringBuilder();
			String redDataText;


			while ((red = clientSocket.getInputStream().read(buffer)) > -1) {
				redData = new byte[red];
				System.arraycopy(buffer, 0, redData, 0, red);
				redDataText = new String(redData,"UTF-8"); // assumption that client sends data UTF-8 encoded
				//System.out.println(redDataText);
				XMLParser parser = new XMLParser();
				clientData.append(redDataText);
			}
			System.out.println("Data From Client :" + clientData.toString());

			// now close the socket connection
			clientSocket.close();
			System.err.println("Connection closed: workerthread ending");
			// upping the semaphore.. since the connnection is gone....
			ShoutingMTServer.mijnSemafoor.verhoog();
		}
		catch (IOException ioe) { }
		catch (InterruptedException ie) {}
	}
}

