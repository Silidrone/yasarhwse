package taskplanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.Map;

public abstract class CRUDTaskFrame extends SubFrame {
    protected TasksTable tasksTable;
    protected JLabel errorLabel;
    protected Map.Entry<JLabel, JTextField> namePair;
    protected Map.Entry<JLabel, JTextArea> shortDescPair;
    protected Map.Entry<JLabel, DatePickerComponent> deadlineDatePair;
    protected JTextField priorityTextField;
    protected JCheckBox reminderImageCheckBox;

    CRUDTaskFrame(String frameLabel, JFrame parent, TasksTable tasksTable) {
        super(frameLabel, 400, 400, parent);
        this.tasksTable = tasksTable;
    }
    public void main() {
        super.main();
        namePair = addTextFieldWithLabel("Task Name", 80, 20);
        shortDescPair = addTextAreaWithLabel("Short Description", 80, 70, 250, 45);
        setTextVerifier(shortDescPair.getValue(), new MaxCharVerifier(90));
        shortDescPair.getValue().setLineWrap(true);
        deadlineDatePair = addDatePickerWithLabel("Deadline", 80, 150);
        addLabel("Priority", 80, 190);
        priorityTextField = addTextField(152, 190, 30, DEFAULT_TEXTFIELD_H);
        setTextVerifier(priorityTextField, new PriorityInputVerifier());
        reminderImageCheckBox = addCheckBox("Reminder Image", 80, 220);
        errorLabel = addLabel("", 80, 245, 400, DEFAULT_LABEL_H, Color.RED);
        errorLabel.setVisible(false);

        addButton("Add task", 80, 270, 130, 50, (ActionEvent e) -> {
            String taskName = namePair.getValue().getText();
            String shortDescription = shortDescPair.getValue().getText();
            LocalDate deadline = deadlineDatePair.getValue().getDate();
            Integer priority = null;
            try {
                priority = Integer.parseInt(priorityTextField.getText());
            } catch (NumberFormatException ignore) {

            }
            boolean reminderImageOn = reminderImageCheckBox.isSelected();

            if (taskName.isEmpty()) {
                errorLabel.setText("Task Name field is required!");
                errorLabel.setVisible(true);
            } else if (shortDescription.isEmpty()) {
                errorLabel.setText("Short Description field is required!");
                errorLabel.setVisible(true);
            } else if (deadline == null) {
                errorLabel.setText("The deadline field is required!");
                errorLabel.setVisible(true);
            } else if (deadline.isBefore(LocalDate.now())) {
                errorLabel.setText("The deadline field mustn't be past!");
                errorLabel.setVisible(true);
            } else {
                try {
                    boolean successful = Repo.getInstance().addTask(new Task(taskName, shortDescription, deadline, priority, reminderImageOn));
                    if (!successful) {
                        errorLabel.setText("An error occurred!");
                        errorLabel.setVisible(true);
                    } else {
                        tasksTable.refreshWithAllData();
                        close();
                    }
                } catch (SQLIntegrityConstraintViolationException exception) {
                    errorLabel.setText("Duplicate name and deadline record exists!");
                    errorLabel.setVisible(true);
                }
            }
        });
    }
}
