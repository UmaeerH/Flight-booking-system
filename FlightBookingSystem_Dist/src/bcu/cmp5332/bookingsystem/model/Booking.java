package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
	private int bookingID;
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
	private double cost; // Checkout price
	private boolean cancelled;
    

    public Booking(int id, Customer customer, Flight flight, LocalDate bookingDate, double cost) {
        this.bookingID = id;
    	this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.cost = cost;
        this.cancelled = false;
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
	
	public boolean getCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean newCancelled) {
		this.cancelled = newCancelled;
	}
    
    
    
}
