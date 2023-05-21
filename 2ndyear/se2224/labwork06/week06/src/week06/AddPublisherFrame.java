/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author jaglu
 */
public class AddPublisherFrame extends ChildFrame {
    Library library;

    AddPublisherFrame(JFrame parent, Library library) {
        super(parent);
        this.library = library;
    }
    
    public void init() {
        super.init();
        
        setTitle("Add Publisher");
        setSize(300, 600);
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 10, 200, 20);
        JTextField nameInput = new JTextField();
        nameInput.setBounds(20, 40, 200, 20);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 70, 200, 20);
        JTextField emailInput = new JTextField();
        emailInput.setBounds(20, 100, 200, 20);
        
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 130, 200, 20);
        JTextArea addressInput = new JTextArea("");  
        addressInput.setBounds(20,160, 200,200);  
        
        addressInput.setLineWrap(true);
        addressInput.setWrapStyleWord(true);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(20, 370, 200, 20);
        addButton.addActionListener((ActionEvent e) -> {
            library.addPublisher(new Publisher(nameInput.getText(), emailInput.getText(), addressInput.getText()));
            close();
        });
        
        add(nameLabel);
        add(nameInput);
        add(emailLabel);
        add(emailInput);
        add(addressLabel);
        add(addressInput);
        add(addButton);
        setLayout(null);
        setVisible(true);
    }
}
