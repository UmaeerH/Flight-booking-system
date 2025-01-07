package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
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
 * GUI window for adding a new customer to the flight booking system.
 * @author UmaeerH
 * @author AnisaU03
 * @version main
 */
public class AddCustomerWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = -2525258430273182291L;
	private MainWindow mw;
    private JTextField custNameText = new JTextField();
    private JTextField custPhoneText = new JTextField();
    private JTextField custEmailText = new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");
    /**
     * Constructs an AddCustomerWindow with a reference to the main window. 
     * @param mw the main window 
     */
    public AddCustomerWindow(MainWindow mw) {
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

        setTitle("Add a New Customer");

        setSize(350, 220);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Customer Name: "));
        topPanel.add(custNameText);
        topPanel.add(new JLabel("Customer Number: "));
        topPanel.add(custPhoneText);
        topPanel.add(new JLabel("Email Address: "));
        topPanel.add(custEmailText);
       

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
     * Adds a new customer based on the input data and executes the AddCustomer command. 
     */
    private void addBook() {
        try {
            String customerName = custNameText.getText();
            String customerNumb = custPhoneText.getText();
            String customerEmail = custEmailText.getText();

            // parsing the data

            // create and execute the AddCustomer Command
            Command addCustomer = new AddCustomer(customerName, customerNumb, customerEmail);
            addCustomer.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
            mw.displayCust();
            // hide (close) the AddCustomer
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
