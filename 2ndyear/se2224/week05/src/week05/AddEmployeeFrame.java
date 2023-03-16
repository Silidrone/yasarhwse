/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author jaglu
 */
public class AddEmployeeFrame extends JFrame {
    private MainFrame parent;
    private EmployeeController controller;
    private final Object[] months = {
        "January", "February", "March", "April", 
        "May", "June", "July", "August", "September",
        "October", "November", "December"
    };
    
    private ArrayList<JLabel> currentLabels = new ArrayList<>();
    private Map<String, JTextField> currentInputs = new HashMap<>();
    
    AddEmployeeFrame(MainFrame parent, EmployeeController controller) {
        this.parent = parent;
        this.controller = controller;
    }
    
    private void close() {
        parent.setVisible(true);
        setVisible(false);
        dispose();
    }
    
    private void removeCurrentControls() {
        for (JComponent c : currentLabels) {
            remove(c);
        }
        
        for (Map.Entry<String, JTextField> set : currentInputs.entrySet()) {
            remove(set.getValue());
        }
        
        currentLabels.clear();
        currentInputs.clear();
    }
    
    private void addLabel(JLabel c) {
        currentLabels.add(c);
        add(c);
    }
    
    private void addInput(String key, JTextField input) {
        currentInputs.put(key, input);
        add(input);
    }
    
    private String getInputText(String key) {
        return currentInputs.get(key).getText();
    }
    
    private void setPieceWorkerControls() {
        JLabel noOfItemsLabel = new JLabel("No. of items:");
        noOfItemsLabel.setBounds(20, 270, 70, 20);
        JTextField noOfItemsInput = new JTextField();
        noOfItemsInput.setBounds(20, 290, 100, 20);

        JLabel rateLabel = new JLabel("Rate:");
        rateLabel.setBounds(20, 310, 70, 20);
        JTextField rateInput = new JTextField();
        rateInput.setBounds(20, 330, 100, 20);
        
        addLabel(noOfItemsLabel);
        addInput("noOfItems", noOfItemsInput);
        addLabel(rateLabel);
        addInput("rate",rateInput);
    }
    
    private void setHourlyEmployeeControls() {
        JLabel hoursLabel = new JLabel("Hours:");
        hoursLabel.setBounds(20, 270, 70, 20);
        JTextField hoursInput = new JTextField();
        hoursInput.setBounds(20, 290, 100, 20);

        JLabel rateLabel = new JLabel("Rate:");
        rateLabel.setBounds(20, 310, 70, 20);
        JTextField rateInput = new JTextField();
        rateInput.setBounds(20, 330, 100, 20);

        addLabel(hoursLabel);
        addInput("hours", hoursInput);
        addLabel(rateLabel);
        addInput("rate", rateInput);
    }
    
    private void setComissionEmployeeControls() {
        JLabel salaryLabel = new JLabel("Base Salary:");
        salaryLabel.setBounds(20, 270, 70, 20);
        JTextField salaryInput = new JTextField();
        salaryInput.setBounds(120, 270, 100, 20);

        JLabel noOfItemsLabel = new JLabel("No. of items:");
        noOfItemsLabel.setBounds(20, 290, 70, 20);
        JTextField noOfItemsInput = new JTextField();
        noOfItemsInput.setBounds(120, 290, 100, 20);
        
        JLabel itemPriceLabel = new JLabel("Item Price:");
        itemPriceLabel.setBounds(20, 310, 70, 20);
        JTextField itemPriceInput = new JTextField();
        itemPriceInput.setBounds(120, 310, 100, 20);
        
        JLabel comissionRateLabel = new JLabel("Comission Rate:");
        comissionRateLabel.setBounds(20, 330, 100, 20);
        JTextField comissionRateInput = new JTextField();
        comissionRateInput.setBounds(120, 330, 100, 20);

        addLabel(salaryLabel);
        addInput("baseSalary", salaryInput);
        addLabel(noOfItemsLabel);
        addInput("noOfItems", noOfItemsInput);
        addLabel(itemPriceLabel);
        addInput("itemPrice", itemPriceInput);
        addLabel(comissionRateLabel);
        addInput("comissionRate", comissionRateInput);
    }
    
