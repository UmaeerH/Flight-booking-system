package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Prints the HELP command for the user. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 * 
 * This class contains the default constructor and other necessary methods to handle command parsing.
 */
public class Help implements Command {
	/**
     * Default constructor for the Help class.
     * 
     * Initialises a new instance of the Help without any specific parameters.
     */
    public Help() {
        // Default constructor
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
        System.out.println(Command.HELP_MESSAGE);
    }
}
