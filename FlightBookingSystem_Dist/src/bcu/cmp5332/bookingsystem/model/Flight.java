package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Represents a Flight in the Flight Booking System.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */
@SuppressWarnings("unused")
public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity;
    private double price;
    private boolean deleted;

    private final Set<Customer> passengers;
    /** 
     * Constructor to create a Flight object. 
     * @param id The unique identifier for the flight. 
     * @param flightNumber The flight number. 
     * @param origin The origin of the flight. 
     * @param destination The destination of the flight. 
     * @param departureDate The departure date of the flight. 
     * @param capacity The seating capacity of the flight. 
     * @param price The base price of the flight. 
     * @param deleted Indicates if the flight is marked as deleted. 
     */
    public Flight(int id, String flightNumber, String origin, 
    			String destination, LocalDate departureDate, int capacity, double price,
    			boolean deleted) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
        this.deleted = deleted;
        
        passengers = new HashSet<>();

    }
    /** 
     * Gets the unique identifier for the flight.
     * @return The flight ID. 
     */
    public int getId() {
        return id;
    }
    /** 
     * Sets the unique identifier for the flight. 
     * @param newid The new flight ID. 
     */
    public void setId(int newid) {
        this.id = newid;
    }
    /** 
     * Gets the flight number. 
     * @return The flight number. 
     */
    public String getFlightNumber() {
        return flightNumber;
    }
    /** 
     * Sets the flight number. 
     * @param newFlightNumber The new flight number. 
     */
    public void setFlightNumber(String newFlightNumber) {
        this.flightNumber = newFlightNumber;
    }
    /** 
     * Gets the origin of the flight. 
     * @return The origin. 
     */
    public String getOrigin() {
        return origin;
    }
    /** 
     * Sets the origin of the flight. 
     * @param newOrigin The new origin. 
     */
    public void setOrigin(String newOrigin) {
        this.origin = newOrigin;
    }
    /** 
     * Gets the destination of the flight. 
     * @return The destination. 
     */
    public String getDestination() {
        return destination;
    }
    /**
     *  Sets the destination of the flight. 
     * @param newDestination The new destination. 
     */
    public void setDestination(String newDestination) {
        this.destination = newDestination;
    }
    /** 
     * Gets the departure date of the flight. 
     * @return The departure date. 
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    /** 
     * Sets the departure date of the flight. 
     * @param newDepartureDate The new departure date. 
     */
    public void setDepartureDate(LocalDate newDepartureDate) {
        this.departureDate = newDepartureDate;
    }
    /** 
     * Gets the seating capacity of the flight.
     * @return The capacity. 
     */
    public int getCapacity() {
    	return capacity;
    }
    /** 
     * Sets the seating capacity of the flight. 
     * @param newCapacity The new capacity. 
     */
    public void setCapacity(int newCapacity) {
    	this.capacity = newCapacity;
    }
    /** 
     * Gets the base price of the flight. 
     * @return The price. 
     */
    public double getPrice() {
    	return price;
    }
    /** 
     * Sets the base price of the flight. 
     * @param newPrice The new price. 
     */
    public void setPrice(double newPrice) {
    	this.price = newPrice;
    }
    /** 
     * Gets the remaining capacity of the flight. 
     * @return The remaining capacity. 
     */
    public int getFreeCapacity() {
    	int returnCap = (capacity - passengers.size());
    	return returnCap;
    }
    /** 
     * Gets the list of passengers. 
     * @return The list of passengers. 
     */
    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
    /** 
     * Gets a short description of the flight. 
     * @return A short description of the flight. 
     */
    public String getDetailsShort() {
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate
                + "\nSeats filled: " + passengers.size() + "/" + capacity;
    }
    /** 
     * Gets a long description of the flight. 
     * @return A long description of the flight. 
     */
    public String getDetailsLong() { 
    	StringBuilder returnString = new StringBuilder("Flight #" + id + "\nFlight No: " + flightNumber + "\nOrigin: " 
    			+ origin + "\nDestination: " + destination + "\nDeparture Date: " + departureDate + ", Base Price: £" + price + 
    			"\n--------------" + "\nPassenger(s): "
    			+ passengers.size() + "/" + capacity + "\n"); 
    	
    	
    	for(Customer customer : passengers) {
    		String customerString = "* ID: " + customer.getId() + " - " + customer.getName() + " - "
    		+ customer.getPhone();
    		returnString.append(customerString).append("\n");
    	} 
    	return returnString.toString(); 
    }
    /** 
     * Adds a passenger to the flight. 
     * @param customer The passenger to add. 
     */
    public void addPassenger(Customer customer) {
    	passengers.add(customer);
    }
    /** 
     * Removes a passenger from the flight. 
     * @param customer The passenger to remove.
     * @throws FlightBookingSystemException If the passenger is not found on the flight. 
     */
    public void removePassenger(Customer customer) throws FlightBookingSystemException { 
    	if (!passengers.contains(customer)) { 
    		throw new FlightBookingSystemException("Customer not found in this flight.");
    	} 
    	passengers.remove(customer); 
    }
    /**
     * Gets the deleted status of the flight.
     * @return The deleted status. 
     */
    public boolean getDeleted() {
    	return deleted;
    }
    /** 
     * Sets the deleted status of the flight. 
     * @param newDeleted The new deleted status. 
     */
    public void setDeleted(boolean newDeleted) {
    	this.deleted = newDeleted;
    
    }
    
    /*
     * Calculating price: we will use two multipliers
     * Late Booking Multiplier: 
     * If days until flight are <30, we will progressively charge more. (1.00 - 1.30x multi)
     * 
     * Availability Multiplier:
     * % of seats filled result in higher cost. (1.00 - 1.5x multi)
     * 
     */
    
    public double getBookingPrice() {
    	double bookingPrice = price;
    	// Late booking multiplier
    	long daysUntilLong = ChronoUnit.DAYS.between(LocalDate.now(), departureDate);
    	int daysUntil = (int) daysUntilLong;
    	if(daysUntil<30 & daysUntil>-1) {
    		bookingPrice = bookingPrice * (1 + (0.01 * (30 - daysUntil)));
    	}
    	
    	// Availability multiplier
    	double fullness = (double) passengers.size() / capacity;
    	bookingPrice *= (1 + (0.5 * fullness));
    	bookingPrice = Math.round(bookingPrice * 100.0) / 100.0;

    	return bookingPrice;
    }
    
}
