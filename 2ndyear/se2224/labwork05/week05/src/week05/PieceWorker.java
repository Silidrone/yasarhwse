/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

/**
 *
 * @author jaglu
 */
public class PieceWorker extends Employee {
    protected int noOfItems;
    protected double rate;
    
    PieceWorker(String name, String surname, int month, int day, int year, int id, String position, int noOfItems, double rate) {
        super(name, surname, month, day, year, id, position);
        this.noOfItems = noOfItems;
        this.rate = rate;
    } 
    
    public double getSalary() {
        return noOfItems * rate;
    }
}
