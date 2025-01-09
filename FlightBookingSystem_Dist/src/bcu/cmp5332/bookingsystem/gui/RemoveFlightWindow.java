package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.RemoveFlight;
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
 * The RemoveFlightWindow class provides a graphical user interface for users to remove an existing flight.
 * @author UmaeerH
 * @author AnisaU03
 * @version main 
 */
public class RemoveFlightWindow extends JFrame implements ActionListener {
	/**
	 * The serialVersionUID helps maintain the compatibility of serialised objects across different versions of a class
	 */
    private static final long serialVersionUID = -553585278027291L;
    /** 
     * This field is used to hold a reference to the main application window
     */
	private MainWindow mw;
	/**
     * Creates a new text field, customer ID, for user input in a graphical user interface (GUI)
     */
    private JTextField flightIDText = new JTextField();
    /**
     * Creates a new button for a graphical user interface (GUI) with the label "Remove Flight" on it
     */
    private JButton delBtn = new JButton("Remove Flight");
    /**
     * Creates a new button for a graphical user interface (GUI) with the label "Back" on it
     */
    private JButton cancelBtn = new JButton("Back");
    /**
     * Constructs a new RemoveFlightWindow. 
     * @param mw The MainWindow instance from which this window is invoked.
     */
    public RemoveFlightWindow(MainWindow mw) {
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

        setTitle("Remove a flight from the system");

        setSize(350, 220);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Flight ID: "));
        topPanel.add(flightIDText);
       

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
        	remFlight();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    /**
     * Removes a Flight using the provided Flight ID. 
     */
    private void remFlight() {
        try {
            String str_fliID = flightIDText.getText();
            
            // parsing the data
            int flightID = Integer.parseInt(str_fliID);

            // create and execute the RemoveFlight Command
            Command removeFlight = new RemoveFlight(flightID);
            removeFlight.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
            mw.displayFlights();
            // hide (close) the RemoveFlights window
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error removing flight", JOptionPane.ERROR_MESSAGE);
        }
    }

}
