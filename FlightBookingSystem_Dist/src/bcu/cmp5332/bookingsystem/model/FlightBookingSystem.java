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
    
    public String displayCustomer(Customer customer) {
    	StringBuilder returnString = new StringBuilder();
    	returnString.append(customer.getDetailsLong());
        if (customer.getBookings().size() !=0) {
        	returnString.append("\n-------------------\nBookings:\n");
        	int bookingAmt = 0;
			for (Booking booking : customer.getBookings()) {
				if (!booking.getCancelled()) {
	                bookingAmt++;
	                returnString.append("================\n")
	                  .append("Booking ID: ").append(booking.getId())
	                  .append(", Booking Date: ").append(booking.getBookingDate())
	                  .append(", Cost: £").append(booking.getCost())
	                  .append("\nFlight: ").append(booking.getFlight().getFlightNumber())
	                  .append(" : ").append(booking.getFlight().getOrigin())
	                  .append(" to ").append(booking.getFlight().getDestination()).append("\n");
	            } else {
	                returnString.append("========CANCELLED========\n")
	                  .append("Booking ID: ").append(booking.getId())
	                  .append(", Date: CANCELLED")
	                  .append(", Cost: £").append(booking.getCost()).append(" (cancellation charge)")
	                  .append("\nFlight: ").append(booking.getFlight().getFlightNumber())
	                  .append(" : ").append(booking.getFlight().getOrigin())
	                  .append(" to ").append(booking.getFlight().getDestination()).append("\n");
	            }

        	}
	        returnString.append("================\n").append(bookingAmt).append(" active booking(s)");
        } else {
            returnString.append("No active bookings");
        }
        return returnString.toString();
    }

    
}
