package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * Command to list all the non-removed flights to the user.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */

public class ListFlights implements Command {
	/**
     * Default constructor for the ListFlights class.
     * 
     * Initialises a new instance of the ListFlights without any specific parameters.
     */
    public ListFlights() {
        // Default constructor
    }
	
	/*
	 * @param flightBookingSystem the flight booking system we are listing from
	 */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> flights = flightBookingSystem.getFlights();
        int numberOf = 0;
        for (Flight flight : flights) {
        	if (flight.getDeleted()==false){
        		System.out.println(flight.getDetailsShort());
        		numberOf++;
        	}
        }
        System.out.println(numberOf + " flight(s)");
    }
}
