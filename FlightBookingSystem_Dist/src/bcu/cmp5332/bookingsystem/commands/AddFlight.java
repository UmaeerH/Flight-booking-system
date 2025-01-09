package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.data.*;

import java.io.IOException;
import java.time.LocalDate;
/**
 * Command to add a new flight to the flight booking system.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */
public class AddFlight implements Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private final int capacity;
    private double price;
    /**
     * Constructor to create an AddFlight command with specified flight details. 
     * @param flightNumber the flight number 
     * @param origin the origin airport of the flight 
     * @param destination the destination airport of the flight 
     * @param departureDate the departure date of the flight 
     * @param capacity the passenger capacity of the flight 
     * @param price the ticket price for the flight 
     */
    public AddFlight(String flightNumber, String origin, String destination,
    		LocalDate departureDate, int capacity, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
    }
    /** 
     * Executes the command to add a new flight to the flight booking system. 
     * @param flightBookingSystem the flight booking system to add the flight to 
     * @throws FlightBookingSystemException if an error occurs during flight addition 
     * 
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
                
        price = Math.round(price * 100.0) / 100.0;
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate, capacity, price, false);
        flightBookingSystem.addFlight(flight);
        try { // Flight Save + revert block
        	FlightDataManager flightDataManager = new FlightDataManager();
			flightDataManager.storeData(flightBookingSystem);
        } catch (IOException ioE) {
        	FlightDataManager flightDataManager2 = new FlightDataManager();
			try {
				flightDataManager2.loadData(flightBookingSystem);
			} catch (IOException | FlightBookingSystemException e) {
				e.printStackTrace();
			}
        	throw new FlightBookingSystemException("Unable to save changes, reverting system " + ioE.getMessage());
        }
        System.out.println("Flight #" + flight.getId() + " added.");
    }
}
