package assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ThreadedTicketClient implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	TicketClient sc;

	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname) {
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
	}

	public void run() {
		System.out.flush();
		try {
			Socket echoSocket = new Socket(hostname, TicketServer.PORT);
			// PrintWriter out =
			new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			echoSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class TicketClient {
	ThreadedTicketClient tc;
	String result = "dummy";
	String hostName = "";
	String threadName = "";
	int seatIt = 0;
	int rowIt = 0;
	static List<String> seats = Arrays.asList("108", "109", "110", "111", "112", "113", "114, "115", 
						  "116", "117", "118", "119", "120", "121", "101", "102", 
						  "103", "104", "105", "106", "107", "122", "123", "124", 
						  "125", "126", "127", "128");
	static List<String> frontRows = Arrays.asList("A", "B", "C", "D", "E", "F", "G, "H", "J", "K", "L", "M");
	static List<String> backRows = Arrays.asList("N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
	static int NUMSEATS = 28;
	static int NUMROWS = 24;

	TicketClient(String hostname, String threadname) {
		tc = new ThreadedTicketClient(this, hostname, threadname);
		hostName = hostname;
		threadName = threadname;
	}

	TicketClient(String name) {
		this("localhost", name);
	}

	TicketClient() {
		this("localhost", "unnamed client");
	}

	void requestTicket() {
		// TODO thread.run()
		tc.run();
		System.out.println(hostName + "," + threadName + " got one ticket");
	}
	
	synchronized void run() {
		// If seats are avilable
		if(seatIt < NUMSEATS && rowIt < NUMROWS) {
			String seat = bestAvailableSeat();
			markAvailableSeatTaken(seat);
			printTicketSeat(seat);	
		}
		//Exit otherwise
		else {
	}
	
	String bestAvailableSeat() {
		String s;
		if(rowIt < NUMROWS/2) {
			s = frontRows[rowIt] + seat[seatIt];
		}
		else {
			s = backRows[rowIt-(NUMROWS/2)] + seat[seatIt];			
		}
		return s;
	}
	
	void markAvailableSeatTaken(String seat) {
		// Updates and resets counters appropriately
		if(seatIt < NUMSEATS) {
			 seatIt++;
		else {
			seatIt = 0;
			rowIt++;
		}
 	}
	
	void printTicketSeat(String seat) {
		
	}

	void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
