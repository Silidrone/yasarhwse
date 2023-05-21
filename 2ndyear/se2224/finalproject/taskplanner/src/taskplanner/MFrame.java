package taskplanner;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.Map;

public abstract class MFrame extends JFrame {
    String label;
    int w;
    int h;
    
    static final int DEFAULT_INPUTBOX_W = 150;
    static final int DEFAULT_INPUTBOX_H = 20;
    static final int DEFAULT_LABEL_H = 20;
    static final int DEFAULT_LETTER_WIDTH = 9;
    static final int DEFAULT_LABEL_GAP = 20;

    private static final Object[] months = {
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September",
            "October", "November", "December"
    };

    MFrame(String label, int w, int h) {
        this.label = label;
        this.w = w;
        this.h = h;
    }
    
    public abstract void main();
    
    public void init() {
        setTitle(label);
        setSize(w, h);
        
        main();
        
        setLayout(null);
        setVisible(true);
    }

    JLabel addLabel(String label, int x, int y) {
        return addLabel(label, x, y, null);
    }

    JLabel addLabel(String label, int x, int y, Color c) {
        JLabel jlabel = new JLabel(label);
        jlabel.setBounds(x, y, DEFAULT_LETTER_WIDTH * label.length(), DEFAULT_LABEL_H);
        if(c != null) {
            jlabel.setForeground(Color.RED);
        }
        add(jlabel);
        return jlabel;
    }

    Map.Entry<JLabel,JTextField> addInputBoxWithLabel(String label, int x, int y) {
        JLabel jlabel = addLabel(label, x, y);
        JTextField jinput = new JTextField();
        jinput.setBounds(x, y + DEFAULT_LABEL_GAP, DEFAULT_INPUTBOX_W, DEFAULT_INPUTBOX_H);
        add(jinput);
        return new AbstractMap.SimpleEntry<>(jlabel, jinput);
    }

    Map.Entry<JLabel, JTextArea> addTextAreaWithLabel(String label, int x, int y, int w, int h, int maxChar) {
        JLabel jlabel = addLabel(label, x, y);
        JTextArea jTextArea = new JTextArea();
        jTextArea.setBounds(x, y + DEFAULT_LABEL_GAP, w, h);
        jTextArea.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null || jTextArea.getText().length() >= maxChar) {
                    return;
                }

                super.insertString(offs, str, a);
            }
        });
        jTextArea.setLineWrap(true);
        add(jTextArea);
        return new AbstractMap.SimpleEntry<>(jlabel, jTextArea);
    }

    void addDatePickerWithLabel(String label, int x, int y) {
        addLabel(label, x, y);
        JComboBox monthsComboBox = new JComboBox(months);
        monthsComboBox.setBounds(x + 70, y, 100, 20);
        JTextField dayInput = new JTextField();
        dayInput.setBounds(x + 175, y, 30, 20);
        JTextField yearInput = new JTextField();
        yearInput.setBounds(x + 205, y, 35, 20);

        addComponents(monthsComboBox, dayInput, yearInput);
    }
    
    JButton addButton(String label, int x, int y, int w, int h, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setBounds(x, y, w, h);
        button.addActionListener(actionListener);
        add(button);
        return button;
    }
    
    void addComponents(JComponent ... components) {
        for(JComponent jc : components) {
            add(jc);
        }
    }
}
