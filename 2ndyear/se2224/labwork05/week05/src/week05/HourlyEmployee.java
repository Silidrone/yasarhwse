/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

/**
 *
 * @author jaglu
 */
public class HourlyEmployee extends Employee {
    protected double hours;
    protected double rate;
    
    HourlyEmployee(String name, String surname, int month, int day, int year, int id, String position, double hours, double rate) {
        super(name, surname, month, day, year, id, position);
        this.hours = hours;
        this.rate = rate;
    } 
    
    public double getSalary() {
        return hours * rate;
    }
}
