/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Math.abs;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;
import java.util.Date;
import javax.swing.JButton;

/**
 *
 * @author jaglu
 */
public class EmployeeDetailsFrame extends JFrame {
    protected MainFrame parent;
    protected Employee employee;
    
    EmployeeDetailsFrame(MainFrame parent, Employee employee) {
        this.parent = parent;
        this.employee = employee;
    }
    
    private void close() {
        parent.setVisible(true);
        setVisible(false);
        dispose();
    }
    
    public void init() {
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                close();
            }
        });
        
        setTitle("Employee Details");
        setSize(400, 500);

        if(employee != null) {
            JLabel idLabel1 = new JLabel("ID:");
            idLabel1.setBounds(10, 15, 20, 20);
            JLabel idLabel2 = new JLabel(Integer.toString(employee.getId()));
            idLabel2.setBounds(35, 15, 100, 20);
            
            JLabel nameLabel1 = new JLabel("Name:");
            nameLabel1.setBounds(10, 40, 50, 20);
            JLabel nameLabel2 = new JLabel(employee.getName() + " " + employee.getSurname());
            nameLabel2.setBounds(55, 40, 100, 20);
            
            JLabel birthDateLabel1 = new JLabel("Birth Date:");
            birthDateLabel1.setBounds(10, 65, 100, 20);
            JLabel birthDateLabel2 = new JLabel(employee.getDateString());
            birthDateLabel2.setBounds(80, 65, 100, 20);
            
            JLabel ageLabel1 = new JLabel("Age:");
            ageLabel1.setBounds(140, 65, 50, 20);
            JLabel ageLabel2 = new JLabel(Integer.toString(employee.getAge()));
            ageLabel2.setBounds(170, 65, 50, 20);
            
            JLabel positionLabel1 = new JLabel("Position:");
            positionLabel1.setBounds(10, 90, 50, 20);
            JLabel positionLabel2 = new JLabel(employee.getPosition());
            positionLabel2.setBounds(70, 90, 200, 20);
            
            JLabel salaryLabel1 = new JLabel("Salary:");
            salaryLabel1.setBounds(10, 115, 50, 20);
            JLabel salaryLabel2 = new JLabel(Double.toString(employee.getSalary()));
            salaryLabel2.setBounds(70, 115, 200, 20);
            
            JButton okayButton = new JButton("OK");
            okayButton.setBounds(130, 400, 100, 50);
            okayButton.addActionListener((ActionEvent e) -> {
                close();
            });  
            
            add(idLabel1);
            add(idLabel2);
            add(nameLabel1);
            add(nameLabel2);
            add(birthDateLabel1);
            add(birthDateLabel2);
            add(ageLabel1);
            add(ageLabel2);
            add(positionLabel1);
            add(positionLabel2);
            add(salaryLabel1);
            add(salaryLabel2);
            add(okayButton);
        } else {
            JLabel notFoundLabel = new JLabel("Employee not found!");
            notFoundLabel.setBounds(125, 200, 150, 50);
            
            JButton goBackButton = new JButton("Go back");
            goBackButton.setBounds(130, 400, 100, 50);
            goBackButton.addActionListener((ActionEvent e) -> {
                close();
            });  
            
            add(notFoundLabel);
            add(goBackButton);
        }
        
        setLayout(null);
        setVisible(true);
    }
}
