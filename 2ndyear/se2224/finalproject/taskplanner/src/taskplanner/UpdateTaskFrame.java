package taskplanner;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;

public class UpdateTaskFrame extends CRUDTaskFrame {
    private Task taskToEdit;
    protected Repo repo = new Repo();

    UpdateTaskFrame(JFrame parent, TasksTable tasksTable, Task taskToEdit) {
        super("Edit Task", parent, tasksTable);
        this.taskToEdit = taskToEdit;
    }

    public boolean submit(Task task) throws SQLIntegrityConstraintViolationException {
        return repo.editTask(taskToEdit.getName(), taskToEdit.getDeadline().toString(), task);
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
        addButton.setText("Edit Task");
    }
}
