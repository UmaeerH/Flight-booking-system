package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Interface for commands that can be executed on the Flight Booking System.
 */
public interface Command {
	
	/**
     * Help message of all the commands.
     */
    public static final String HELP_MESSAGE = "Commands:\n"
        + "\tlistflights                               print all flights\n"
        + "\tlistcustomers                             print all customers\n"
        + "\taddflight                                 add a new flight\n"
        + "\taddcustomer                               add a new customer\n"
        + "\tshowflight(detailed) [flight id]          show flight details\n"
        + "\tshowcustomer [customer id]                show customer details\n"
        + "\taddbooking [customer id] [flight id]      add a new booking\n"
        + "\teditbooking [booking id] [flight id]      update a booking\n"
        + "\tremovecustomer [customer id]              remove a customer\n"
        + "\tremoveflight [flight id]                  remove a flight\n"
        + "\tcancelbooking [booking id]                cancel a booking\n"
        + "\tloadgui                                   loads the GUI version of the app\n"
        + "\thelp                                      prints this help message\n"
        + "\texit                                      exits the program";

    /**
     * Executes a command on the given Flight Booking System.
     * 
     * @param flightBookingSystem the flight booking system on which to execute the command
     * @throws FlightBookingSystemException if an error occurs during the execution of the command
     */
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException;
    
    
    
}
