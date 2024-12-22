package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddBooking implements Command {

    private final Customer customer;
    private final Flight flight;
    private final double cost;
    private final LocalDate bookingDate;

    public AddBooking(Customer customer, Flight flight, LocalDate bookingDate, double cost) {
        this.customer = customer;
        this.flight = flight;
        this.cost = cost;
		this.bookingDate = bookingDate;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getBookings().size() > 0) {
            int lastIndex = flightBookingSystem.getBookings().size() - 1;
            maxId = flightBookingSystem.getBookings().get(lastIndex).getId();
        }
        
        Booking booking = new Booking(++maxId, customer, flight, bookingDate, cost);
        flightBookingSystem.addBooking(booking);
        System.out.println("Booking #" + booking.getId() + " for " + booking.getCustomer() + " added.");
    }
}
