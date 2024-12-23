	package bcu.cmp5332.bookingsystem.commands;
	
	import java.time.LocalDate;
	
	import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
	import bcu.cmp5332.bookingsystem.model.*;
	import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
	
	public class AddBooking implements Command {
	
	    private Customer customer;
	    private final int customerID;
	    private Flight flight;
	    private final int flightID;
	    private double cost;
	    private LocalDate bookingDate;
	
	    public AddBooking(int customerID ,int flightID) {
	        this.customerID = customerID;
	        this.flightID = flightID;
	        
	        this.customer = null; 
	        this.flight = null; 
	        this.bookingDate = null; 
	        this.cost = 0.0;
	        
	    }
	
	    @Override
	    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
	    	// Retrieve Customer and Flight by their IDs
	        this.customer = flightBookingSystem.getCustomerByID(customerID);
	        this.flight = flightBookingSystem.getFlightByID(flightID);
	        this.bookingDate = flight.getDepartureDate();
	        this.cost = flight.getPrice();
	        
	        
	    	int maxId = 0;
	        if (flightBookingSystem.getBookings().size() > 0) {
	            int lastIndex = flightBookingSystem.getBookings().size() - 1;
	            maxId = flightBookingSystem.getBookings().get(lastIndex).getId();
	        }
	        
	        Booking booking = new Booking(++maxId, customer, flight, bookingDate, cost);
	        flightBookingSystem.addBooking(booking);
	        System.out.println("Booking #" + booking.getId() + " for " + booking.getCustomer().getName() + " added.");
	    }
	}
