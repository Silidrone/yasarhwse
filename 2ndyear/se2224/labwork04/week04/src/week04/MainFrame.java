/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week04;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author jaglu
 */
public class MainFrame extends JFrame {
    FlightController fc;
    TicketController tc;
    
    MainFrame(FlightController fc, TicketController tc) {
        this.fc = fc;
        this.tc = tc;
    }
    
    public void init() {
        setTitle("Main Frame");
        setSize(300, 500);
        
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(30, 50, 170, 50);
        exitButton.addActionListener((ActionEvent e) -> {
            dispose();
            System.exit(0);
        });  
        
        JButton createFlightButton = new JButton("Create Flight");
        createFlightButton.setBounds(30, 125, 170, 50);
        createFlightButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            BuyFrame bf = new BuyFrame(this, fc, "Create a Flight", 
                    "Enter Flight Code", "Enter Departure City", "Enter Destination City");
            bf.init();
        });
        
        JButton buyTicketButton = new JButton("Buy Ticket");
        buyTicketButton.setBounds(30, 200, 170, 50);
        buyTicketButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            BuyFrame bf = new BuyFrame(this, tc, "Buy a ticket", 
                    "Enter Flight Number", "Enter Seat Number", "Enter luggage");
            bf.init();
        });
        
        JButton showFlightsButton = new JButton("Show Flights");
        showFlightsButton.setBounds(30, 275, 170, 50);
        showFlightsButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            ListDataFrame ldf = new ListDataFrame(this, fc, "Flights List");
            ldf.init();
        });
        
        JButton showTicketsButton = new JButton("Show Tickets");
        showTicketsButton.setBounds(30, 350, 170, 50);
        showTicketsButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            ListDataFrame ldf = new ListDataFrame(this, tc, "Tickets List");
            ldf.init();
        });
        
        add(exitButton);
        add(createFlightButton);
        add(buyTicketButton);
        add(showFlightsButton);
        add(showTicketsButton);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
}
