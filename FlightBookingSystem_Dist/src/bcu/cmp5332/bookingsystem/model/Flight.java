package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity;
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

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }

    public String getDetailsLong() {
        // TODO: implementation here
        return null;
    }
    
    public void addPassenger(Customer passenger) {
        
    }
}
