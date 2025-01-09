package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * Command to show flight to the user.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */


/**
 * Command to show flight to the user.
 */
public class ShowFlight implements Command {
	
    private final int id;
	/**
	 * Command to show flight to the user.
	 * @param id for flight ID
	 */
    public ShowFlight(int id) {
        this.id = id;
    }
    

	/**
	 * Command to show flight to the user.
	 * @param flightBookingSystem the fbs we are running
	 * @throws FlightBookingSystemException when there is an issue finding the flight
	 */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Flight flight = flightBookingSystem.getFlightByID(id);
        System.out.println(flight.getDetailsShort());

    }
}