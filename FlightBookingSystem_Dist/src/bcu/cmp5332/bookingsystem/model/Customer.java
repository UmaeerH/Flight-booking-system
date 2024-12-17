package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    
    public void addBooking(Booking booking) {
        // TODO: implementation here
    }
}
