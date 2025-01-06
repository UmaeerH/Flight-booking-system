	package bcu.cmp5332.bookingsystem.commands;
	
	import java.time.LocalDate;
	/**
	 * Command to add a new booking to the flight booking system.
	 */
	
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
	    private boolean cancelled;
	    
	    /** 
	     * Constructor to create an AddBooking command with specified customer ID and flight ID. *
	     * 
	     * @param customerID the ID of the customer making the booking *
	     * @param flightID the ID of the flight to be booked 
	     */
	
	    public AddBooking(int customerID ,int flightID) {
	        this.customerID = customerID;
	        this.flightID = flightID;
	        this.customer = null; 
	        this.flight = null; 
	        this.cost = 0.0;
	        this.bookingDate = LocalDate.now();
	        this.cancelled = false;
	    }
	    
	    /** 
	     * Executes the command to add a new booking to the flight booking system. *
	     * @param flightBookingSystem the flight booking system to add the booking to *
	     * @throws FlightBookingSystemException if the flight is full or if an error occurs during booking 
	     */
	
	    @Override
	    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
	    	// Retrieve Customer and Flight by their IDs
	        this.customer = flightBookingSystem.getCustomerByID(customerID);
	        this.flight = flightBookingSystem.getFlightByID(flightID);
	        this.cost = flight.getPrice();
	        
	        if (this.flight.getFreeCapacity() == 0) {
	        	throw new FlightBookingSystemException("Flight is full");
	        }
	        
	    	int maxId = 0;
	        if (flightBookingSystem.getBookings().size() > 0) {
	            int lastIndex = flightBookingSystem.getBookings().size() - 1;
	            maxId = flightBookingSystem.getBookings().get(lastIndex).getId();
	        }
	        
	        
	        Booking booking = new Booking(++maxId, customer, flight, cost, bookingDate, cancelled);
	        try {
	        	flightBookingSystem.getCustomerByID(customerID).addBooking(booking);
	        	flightBookingSystem.getFlightByID(flightID).addPassenger(customer);
	        }
	        catch(FlightBookingSystemException fbse) {
	        	System.out.println("Customer can not be booked for this flight");
	        	return; // break
	        }
	        flightBookingSystem.addBooking(booking);
	        
	    }
	}
