package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
/**
 * Command to add a new customer to the flight booking system.
 */
public class AddCustomer implements Command {

    private final String name;
    private final String phone;
    private final String email;

    /**
     * Constructor to create an AddCustomer command with specified name, phone, and email. *
     * @param name the name of the customer 
     * @param phone the phone number of the customer 
     * @param email the email address of the customer 
     */
    public AddCustomer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    /**
     * Executes the command to add a new customer to the flight booking system. *
     * @param flightBookingSystem the flight booking system to add the customer to 
     * @throws FlightBookingSystemException if an error occurs during customer addition 
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getCustomers().size() > 0) {
            int lastIndex = flightBookingSystem.getCustomers().size() - 1;
            maxId = flightBookingSystem.getCustomers().get(lastIndex).getId();
        }
        
        Customer customer = new Customer(++maxId, name, phone, email, false);
        flightBookingSystem.addCustomer(customer);
        System.out.println("Customer #" + customer.getId() + ", " + customer.getName() + " added.");
    }
}
