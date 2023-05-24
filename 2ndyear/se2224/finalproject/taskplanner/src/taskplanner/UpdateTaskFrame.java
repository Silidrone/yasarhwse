package taskplanner;

import javax.swing.*;

public class UpdateTaskFrame extends CRUDTaskFrame {
    protected Task taskToEdit;

    UpdateTaskFrame(JFrame parent, TasksTable tasksTable, Task taskToEdit) {
        super("Insert A Task", parent, tasksTable);
        this.taskToEdit = taskToEdit;
    }

    @Override
    protected boolean postValidationF(Task task) {
        boolean successful = Repo.getInstance().editTask(taskToEdit.getName(), taskToEdit.getDeadline().toString(), task);
        if (!successful) {
            errorLabel.setText("There was an error editing the task!");
            errorLabel.setVisible(true);
        }

        return successful;
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
