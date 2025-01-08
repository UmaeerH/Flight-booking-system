package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

/**
 * Command to cancel an existing booking in the flight booking system. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */
public class CancelBooking implements Command {
    private final int id;
    /** 
     * Constructor to create a CancelBooking command with the specified booking ID. 
     * @param id the ID of the booking to be cancelled 
     */
    public CancelBooking(int id) {
        this.id = id;
    }
    /**
     * Executes the command to cancel a booking in the flight booking system. 
     * @param flightBookingSystem the flight booking system containing the booking 
     * @throws FlightBookingSystemException if an error occurs during booking cancellation 
     */
    
    // Cancellation fee = £10 + 10% of ticket cost
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Booking booking = flightBookingSystem.getBookingByID(id);
		booking.setCancelled(true);
		booking.getFlight().removePassenger(booking.getCustomer());
		double canCost = (double) booking.getCost() / 10 ;
		canCost += 10;
		canCost = Math.round(canCost * 100.0) / 100.0;
		booking.setCanCost(canCost);
		System.out.println("Booking #" + booking.getId() + " has been cancelled");
	}
}