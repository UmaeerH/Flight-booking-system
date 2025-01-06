package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Command to remove a existing customer from the flight booking system.
 * @author AnisaU
 * @author UmaeerH
 * @version main
 */
public class RemoveCustomer implements Command {
;

    private int customerID;
    
	/**
     * Constructor to create an AddCustomer command with specified name, phone, and email. 
     * @param name the name of the customer 
     * @param phone the phone number of the customer 
     * @param email the email address of the customer 
     */
    
    public RemoveCustomer(int customerid) {
        this.customerID = customerid;
    }
    /**
     * Executes the command to remove a customer from the flight booking system. 
     * @param flightBookingSystem the flight booking system to remove the customer from
     * @throws FlightBookingSystemException if an error occurs during customer removal 
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        try {
        	flightBookingSystem.getCustomerByID(customerID);
        } catch(FlightBookingSystemException ex) {
        	System.out.println(ex);
        }
        Customer customer = flightBookingSystem.getCustomerByID(customerID);
        
        
        // Check to see if the customer has any active bookings
        for (Booking booking : customer.getBookings()) {
        	if (booking.getCancelled()==false) {
        		throw new FlightBookingSystemException("Customer has active bookings and cannot be removed.");
        	}
        }

    	
    	flightBookingSystem.removeCustomer(customerID);
        System.out.println("Customer #" + customerID + " has been removed.");
    }
}
