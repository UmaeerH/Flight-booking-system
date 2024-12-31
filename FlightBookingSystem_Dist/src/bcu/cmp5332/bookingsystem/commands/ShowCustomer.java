package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowCustomer implements Command {
    private final int id;
    public ShowCustomer(int id) {
        this.id = id;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        Customer customer = flightBookingSystem.getCustomerByID(id);
        System.out.println(customer.getDetailsShort());
        for (Booking booking : customer.getBookings()) {
        	if(booking.getCancelled() == false) {
        		System.out.println(
        						"=======NOT CANCELLED==========\n" +
        						"Booking ID: " + booking.getId() + // I hate printing large bits
        						", Date: " + booking.getBookingDate() + ", Cost: £" + booking.getCost() 
        						+ "\n" + "Flight: " + booking.getFlight().getFlightNumber());
        	}
        	else {
        		System.out.println(
						"========CANCELLED=========\n" +
						"Booking ID: " + booking.getId() + // I hate printing large bits
						", Date: " + booking.getBookingDate() + ", Cost: £" + booking.getCost() 
						+ "\n" + "Flight: " + booking.getFlight().getFlightNumber());
        	}
        }
        System.out.println("================= End of Flights");

    }
}