package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
/**
 * Interface for managing data operations related to the Flight Booking System. 
 * It provides methods to load and store data. * The data elements are separated by the "::" separator.
 * @version 1.0 
 */
public interface DataManager {
	/**
	 * Separator used to distinguish data elements in the data files. 
	 */
    public static final String SEPARATOR = "::";
    /**
     * Loads data into the flight booking system. 
     * @param fbs the flight booking system to load data into 
     * @throws IOException if an I/O error occurs during data loading 
     * @throws FlightBookingSystemException if a flight booking system-specific error occurs during data loading 
     */
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException;
    /**
     * Stores data from the flight booking system. 
     * @param fbs the flight booking system to store data from 
     * @throws IOException if an I/O error occurs during data storing 
     */
    public void storeData(FlightBookingSystem fbs) throws IOException;
    
}
