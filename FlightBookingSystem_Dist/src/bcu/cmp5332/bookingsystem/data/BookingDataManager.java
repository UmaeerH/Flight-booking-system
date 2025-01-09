package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/** 
 * Manages the loading and storing of booking data in the flight booking system.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */

public class BookingDataManager implements DataManager {
	
	/**
     * The path to the bookings data file.
     */
    public final String RESOURCE = "./resources/data/bookings.txt";
    /** 
     * Loads booking data from a file into the flight booking system. 
     * @param fbs the flight booking system to load booking data into 
     * @throws IOException if an I/O error occurs while reading the file 
     * @throws FlightBookingSystemException if an error occurs while parsing booking data 
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    int custID = Integer.parseInt(properties[1]);
                    int flightID = Integer.parseInt(properties[2]);
                    LocalDate bookingDate = LocalDate.parse(properties[3]);
                    double cost = Double.parseDouble(properties[4]);
                    boolean cancelled = Boolean.parseBoolean(properties[5]);
                    double canCost = Double.parseDouble(properties[6]);
                    // Search for customer and flight with their IDs
                    Customer customer = fbs.getCustomerByID(custID);
                    Flight flight = fbs.getFlightByID(flightID);
                    
                    Booking booking = new Booking(id, customer, flight, cost, bookingDate, cancelled, canCost);                   
                    customer.addBooking(booking);
                    fbs.addBooking(booking);
                    if(cancelled==false) {
                    	flight.addPassenger(customer);
                    }
                    
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    	
    	
    }
    /**
     * Stores booking data from the flight booking system into a file. 
     * @param fbs the flight booking system containing booking data 
     * @throws IOException if an I/O error occurs while writing to the file 
     */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
    	System.out.println("Storing data to file...");
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Booking booking : fbs.getBookings()) {
                out.print(booking.getId() + SEPARATOR);
                out.print(booking.getCustomerID() + SEPARATOR);
                out.print(booking.getFlightID() + SEPARATOR);
                out.print(booking.getBookingDate() + SEPARATOR);
                out.print(booking.getCost() + SEPARATOR);
                out.print(booking.getCancelled() + SEPARATOR);
                out.print(booking.getCanCost() + SEPARATOR);
                out.println();
            }
        }
    }
    
}
