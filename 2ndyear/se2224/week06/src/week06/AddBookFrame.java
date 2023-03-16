/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author jaglu
 */
public class AddBookFrame extends ChildFrame {
    Library library;
    private final Object[] months = {
        "January", "February", "March", "April", 
        "May", "June", "July", "August", "September",
        "October", "November", "December"
    };
    
    AddBookFrame(JFrame parent, Library library) {
        super(parent);
        this.library = library;
    }
    
    public void init() {
        super.init();
        
        setTitle("Add Book");
        setSize(300, 600);
        
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 10, 200, 20);
        JTextField titleInput = new JTextField();
        titleInput.setBounds(20, 40, 200, 20);
        
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(20, 70, 70, 20);
        JComboBox authorsComboBox = new JComboBox(library.getAuthorsNames());
        authorsComboBox.setBounds(20, 100, 100, 20);
        
        JLabel ISBNLabel = new JLabel("ISBN:");
        ISBNLabel.setBounds(20, 130, 200, 20);
        JTextField ISBNInput = new JTextField();
        ISBNInput.setBounds(20, 160, 200, 20);
        
        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setBounds(20, 190, 70, 20);
        JComboBox publishersComboBox = new JComboBox(library.getPublishersNames());
        publishersComboBox.setBounds(20, 220, 100, 20);
        
        JLabel birthDateLabel = new JLabel("Birth Date:");
        birthDateLabel.setBounds(20, 250, 70, 20);
        JComboBox monthsComboBox = new JComboBox(months);
        monthsComboBox.setBounds(90, 250, 100, 20);
        JTextField dayInput = new JTextField();
        dayInput.setBounds(195, 250, 30, 20);
        JTextField yearInput = new JTextField();
        yearInput.setBounds(225, 250, 35, 20);
        
        JLabel numberOfCopiesLabel = new JLabel("No. of copies:");
        numberOfCopiesLabel.setBounds(20, 280, 70, 20);
        JTextField numberOfCopiesInput = new JTextField();
        numberOfCopiesInput.setBounds(20, 310, 200, 20);
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(20, 370, 200, 20);
        addButton.addActionListener((ActionEvent e) -> {
            library.addBook(new Book(
                    titleInput.getText(), 
                    library.findAuthor(authorsComboBox.getSelectedItem().toString()), 
                    ISBNInput.getText(),
                    new MDate(monthsComboBox.getSelectedIndex(),  Integer.parseInt(dayInput.getText()),
                        Integer.parseInt(yearInput.getText())),
                    library.findPublisher(publishersComboBox.getSelectedItem().toString()), 
                    Integer.parseInt(numberOfCopiesInput.getText())
            ));
            close();
        });
        
        add(titleLabel);
        add(titleInput);
        add(authorLabel);
        add(authorsComboBox);
        add(ISBNLabel);
        add(ISBNInput);
        add(publisherLabel);
        add(publishersComboBox);
        add(birthDateLabel);
        add(monthsComboBox);
        add(dayInput);
        add(yearInput);
        add(numberOfCopiesLabel);
        add(numberOfCopiesInput);
        add(addButton);
        setLayout(null);
        setVisible(true);
    }
}
