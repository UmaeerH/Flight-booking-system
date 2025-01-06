package bcu.cmp5332.bookingsystem.test;

import bcu.cmp5332.bookingsystem.model.*;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightTests {
	private Flight testFlight;
	private Customer testCustomer;
	private Booking testBooking;
	@Before
    public void setUp() {
        testFlight = new Flight(50, "AA123", "London", "New York", LocalDate.now(), 100, 500.0);
        testCustomer = new Customer(999, "Choi Yu-Jin", "42894829", "Choi@gmail.com");
        testBooking = new Booking(1, testCustomer, testFlight, 500.0, LocalDate.now(), false);
    }

    @Test
    public void getIdTest() {
    	assertEquals(50, testFlight.getId());
    }

	@Test
    public void setIdTest() {
		testFlight.setId(23);
		assertEquals(23, testFlight.getId());
    }

    @Test
    public void getFlightNumberTest() {
    	assertEquals("AA123", testFlight.getFlightNumber());
    }

    @Test
    public void setFlightNumberTest() {
    	testFlight.setFlightNumber("LS3421");
    	assertEquals("LS3421", testFlight.getFlightNumber());
    }

    @Test
    public void getOriginTest() {
    	assertEquals("London", testFlight.getOrigin());
    }

    @Test
    public void setOriginTest() {
    	testFlight.setOrigin("Birmingham");
    	assertEquals("Birmingham", testFlight.getOrigin());
    }

    @Test
    public void getDestinationTest() {
    	assertEquals("New York", testFlight.getDestination());
    }

    @Test
    public void setDestinationTest() {
    	testFlight.setDestination("Bucharest Otopeni");
    	assertEquals("Bucharest Otopeni", testFlight.getDestination());
    }

    @Test
    public void getDepartureDateTest() {
    	assertEquals(LocalDate.now(), testFlight.getDepartureDate());
    }

    @Test
    public void setDepartureDateTest() {
    	testFlight.setDepartureDate(LocalDate.of(2025, 9, 13));
    	assertEquals(LocalDate.of(2025, 9, 13), testFlight.getDepartureDate());
    }

    @Test
    public void getCapacityTest() {
    	assertEquals(100, testFlight.getCapacity());
    }

    @Test
    public void setCapacityTest() {
    	testFlight.setCapacity(78);
    	assertEquals(78, testFlight.getCapacity());
    }

    @Test
    public void getPriceTest() {
    	assertEquals(500.0, testFlight.getPrice());
    }

    @Test
    public void setPriceTest() {
    	testFlight.setPrice(749.91);
    	assertEquals(749.91, testFlight.getPrice());
    }

    @Test
    public void getFreeCapacityTest() {
    	assertEquals(100, testFlight.getFreeCapacity());
    }

    @Test
    public void getPassengersTest() {
    	assertEquals(Collections.emptyList(), testFlight.getPassengers());
    }

    @Test
    public void addPassengerTest() {
    	List<Customer> customerList = new ArrayList<>();
    	customerList.add(testCustomer);
    	testFlight.addPassenger(testCustomer);
    	assertEquals(customerList, testFlight.getPassengers());
    }

    @Test(expected = FlightBookingSystemException.class)
    public void removePassengerTest() throws FlightBookingSystemException {
    	testFlight.removePassenger(testCustomer);
    }

    @Test
    public void getDetailsShortTest() {
    	assertEquals("Flight #50 - AA123 - London to New York on " + LocalDate.now() + "\nSeats filled: 0/100", testFlight.getDetailsShort());
    }
}
