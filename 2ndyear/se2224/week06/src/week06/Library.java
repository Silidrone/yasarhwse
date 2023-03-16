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
public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<Publisher> publishers = new ArrayList<>();
    private ArrayList<Borrower> borrowers = new ArrayList<>();
    
    public void addBook(Book b) {
        books.add(b);
    }
    
    public void removeBook(Book b) {
        books.remove(b);
    }
    
    public void addAuthor(Author a) {
        authors.add(a);
    }
    
    public void removeAuthor(Author a) {
        authors.remove(a);
    }
    
    public void addPublisher(Publisher p) {
        publishers.add(p);
    }
    
    public void removePublisher(Publisher p) {
        publishers.remove(p);
    }
    
    public void addBorrower(Borrower b) {
        borrowers.add(b);
    }
    
    public void removeBorrower(Borrower b) {
        borrowers.remove(b);
    }
    
    public Author findAuthor(String name) {
        for(Author a : authors) {
            if(a.getName().equals(name)) {
                return a;
            }
        }
        
        return null;
    }
    
    public Book findBook(String ISBN) {
        for(Book b : books) {
            if(b.getISBN().equals(ISBN)) {
                return b;
            }
        }
        
        return null;
    }
    
    public Publisher findPublisher(String name) {
        for(Publisher p : publishers) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        
        return null;
    }
    
    public ArrayList<Book> findBooks(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for(Book b : books) {
            if(b.getTitle().equals(title)) {
                result.add(b);
            }
        }
        
        return result;
    }
    
    public ArrayList<Book> findBooks(Author a) {
        ArrayList<Book> result = new ArrayList<>();
         for(Book b : books) {
            if(b.getAuthor().equals(a)) {
                result.add(b);
            }
        }
        
        return result;
    }
    
    
    public Borrower findBorrower(String text, boolean email) {
        for(Borrower b : borrowers) {
            if((email ? b.getEmail() : b.getName()).equals(text)) {
               return b;
            }
        }
        
        return null;
    }
    
    public Object[] getAuthorsNames() {
        Object[] names = new Object[authors.size()];
        for(int i = 0; i < authors.size(); i++) {
            names[i] = authors.get(i).getName();
        }
        
        return names;
    }
    
    public Object[] getPublishersNames() {
        Object[] names = new Object[publishers.size()];
        for(int i = 0; i < publishers.size(); i++) {
            names[i] = publishers.get(i).getName();
        }
        
        return names;
    }
}
