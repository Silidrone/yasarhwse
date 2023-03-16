/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

/**
 *
 * @author jaglu
 */
public class Publisher {
    private String name;
    private String email;
    private String address;
    
    Publisher(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Publisher)) return false;
        Publisher otherPublisher = (Publisher) other;
        return this.email.equals(otherPublisher.getEmail());
    }
     
    public String getName() {
        return this.name;
    }
    
    public String getEmail() {
        return this.email;
    }
}
