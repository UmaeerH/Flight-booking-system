package bcu.cmp5332.bookingsystem.model;

//import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private List<Booking> bookings = new ArrayList<>();
    
    public Customer(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        
        bookings = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int newid) {
        this.id = newid;
    }

    public String getName() {
    	return name;
    }
    
    public void setName(String newName) {
    	this.name = newName;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String newPhone) {
    	this.phone = newPhone;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String newEmail) {
    	this.email = newEmail;
    }
    
    public String getDetailsShort() {
    	return "Customer: #" + id + " - " + name + "\t | " + phone + " | " + email;
    }
    
    public String getDetailsLong() {
    	String returnString = "Customer #" + id
    						+ "\nName: "   + name
    						+ "\nContact: "  + phone + " | " + email;
    	return returnString;
    }
    
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    
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



}

