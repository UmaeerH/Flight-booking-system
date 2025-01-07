package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Command to remove a existing flight from the flight booking system.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */
public class RemoveFlight implements Command {
;

    private int flightID;
    
	/**
     * Constructor to create an RemoveFlight command.
     * @param flightID id of the flight
     */
    
    public RemoveFlight(int flightID) {
        this.flightID = flightID;
    }
    /**
     * Executes the command to remove a flight from the flight booking system. 
     * @param flightBookingSystem the flight booking system to remove the flight from
     * @throws FlightBookingSystemException if an error occurs during flight removal 
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        try {
        	flightBookingSystem.getFlightByID(flightID);
        } catch(FlightBookingSystemException ex) {
        	System.out.println(ex);
        }
        Flight flight = flightBookingSystem.getFlightByID(flightID);
        
        
        // Check to see if the flight has any passengers
        	if (flight.getPassengers().size()!=0) {
        		throw new FlightBookingSystemException("Flight cannot be deleted as it has passengers");
        }

    	
    	flightBookingSystem.removeFlight(flightID);
        System.out.println("Flight #" + flightID + " has been removed.");
    }
}
