/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package week05;

/**
 *
 * @author jaglu
 */
public class Week05 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        MainFrame mainFrame = new MainFrame(employeeController);
        mainFrame.init();
    }
}
