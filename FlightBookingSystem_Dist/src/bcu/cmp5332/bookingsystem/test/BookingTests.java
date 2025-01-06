package bcu.cmp5332.bookingsystem.test;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class BookingTests {
    private Booking testBooking;
    private Flight testFlight;
	private Customer testCustomer;

	@Before
    public void setUp() {
        testFlight = new Flight(50, "AA123", "London", "New York", LocalDate.now(), 100, 500.0);
        testCustomer = new Customer(999, "Choi Yu-Jin", "42894829", "Choi@gmail.com", false);
        testBooking = new Booking(1, testCustomer, testFlight, 500.0, LocalDate.now(), false);
    }

	@Test
    public void getIdTest() {
    	assertEquals(1, testBooking.getId());
    }

	@Test
    public void getCustomerTest() {
    	assertEquals(testCustomer, testBooking.getCustomer());
    }

    @Test
    public void getFlightTest() {
    	assertEquals(testFlight, testBooking.getFlight());
    }

    @Test
    public void getBookingDateTest() {
    	assertEquals(LocalDate.now() , testBooking.getBookingDate());
    }

    @Test
    public void setBookingDateTest() {
    	testBooking.setBookingDate(LocalDate.of(2025, 11, 23));
    	assertEquals(LocalDate.of(2025, 11, 23), testBooking.getBookingDate());
    }

    @Test
    public void getCostTest() {
    	assertEquals(500.0, testBooking.getCost());
    }

    @Test
    public void setCostTest() {
    	testFlight.setPrice(603.21);
    	testBooking.setCost(testFlight.getPrice());
    	assertEquals(603.21, testBooking.getCost());
    }

    @Test
    public void getCancelledTest() {
    	// assertTrue(false, testBooking.getCancelled());
    	assertEquals(false, testBooking.getCancelled());
    }

    @Test
    public void getCustomerIDTest() {
    	assertEquals(999, testBooking.getCustomerID());
    	
    }

    @Test
    public void getFlightIDTest() {
    	assertEquals(50, testBooking.getFlightID());
    }
}
