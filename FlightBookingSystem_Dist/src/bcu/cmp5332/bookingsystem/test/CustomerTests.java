package bcu.cmp5332.bookingsystem.test;
import java.time.LocalDate;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTests {
	private Flight testFlight;
	private Customer testCustomer;
	private Booking testBooking;
	@Before
    public void setUp() {
        testFlight = new Flight(50, "AA123", "London", "New York", LocalDate.now(), 100, 500.0, false);
        testCustomer = new Customer(999, "Choi Yu-Jin", "42894829", "Choi@gmail.com", false);
        testBooking = new Booking(1, testCustomer, testFlight, 500.0, LocalDate.now(), false);
    }
	
	
    @Test
    public void getIDTest() {
        assertEquals(999, testCustomer.getId());
    }

    @Test
    public void setIDTest() {
        testCustomer.setId(50);
        assertEquals(50, testCustomer.getId());
    }
	

	@Test
    public void getNameTest() {
        //assert (testCustomer.getName().equals("Choi Yu-Jin"));
		assertEquals("Choi Yu-Jin", testCustomer.getName());
    }
	
    @Test
    public void setNameTest() {
        testCustomer.setName("Garry Kasparov");
        assertEquals("Garry Kasparov", testCustomer.getName());
    }

	@Test
	public void getPhoneTest() {
		assertEquals("42894829", testCustomer.getPhone());
	}
	
    @Test
    public void setPhoneTest() {
    	testCustomer.setPhone("123123123");
    	assertEquals("123123123", testCustomer.getPhone());
    }

    @Test
    public void getEmailTest() {
    	assertEquals("Choi@gmail.com", testCustomer.getEmail());
    }

    @Test
    public void setEmailTest() {
        testCustomer.setEmail("newMail@mail.com");
        assertEquals("newMail@mail.com", testCustomer.getEmail());
    }

    @Test			//it should be empty
    public void getBookings() {
    	assertEquals(Collections.emptyList(), testCustomer.getBookings());
    }

    
    @Test // Correctly add a booking
    public void addBooking() throws FlightBookingSystemException {
        testCustomer.addBooking(testBooking);
        assertEquals(1, testCustomer.getBookings().size());
    }
    
    
    @Test(expected = FlightBookingSystemException.class)	
    public void addBookingFail() throws FlightBookingSystemException {
        testCustomer.addBooking(testBooking);				
        testCustomer.addBooking(testBooking);				// Adding dupe booking, should throw exception
    }
	
	
}
