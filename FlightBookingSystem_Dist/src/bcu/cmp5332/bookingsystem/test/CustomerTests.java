package bcu.cmp5332.bookingsystem.test;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

public class CustomerTests {
	private Flight testFlight;
	private Customer testCustomer;
	@Before
    public void setUp() {
        testFlight = new Flight(50, "AA123", "London", "New York", LocalDate.now(), 100, 500.0);
        testCustomer = new Customer(999, "Choi Yu-Jin", "42894829", "Choi@gmail.com");
    }
	
	
    @Test
    public void getIDTest() {
        // TODO: Implement test for getID method
    }

    @Test
    public void setIDTest() {
        // TODO: Implement test for setID method
    }
	

	@Test
    public void getNameTest() {
        assert (testCustomer.getName().equals("Choi Yu-Jin"));
    }
	
    @Test
    public void setNameTest() {
        // TODO: Implement test for setName method
    }

	@Test
	public void getPhoneTest() {
		assert (testCustomer.getPhone().equals("42894829"));
	}
	
    @Test
    public void setPhoneTest() {
        // TODO: Implement test for setPhone method
    }

    @Test
    public void getEmailTest() {
        // TODO: Implement test for getEmail method
    }

    @Test
    public void setEmailTest() {
        // TODO: Implement test for setEmail method
    }

    @Test			//it should be empty
    public void getBookings() {
        // TODO: Implement test for getBookings method
    }

    @Test(expected = FlightBookingSystemException.class)
    public void addBooking() throws FlightBookingSystemException {
        // TODO: Implement test for addBooking method
        // This test should check for duplicate bookings and ensure an exception is thrown
    }
	
	
}
