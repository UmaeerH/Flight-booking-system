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
    private int freeCapacity;
    private double price;

    private final Set<Customer> passengers;

    public Flight(int id, String flightNumber, String origin, 
    			String destination, LocalDate departureDate, int capacity, double price) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
        
        passengers = new HashSet<>();
        
        this.freeCapacity = (capacity - passengers.size()); // Initial capacity - 1 per customer
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
    	return freeCapacity;
    }
    
    public void setFreeCapacity(int newFree) {
    	this.freeCapacity = newFree;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
    
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf)
                + "\nSeats filled: " + passengers.size() + "/" + capacity;
    }

    public String getDetailsLong() { 
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
    	StringBuilder returnString = new StringBuilder("Flight #" + id + " - " + flightNumber + " - " 
    			+ origin + " to " + destination + " on " + departureDate.format(dtf) + "\nSeats filled: "
    			+ passengers.size() + "/" + capacity + "\n"); 
    	
    	for(Customer customer : passengers) { 
    		returnString.append(customer.getName().toString()).append("\n");
    	} 
    	return returnString.toString(); 
    }
    
    public void addPassenger(Customer customer) {
    	passengers.add(customer);
    }
}
