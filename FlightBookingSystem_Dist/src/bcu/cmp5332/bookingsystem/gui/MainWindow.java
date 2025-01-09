package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
@SuppressWarnings("unused")

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -565274564213160712L;
	
	private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;

    private JMenuItem adminExit;

    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;
    
    private JMenuItem bookingsView;
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;

    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;

    private FlightBookingSystem fbs;

    public MainWindow(FlightBookingSystem fbs) {

        initialize();
        this.fbs = fbs;
    }
    
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
        setTitle("Flight Booking Management System");
        getContentPane().setBackground(new Color(252, 210, 241));
        

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding Flights menu and menu items
        flightsMenu = new JMenu("Flights");
        menuBar.add(flightsMenu);

        flightsView = new JMenuItem("View");
        flightsAdd = new JMenuItem("Add");
        flightsDel = new JMenuItem("Delete");
        flightsMenu.add(flightsView);
        flightsMenu.add(flightsAdd);
        flightsMenu.add(flightsDel);
        // adding action listener for Flights menu items
        for (int i = 0; i < flightsMenu.getItemCount(); i++) {
            flightsMenu.getItem(i).addActionListener(this);
        }
        
        // adding Bookings menu and menu items
        bookingsMenu = new JMenu("Bookings");
        menuBar.add(bookingsMenu);
        
        bookingsView = new JMenuItem("View");
        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        bookingsMenu.add(bookingsView);
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        // adding action listener for Bookings menu items
        for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
            bookingsMenu.getItem(i).addActionListener(this);
        }

        // adding Customers menu and menu items
        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        custView = new JMenuItem("View");
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");

        customersMenu.add(custView);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);
        // adding action listener for Customers menu items
        custView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);

        setSize(800, 500);
        setResizable(false);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
/* Uncomment the following line to not terminate the console app when the window is closed */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }	

