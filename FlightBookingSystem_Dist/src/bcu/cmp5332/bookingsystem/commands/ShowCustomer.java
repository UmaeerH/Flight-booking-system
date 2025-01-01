package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowCustomer implements Command {
    private final int id;
    private int bookingAmt;
    public ShowCustomer(int id) {
        this.id = id;
        bookingAmt = 0;
    }

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