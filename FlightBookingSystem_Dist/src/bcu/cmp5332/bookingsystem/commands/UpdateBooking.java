package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

/**
 * Command to Update an existing booking to have a new flight. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */
public class UpdateBooking implements Command {
    private final int id;
    private final int newFlightid;
    
    /** 
     * Constructor to create a UpdateBooking command with the specified booking ID. 
     * @param id the ID of the booking to be updated 
     * @param newFlightid the ID of the new flight
     */
    
    public UpdateBooking(int id, int newFlightid) {
        this.id = id;
        this.newFlightid = newFlightid;
    }
    /**
     * Executes the command to update a booking in the flight booking system. 
     * @param flightBookingSystem the flight booking system containing the booking 
     * @throws FlightBookingSystemException if an error occurs during booking updating 
     */
    
    // Update fee = £5 + 10% of previous flight cost
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Booking booking = flightBookingSystem.getBookingByID(id);
		double updateFee = (double) booking.getCost() / 10;
		updateFee += 10;
		updateFee = Math.round(updateFee * 100.0) / 100.0;
		Flight newFlight = flightBookingSystem.getFlightByID(newFlightid);
		
		if (newFlight.getFreeCapacity() == 0) {
        	throw new FlightBookingSystemException("Flight is full");
        }
        if (newFlight.getDepartureDate().isBefore(LocalDate.now())) {
        	throw new FlightBookingSystemException("Flight has already departed");
        }
		
        booking.getFlight().removePassenger(booking.getCustomer()); // Remove passenger from old
		booking.setFlight(newFlight); // Set new flight
		booking.setCost(newFlight.getBookingPrice() + updateFee); // Set cost
		booking.getFlight().addPassenger(booking.getCustomer()); // put passenger in new flight
		
		System.out.println("Booking #" + booking.getId() + " has been updated");
	}
}