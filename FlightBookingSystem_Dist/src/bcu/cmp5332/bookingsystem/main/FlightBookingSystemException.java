package bcu.cmp5332.bookingsystem.main;

/**
 * FlightBookingSystemException extends {@link Exception} class and is a custom exception
 * that is used to notify the user about errors or invalid commands.
 * 
 */
public class FlightBookingSystemException extends Exception {

    /**
	 * The serialVersionUID helps maintain the compatibility of serialised objects across different versions of a class
	 */
	private static final long serialVersionUID = -2737613743849322412L;
	
	/**
	 * Constructs a new FlightBookingSystemException with the specified detail message. 
	 * @param message the detail message 
	 */
	public FlightBookingSystemException(String message) {
        super(message);
    }
}
