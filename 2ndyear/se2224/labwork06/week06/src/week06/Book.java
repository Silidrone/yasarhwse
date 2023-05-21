/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

/**
 *
 * @author jaglu
 */
public class Book {
    private String title;
    private Author author;
    private String ISBN;
    private MDate publicationDate;
    private Publisher publisher;
    private int numberOfCopies;
    
    Book(String title, Author author, String ISBN, MDate publicationDate, Publisher publisher, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.numberOfCopies = numberOfCopies;
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Book)) return false;
        Book otherBook = (Book) other;
        return this.ISBN.equals(otherBook.getISBN());
    }
    
    int borrow() {
        if(this.numberOfCopies == 0) return 0;
        this.numberOfCopies -= 1;
        return this.getNumberOfCopies();
    }
    
    int returnBook() {
        this.numberOfCopies += 1;
        return this.getNumberOfCopies();
    }
    
    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public MDate getPublicationDate() {
        return publicationDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }
}
