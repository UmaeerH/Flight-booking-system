package bcu.cmp5332.bookingsystem.test;

import bcu.cmp5332.bookingsystem.model.*;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class FlightTests {
	private Flight testFlight;
	private Customer testCustomer;
	@Before
    public void setUp() {
        testFlight = new Flight(50, "AA123", "London", "New York", LocalDate.now(), 100, 500.0);
        testCustomer = new Customer(999, "Choi Yu-Jin", "42894829", "Choi@gmail.com");
    }

    @Test
    public void getIdTest() {
        // TODO: Implement test for getId method
    }

    @Test
    public void setIdTest() {
        // TODO: Implement test for setId method
    }

    @Test
    public void getFlightNumberTest() {
        // TODO: Implement test for getFlightNumber method
    }

    @Test
    public void setFlightNumberTest() {
        // TODO: Implement test for setFlightNumber method
    }

    @Test
    public void getOriginTest() {
        // TODO: Implement test for getOrigin method
    }

    @Test
    public void setOriginTest() {
        // TODO: Implement test for setOrigin method
    }

    @Test
    public void getDestinationTest() {
        // TODO: Implement test for getDestination method
    }

    @Test
    public void setDestinationTest() {
        // TODO: Implement test for setDestination method
    }

    @Test
    public void getDepartureDateTest() {
        // TODO: Implement test for getDepartureDate method
    }

    @Test
    public void setDepartureDateTest() {
        // TODO: Implement test for setDepartureDate method
    }

    @Test
    public void getCapacityTest() {
        // TODO: Implement test for getCapacity method
    }

    @Test
    public void setCapacityTest() {
        // TODO: Implement test for setCapacity method
    }

    @Test
    public void getPriceTest() {
        // TODO: Implement test for getPrice method
    }

    @Test
    public void setPriceTest() {
        // TODO: Implement test for setPrice method
    }

    @Test
    public void getFreeCapacityTest() {
        // TODO: Implement test for getFreeCapacity method
    }

    @Test
    public void setFreeCapacityTest() {
        // TODO: Implement test for setFreeCapacity method
    }

    @Test
    public void getPassengersTest() {
        // TODO: Implement test for getPassengers method
    }

    @Test
    public void addPassengerTest() {
        // TODO: Implement test for addPassenger method
    }

    @Test(expected = FlightBookingSystemException.class)
    public void removePassengerTest() throws FlightBookingSystemException {
        // TODO: Implement test for removePassenger method
        // This test should ensure that an exception is thrown when trying to remove a passenger who is not on the flight
    }

    @Test
    public void getDetailsShortTest() {
        // TODO: Implement test for getDetailsShort method
    }

    @Test
    public void getDetailsLongTest() {
        // TODO: Implement test for getDetailsLong method
    }
}
