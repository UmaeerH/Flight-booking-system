package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;
/**
 * Command to list all customers in the flight booking system. 
 * @author UmaeerH
 * @author AnisaU
 * @version main
 */
public class ListCustomers implements Command {
	/**
	 * Executes the command to list all customers in the flight booking system. 
	 * @param flightBookingSystem the flight booking system containing the customers 
	 * @throws FlightBookingSystemException if an error occurs while retrieving customers 
	 */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Customer> customers = flightBookingSystem.getCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.getDetailsShort());
        }
        System.out.println(customers.size() + " customer(s)");
    }
}
