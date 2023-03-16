/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jaglu
 */
public class BorrowerFrame extends ChildFrame {
    Library library;
    protected JTable table;
    protected AbstractTableModel tableModel;
    protected JTextField bookNameInput;
    protected JTextField borrowerNameInput;
    protected ArrayList<ArrayList<Object>> currentData = new ArrayList<>();
    protected JButton giveButton = new JButton("Give");
    protected JButton borrowButton = new JButton("Borrow");
    protected Book selectedBook = null;

    BorrowerFrame(JFrame parent, Library library) {
        super(parent);
        this.library = library;
        
        bookNameInput = new JTextField();
        borrowerNameInput = new JTextField();
        
        giveButton.setVisible(false);
        borrowButton.setVisible(true);
        borrowButton.setEnabled(false);
        
        String[] columns = {"Book Name", "Author", "ISBN", "Publication", "Publisher", "Available amount"};
        tableModel = new AbstractTableModel() {
            public int getColumnCount() {
                return columns.length;
            }

            public String getColumnName(int column) {
                return columns[column];
            }

            public int getRowCount() {
                return currentData.size();
            }

            public Object getValueAt(int row, int col) {
                return currentData.get(row).get(col);
            }
        };
        table = new JTable(tableModel);
        
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (event.getValueIsAdjusting() || table.getSelectedRow() == -1) return;
                
            Book book = library.findBook(table.getValueAt(table.getSelectedRow(), 2).toString()); //column 2 is ISBN
            String borrowerName = borrowerNameInput.getText();
            Borrower borrower = library.findBorrower(borrowerName, false);
            if(book != null) {
                selectedBook = book;
                if(borrower != null) {
                    if(borrower.bookExists(selectedBook)) {
                        giveButton.setVisible(true);
                        borrowButton.setVisible(false);
                    } else {
                        giveButton.setVisible(false);
                        borrowButton.setVisible(true);
                    }
                } else if(!borrowerName.isBlank()) {
                    library.addBorrower(new Borrower(borrowerName, ""));
                    borrowButton.setVisible(true);
                    borrowButton.setEnabled(true);
                    giveButton.setVisible(false);
                }
            }
        });
    }
    
    public void updateTable() {
        currentData.clear();
        ArrayList<Book> books = new ArrayList<>();
        String bookNameInputText = bookNameInput.getText();
        String borrowerNameInputText = borrowerNameInput.getText();
        Borrower borrower = library.findBorrower(borrowerNameInputText, false);
        if(borrower != null && !bookNameInputText.isBlank()) {
            books.add(borrower.findBorrowedBook(bookNameInputText));
        } else if(!bookNameInputText.isBlank()){
            books = library.findBooks(bookNameInputText);
        } else if (borrower != null) {
            books = borrower.getBorrowedBooks();
        }
        for(Book b : books) {
            if(b == null) continue;
            
            ArrayList<Object> row = new ArrayList<>(Arrays.asList(b.getTitle(), b.getAuthor().getName(), b.getISBN(), b.getPublicationDate().toString(), b.getPublisher().getName(), Integer.toString(b.getNumberOfCopies())));
            currentData.add(row);
        }
        
        tableModel.fireTableDataChanged();
    }
    
    public void init() {
        super.init();
        
        setTitle("Search");
        setSize(1000, 600);
        
        JLabel borrowerNameLabel = new JLabel("Borrower Name:");
        borrowerNameLabel.setBounds(20, 10, 200, 20);
        borrowerNameInput.setBounds(20, 40, 200, 20);
        
        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setBounds(20, 70, 200, 20);
        bookNameInput.setBounds(20, 100, 200, 20);
        
        JButton findButton = new JButton("Find");
        findButton.setBounds(20, 130, 200, 20);
        findButton.addActionListener((ActionEvent e) -> {
            updateTable();
        });
        
        giveButton.setBounds(20, 160, 200, 20);
        giveButton.addActionListener((ActionEvent e) -> {
            Borrower borrower = library.findBorrower(borrowerNameInput.getText(), false);
            if(borrower != null) {
                borrower.returnBook(selectedBook);
            }
            updateTable();
        });
        
        borrowButton.setBounds(20, 160, 200, 20);
        borrowButton.addActionListener((ActionEvent e) -> {
            Borrower borrower = library.findBorrower(borrowerNameInput.getText(), false);
            if(borrower != null) {
                borrower.borrow(selectedBook);
            }
            updateTable();
        });
        
        JScrollPane panel = new JScrollPane(table);
        panel.setBounds(300, 10, 400, 300);
        
        add(borrowerNameLabel);
        add(borrowerNameInput);
        add(bookNameLabel);
        add(bookNameInput);
        add(findButton);
        add(borrowButton);
        add(giveButton);
        add(panel);
        setLayout(null);
        setVisible(true);
    }
}
