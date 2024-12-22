package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    

    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
        
    }

	Customer getCustomer() {
		return customer;
	}
	
	void setCustomer(Customer newCustomer) {
		this.customer = newCustomer;
	}

	Flight getFlight() {
		return flight;
	}

	void setFlight(Flight newFlight) {
		this.flight = newFlight;
	}

	LocalDate getBookingDate() {
		return bookingDate;
	}

	void setBookingDate(LocalDate newBookingDate) {
		this.bookingDate = newBookingDate;
	}
    
    
    
}
