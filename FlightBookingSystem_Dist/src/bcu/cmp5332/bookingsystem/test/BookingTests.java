package bcu.cmp5332.bookingsystem.test;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void getIdTest() {
        // TODO: Implement test for getId method
    }

    @Test
    public void getCustomerTest() {
        // TODO: Implement test for getCustomer method
    }

    @Test
    public void getFlightTest() {
        // TODO: Implement test for getFlight method
    }

    @Test
    public void getBookingDateTest() {
        // TODO: Implement test for getBookingDate method
    }

    @Test
    public void setBookingDateTest() {
        // TODO: Implement test for setBookingDate method
    }

    @Test
    public void getCostTest() {
        // TODO: Implement test for getCost method
    }

    @Test
    public void setCostTest() {
        // TODO: Implement test for setCost method
    }

    @Test
    public void getCancelledTest() {
        // TODO: Implement test for getCancelled method
    }

    @Test
    public void getCustomerIDTest() {
        // TODO: Implement test for getCustomerID method
    }

    @Test
    public void getFlightIDTest() {
        // TODO: Implement test for getFlightID method
    }
}
