package bcu.cmp5332.bookingsystem.gui;

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

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.UpdateBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * GUI window for adding a new booking to the flight booking system. 
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */

public class UpdateBookingWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = -228991293260914891L;
	private MainWindow mw;
    private JTextField bookingIDText = new JTextField();
    private JTextField flightIDText = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * Constructs an UpdateBookingWindow with a reference to the main window. 
     * @param mw the main window 
     */
    public UpdateBookingWindow(MainWindow mw) {
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

        setTitle("Update a booking");

        setSize(350, 220);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Booking ID: "));
        topPanel.add(bookingIDText);
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
            updateBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    /**
     * Update a new booking based on the input data and executes the AddBooking command.
     */
    private void updateBook() {
        try {
            String str_bookingID = bookingIDText.getText();
            String str_flightID = flightIDText.getText();
            
            // parsing the data
            int bookingID = Integer.parseInt(str_bookingID);
            int flightID = Integer.parseInt(str_flightID);

            // create and execute the UpdateFlight Command
            Command updateBooking = new UpdateBooking(bookingID, flightID);
            updateBooking.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
            mw.displayBookings();
            // hide (close) the AddFlightWindow
            this.setVisible(false);
            } catch (FlightBookingSystemException ex) {
            	JOptionPane.showMessageDialog(this, ex, "Error updating booking:", JOptionPane.ERROR_MESSAGE);
            	}
        }
}
