package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddFlight;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * GUI window for adding a new Window to the flight booking system. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */

public class AddFlightWindow extends JFrame implements ActionListener {
	/**
	 * The serialVersionUID helps maintain the compatibility of serialised objects across different versions of a class
	 * 
	 */
    private static final long serialVersionUID = -2525255480273182291L;
    /** 
     * This field is used to hold a reference to the main application window
     */
	private MainWindow mw;
	/**
     * Creates a new text field, flight's no., for user input in a graphical user interface (GUI)
     */
    private JTextField flightNoText = new JTextField();
    /**
     * Creates a new text field, in-bound destination, for user input in a graphical user interface (GUI)
     */
    private JTextField originText = new JTextField();
    /**
     * Creates a new text field, out-bound destination, for user input in a graphical user interface (GUI)
     */
    private JTextField destinationText = new JTextField();
    /**
     * Creates a new text field, departure date, for user input in a graphical user interface (GUI)
     */
    private JTextField depDateText = new JTextField();
    /**
     * Creates a new text field, vacancy, for user input in a graphical user interface (GUI)
     */
    private JTextField capacityText = new JTextField();
    /**
     * Creates a new text field, price, for user input in a graphical user interface (GUI)
     */
    private JTextField priceText = new JTextField();
    /**
     * Creates a new button for a graphical user interface (GUI) with the label "Add" on it
     */
    private JButton addBtn = new JButton("Add");
    /**
     * Creates a new button for a graphical user interface (GUI) with the label "Cancel" on it
     */
    private JButton cancelBtn = new JButton("Cancel");
    
    /**
     * Constructs an AddCustomerWindow with a reference to the main window. 
     * @param mw the main window 
     */
    public AddFlightWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialise the contents of the frame.
     */
    
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Add a New Flight");

        setSize(350, 220);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Flight No: "));
        topPanel.add(flightNoText);
        topPanel.add(new JLabel("Origin: "));
        topPanel.add(originText);
        topPanel.add(new JLabel("Destination: "));
        topPanel.add(destinationText);
        topPanel.add(new JLabel("Departure Date (YYYY-MM-DD): "));
        topPanel.add(depDateText);
        topPanel.add(new JLabel("Capacity: "));
        topPanel.add(capacityText);
        topPanel.add(new JLabel("Price: "));
        topPanel.add(priceText);
       

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
    
    /**
     * Handles action events for the buttons. 
     * @param ae the action event 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
        	addFli();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    
    /**
     * Adds a new flight based on the input data and executes the AddFlight command.
     */
    private void addFli() {
        try {
            String flightNumber = flightNoText.getText();
            String origin = originText.getText();
            String destination = destinationText.getText();
            LocalDate departureDate = null;
            int capacity;
            double price;
            // parsing the data
            try {
                departureDate = LocalDate.parse(depDateText.getText());
            }
            catch (DateTimeParseException dtpe) {
                throw new FlightBookingSystemException("Date must be in YYYY-MM-DD format");
            }
            try {
                capacity = Integer.parseInt(capacityText.getText());
            }
            catch (IllegalArgumentException e) {
                throw new FlightBookingSystemException("Capacity must be a positive integer");
            }
            try {
                price = Double.parseDouble(priceText.getText());
                price = Math.round(price * 100.0) / 100.0; // Rounds to 2 d.p.
            }
            catch (IllegalArgumentException e) {
                throw new FlightBookingSystemException("Price must be a valid number");
            }
            // create and execute the AddFlight Command
            Command addFlight = new AddFlight(flightNumber, origin, destination, departureDate, capacity, price);
            addFlight.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
            mw.displayFlights();
            // hide (close) the AddFlightWindow
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
