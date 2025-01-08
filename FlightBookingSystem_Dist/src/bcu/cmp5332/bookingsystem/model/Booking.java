package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
	private int bookingID;
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
	private double cost; // Checkout price
	private boolean cancelled;
	private double canCost; // Cancellation fee
    
    public Booking(int id, Customer customer, Flight flight, double cost, LocalDate bookingDate, boolean cancelled, double canCost) {
        this.bookingID = id;
    	this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.cost = cost;
        this.cancelled = cancelled;
        this.canCost = canCost;
    }

    public int getId() {
    	return bookingID;
    }
    
	public void setId(int newID) {
    	this.bookingID = newID;
    }
    
	public Customer getCustomer() {
		return customer;
	}
	
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
	
	public double getCanCost() {
		return canCost;
	}
	
	public void setCanCost(double newCanCost) {
		this.canCost = newCanCost;
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
