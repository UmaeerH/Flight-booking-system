package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.BookingDataManager;
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
		
		try { // Booking Save + revert block
        	BookingDataManager bookingDataManager = new BookingDataManager();
        	bookingDataManager.storeData(flightBookingSystem);
        } catch (IOException ioE) {
        	BookingDataManager bookingDataManager2 = new BookingDataManager();
			try {
				bookingDataManager2.loadData(flightBookingSystem);
			} catch (IOException | FlightBookingSystemException e) {
				e.printStackTrace();
			}
        	throw new FlightBookingSystemException("Unable to save changes, reverting system " + ioE.getMessage());
        }
		
		System.out.println("Booking #" + booking.getId() + " has been cancelled");
	}
}