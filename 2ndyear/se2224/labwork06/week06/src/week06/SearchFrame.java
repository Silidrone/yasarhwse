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
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jaglu
 */
public class SearchFrame extends ChildFrame {
    Library library;
    protected JTextField searchTextInput;
    protected JTable table;
    protected AbstractTableModel tableModel;
    protected ArrayList<String> currentColumns = new ArrayList<>();
    protected ArrayList<ArrayList<Object>> currentData = new ArrayList<>();
    
    enum SearchType {
        BOOKBYTITLE,
        BORROWERBYNAME,
        BOOKBYAUTHOR,
        BORROWERBYEMAIL
    }
    
    SearchFrame(JFrame parent, Library library) {
        super(parent);
        this.library = library;
        searchTextInput = new JTextField();
        table = new JTable();

        updateTable(SearchType.BOOKBYTITLE);
    }
    
    public void updateTable(SearchType searchType) {
        currentColumns.clear();
        currentData.clear();
        if(searchType == SearchType.BOOKBYTITLE || searchType == SearchType.BOOKBYAUTHOR) {
            currentColumns.add("Name");
            currentColumns.add("Author");
            currentColumns.add("ISBN");
            currentColumns.add("Publication");
            currentColumns.add("Available Copies");
            ArrayList<Book> books;
            if(searchType == SearchType.BOOKBYTITLE) {
                books = library.findBooks(searchTextInput.getText());
            } else {
                books = library.findBooks(library.findAuthor(searchTextInput.getText()));
            }
            for(Book b : books) {
                ArrayList<Object> row = new ArrayList<>(Arrays.asList(b.getTitle(), b.getAuthor().getName(), b.getISBN(), b.getPublicationDate().toString(), Integer.toString(b.getNumberOfCopies())));
                currentData.add(row);
            }
        } else if(searchType == SearchType.BORROWERBYNAME || searchType == SearchType.BORROWERBYEMAIL) {
            currentColumns.add("Name");
            currentColumns.add("Email");
            currentColumns.add("Books");
            Borrower b = library.findBorrower(searchTextInput.getText(), searchType == SearchType.BORROWERBYNAME);
            if(b != null) {
                ArrayList<Object> row = new ArrayList<>(Arrays.asList(b.getName(), b.getEmail(), b.getBorrowedBooksAsString()));
                currentData.add(row);            
            }
        }
        
        tableModel = new AbstractTableModel() {
            public int getColumnCount() {
                return currentColumns.size();
            }

            public String getColumnName(int column) {
                return currentColumns.get(column);
            }

            public int getRowCount() {
                return currentData.size();
            }

            public Object getValueAt(int row, int col) {
                return currentData.get(row).get(col);
            }
        };
        table.setModel(tableModel);
    }
    
    public void init() {
        super.init();
        
        setTitle("Search");
        setSize(1000, 600);
        
        JLabel searchTextLabel = new JLabel("Searched Text:");
        searchTextLabel.setBounds(20, 10, 200, 20);
        searchTextInput.setBounds(20, 40, 200, 20);
        
        JButton bookTitleButton = new JButton("Find Book By Title");
        bookTitleButton.setBounds(20, 70, 200, 20);
        bookTitleButton.addActionListener((ActionEvent e) -> {
            updateTable(SearchType.BOOKBYTITLE);
        });
        
        JButton borrowerNameButton = new JButton("Find Borrower By Name");
        borrowerNameButton.setBounds(20, 100, 200, 20);
        borrowerNameButton.addActionListener((ActionEvent e) -> {
            updateTable(SearchType.BORROWERBYNAME);
        });
        
        JButton bookAuthorButton = new JButton("Find Book By Author");
        bookAuthorButton.setBounds(20, 130, 200, 20);
        bookAuthorButton.addActionListener((ActionEvent e) -> {
            updateTable(SearchType.BOOKBYAUTHOR);
        });
        
        JButton borrowerEmailButton = new JButton("Find Borrower By Email");
        borrowerEmailButton.setBounds(20, 160, 200, 20);
        borrowerEmailButton.addActionListener((ActionEvent e) -> {
            updateTable(SearchType.BORROWERBYEMAIL);
         });
        
        JScrollPane panel = new JScrollPane(table);
        panel.setBounds(300, 10, 400, 300);
        
        add(searchTextLabel);
        add(searchTextInput);
        add(bookTitleButton);
        add(borrowerNameButton);
        add(bookAuthorButton);
        add(borrowerEmailButton);
        add(panel);
        setLayout(null);
        setVisible(true);
    }
}
