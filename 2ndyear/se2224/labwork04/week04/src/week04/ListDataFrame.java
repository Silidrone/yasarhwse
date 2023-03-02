/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week04;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author jaglu
 */
public class ListDataFrame extends JFrame {
    private MainFrame parent;
    private BaseController controller;
    private String title;
    
    ListDataFrame(MainFrame parent, BaseController controller, String title) {
        this.parent = parent;
        this.controller = controller;
        this.title = title;
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
        
        JTextArea output = new JTextArea(controller.showData());
        output.setBounds(50, 50, 300, 400);
        output.setEditable(false);
        output.setFocusable(false);
        add(output);
        setLayout(null);
        setVisible(true);
    }
}