    public void init() {
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                close();
            }
        });
        setTitle("Add Employee");
        setSize(300, 600);
        
        JLabel nameLabel = new JLabel("Firstname:");
        nameLabel.setBounds(20, 5, 70, 20);
        JTextField nameInput = new JTextField();
        nameInput.setBounds(20, 30, 100, 20);
        
        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setBounds(20, 50, 70, 20);
        JTextField surnameInput = new JTextField();
        surnameInput.setBounds(20, 70, 100, 20);
        
        JLabel birthDateLabel = new JLabel("Birth Date:");
        birthDateLabel.setBounds(20, 90, 70, 20);
        JComboBox monthsComboBox = new JComboBox(months);
        monthsComboBox.setBounds(90, 90, 100, 20);
        JTextField dayInput = new JTextField();
        dayInput.setBounds(195, 90, 30, 20);
        JTextField yearInput = new JTextField();
        yearInput.setBounds(225, 90, 35, 20);
        
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 110, 70, 20);
        JTextField idInput = new JTextField();
        idInput.setBounds(20, 130, 100, 20);
        
        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setBounds(20, 150, 70, 20);
        JTextField positionInput = new JTextField();
        positionInput.setBounds(20, 170, 200, 20);
        
        JLabel employeeTypeLabel = new JLabel("Employee Type:");
        employeeTypeLabel.setBounds(20, 190, 100, 20);
        JRadioButton pieceWorker = new JRadioButton("Piece Worker");
        pieceWorker.setBounds(20, 210, 120, 20);
        JRadioButton hourlyEmployee = new JRadioButton("Hourly Employee");
        hourlyEmployee.setBounds(20, 230, 120, 20);
        JRadioButton comissionEmployee = new JRadioButton("Comission Employee");
        comissionEmployee.setBounds(20, 250, 150, 20);
        
        ButtonGroup employeeTypeGroup = new ButtonGroup();
        employeeTypeGroup.add(pieceWorker);
        employeeTypeGroup.add(hourlyEmployee);
        employeeTypeGroup.add(comissionEmployee);
        
        pieceWorker.addActionListener((ActionEvent e) -> {
            removeCurrentControls();
            setPieceWorkerControls();
            repaint();
        });  
        
        hourlyEmployee.addActionListener((ActionEvent e) -> {
            removeCurrentControls();
            setHourlyEmployeeControls();
            repaint();
        });  
        
        comissionEmployee.addActionListener((ActionEvent e) -> {
            removeCurrentControls();
            setComissionEmployeeControls();
            repaint();
        });  
        
        pieceWorker.setSelected(true);
        setPieceWorkerControls();
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(20, 450, 100, 50);
        addButton.addActionListener((ActionEvent e) -> {
            if(pieceWorker.isSelected()) {
                controller.add(
                    new PieceWorker(
                        nameInput.getText(), 
                        surnameInput.getText(), 
                        monthsComboBox.getSelectedIndex(), 
                        Integer.parseInt(dayInput.getText()),
                        Integer.parseInt(yearInput.getText()),
                        Integer.parseInt(idInput.getText()),
                        positionInput.getText(),
                        Integer.parseInt(getInputText("noOfItems")),
                        Double.parseDouble(getInputText("rate"))
                    )
                );
            } else if(hourlyEmployee.isSelected()) {
                controller.add(
                    new HourlyEmployee(
                        nameInput.getText(), 
                        surnameInput.getText(), 
                        monthsComboBox.getSelectedIndex(), 
                        Integer.parseInt(dayInput.getText()),
                        Integer.parseInt(yearInput.getText()),
                        Integer.parseInt(idInput.getText()),
                        positionInput.getText(),
                        Integer.parseInt(getInputText("hours")),
                        Double.parseDouble(getInputText("rate"))
                    )
                );
            } else if(comissionEmployee.isSelected()) {
                controller.add(
                    new ComissionEmployee(
                        nameInput.getText(), 
                        surnameInput.getText(), 
                        monthsComboBox.getSelectedIndex(), 
                        Integer.parseInt(dayInput.getText()),
                        Integer.parseInt(yearInput.getText()),
                        Integer.parseInt(idInput.getText()),
                        positionInput.getText(),
                        Double.parseDouble(getInputText("baseSalary")),
                        Integer.parseInt(getInputText("noOfItems")),
                        Double.parseDouble(getInputText("itemPrice")),
                        Double.parseDouble(getInputText("comissionRate"))
                    )
                );
            }
            close();
            parent.refreshTable();
            parent.repaint();
        });         
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(130, 450, 100, 50);
        cancelButton.addActionListener((ActionEvent e) -> {
            close();
        });  
        
        add(nameLabel);
        add(nameInput);
        add(surnameLabel);
        add(surnameInput);
        add(birthDateLabel);
        add(monthsComboBox);
        add(dayInput);
        add(yearInput);
        add(idLabel);
        add(idInput);
        add(positionLabel);
        add(positionInput);
        add(employeeTypeLabel);
        add(pieceWorker);
        add(hourlyEmployee);
        add(comissionEmployee);
        add(addButton);
        add(cancelButton);
        setLayout(null);
        setVisible(true);
    }
}

