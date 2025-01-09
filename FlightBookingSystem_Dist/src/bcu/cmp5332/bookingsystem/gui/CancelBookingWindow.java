package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.CancelBooking;
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
 * The CancelBookingWindow class provides a graphical user interface for users to cancel an existing booking.
 * @author UmaeerH
 * @author AnisaU03
 * @version main 
 */
public class CancelBookingWindow extends JFrame implements ActionListener {
	/**
	 * The serialVersionUID helps maintain the compatibility of serialised objects across different versions of a class
	 */
    private static final long serialVersionUID = -5535878027291L;
    /** 
     * This field is used to hold a reference to the main application window
     */
	private MainWindow mw;
	/**
     * Creates a new text field, booking ID, for user input in a graphical user interface (GUI)
     */
    private JTextField bookingIDText = new JTextField();
    /**
     * Creates a new button for a graphical user interface (GUI) with the label "Confirm Cancellation" on it
     */
    private JButton delBtn = new JButton("Confirm Cancellation");
    /**
     * Creates a new button for a graphical user interface (GUI) with the label "Back" on it
     */
    private JButton cancelBtn = new JButton("Back");
    /**
     * Constructs a new CancelBookingWindow. 
     * @param mw The MainWindow instance from which this window is invoked.
     */
    public CancelBookingWindow(MainWindow mw) {
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

        setTitle("Cancel an Existing Booking");

        setSize(350, 220);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Booking ID: "));
        topPanel.add(bookingIDText);
       

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(delBtn);
        bottomPanel.add(cancelBtn);

        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
    /**
     * Handles button click events. 
     * @param ae The action event triggered by the button click. 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delBtn) {
            addBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    /**
     * Cancels a booking using the provided booking ID. 
     */
    private void addBook() {
        try {
            String str_bookID = bookingIDText.getText();
            
            // parsing the data
            int bookID = Integer.parseInt(str_bookID);

            // create and execute the CancelBooking Command
            Command cancelBooking = new CancelBooking(bookID);
            cancelBooking.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
            mw.displayBookings();
            // hide (close) the CancelBooking
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