/* Uncomment the following code to run the GUI version directly from the IDE */
    //FIXME - comment lines below to convert back to CLI
    public static void main(String[] args) throws IOException, FlightBookingSystemException {
        FlightBookingSystem fbs = FlightBookingSystemData.load();
         new MainWindow(fbs);			
    }


    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
            try {
                FlightBookingSystemData.store(fbs);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        } else if (ae.getSource() == flightsView) {
            displayFlights(); 
        } else if (ae.getSource() == flightsAdd) {
            new AddFlightWindow(this);
        } else if (ae.getSource() == flightsDel) {
        	new RemoveFlightWindow(this);
        } else if (ae.getSource() == bookingsView) {
        	displayBookings();
        } else if (ae.getSource() == bookingsIssue) {
        	new AddBookingWindow(this);
        } else if (ae.getSource() == bookingsCancel) {
        	new CancelBookingWindow(this);
        } else if (ae.getSource() == bookingsUpdate) {
        	new UpdateBookingWindow(this);
        } else if (ae.getSource() == custView) {
        	displayCust();
        } else if (ae.getSource() == custAdd) {
        	 new AddCustomerWindow(this);
        } else if (ae.getSource() == custDel) {
            new RemoveCustomerWindow(this);
        }
    }

    public void displayFlights() {
    	List<Flight> flightlistFull = fbs.getFlights();
        List<Flight> flightlist = new ArrayList<>();
        // Creates a list of non-deleted flights
        for(Flight flight : flightlistFull) {
        	if(flight.getDeleted() == false) {
        		flightlist.add(flight);
        	}
        }
        
        
        // headers for the table
        String[] columns = new String[]{"ID", "Flight No", "Origin", "Destination", 
        		"Departure Date", "Remaining Seats", "Current Price"};

        Object[][] data = new Object[flightlist.size()][7];
        for (int i = 0; i < flightlist.size(); i++) {
            Flight flight = flightlist.get(i);
            data[i][0] = flight.getId();
        	data[i][1] = flight.getFlightNumber();
        	data[i][2] = flight.getOrigin();
        	data[i][3] = flight.getDestination();
        	
            if (flight.getDepartureDate().isBefore(LocalDate.now())) {
            	data[i][4] = "Departed: " + flight.getDepartureDate();
            } else {
            	data[i][4] = flight.getDepartureDate();
            }
            data[i][5] = flight.getFreeCapacity();
            data[i][6] = flight.getBookingPrice();
        }

        DefaultTableModel model = new DefaultTableModel(data, columns) { 
    		private static final long serialVersionUID = 1352383242L;
    		@Override 
        	public boolean isCellEditable(int row, int column) {
        		return false; // All cells are not editable 
        		} 
        	};

        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(20); 	//ID and free seats are now smaller
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click detected
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int flightID = (int) table.getValueAt(row, 0);
                        try {
							FlightDetailsWindow(flightID);
						} catch (FlightBookingSystemException er) {
							er.printStackTrace();
						}
                    }
                }
            }
        });
        
    }
    
    
    
    public void displayCust() {
        List<Customer> customerlistFull = fbs.getCustomers();
        List<Customer> customerlist = new ArrayList<>();
        // Creates a list of non-deleted customers
        for(Customer customer : customerlistFull) {
        	if(customer.getDeleted() == false) {
        		customerlist.add(customer);
        	}
        }
        
        // headers for the table
        String[] columns = new String[]{"ID", "Name", "Phone", "Email", "Booking No."};

        Object[][] data = new Object[customerlist.size()][5];
        for (int i = 0; i < customerlist.size(); i++) {
            Customer customer = customerlist.get(i);
            data[i][0] = customer.getId();
            data[i][1] = customer.getName();
            data[i][2] = customer.getPhone();
            data[i][3] = customer.getEmail();
            data[i][4] = customer.getBookings().size();
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columns) { 
    		private static final long serialVersionUID = 13244233242L;
    		@Override 
        	public boolean isCellEditable(int row, int column) {
        		return false; // All cells are not editable 
        		} 
        	};

        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(20); 	//ID and free seats are now smaller
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click detected
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int customerId = (int) table.getValueAt(row, 0);
                        try {
							CustomerDetailsWindow(customerId);
						} catch (FlightBookingSystemException er) {
							er.printStackTrace();
						}
                    }
                }
            }
        });

        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }
        
    public void displayBookings() {
        List<Booking> bookinglistFull = fbs.getBookings();
        List<Booking> bookinglist = new ArrayList<>();
        
        for(Booking booking : bookinglistFull) {
        	if(booking.getCancelled() == false) {
        		bookinglist.add(booking);
        	}
        }
        // headers for the table
        String[] columns = new String[]{"Booking ID", "Created On:", "CustomerID", 
        		"CustomerName", "FlightID", "Flight No"};

        Object[][] data = new Object[bookinglist.size()][6];
        for (int i = 0; i < bookinglist.size(); i++) {
            Booking booking = bookinglist.get(i);
            data[i][0] = booking.getId();
            data[i][1] = booking.getBookingDate();
            data[i][2] = booking.getCustomerID();
            data[i][3] = booking.getCustomer().getName();
            data[i][4] = booking.getFlightID();
            data[i][5] = booking.getFlight().getFlightNumber();
        }

        JTable table = new JTable(data, columns);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(15);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }
    
    // Window that pops up when a customer is double clicked. Works identical to displaying customer in CLI
    private void CustomerDetailsWindow(int customerId) throws FlightBookingSystemException {
        Customer customer = fbs.getCustomerByID(customerId);
        JFrame frame = new JFrame("Customer Details");
        JPanel panel = new JPanel(new BorderLayout());
        
        JTextArea textArea = new JTextArea();
        textArea.setText(fbs.displayCustomer(customer));
        textArea.setEditable(false);
        textArea.setBackground(new Color(225, 228, 252));

        panel.add(new JLabel("Customer Details:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    // Window that pops up when a flight is double clicked
    private void FlightDetailsWindow(int flightID) throws FlightBookingSystemException {
        Flight flight = fbs.getFlightByID(flightID);
        JFrame frame = new JFrame("Flight Details");
        JPanel panel = new JPanel(new BorderLayout());
        
        JTextArea textArea = new JTextArea();
        textArea.setText(fbs.displayFlight(flight));
        textArea.setEditable(false);
        textArea.setBackground(new Color(54, 133, 199));

        panel.add(new JLabel("Flight Details:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    
}


