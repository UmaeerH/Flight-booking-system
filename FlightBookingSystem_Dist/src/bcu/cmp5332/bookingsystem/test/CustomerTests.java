package bcu.cmp5332.bookingsystem.test;
import java.time.LocalDate;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
/** 
 * Test suite for the Customer class.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 * 
 * This class contains the default constructor and other necessary methods to handle command parsing.
 */
public class CustomerTests {
	private Flight testFlight;
	private Customer testCustomer;
	private Booking testBooking;
	/** 
	 * Sets up the test environment before each test. 
	 */
	@Before
    public void setUp() {
        testFlight = new Flight(50, "AA123", "London", "New York", LocalDate.now(), 100, 500.0, false);
        testCustomer = new Customer(999, "Choi Yu-Jin", "42894829", "Choi@gmail.com", false);
        testBooking = new Booking(1, testCustomer, testFlight, 500.0, LocalDate.now(), false, 0);
    }
	
	/** 
	 * Tests the getId method of the Customer class. 
	 */
    @Test
    public void getIDTest() {
        assertEquals(999, testCustomer.getId());
    }
    /** 
     * Tests the setId method of the Customer class. 
     */
    @Test
    public void setIDTest() {
        testCustomer.setId(50);
        assertEquals(50, testCustomer.getId());
    }
    /** 
     * Tests the getName method of the Customer class. 
     */
	@Test
    public void getNameTest() {
        //assert (testCustomer.getName().equals("Choi Yu-Jin"));
		assertEquals("Choi Yu-Jin", testCustomer.getName());
    }
	/** 
	 * Tests the setName method of the Customer class. 
	 */
    @Test
    public void setNameTest() {
        testCustomer.setName("Garry Kasparov");
        assertEquals("Garry Kasparov", testCustomer.getName());
    }
    /** 
     * Tests the getPhone method of the Customer class. 
     */
	@Test
	public void getPhoneTest() {
		assertEquals("42894829", testCustomer.getPhone());
	}
	/** 
	 * Tests the setPhone method of the Customer class. 
	 */
    @Test
    public void setPhoneTest() {
    	testCustomer.setPhone("123123123");
    	assertEquals("123123123", testCustomer.getPhone());
    }
    /** 
     * Tests the getEmail method of the Customer class. 
     */
    @Test
    public void getEmailTest() {
    	assertEquals("Choi@gmail.com", testCustomer.getEmail());
    }
    /** 
     * Tests the setEmail method of the Customer class. 
     */
    @Test
    public void setEmailTest() {
        testCustomer.setEmail("newMail@mail.com");
        assertEquals("newMail@mail.com", testCustomer.getEmail());
    }
    /** 
     * Tests the getBookings method of the Customer class. 
     * Ensures the bookings list is empty initially. 
     */
    @Test			//it should be empty
    public void getBookings() {
    	assertEquals(Collections.emptyList(), testCustomer.getBookings());
    }
    /** 
     * Tests the addBooking method of the Customer class. 
     * Ensures a booking is correctly added. 
     * @throws FlightBookingSystemException if booking fails. 
     */
    @Test // Correctly add a booking
    public void addBooking() throws FlightBookingSystemException {
        testCustomer.addBooking(testBooking);
        assertEquals(1, testCustomer.getBookings().size());
    }
    /** 
     * Tests the addBooking method of the Customer class. 
     * Ensures adding a duplicate booking throws an exception. 
     * @throws FlightBookingSystemException if booking fails. 
     */
    
    @Test(expected = FlightBookingSystemException.class)	
    public void addBookingFail() throws FlightBookingSystemException {
        testCustomer.addBooking(testBooking);				
        testCustomer.addBooking(testBooking);				// Adding dupe booking, should throw exception
    }
	
	
}
