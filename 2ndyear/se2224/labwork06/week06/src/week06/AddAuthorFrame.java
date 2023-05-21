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
public class AddAuthorFrame extends ChildFrame {
    Library library;

    AddAuthorFrame(JFrame parent, Library library) {
        super(parent);
        this.library = library;
    }
    
    public void init() {
        super.init();
        
        setTitle("Add Author");
        setSize(300, 600);
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 10, 200, 20);
        JTextField nameInput = new JTextField();
        nameInput.setBounds(20, 40, 200, 20);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 70, 200, 20);
        JTextField emailInput = new JTextField();
        emailInput.setBounds(20, 100, 200, 20);
        
        JLabel biographyLabel = new JLabel("Biography:");
        biographyLabel.setBounds(20, 130, 200, 20);
        JTextArea biographyInput = new JTextArea("");  
        biographyInput.setBounds(20,160, 200,200);  
        
        biographyInput.setLineWrap(true);
        biographyInput.setWrapStyleWord(true);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(20, 370, 200, 20);
        addButton.addActionListener((ActionEvent e) -> {
            library.addAuthor(new Author(nameInput.getText(), emailInput.getText(), biographyInput.getText()));
            close();
        });
        
        add(nameLabel);
        add(nameInput);
        add(emailLabel);
        add(emailInput);
        add(biographyLabel);
        add(biographyInput);
        add(addButton);
        setLayout(null);
        setVisible(true);
    }
}
