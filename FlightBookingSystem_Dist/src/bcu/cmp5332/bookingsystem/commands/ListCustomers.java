package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;
/**
 * Command to list all customers in the flight booking system. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 * 
 * This class contains the default constructor and other necessary methods to handle command parsing.
 */
public class ListCustomers implements Command {
	/**
     * Default constructor for the ListCustomers class.
     * 
     * Initialises a new instance of the ListCustomers without any specific parameters.
     */
    public ListCustomers() {
        // Default constructor
    }
	/**
	 * Executes the command to list all customers in the flight booking system. 
	 * @param flightBookingSystem the flight booking system containing the customers 
	 * @throws FlightBookingSystemException if an error occurs while retrieving customers 
	 */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Customer> customers = flightBookingSystem.getCustomers();
        int numberOf = 0;
        for (Customer customer : customers) {
        	if(customer.getDeleted()==false) {
        		System.out.println(customer.getDetailsShort());
        		numberOf++;
        	}
        }
        System.out.println(numberOf + " customer(s)");
    }
}
