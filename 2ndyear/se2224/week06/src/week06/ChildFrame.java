/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week06;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author jaglu
 */
public class ChildFrame extends JFrame {
    protected JFrame parent;
    
    ChildFrame(JFrame parent) {
        this.parent = parent;
    }
    
    protected void close() {
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
    }
}
