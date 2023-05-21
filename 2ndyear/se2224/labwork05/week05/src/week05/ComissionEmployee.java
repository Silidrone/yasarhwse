/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

/**
 *
 * @author jaglu
 */
public class ComissionEmployee extends Employee {

    protected double baseSalary;
    protected int noOfItems;
    protected double itemPrice;
    protected double commisionRate;

    ComissionEmployee(String name, String surname, int month, int day, int year,
            int id, String position, double baseSalary, int noOfItems, double itemPrice, double commisionRate) {
        super(name, surname, month, day, year, id, position);
        this.baseSalary = baseSalary;
        this.noOfItems = noOfItems;
        this.itemPrice = itemPrice;
        this.commisionRate = commisionRate;
    }

    public double getSalary() {
        return baseSalary + noOfItems * itemPrice * commisionRate * 0.01;
    }
}
