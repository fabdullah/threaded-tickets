/**
 * Defines the recital hall as well as methods for finding optimal seats
 * Solves EE422C programming assignment #6
 * @authors Fatima Abdullah, Jai Bock Lee
 * @version 1.1 2016-4-20
 * 
 * UTEID: faa449, jbl932
 * Lab Section: 11-12:30pm, Lisa Hua
 * 
 */

package assignment6;

import java.util.*;

public class BatesRecitalHall {
	static ArrayList<String> seats = new ArrayList<String>();
	BatesRecitalHall(){
		int n = 115; // rows by columns
		 int m = 114;
		for(char i = 'A'; i <= 'Z'; i++){
			while(n <= 128 && m >= 101){
				String seatString = "" + i + m;
				seats.add(seatString);
				m--;
				String seatString2 = "" + i + n;
				seats.add(seatString2);
				n++;
			}
		}
	}
	public synchronized String bestFreeSeat(){
		try{
			String bestSeat = seats.get(0);
			seats.remove(0);
			return "Your assigned seat is: " + bestSeat;
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("Sorry, tickets are sold out!");
			System.exit(0);
		}
		return null;
	}
}