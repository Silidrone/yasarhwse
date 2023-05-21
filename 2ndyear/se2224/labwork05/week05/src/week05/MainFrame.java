/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week05;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jaglu
 */
public class MainFrame extends JFrame {
    protected EmployeeController employeeController;
    protected JTable table;
    protected AbstractTableModel tableModel;
    JRadioButton pieceWorker;
    JRadioButton hourlyEmployee;
    JRadioButton comissionEmployee;

    MainFrame(EmployeeController employeeController) {
        this.employeeController = employeeController;
        pieceWorker = new JRadioButton("Piece Worker");
        pieceWorker.setSelected(true);
        hourlyEmployee = new JRadioButton("Hourly Employee");
        comissionEmployee = new JRadioButton("Comission Employee");

        String[] columns = {"First Name", "Second Name", "Salary"};
        tableModel = new AbstractTableModel() {
            public int getColumnCount() {
                return columns.length;
            }

            public String getColumnName(int column) {
                return columns[column];
            }

            public int getRowCount() {
                return employeeController.getEmployees(getSelectedEmployeeClass()).size();
            }

            public Object getValueAt(int row, int col) {
                Employee employee = employeeController.getEmployees(getSelectedEmployeeClass()).get(row);
                if (col == 0) {
                    return employee.getName();
                } else if (col == 1) {
                    return employee.getSurname();
                } else if (col == 2) {
                    return employee.getSalary();
                }

                return "";
            }
        };
        table = new JTable(tableModel);
    }

    public Class<? extends Employee> getSelectedEmployeeClass() {
        Class<? extends Employee> employeeClass = null;
        if (pieceWorker.isSelected()) {
            employeeClass = PieceWorker.class;
        } else if (hourlyEmployee.isSelected()) {
            employeeClass = HourlyEmployee.class;
        } else if (comissionEmployee.isSelected()) {
            employeeClass = ComissionEmployee.class;
        }

        return employeeClass;
    }

    public void refreshTable() {
        tableModel.fireTableDataChanged();
    }

    void init() {
        setTitle("Main Frame");
        setSize(700, 700);

        JScrollPane panel = new JScrollPane(table);
        panel.setBounds(30, 10, 400, 500);

        JLabel filterLabel = new JLabel("FILTER");
        filterLabel.setBounds(450, 10, 100, 20);

        pieceWorker.setBounds(450, 30, 120, 20);
        hourlyEmployee.setBounds(450, 50, 120, 20);
        comissionEmployee.setBounds(450, 70, 150, 20);

        ButtonGroup employeeTypeGroup = new ButtonGroup();
        employeeTypeGroup.add(pieceWorker);
        employeeTypeGroup.add(hourlyEmployee);
        employeeTypeGroup.add(comissionEmployee);

        pieceWorker.addActionListener((ActionEvent e) -> {
            refreshTable();
        });

        hourlyEmployee.addActionListener((ActionEvent e) -> {
            refreshTable();
        });

        comissionEmployee.addActionListener((ActionEvent e) -> {
            refreshTable();
        });
        
        JLabel searchLabel = new JLabel("Search by ID");
        searchLabel.setBounds(20, 520, 80, 30);
        JTextField searchInput = new JTextField();
        searchInput.setBounds(110, 520, 80, 30);
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 520, 80, 30);

        searchButton.addActionListener((ActionEvent e) -> {
            String searchText = searchInput.getText();
            if(!searchText.isBlank()) {
                Employee employee = employeeController.find(Integer.parseInt(searchText));
                setVisible(false);
                EmployeeDetailsFrame employeeDetailsFrame = new EmployeeDetailsFrame(this, employee);
                employeeDetailsFrame.init();
            }
        });
        
        JButton addButton = new JButton("Add");
        addButton.setBounds(20, 600, 100, 50);
        addButton.addActionListener((ActionEvent e) -> {
            setVisible(false);
            AddEmployeeFrame employeeFrame = new AddEmployeeFrame(this, employeeController);
            employeeFrame.init();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(140, 600, 100, 50);
        exitButton.addActionListener((ActionEvent e) -> {
            dispose();
            System.exit(0);
        });

        add(panel);
        add(filterLabel);
        add(pieceWorker);
        add(hourlyEmployee);
        add(comissionEmployee);
        add(searchLabel);
        add(searchInput);
        add(searchButton);
        add(addButton);
        add(exitButton);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
}
