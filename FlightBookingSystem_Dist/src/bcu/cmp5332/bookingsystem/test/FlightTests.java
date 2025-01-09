package bcu.cmp5332.bookingsystem.test;

import bcu.cmp5332.bookingsystem.model.*;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/** 
 * Test class for Flight functionalities. 
 * @author UmaeerH
 * @author AnisaU
 * @version main
 * 
 */
public class FlightTests {
	private Flight testFlight;
	private Customer testCustomer;
	/** 
	 * Sets up the test environment before each test case. 
	 */
	@Before
    public void setUp() {
        testFlight = new Flight(50, "AA123", "London", "New York", LocalDate.now(), 100, 500.0, false);
        testCustomer = new Customer(999, "Choi Yu-Jin", "42894829", "Choi@gmail.com", false);
    }
	/** 
	 * Tests the getId method of Flight class. 
	 */
    @Test
    public void getIdTest() {
    	assertEquals(50, testFlight.getId());
    }
    /** 
     * Tests the setId method of Flight class. 
     */
	@Test
    public void setIdTest() {
		testFlight.setId(23);
		assertEquals(23, testFlight.getId());
    }
	/** 
	 * Tests the getFlightNumber method of Flight class. 
	 */
    @Test
    public void getFlightNumberTest() {
    	assertEquals("AA123", testFlight.getFlightNumber());
    }
    /** 
     * Tests the setFlightNumber method of Flight class. 
     */
    @Test
    public void setFlightNumberTest() {
    	testFlight.setFlightNumber("LS3421");
    	assertEquals("LS3421", testFlight.getFlightNumber());
    }
    /** 
     * Tests the getOrigin method of Flight class. 
     */
    @Test
    public void getOriginTest() {
    	assertEquals("London", testFlight.getOrigin());
    }
    /** 
     * Tests the setOrigin method of Flight class. 
     */
    @Test
    public void setOriginTest() {
    	testFlight.setOrigin("Birmingham");
    	assertEquals("Birmingham", testFlight.getOrigin());
    }
    /** 
     * Tests the getDestination method of Flight class. 
     */
    @Test
    public void getDestinationTest() {
    	assertEquals("New York", testFlight.getDestination());
    }
    /** 
     * Tests the setDestination method of Flight class. 
     */
    @Test
    public void setDestinationTest() {
    	testFlight.setDestination("Bucharest Otopeni");
    	assertEquals("Bucharest Otopeni", testFlight.getDestination());
    }
    /** 
     * Tests the getDepartureDate method of Flight class. 
     */
    @Test
    public void getDepartureDateTest() {
    	assertEquals(LocalDate.now(), testFlight.getDepartureDate());
    }
    /** 
     * Tests the setDepartureDate method of Flight class. 
     */
    @Test
    public void setDepartureDateTest() {
    	testFlight.setDepartureDate(LocalDate.of(2025, 9, 13));
    	assertEquals(LocalDate.of(2025, 9, 13), testFlight.getDepartureDate());
    }
    /** 
     * Tests the getCapacity method of Flight class. 
     */
    @Test
    public void getCapacityTest() {
    	assertEquals(100, testFlight.getCapacity());
    }
    /** 
     * Tests the setCapacity method of Flight class. 
     */
    @Test
    public void setCapacityTest() {
    	testFlight.setCapacity(78);
    	assertEquals(78, testFlight.getCapacity());
    }
    /** 
     * Tests the getPrice method of Flight class. 
     */
    @Test
    public void getPriceTest() {
    	assertEquals(500.0, testFlight.getPrice());
    }
    /** 
     * Tests the setPrice method of Flight class. 
     */
    @Test
    public void setPriceTest() {
    	testFlight.setPrice(749.91);
    	assertEquals(749.91, testFlight.getPrice());
    }
    /** 
     * Tests the getFreeCapacity method of Flight class. 
     */
    @Test
    public void getFreeCapacityTest() {
    	assertEquals(100, testFlight.getFreeCapacity());
    }
    /** 
     * Tests the getPassengers method of Flight class. 
     */
    @Test
    public void getPassengersTest() {
    	assertEquals(Collections.emptyList(), testFlight.getPassengers());
    }
    /** 
     * Tests the getDeleted method of Flight class. 
     */
    @Test
    public void getDeletedTest() {
    	assertEquals(false, testFlight.getDeleted());
    }
    /** 
     * Tests the setDeleted method of Flight class. 
     */
    @Test
    public void setDeletedTest() {
    	testFlight.setDeleted(true);
    	assertEquals(true, testFlight.getDeleted());
    }
    /** 
     * Tests the addPassengers method of Flight class. 
     */
    @Test
    public void addPassengerTest() {
    	List<Customer> customerList = new ArrayList<>();
    	customerList.add(testCustomer);
    	testFlight.addPassenger(testCustomer);
    	assertEquals(customerList, testFlight.getPassengers());
    }
    /** 
     * Tests the removePassenger method of Flight class. 
     * @throws FlightBookingSystemException if the passenger is not found 
     */
    @Test(expected = FlightBookingSystemException.class)
    public void removePassengerTest() throws FlightBookingSystemException {
    	testFlight.removePassenger(testCustomer);
    }
    /**
     * Tests the getDetailsShort method of Flight class. 
     */
    @Test
    public void getDetailsShortTest() {
    	assertEquals("Flight #50 - AA123 - London to New York on " + LocalDate.now() + "\nSeats filled: 0/100", testFlight.getDetailsShort());
    }
}
