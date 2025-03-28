package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Handles the loading and saving of the FLIGHT class. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 * 
 * This class contains the default constructor and other necessary methods to handle command parsing.
 */

public class FlightDataManager implements DataManager {
	/**
     * Default constructor for the FlightDataManager class.
     * 
     * Initialises a new instance of the FlightDataManager without any specific parameters.
     */
    public FlightDataManager() {
        // Default constructor
    }
	
    
    private final String RESOURCE = "./resources/data/flights.txt";
    /**
     * Loads flight data from a file into the flight booking system. 
     * @param fbs the flight booking system to load flight data into 
     * @throws IOException if an I/O error occurs while reading the file 
     * @throws FlightBookingSystemException if an error occurs while parsing flight data 
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
                    String flightNumber = properties[1];
                    String origin = properties[2];
                    String destination = properties[3];
                    LocalDate departureDate = LocalDate.parse(properties[4]);
                    int capacity = Integer.parseInt(properties[5]);
                    double price = Double.parseDouble(properties[6]);
                    boolean deleted = Boolean.parseBoolean(properties[7]);
                    Flight flight = new Flight(id, flightNumber, origin, destination, departureDate, capacity, price, deleted);
                    fbs.addFlight(flight);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    /**
     * Stores flight data from the flight booking system into a file. 
     * @param fbs the flight booking system containing flight data 
     * @throws IOException if an I/O error occurs while writing to the file 
     */
    
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
    	System.out.println("Storing data to file...");
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Flight flight : fbs.getFlights()) {
                out.print(flight.getId() + SEPARATOR);
                out.print(flight.getFlightNumber() + SEPARATOR);
                out.print(flight.getOrigin() + SEPARATOR);
                out.print(flight.getDestination() + SEPARATOR);
                out.print(flight.getDepartureDate() + SEPARATOR);
                out.print(flight.getCapacity() + SEPARATOR);
                out.print(flight.getPrice() + SEPARATOR);
                out.print(flight.getDeleted() + SEPARATOR);
                out.println();
            }
        }
    }
}
