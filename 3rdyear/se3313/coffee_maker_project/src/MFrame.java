import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.util.AbstractMap;
import java.util.Map;

public abstract class MFrame extends JFrame {
    protected String label;
    protected int w, h;

    static final int DEFAULT_TEXTFIELD_W = 150;
    static final int DEFAULT_TEXTFIELD_H = 20;
    static final int DEFAULT_LABEL_H = 20;
    static final int DEFAULT_LETTER_WIDTH = 9;
    static final int DEFAULT_LABEL_GAP_Y = 20;

    MFrame(String label, int w, int h) {
        this.label = label;
        this.w = w;
        this.h = h;
    }

    protected abstract void main();

    public void init() {
        setTitle(label);
        setLocationRelativeTo(null);
        setBounds(getX() - w / 2, getY() - h / 2, w, h); //center the frame

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
        JTextField jInput = new JTextField();
        jInput.setBounds(x, y, w, h);
        add(jInput);
        return jInput;
    }

    JTextField addTextField(int x, int y) {
        return addTextField(x, y, DEFAULT_TEXTFIELD_W, DEFAULT_TEXTFIELD_H);
    }
    JButton addButton(String label, int x, int y, int w, int h, ActionListener actionListener) {
        JButton button = new JButton(label);
        button.setBounds(x, y, w, h);
        button.addActionListener(actionListener);
        add(button);
        return button;
    }

    Map.Entry<JLabel, JTextField> addTextFieldWithLabel(String label, int x, int y) {
        return new AbstractMap.SimpleEntry<>(addLabel(label, x, y), addTextField(x, y + DEFAULT_LABEL_GAP_Y));
    }
}
