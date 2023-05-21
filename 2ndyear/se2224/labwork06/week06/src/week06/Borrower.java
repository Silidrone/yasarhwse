/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

import java.util.ArrayList;

/**
 *
 * @author jaglu
 */
public class Borrower {
    private String name;
    private String email;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    Borrower(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Borrower)) return false;
        Borrower otherBorrower = (Borrower) other;
        return this.email.equals(otherBorrower.getEmail());
    }
    
    public boolean bookExists(Book book) {
        for(Book b : borrowedBooks) {
            if(b.getISBN().equals(book.getISBN())) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean borrow(Book b) {
        if(bookExists(b)) return false;
        
        borrowedBooks.add(b);
        b.borrow();
        return true;
    }
    
    public boolean returnBook(Book b) {
        if(!bookExists(b)) return false;
        
        borrowedBooks.remove(b);
        b.returnBook();
        return true;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Book findBorrowedBook(String title) {
        for(Book b : borrowedBooks) {
            if(b.getTitle().equals(title)) {
                return b;
            }
        }
        
        return null;
    }
    
    public String getBorrowedBooksAsString() {
        String result = "";
        for(int i = 0; i < borrowedBooks.size(); i++) {
            result += borrowedBooks.get(i).getTitle();
            if(i != borrowedBooks.size() - 1) {
                result += ", ";
            }
        }
        
        return result;
    }
    
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
