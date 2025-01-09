package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.*;
import bcu.cmp5332.bookingsystem.model.*;
import bcu.cmp5332.bookingsystem.data.*;

// Importing entire commands package at once
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 * Class responsible for parsing command line input and returning corresponding Command objects.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 * 
 * This class contains the default constructor and other necessary methods to handle command parsing.
 * 
 */

@SuppressWarnings("unused")
public class CommandParser {
	/**
     * Default constructor for the CommandParser class.
     */
    public CommandParser() {
        // Default constructor
    }
	/** 
	 * Parses a line of input and returns the corresponding Command object. 
	 * @param line the input line containing the command and its parameters 
	 * @return the Command object corresponding to the parsed input 
	 * @throws IOException if an I/O error occurs 
	 * @throws FlightBookingSystemException if an error occurs related to the flight booking system
	 * This class contains the default constructor and other necessary methods to handle command parsing.
	 */
    public static Command parse(String line) throws IOException, FlightBookingSystemException {

    	try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];
            
            if (cmd.equalsIgnoreCase("addflight")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Flight Number: ");
                String flightNumber = reader.readLine();
                System.out.print("Origin: ");
                String origin = reader.readLine();
                System.out.print("Destination: ");
                String destination = reader.readLine();
                System.out.print("Capacity (integer only): ");
                int capacity = Integer.parseInt(reader.readLine());
                System.out.print("Price (in GBP): ");
                double price = Double.parseDouble(reader.readLine());
                price = Math.round(price * 100.0) / 100.0; // Rounds to 2 d.p.
                
                LocalDate departureDate = parseDateWithAttempts(reader);
                // Add Flight to Flight tree
                return new AddFlight(flightNumber, origin, destination, departureDate, capacity, price);
                
            } else if (cmd.equalsIgnoreCase("addcustomer")) {
            	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            	System.out.print("Customer\'s Name: ");					// Take in customer name and phone
            	String customerName = reader.readLine();
            	System.out.print("Customer\'s Phone Number: ");
            	String customerPhone = reader.readLine();
            	System.out.print("Customer\'s Email: ");
            	String customerEmail = reader.readLine();
            	// Add customer to customer tree
            	return new AddCustomer(customerName, customerPhone, customerEmail);
            	
            } else if (cmd.equalsIgnoreCase("loadgui")) {
                return new LoadGUI();
                
            } else if (parts.length == 1) {
                if (line.equalsIgnoreCase("listflights")) {
                    return new ListFlights();
                } else if (line.equalsIgnoreCase("listcustomers")) {
                	return new ListCustomers();
                } else if (line.equalsIgnoreCase("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equalsIgnoreCase("showflight")) {
                	return new ShowFlight(id);
                } else if (cmd.equalsIgnoreCase("showflightdetailed")) {
                	return new ShowFlightDetailed(id);
                } else if (cmd.equalsIgnoreCase("removeflight")) {
                	return new RemoveFlight(id);
                }else if (cmd.equalsIgnoreCase("showcustomer")) {
                	return new ShowCustomer(id);
                } else if (cmd.equalsIgnoreCase("removecustomer")) {
                	return new RemoveCustomer(id);
                } else if (cmd.equalsIgnoreCase("cancelbooking")) {
                	return new CancelBooking(id);
                	}
            } else if (parts.length == 3) {		// Command [customer ID] [Flight ID]
                if (cmd.equalsIgnoreCase("addbooking")) {
                	int customerID = Integer.parseInt(parts[1]);
                    int flightID = Integer.parseInt(parts[2]);
                    return new AddBooking(customerID, flightID);
                } else if (cmd.equalsIgnoreCase("editbooking")) { // Command [Booking ID] [FlightID]
                    int BookingID = Integer.parseInt(parts[1]);
                    int flightID = Integer.parseInt(parts[2]);
                    return new UpdateBooking(BookingID, flightID);
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }
    /**
     * Parses a departure date from the input with a specified number of attempts. 
     * This method prompts the user to input a departure date in "YYYY-MM-DD" format. 
     * If the date is not provided in the correct format within the specified number of attempts, 
     * an exception is thrown. * * @param br the BufferedReader to read the input from 
     * @param attempts the number of attempts allowed to enter a valid date 
     * @return the parsed LocalDate object representing the departure date 
     * @throws IOException if an I/O error occurs * @throws FlightBookingSystemException if an invalid departure date is provided after the allowed attempts 
     * @throws IllegalArgumentException if the number of attempts is less than 1 
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher than 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    /** 
     * Parses a departure date from the input with a default number of attempts. 
     * This method calls the overloaded parseDateWithAttempts method with 3 attempts. 
     * @param br the BufferedReader to read the input from 
     * @return the parsed LocalDate object representing the departure date 
     * @throws IOException if an I/O error occurs 
     * @throws FlightBookingSystemException if an invalid departure date is provided after the allowed attempts 
     */
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
