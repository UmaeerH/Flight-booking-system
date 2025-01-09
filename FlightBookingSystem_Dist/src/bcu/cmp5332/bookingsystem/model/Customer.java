package bcu.cmp5332.bookingsystem.model;

//import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
/** 
 * Represents a Customer in the Flight Booking System. 
 * @author UmaeerH
 * @author AnisaU
 */
public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private List<Booking> bookings = new ArrayList<>();
    private boolean deleted;
    /** 
     * Constructor to create a Customer object. 
     * @param id The unique identifier for the customer. 
     * @param name The name of the customer. 
     * @param phone The phone number of the customer. 
     * @param email The email address of the customer. 
     * @param deleted Indicates if the customer is marked as deleted. 
     */
    public Customer(int id, String name, String phone, String email, boolean deleted) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.deleted = deleted;
        
        bookings = new ArrayList<>();
    }
    /**
     * Gets the unique identifier for the customer. 
     * @return The customer ID. 
     */
    public int getId() {
        return id;
    }
    /** 
     * Sets the unique identifier for the customer. 
     * @param newid The new customer ID. 
     */
    public void setId(int newid) {
        this.id = newid;
    }
    /** 
     * Gets the name of the customer. 
     * @return The customer name. 
     */
    public String getName() {
    	return name;
    }
    /**
     * Sets the name of the customer. 
     * @param newName The new customer name. 
     */
    public void setName(String newName) {
    	this.name = newName;
    }
    /**
     * Gets the phone number of the customer. 
     * @return The customer phone number. 
     */
    public String getPhone() {
    	return phone;
    }
    /** 
     * Sets the phone number of the customer. 
     * @param newPhone The new customer phone number.
     */
    public void setPhone(String newPhone) {
    	this.phone = newPhone;
    }
    /** 
     * Gets the email address of the customer. 
     * @return The customer email address. 
     */
    public String getEmail() {
    	return email;
    }
    /** 
     * Sets the email address of the customer. 
     * @param newEmail The new customer email address.
     */
    public void setEmail(String newEmail) {
    	this.email = newEmail;
    }
    /** 
     * Gets a short description of the customer. 
     * @return A short description of the customer. 
     */
    public String getDetailsShort() {
    	return "Customer: #" + id + " - " + name + "\t | " + phone + " | " + email;
    }
    /** 
     * Gets a long description of the customer. 
     * @return A long description of the customer. 
     */
    public String getDetailsLong() {
    	String returnString = "Customer #" + id
    						+ "\nName: "   + name
    						+ "\nContact: "  + phone + " | " + email;
    	return returnString;
    }
    /** 
     * Gets the list of bookings for the customer. 
     * @return The list of bookings. 
     */
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    /** 
     * Adds a booking to the customer's list of bookings. 
     * @param booking The booking to add. 
     * @throws FlightBookingSystemException If the customer already has a booking for the same flight and date. 
     */
    public void addBooking(Booking booking) throws FlightBookingSystemException {
        for (Booking existingBooking : bookings) {
            // Check if this customer already has a booking for the same flight and date
            if (existingBooking.getFlight().equals(booking.getFlight()) && 
                existingBooking.getBookingDate().equals(booking.getBookingDate())) {
                System.out.println("This booking already exists for the customer: " + booking.getFlight().getFlightNumber());
                throw new FlightBookingSystemException("Customer is already booked for this flight: "
                										+ booking.getFlight().getFlightNumber());
            }
        }
        bookings.add(booking);
    }
    /** 
     * Gets the deleted status of the customer. 
     * @return The deleted status. 
     */
    public boolean getDeleted() {
    	return deleted;
    }
    /** 
     * Sets the deleted status of the customer. 
     * @param newDeleted The new deleted status. 
     */
    public void setDeleted(boolean newDeleted) {
    	this.deleted = newDeleted;
    }



}

