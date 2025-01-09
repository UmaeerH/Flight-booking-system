package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;
/**
 * Main class for the Flight Booking System application. 
 * This class is responsible for loading the flight booking system data, 
 * processing user input, executing commands, and storing the data upon exit.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 * <p>This class does not explicitly declare a constructor as it uses the default * no-argument constructor provided by the compiler.</p>
 */
public class Main {
	/** 
	 * The main method of the Flight Booking System application. 
	 * This method loads the flight booking system data, continuously reads user input, 
	 * processes commands, and stores the data upon exiting the application. 
	 * @param args command-line arguments (not used) 
	 * @throws IOException if an I/O error occurs 
	 * @throws FlightBookingSystemException if an error specific to the flight booking system occurs 
	 */
    public static void main(String[] args) throws IOException, FlightBookingSystemException {
        
        FlightBookingSystem fbs = FlightBookingSystemData.load();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Flight Booking System");
        System.out.println("Enter 'help' to see a list of available commands.");
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line.equals("exit")) {
                break;
            }

            try {
                Command command = CommandParser.parse(line);
                command.execute(fbs);
            } catch (FlightBookingSystemException ex) {
                System.out.println(ex.getMessage());
            } catch (IllegalArgumentException il) {
            	System.out.println(il.getMessage());
            }
        }
        FlightBookingSystemData.store(fbs);
        System.exit(0);
    }
}
