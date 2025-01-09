package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;
/** 
 * Represents a booking in the flight booking system. 
 * This class contains details about the booking, including the booking ID, customer, flight, 
 * booking date, cost, cancellation status, and cancellation fee.
 * @author UmaeerH
 * @author AnisaU
 * @version main
 */
public class Booking {
    
	private int bookingID;
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
	private double cost; // Checkout price
	private boolean cancelled;
	private double canCost; // Cancellation fee
	/** 
	 * Constructs a new Booking with the specified details. 
	 * @param id the booking ID 
	 * @param customer the customer associated with the booking 
	 * @param flight the flight associated with the booking 
	 * @param cost the cost of the booking 
	 * @param bookingDate the date of the booking 
	 * @param cancelled the cancellation status of the booking 
	 * @param canCost the cancellation fee
	 */
    public Booking(int id, Customer customer, Flight flight, double cost, LocalDate bookingDate, boolean cancelled, double canCost) {
        this.bookingID = id;
    	this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.cost = cost;
        this.cancelled = cancelled;
        this.canCost = canCost;
    }
    /**
     * Returns the booking ID. 
     * @return the booking ID 
     */
    public int getId() {
    	return bookingID;
    }
    /**
     * Sets the ID of the booking. 
     * @param newID the ID of the new booking. 
     */
	public void setId(int newID) {
    	this.bookingID = newID;
    }
	/**
	 * Gets the customer associated with the booking. 
	 * @return returns the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * Sets the customer associated with the booking. 
	 * @param newCustomer the new customer associated with the booking 
	 */
	public void setCustomer(Customer newCustomer) {
		this.customer = newCustomer;
	}
	/**
	 * Returns the flight associated with the booking. 
	 * @return the flight associated with the booking 
	 */
	public Flight getFlight() {
		return flight;
	}
	/**
	 * Sets the flight associated with the booking. 
	 * @param newFlight the new flight associated with the booking 
	 */
	public void setFlight(Flight newFlight) {
		this.flight = newFlight;
	}
	/**
	 * Returns the date of the booking. 
	 * @return the date of the booking 
	 */
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	/**
	 * Sets the date of the booking. 
	 * @param newBookingDate the new date of the booking 
	 */
	public void setBookingDate(LocalDate newBookingDate) {
		this.bookingDate = newBookingDate;
	}
	/**
	 * Returns the cost of the booking. 
	 * @return the cost of the booking 
	 */
	public double getCost() {
		return cost;
	}
	/** 
	 * Sets the cost of the booking. 
	 * @param newCost the new cost of the booking 
	 */
	public void setCost(double newCost) {
		this.cost = newCost;
	}
	/** 
	 * Returns the cancellation fee of the booking. 
	 * @return the cancellation fee of the booking 
	 */
	public double getCanCost() {
		return canCost;
	}
	/** 
	 * Sets the cancellation fee of the booking. 
	 * @param newCanCost the new cancellation fee of the booking 
	 */
	public void setCanCost(double newCanCost) {
		this.canCost = newCanCost;
	}
	/** 
	 * Returns the cancellation status of the booking. 
	 * @return the cancellation status of the booking
	 */
	public boolean getCancelled() {
		return cancelled;
	}
	/** 
	 * Sets the cancellation status of the booking. 
	 * @param newCancelled the new cancellation status of the booking 
	 */
	public void setCancelled(boolean newCancelled) {
		this.cancelled = newCancelled;
	}
	/**
	* Returns the customer ID. 
	* This method is used for loading/saving purposes. 
	* @return the customer ID 
	*/
	public int getCustomerID() {
		return this.customer.getId();
	}
	/** 
	 * Returns the flight ID. 
	 * This method is used for loading/saving purposes. 
	 * @return the flight ID 
	 */
	public int getFlightID() {
		return this.flight.getId();
	}
    
    
    
}
