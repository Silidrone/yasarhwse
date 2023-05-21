/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author jaglu
 */
public class MainFrame extends JFrame {
    private Library library;
    
    MainFrame(Library library) {
        this.library = library;
    }
    
    
    void init() {
        setTitle("Library System");
        setSize(400, 400);
        
        JButton librarianLoginButton = new JButton("Librarian Login");
        librarianLoginButton.setBounds(100, 100, 200, 50);
        librarianLoginButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            LibrarianFrame librarianFrame = new LibrarianFrame(this, library);
            librarianFrame.init();
        });

        JButton borrowerLoginButton = new JButton("Borrower Login");
        borrowerLoginButton.setBounds(100, 200, 200, 50);
        borrowerLoginButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            BorrowerFrame borrowerFrame = new BorrowerFrame(this, library);
            borrowerFrame.init();
        });

        add(librarianLoginButton);
        add(borrowerLoginButton);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
}
