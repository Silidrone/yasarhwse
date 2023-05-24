package taskplanner;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.image.BufferedImage;
import java.util.AbstractMap;
import java.util.Map;

public abstract class MFrame extends JFrame {
    String label;
    int w;
    int h;
    
    static final int DEFAULT_TEXTFIELD_W = 150;
    static final int DEFAULT_TEXTFIELD_H = 20;
    static final int DEFAULT_LABEL_H = 20;
    static final int DEFAULT_LETTER_WIDTH = 9;
    static final int DEFAULT_LABEL_GAP_Y = 20;
    static final int DEFAULT_CHECKBOX_W = 20;
    static final int DEFAULT_CHECKBOX_H = 20;

    private static final String[] months = {
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

    void setTextVerifier(JTextComponent jTextComponent, TextVerifier tv) {
        jTextComponent.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (!tv.verify(jTextComponent.getText() + str)) {
                    return;
                }

                super.insertString(offs, str, a);
            }
        });
    }

    JLabel addLabel(String label, int x, int y, int w, int h, Color c) {
        JLabel jlabel = new JLabel(label);
        jlabel.setBounds(x, y, w, h);
        if(c != null) {
            jlabel.setForeground(Color.RED);
        }
        add(jlabel);
        return jlabel;
    }

    JLabel addLabel(String label, int x, int y, Color c) {
        return addLabel(label, x, y, DEFAULT_LETTER_WIDTH * label.length(), DEFAULT_LABEL_H, c);
    }

    JLabel addLabel(String label, int x, int y) {
        return addLabel(label, x, y, null);
    }

    JTextField addTextField(int x, int y, int w, int h) {
        JTextField jinput = new JTextField();
        jinput.setBounds(x, y, w, h);
        add(jinput);
        return jinput;
    }

    JTextField addTextField(int x, int y) {
        return addTextField(x, y, DEFAULT_TEXTFIELD_W, DEFAULT_TEXTFIELD_H);
    }

    JTextArea addTextArea(int x, int y, int w, int h) {
        JTextArea jTextArea = new JTextArea();
        jTextArea.setBounds(x, y, w, h);
        add(jTextArea);
        return jTextArea;
    }

    DatePickerComponent addDatePicker(int x, int y) {
        addLabel("Year", x, y);
        JTextField yearInput = addTextField(x, y + DEFAULT_LABEL_GAP_Y, 40, 20);
        setTextVerifier(yearInput, new YearInputVerifier());

        addLabel("Month", x + 45, y);
        JComboBox<String> monthsComboBox = new JComboBox<>(months);
        monthsComboBox.setBounds(x + 45, y + DEFAULT_LABEL_GAP_Y, 100, 20);
        add(monthsComboBox);

        addLabel("Day", x + 150, y);
        JTextField dayInput = addTextField(x + 150, y + DEFAULT_LABEL_GAP_Y, 23, 20);
        setTextVerifier(dayInput, new DayInputVerifier());

        return new DatePickerComponent(yearInput, monthsComboBox, dayInput);
    }

    JButton addButton(String label, int x, int y, int w, int h, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setBounds(x, y, w, h);
        button.addActionListener(actionListener);
        add(button);
        return button;
    }

    JCheckBox addCheckBox(String label, int x, int y) {
        JCheckBox checkBox = new JCheckBox(label);
        checkBox.setBounds(x - 5, y, label.length() * DEFAULT_LETTER_WIDTH + DEFAULT_CHECKBOX_W, DEFAULT_CHECKBOX_H);
        checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
        add(checkBox);
        return checkBox;
    }

    TasksTable addTasksTable(int x, int y, int w, int h) {
        TasksTable tasksTable = new TasksTable();
        JScrollPane panel = new JScrollPane(tasksTable.getJTable());
        panel.setBounds(x, y, w, h);
        add(panel);
        return tasksTable;
    }

    JDialog showDialog(String title, String text, int x, int y) {
        final JOptionPane pane = new JOptionPane(text);
        final JDialog dialog = pane.createDialog(null, title);
        dialog.setLocation(x - pane.getWidth() / 2, y - pane.getHeight() / 2);
        dialog.setVisible(true);

        return dialog;
    }

    Map.Entry<JLabel, JTextField> addTextFieldWithLabel(String label, int x, int y) {
        return new AbstractMap.SimpleEntry<>(addLabel(label, x, y), addTextField(x, y + DEFAULT_LABEL_GAP_Y));
    }

    Map.Entry<JLabel, JTextArea> addTextAreaWithLabel(String label, int x, int y, int w, int h) {
        return new AbstractMap.SimpleEntry<>(addLabel(label, x, y), addTextArea(x, y + DEFAULT_LABEL_GAP_Y, w, h));
    }

    Map.Entry<JLabel, DatePickerComponent> addDatePickerWithLabel(String label, int x, int y) {
        JLabel jLabel = addLabel(label, x, y + DEFAULT_LABEL_GAP_Y);
        return new AbstractMap.SimpleEntry<>(jLabel, addDatePicker(x + jLabel.getWidth(), y));
    }

    void addComponents(JComponent ... components) {
        for(JComponent jc : components) {
            add(jc);
        }
    }
}
