/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taskplanner;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author jaglu
 */
public class SubFrame extends MFrame {
    protected JFrame parent;
    
    SubFrame(String label, int w, int h, JFrame parent) {
        super(label, w, h);
        this.parent = parent;
    }
    
    protected void close() {
        parent.setVisible(true);
        dispose();
    }
    
    public void main() {
        parent.setVisible(false);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                close();
            }
        });
    }
}
