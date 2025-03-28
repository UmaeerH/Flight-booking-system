package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.gui.MainWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * Command to load the GUI system.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 * 
 * This class contains the default constructor and other necessary methods to handle command parsing.
 */

public class LoadGUI implements Command {
	/**
     * Default constructor for the LoadGUI class.
     * 
     * Initialises a new instance of the LoadGUI without any specific parameters.
     */
    public LoadGUI() {
        // Default constructor
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        new MainWindow(flightBookingSystem);
    }
    
}
