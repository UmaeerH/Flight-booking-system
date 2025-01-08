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

@SuppressWarnings("unused")
public class CommandParser {
    
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
    
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
