package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
/**
 * GUI window for adding a new booking to the flight booking system. 
 * @author UmaeerH
 * @author AnisaU
 * @version main
 */
public class AddBookingWindow extends JFrame implements ActionListener {
	
    private static final long serialVersionUID = -252432525878027291L;
	private MainWindow mw;
    private JTextField customerIDText = new JTextField();
    private JTextField flightIDText = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * Constructs an AddBookingWindow with a reference to the main window. 
     * @param mw the main window 
     */
    public AddBookingWindow(MainWindow mw) {
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

        setTitle("Create a new booking");

        setSize(350, 220);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Customer ID: "));
        topPanel.add(customerIDText);
        topPanel.add(new JLabel("Flight ID: "));
        topPanel.add(flightIDText);
       

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
            addBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    /**
     * Adds a new booking based on the input data and executes the AddBooking command.
     */
    private void addBook() {
        try {
            String str_custID = customerIDText.getText();
            String str_flightID = flightIDText.getText();
            
            // parsing the data
            int custID = Integer.parseInt(str_custID);
            int flightID = Integer.parseInt(str_flightID);

            // create and execute the AddFlight Command
            Command addBooking = new AddBooking(custID, flightID);
            addBooking.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
            mw.displayFlights();
            // hide (close) the AddFlightWindow
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
