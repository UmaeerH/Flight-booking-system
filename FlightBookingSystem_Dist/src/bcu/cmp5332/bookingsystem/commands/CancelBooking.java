package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

public class CancelBooking implements Command {
    private final int id;
    public CancelBooking(int id) {
        this.id = id;
    }

    
    
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Booking booking = flightBookingSystem.getBookingByID(id);
		booking.setCancelled(true);
		booking.getFlight().removePassenger(booking.getCustomer());
		System.out.println("Booking #" + booking.getId() + " has been cancelled");
	}
}