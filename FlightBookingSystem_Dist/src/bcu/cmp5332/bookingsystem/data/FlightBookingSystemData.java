package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Saving and loading for the flightbooking system. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 * 
 * This class contains the default constructor and other necessary methods to handle command parsing.
 */
public class FlightBookingSystemData {
    
	/*
	 * Loads the data once
	 */
    private static final List<DataManager> dataManagers = new ArrayList<>();
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new FlightDataManager());
        dataManagers.add(new CustomerDataManager());
        dataManagers.add(new BookingDataManager());
    }
    
    /**
     * Loads the data for the flight booking system
     * @return fbs returns the fbs
     * @throws FlightBookingSystemException when error constructing a class
     * @throws IOException when error accessing or opening a file
     */
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

        FlightBookingSystem fbs = new FlightBookingSystem();
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        return fbs;
    }
    
    
    /**
     * Stores the data for the flight booking system
     * @throws IOException when error accessing or writing to a file
     * @param fbs this is the flight booking system
     */
    public static void store(FlightBookingSystem fbs) throws IOException {

        for (DataManager dm : dataManagers) {
            dm.storeData(fbs);
        }
    }
    
    
    
    
    
}
