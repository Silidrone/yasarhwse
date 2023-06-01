package taskplanner;

import javax.swing.*;

public class UpdateTaskFrame extends CRUDTaskFrame {
    private Task taskToEdit;

    UpdateTaskFrame(JFrame parent, TasksTable tasksTable, Task taskToEdit) {
        super("Insert A Task", parent, tasksTable);
        this.taskToEdit = taskToEdit;
    }

    @Override
    public void main() {
        super.main();
        //Fill the input with the task to be edited
        namePair.getValue().setText(taskToEdit.getName());
        shortDescPair.getValue().setText(taskToEdit.getShortDescription());
        deadlineDatePair.getValue().setDate(taskToEdit.getDeadline());
        priorityTextField.setText(taskToEdit.getPriority().toString());
        reminderImageCheckBox.setSelected(taskToEdit.isReminderImageOn());
    }
}
