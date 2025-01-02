package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
	private static int bookingID;
    private static Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
	private double cost; // Checkout price
	private boolean cancelled;
    
	@SuppressWarnings("static-access")
    public Booking(int id, Customer customer, Flight flight, double cost, LocalDate bookingDate, boolean cancelled) {
        this.bookingID = id;
    	this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.cost = cost;
        this.cancelled = cancelled;
    }

    public static int getId() {
    	return bookingID;
    }
    
	@SuppressWarnings("static-access")
	public void setId(int newID) {
    	this.bookingID = newID;
    }
    
	public static Customer getCustomer() {
		return customer;
	}
	
	@SuppressWarnings("static-access")
	public void setCustomer(Customer newCustomer) {
		this.customer = newCustomer;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight newFlight) {
		this.flight = newFlight;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate newBookingDate) {
		this.bookingDate = newBookingDate;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double newCost) {
		this.cost = newCost;
	}
	
	public boolean getCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean newCancelled) {
		this.cancelled = newCancelled;
	}
	
	// Additional getters for loading/saving purposes
	public int getCustomerID() {
		return this.customer.getId();
	}
	
	public int getFlightID() {
		return this.flight.getId();
	}
    
    
    
}
