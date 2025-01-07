package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public int getId() {
        return id;
    }

    public void setId(int newid) {
        this.id = newid;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String newFlightNumber) {
        this.flightNumber = newFlightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String newOrigin) {
        this.origin = newOrigin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String newDestination) {
        this.destination = newDestination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate newDepartureDate) {
        this.departureDate = newDepartureDate;
    }
    
    public int getCapacity() {
    	return capacity;
    }
    
    public void setCapacity(int newCapacity) {
    	this.capacity = newCapacity;
    }
    
    public double getPrice() {
    	return price;
    }
    
    public void setPrice(double newPrice) {
    	this.price = newPrice;
    }
    
    public int getFreeCapacity() {
    	int returnCap = (capacity - passengers.size());
    	return returnCap;
    }
    

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
    
	
    public String getDetailsShort() {
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate
                + "\nSeats filled: " + passengers.size() + "/" + capacity;
    }

    public String getDetailsLong() { 
    	StringBuilder returnString = new StringBuilder("Flight #" + id + "\nFlight No: " + flightNumber + "\nOrigin: " 
    			+ origin + "\nDestination: " + destination + "\nDeparture Date: " + departureDate + 
    			"\n--------------" + "\nPassenger(s): "
    			+ passengers.size() + "/" + capacity + "\n"); 
    	
    	
    	for(Customer customer : passengers) {
    		String customerString = "* ID: " + customer.getId() + " - " + customer.getName() + " - "
    		+ customer.getPhone();
    		returnString.append(customerString).append("\n");
    	} 
    	return returnString.toString(); 
    }
    
    public void addPassenger(Customer customer) {
    	passengers.add(customer);
    }
    
    public void removePassenger(Customer customer) throws FlightBookingSystemException { 
    	if (!passengers.contains(customer)) { 
    		throw new FlightBookingSystemException("Customer not found in this flight.");
    	} 
    	passengers.remove(customer); 
    }
    
    public boolean getDeleted() {
    	return deleted;
    }
    
    public void setDeleted(boolean newDeleted) {
    	this.deleted = newDeleted;
    
    }
}
