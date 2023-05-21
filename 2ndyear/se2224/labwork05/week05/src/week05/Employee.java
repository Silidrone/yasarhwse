/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

/**
 *
 * @author jaglu
 */
abstract class Employee extends Person {
    protected int id;
    protected String position;
    
    Employee(String name, String surname, int month, int day, int year, int id, String position) {
        super(name, surname, month, day, year);
        this.id = id;
        this.position = position;
    }
    
    int getId() {
        return id;
    }
    
    String getPosition() {
        return position;
    }
    
    abstract double getSalary();
}
