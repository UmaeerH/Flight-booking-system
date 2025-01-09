package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.time.LocalDate;
import bcu.cmp5332.bookingsystem.data.BookingDataManager;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

	/**
	 * Command to add a new booking to the flight booking system.
	 * @author AnisaU
	 * @author UmaeerH
	 * @version main
	 */
	public class AddBooking implements Command {
	
	    private Customer customer;
	    private final int customerID;
	    private Flight flight;
	    private final int flightID;
	    private double cost;
	    private LocalDate bookingDate;
	    private boolean cancelled;
	    private double canCost;
	    
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
	        this.canCost = 0;
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
	        this.cost = flight.getBookingPrice();
	        
	        if (this.flight.getFreeCapacity() == 0) {
	        	throw new FlightBookingSystemException("Flight is full");
	        }
	        
	        if (this.flight.getDepartureDate().isBefore(LocalDate.now())) {
	        	throw new FlightBookingSystemException("Flight has already departed");
	        }
	        
	    	int maxId = 0;
	        if (flightBookingSystem.getBookings().size() > 0) {
	            int lastIndex = flightBookingSystem.getBookings().size() - 1;
	            maxId = flightBookingSystem.getBookings().get(lastIndex).getId();
	        }
	        
	        
	        Booking booking = new Booking(++maxId, customer, flight, cost, bookingDate, cancelled, canCost);
	        try {
	        	flightBookingSystem.getCustomerByID(customerID).addBooking(booking);
	        	flightBookingSystem.getFlightByID(flightID).addPassenger(customer);
	        }
	        catch(FlightBookingSystemException fbse) {
	        	System.out.println("Customer can not be booked for this flight");
	        	return; // break
	        }
	        flightBookingSystem.addBooking(booking);
	        
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
	        
	        
	    }
	}
