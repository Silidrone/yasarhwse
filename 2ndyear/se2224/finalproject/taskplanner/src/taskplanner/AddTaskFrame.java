package taskplanner;

import javax.swing.*;

public class AddTaskFrame extends CRUDTaskFrame {
    AddTaskFrame(JFrame parent, TasksTable tasksTable) {
        super("Insert a Task", parent, tasksTable);
    }

    @Override
    protected boolean postValidationF(Task task) {
        boolean successful = Repo.getInstance().addTask(task);
        if (!successful) {
            errorLabel.setText("There was an error adding the task!");
            errorLabel.setVisible(true);
        }

        return successful;
    }
}
