package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * Command to show detailed information about a specific flight in the flight booking system. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */
public class ShowFlightDetailed implements Command {
    private final int id;
    /**
     * Constructor to create a ShowFlightDetailed command with the specified flight ID. 
     * @param id the ID of the flight to be shown 
     */
    public ShowFlightDetailed(int id) {
        this.id = id;
    }
    /**
     * Executes the command to show detailed information about a flight in the flight booking system. 
     * @param flightBookingSystem the flight booking system containing the flight * @throws FlightBookingSystemException if an error occurs while retrieving flight details 
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Flight flight = flightBookingSystem.getFlightByID(id);
        System.out.println(flight.getDetailsLong());

    }
}