import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.Color;

public class CoffeeView extends MFrame implements ViewObserver {
    protected CoffeeController coffeeController;
    protected JButton idleIndicator;
    protected JButton brewingIndicator;
    protected JButton doneIndicator;
    protected JLabel totalCupsLabel;
    protected JTextField cupsToFillTextbox;

    protected JLabel messageLabel;

    CoffeeView(CoffeeController coffeeController) {
        super("Coffee Maker", 550, 400);
        this.coffeeController = coffeeController;
    }

    @Override
    protected void main() {
        addButton("FILLED", 40, 200, 130, 50, (ActionEvent e) -> {
            coffeeController.filled(cupsToFillTextbox.getText());
        });
        addButton("START", 190, 200, 130, 50, (ActionEvent e) -> coffeeController.start());
        addButton("RESET", 190, 260, 130, 50, (ActionEvent e) -> coffeeController.reset());
        addButton("TOTAL CUPS", 340, 200, 130, 50, (ActionEvent e) -> coffeeController.updateTotalCups());

        var cupsToFillPair = addTextFieldWithLabel("Cups To Fill", 40, 150);
        this.cupsToFillTextbox = cupsToFillPair.getValue();
        cupsToFillTextbox.setBounds(cupsToFillTextbox.getX(), cupsToFillTextbox.getY(), cupsToFillTextbox.getWidth() - 20, cupsToFillTextbox.getHeight());
        setTextVerifier(cupsToFillTextbox, new NumberVerifier(2));
        totalCupsLabel = addLabel("Total Cups:", 340, 170);
        totalCupsLabel.setBounds(340, 170, 210, 40);
        idleIndicator = addButton("IDLE", 190, 20, 130, 50, (ActionEvent e) -> {
        });
        brewingIndicator = addButton("BREWING", 190, 70, 130, 50, (ActionEvent e) -> {
        });
        doneIndicator = addButton("DONE", 190, 120, 130, 50, (ActionEvent e) -> {
        });

        messageLabel = addLabel("BOO", 40, 300);
        messageLabel.setBounds(40, 300, 410, 50);
        messageLabel.setForeground(Color.ORANGE);

        idleIndicator.setEnabled(false);
        brewingIndicator.setEnabled(false);
        doneIndicator.setEnabled(false);

        turnOffIndicators();

        idleIndicator.setForeground(Color.WHITE);
        brewingIndicator.setForeground(Color.WHITE);
        doneIndicator.setForeground(Color.WHITE);

        turnOnIdle();
        coffeeController.updateTotalCups();
    }

    void turnOnIdle() {
        idleIndicator.setBackground(Color.YELLOW);
        brewingIndicator.setBackground(Color.decode("#d2d2d2"));
        doneIndicator.setBackground(Color.decode("#d2d2d2"));
    }

    void turnOnBrewing() {
        idleIndicator.setBackground(Color.decode("#d2d2d2"));
        brewingIndicator.setBackground(Color.YELLOW);
        doneIndicator.setBackground(Color.decode("#d2d2d2"));
    }

    void turnOnDone() {
        idleIndicator.setBackground(Color.decode("#d2d2d2"));
        brewingIndicator.setBackground(Color.decode("#d2d2d2"));
        doneIndicator.setBackground(Color.YELLOW);
    }

    void turnOffIndicators() {
        idleIndicator.setBackground(Color.decode("#d2d2d2"));
        brewingIndicator.setBackground(Color.decode("#d2d2d2"));
        doneIndicator.setBackground(Color.decode("#d2d2d2"));
    }

    void setMessage(String msg) {
        messageLabel.setText(msg);
    }

    void clearInputs() {
        messageLabel.setText("");
        cupsToFillTextbox.setText("");
    }

    @Override
    public void update(int totalCount) {
        totalCupsLabel.setText("Total Cups: " + totalCount);
    }
}
