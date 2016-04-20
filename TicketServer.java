(package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TicketServer {
	static int PORT = 2222;
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;

	public static void start(int portNumber) throws IOException {
		initalizeSeats();
		PORT = portNumber;
		Runnable serverThread = new ThreadedTicketServer();
		Thread t = new Thread(serverThread);
		t.start();
	}
	
	public static void initalizeSeats() {
		availableSeats = new ArrayList<String>();
		String seatToAdd;
		static int NUMSEATS = 28;
		static int NUMROWS = 24;
		
		static List<String> middleSeats = Arrays.asList("108", "109", "110", "111", "112", "113", "114, "115", 
						  "116", "117", "118", "119", "120", "121");
		static List<String> sideSeats = Arrays.asList("101", "102", "103", "104", "105", "106", "107", "122", 
						"123", "124", "125", "126", "127", "128");						  
		static List<String> frontRows = Arrays.asList("A", "B", "C", "D", "E", "F", "G, "H", "J", "K", "L", "M");
		static List<String> backRows = Arrays.asList("N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
		
		// Middle front seats
		for(int i=0; i<(NUMROWS/2); i++) {
			for(int j=0; j<(NUMSEATS/2); j++) {
				seatToAdd = frontRows[i] + middleSeats[j];
				availableSeats.add(seatToAdd);
			}
		}
		// Side front seats
		for(int i=0; i<(NUMROWS/2); i++) {
			for(int j=0; j<(NUMSEATS/2); j++) {
				seatToAdd = frontRows[i] + sideSeats[j];
				availableSeats.add(seatToAdd);
			}
		}		
		
		// Middle back seats
		for(int i=0; i<(NUMROWS/2); i++) {
			for(int j=0; j<(NUMSEATS/2); j++) {
				seatToAdd = backRows[i] + middleSeats[j];
				availableSeats.add(seatToAdd);
			}
		}		
		
		// Side back seats 
		for(int i=0; i<(NUMROWS/2); i++) {
			for(int j=0; j<(NUMSEATS/2); j++) {
				seatToAdd = backRows[i] + sideSeats[j];
				availableSeats.add(seatToAdd);
			}
		}		
		
	}
}

class ThreadedTicketServer implements Runnable {

	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;

	public void run() {
		// TODO 422C
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(TicketServer.PORT);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
