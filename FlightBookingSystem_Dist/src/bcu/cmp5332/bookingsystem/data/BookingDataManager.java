package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    	System.out.println("Loading Booking placeholder");
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Booking booking : fbs.getBookings()) {
                out.print(booking.getId() + SEPARATOR);
                out.print(booking.getCustomerID() + SEPARATOR);
                out.print(booking.getFlightID() + SEPARATOR);
                out.print(booking.getBookingDate() + SEPARATOR);
                out.print(booking.getCost() + SEPARATOR);
                out.print(booking.getCancelled() + SEPARATOR);
                out.println();
            }
        }
    }
    
}
