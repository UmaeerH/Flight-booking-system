package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Command to show details of a specific customer in the flight booking system. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */
public class ShowCustomer implements Command {
    private final int id;
    private int bookingAmt;
    /**
     * Constructor to create a ShowCustomer command with the specified customer ID. 
     * @param id the ID of the customer to be shown 
     */
    public ShowCustomer(int id) {
        this.id = id;
        bookingAmt = 0;
    }
    /** 
     * Executes the command to show detailed information about a customer in the flight booking system. 
     * @param flightBookingSystem the flight booking system containing the customer 
     * @throws FlightBookingSystemException if an error occurs while retrieving customer details 
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Customer customer = flightBookingSystem.getCustomerByID(id);
        System.out.println(customer.getDetailsLong());
        if (customer.getBookings().size() !=0) {
            System.out.println("-------------------\nBookings:");
        	for (Booking booking : customer.getBookings()) {
        		if(booking.getCancelled() == false) {
        			bookingAmt++;
        			System.out.println(
        							"================\n" +
        							"Booking ID: " + booking.getId() + // I hate printing large bits
        							", Booking Date: " + booking.getBookingDate() + ", Cost: £" + booking.getCost() 
        							+ "\n" + "Flight: " + booking.getFlight().getFlightNumber() + " : " +
        							booking.getFlight().getOrigin() + " to " + booking.getFlight().getDestination());
        		}
        		else {
        			System.out.println(
							"========CANCELLED========\n" +
							"Booking ID: " + booking.getId() + // I hate printing large bits
							", Date: CANCELLED" + ", Cost: £" + booking.getCost() + "(cancellation charge)"
							+ "\n" + "Flight: " + booking.getFlight().getFlightNumber() + " : " +
        							booking.getFlight().getOrigin() + " to " + booking.getFlight().getDestination());
        		}
        	}
        	System.out.println("================\n" + bookingAmt + " active booking(s)");
        } else {
        	System.out.println("No active bookings");
        }
        

    }
}