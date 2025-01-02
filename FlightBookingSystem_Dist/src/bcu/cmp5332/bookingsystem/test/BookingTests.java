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
        testCustomer = new Customer(999, "Choi Yu-Jin", "42894829", "Choi@gmail.com");
        testBooking = new Booking(1, testCustomer, testFlight, 500.0, LocalDate.now(), false);
    }

    @SuppressWarnings("static-access")
	@Test
    public void getIdTest() {
        // TODO: Implement test for getId method
    	assertEquals(1, testBooking.getId());
    }

    @SuppressWarnings("static-access")
	@Test
    public void getCustomerTest() {
        // TODO: Implement test for getCustomer method
    	assertEquals(testCustomer, testBooking.getCustomer());
    }

    @Test
    public void getFlightTest() {
        // TODO: Implement test for getFlight method
    	assertEquals(testFlight, testBooking.getFlight());
    }

    @Test
    public void getBookingDateTest() {
        // TODO: Implement test for getBookingDate method
    	assertEquals(LocalDate.now() , testBooking.getBookingDate());
    }

    @Test
    public void setBookingDateTest() {
        // TODO: Implement test for setBookingDate method
    	testBooking.setBookingDate(LocalDate.of(2025, 11, 23));
    	assertEquals(LocalDate.of(2025, 11, 23), testBooking.getBookingDate());
    }

    @Test
    public void getCostTest() {
        // TODO: Implement test for getCost method
    	assertEquals(500.0, testBooking.getCost());
    }

    @Test
    public void setCostTest() {
        // TODO: Implement test for setCost method
    	testFlight.setPrice(603.21);
    	testBooking.setCost(testFlight.getPrice());
    	assertEquals(603.21, testBooking.getCost());
    }

    @Test
    public void getCancelledTest() {
        // TODO: Implement test for getCancelled method
    	// assertTrue("Flight is not cancelled.", testBooking.getCancelled());
    	
    	assertEquals(false, testBooking.getCancelled());
    }

    @Test
    public void getCustomerIDTest() {
        // TODO: Implement test for getCustomerID method
    	assertEquals(999, testBooking.getCustomerID());
    	
    }

    @Test
    public void getFlightIDTest() {
        // TODO: Implement test for getFlightID method
    	assertEquals(50, testBooking.getFlightID());
    }
}
