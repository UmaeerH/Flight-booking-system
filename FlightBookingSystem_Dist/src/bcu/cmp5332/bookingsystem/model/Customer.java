package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SuppressWarnings("unused")
public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private List<Booking> bookings = new ArrayList<>();
    
    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        
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
    
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    
    public void addBooking(Booking booking) {
        // TODO: implementation here
    }
}
