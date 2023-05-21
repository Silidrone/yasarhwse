/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

/**
 *
 * @author jaglu
 */
public class Author {
    private String name;
    private String email;
    private String biography;
    
    Author(String name, String email, String biography) {
        this.name = name;
        this.email = email;
        this.biography = biography;
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Author)) return false;
        Author otherAuthor = (Author) other;
        return this.email.equals(otherAuthor.getEmail());
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return this.email;
    }
}
