/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week04;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author jaglu
 */
public class BuyFrame extends JFrame {
    private MainFrame parent;
    private BaseController controller;
    private String title;
    private String l1;
    private String l2;
    private String l3;
    
    BuyFrame(MainFrame parent, BaseController controller, String title, String l1, String l2, String l3) {
        this.parent = parent;
        this.controller = controller;
        this.title = title;
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
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
        setTitle(title);
        setSize(400, 500);
        
        JLabel jl1 = new JLabel(l1);
        jl1.setBounds(20, 25, 140, 30);
        JTextField input1 = new JTextField();
        input1.setBounds(170, 25, 100, 30);
        
        JLabel jl2 = new JLabel(l2);
        jl2.setBounds(20, 75, 140, 30);
        JTextField input2 = new JTextField();
        input2.setBounds(170, 75, 100, 30);

        
        JLabel jl3 = new JLabel(l3);
        jl3.setBounds(20, 125, 140, 30);
        JTextField input3 = new JTextField();
        input3.setBounds(170, 125, 100, 30);

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(100, 175, 100, 50);
        
        JLabel resultLabel = new JLabel();
        resultLabel.setBounds(10, 250, 350, 100);
        enterButton.addActionListener((ActionEvent e) -> {
            ArrayList<String> input = new ArrayList<>();
            input.add(input1.getText());
            input.add(input2.getText());
            input.add(input3.getText());
            String error_text = controller.create(input);
            if(!error_text.isEmpty()) {
                resultLabel.setText(error_text);
                resultLabel.setForeground(Color.RED);
            } else {
                resultLabel.setText("Successfully added!");
                resultLabel.setForeground(Color.GREEN);
             }
        });
        
        add(jl1);
        add(input1);
        add(jl2);
        add(input2);
        add(jl3);
        add(input3);
        add(enterButton);
        add(resultLabel);
        setLayout(null);
        setVisible(true);
    }
}
