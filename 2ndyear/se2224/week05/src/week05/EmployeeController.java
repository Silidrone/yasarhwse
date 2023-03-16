/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

import java.util.ArrayList;

/**
 *
 * @author jaglu
 */
public class EmployeeController {
    protected ArrayList<Employee> employees = new ArrayList<>();
    
    void add(Employee e) {
        if(find(e.getId()) == null) {
            employees.add(e);
        }
    }
    
    Employee find(int id) {
        for(Employee employee : employees) {
            if(employee.getId() == id) {
                return employee;
            }
        }
        
        return null;
    }
   
    ArrayList<Employee> getEmployees(Class<? extends Employee> employeeClass) {
        ArrayList<Employee> result = new ArrayList<>();
        if(employeeClass == null) return result;
        
        for(Employee employee : employees) {
            if(employeeClass.isInstance(employee)) {
                result.add(employee);
            }
        }
        
        return result;
    }
}
