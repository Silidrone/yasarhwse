/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author jaglu
 */
public class LibrarianFrame extends ChildFrame {
    Library library;

    LibrarianFrame(JFrame parent, Library library) {
        super(parent);
        this.library = library;
    }
    
    public void init() {
        super.init();
        
        setTitle("Library System");
        setSize(400, 400);
        
        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(100, 80, 200, 50);
        addBookButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            AddBookFrame addBookFrame = new AddBookFrame(this, library);
            addBookFrame.init();
        });

        JButton addAuthorButton = new JButton("Add Author");
        addAuthorButton.setBounds(100, 150, 200, 50);
        addAuthorButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            AddAuthorFrame addAuthorFrame = new AddAuthorFrame(this, library);
            addAuthorFrame.init();
        });

        JButton addPublisherButton = new JButton("Add Publisher");
        addPublisherButton.setBounds(100, 220, 200, 50);
        addPublisherButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            AddPublisherFrame addPublisherFrame = new AddPublisherFrame(this, library);
            addPublisherFrame.init();
        });
        
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(100, 290, 200, 50);
        searchButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            SearchFrame searchFrame = new SearchFrame(this, library);
            searchFrame.init();
        });
        
        add(addBookButton);
        add(addAuthorButton);
        add(addPublisherButton);
        add(searchButton);
        setLayout(null);
        setVisible(true);
    }
}
