package taskplanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    protected abstract boolean postValidationF(Task task);
    public void main() {
        super.main();
        namePair = addTextFieldWithLabel("Task Name", 100, 20);
        shortDescPair = addTextAreaWithLabel("Short Description", 100, 70, 250, 45);
        setTextVerifier(shortDescPair.getValue(), new MaxCharVerifier(90));
        shortDescPair.getValue().setLineWrap(true);
        deadlineDatePair = addDatePickerWithLabel("Deadline", 100, 150);
        addLabel("Priority", 100, 190);
        priorityTextField = addTextField(172, 190, 30, DEFAULT_TEXTFIELD_H);
        setTextVerifier(priorityTextField, new PriorityInputVerifier());
        reminderImageCheckBox = addCheckBox("Reminder Image", 100, 220);
        errorLabel = addLabel("", 100, 245, 400, DEFAULT_LABEL_H, Color.RED);
        errorLabel.setVisible(false);

        addButton("Add task", 100, 270, 130, 50, (ActionEvent e) -> {
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
            } else if (!deadline.isAfter(LocalDate.now())) {
                errorLabel.setText("The deadline field has to be future!");
                errorLabel.setVisible(true);
            } else {
                if(postValidationF(new Task(taskName, shortDescription, deadline, priority, reminderImageOn))) {
                    tasksTable.refreshWithAllData();
                    close();
                }
            }
        });
    }
}
