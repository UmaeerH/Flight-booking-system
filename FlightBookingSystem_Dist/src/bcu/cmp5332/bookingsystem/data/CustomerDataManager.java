package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Manages the loading and storing of customer data in the flight booking system.
 * @author UmaeerH
 * @author AnisaU03
 * @version main 
 * 
 * This class contains the default constructor and other necessary methods to handle command parsing.
 */
public class CustomerDataManager implements DataManager {
	/**
     * Default constructor for the CustomerDataManager class.
     * 
     * Initialises a new instance of the CustomerDataManager without any specific parameters.
     */
    public CustomerDataManager() {
        // Default constructor
    }

    private final String RESOURCE = "./resources/data/customers.txt";
    /**
     * Loads customer data from a file into the flight booking system. 
     * @param fbs the flight booking system to load customer data into 
     * @throws IOException if an I/O error occurs while reading the file 
     * @throws FlightBookingSystemException if an error occurs while parsing customer data 
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String customerName = properties[1];
                    String customerPhone = properties[2];
                    String customerMail = properties[3];
                    boolean deleted = Boolean.parseBoolean(properties[4]);
                    Customer customer = new Customer(id, customerName, customerPhone, customerMail, deleted);
                    fbs.addCustomer(customer);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    /**
     * Stores customer data from the flight booking system into a file. 
     * @param fbs the flight booking system containing customer data 
     * @throws IOException if an I/O error occurs while writing to the file 
     * 
     * This class contains the default constructor and other necessary methods to handle command parsing.
     */

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
    	System.out.println("Storing data to file...");
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Customer customer : fbs.getCustomers()) {
                out.print(customer.getId() + SEPARATOR);
                out.print(customer.getName() + SEPARATOR);
                out.print(customer.getPhone() + SEPARATOR);
                out.print(customer.getEmail() + SEPARATOR);
                out.print(customer.getDeleted() + SEPARATOR);
                out.println();
            }
        }
    }
}
