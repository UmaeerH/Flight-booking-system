package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

public class FlightBookingSystem {
    
    private final LocalDate systemDate = LocalDate.now();
    
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();
    private final Map<Integer, Booking> bookings = new TreeMap<>();

    public LocalDate getSystemDate() {
        return systemDate;
    }
    
    // FLIGHTS
    
    public List<Flight> getFlights() {
        List<Flight> out = new ArrayList<>(flights.values());
        return Collections.unmodifiableList(out);
    }

    
    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }
    
    // CUSTOMERS
    
    public List<Customer> getCustomers() {
    	List<Customer> out = new ArrayList<>(customers.values());
    	return Collections.unmodifiableList(out); 
    }
    
    
    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
        	throw new FlightBookingSystemException("There is no customer with that ID.");
        }
        return customers.get(id);
    }
     
    // BOOKINGS 
    
    public List<Booking> getBookings() {
    	List<Booking> out = new ArrayList<>(bookings.values());
    	return Collections.unmodifiableList(out); 
    }
    
    
    public Booking getBookingByID(int id) throws FlightBookingSystemException {
        if (!bookings.containsKey(id)) {
        	throw new FlightBookingSystemException("There is no booking with that ID.");
        }
        return bookings.get(id);
    }
    
    // ADD METHODS

    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with same "
                        + "number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }
    
    
    public void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
        // Iterate through customer list to see if email is a duplicate
        for (Customer present : customers.values()) {
        	if(customer.getEmail().equals(present.getEmail()))
        	throw new IllegalArgumentException("Email already associated with a customer.");
        }
        customers.put(customer.getId(), customer);
    }

    
    public void addBooking(Booking booking) {
        if (bookings.containsKey(booking.getId())) {
            throw new IllegalArgumentException("Duplicate booking ID.");
        }
        bookings.put(booking.getId(), booking);
    }
    
    // REMOVE/DELETE Methods
    
    public void removeCustomer(int customerID) throws FlightBookingSystemException {
    	if (!customers.containsKey(customerID)) {
    		throw new IllegalArgumentException("Customer ID is not found in the system");
    	}
    	Customer customer = customers.get(customerID);
    	if (customer.getDeleted() == true) {
    		throw new FlightBookingSystemException("Customer is already removed");
    	}
    	customer.setDeleted(true);
    }
    
    public void removeFlight(int flightID) throws FlightBookingSystemException {
    	if (!flights.containsKey(flightID)) {
    		throw new IllegalArgumentException("Flight ID is not found in the system");
    	}
    	Flight flight = flights.get(flightID);
    	if (flight.getDeleted() == true) {
    		throw new FlightBookingSystemException("Flight is already removed");
    	}
    	flight.setDeleted(true);
    }
    
    public String displayCustomer(Customer customer) {
    	StringBuilder returnString = new StringBuilder();
    	returnString.append(customer.getDetailsLong());
        if (customer.getBookings().size() !=0) {
        	returnString.append("\n-------------------\nBookings:\n");
        	int bookingAmt = 0;
			for (Booking booking : customer.getBookings()) {
				if (!booking.getCancelled()) {
					
					if(booking.getFlight().getDepartureDate().isBefore(LocalDate.now())) {
		                returnString.append("========EXPIRED========\n")
		                  .append("Booking ID: ").append(booking.getId())
		                  .append(", Booking Date: ").append(booking.getBookingDate())
		                  .append(", Flight Date: ").append(booking.getFlight().getDepartureDate())
		                  .append("\nCost: £").append(booking.getCost())
		                  .append(", Flight: ").append(booking.getFlight().getFlightNumber())
		                  .append(", ID: ").append(booking.getFlight().getId())
		                  .append(" : ").append(booking.getFlight().getOrigin())
		                  .append(" to ").append(booking.getFlight().getDestination()).append("\n");
					} else {
						bookingAmt++;
	                	returnString.append("================\n")
	                	  .append("Booking ID: ").append(booking.getId())
	                	  .append(", Booking Date: ").append(booking.getBookingDate())
	                	  .append(", Flight Date: ").append(booking.getFlight().getDepartureDate())
	                	  .append("\nCost: £").append(booking.getCost())
	                	  .append(", Flight: ").append(booking.getFlight().getFlightNumber())
	                	  .append(", ID: ").append(booking.getFlight().getId())
	                	  .append(" | ").append(booking.getFlight().getOrigin())
	                	  .append(" to ").append(booking.getFlight().getDestination()).append("\n");
					}
				} else {
	                returnString.append("========CANCELLED========\n")
	                  .append("Booking ID: ").append(booking.getId())
	                  .append(", Booking Date: ").append(booking.getBookingDate())
	                  .append(", Flight Date: CANCELLED")
	                  .append("\nCancellation Cost: £").append(booking.getCanCost())
	                  .append("\nCost: £").append(booking.getCost())
	                  .append(", Flight: ").append(booking.getFlight().getFlightNumber())
	                  .append(", ID: ").append(booking.getFlight().getId())
	                  .append(" | ").append(booking.getFlight().getOrigin())
	                  .append(" to ").append(booking.getFlight().getDestination()).append("\n");
	            }
        	}
	        returnString.append("================\n").append(bookingAmt).append(" active booking(s)");
        } else {
            returnString.append("\nNo active bookings");
        }
        return returnString.toString();
    }

    public String displayFlight(Flight flight) {
    	return flight.getDetailsLong();
    }
    
    
}
