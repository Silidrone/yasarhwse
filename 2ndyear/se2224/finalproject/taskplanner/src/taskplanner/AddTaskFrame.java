package taskplanner;

import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;

public class AddTaskFrame extends CRUDTaskFrame {
    protected Repo repo = new Repo();

    public boolean submit(Task task) throws SQLIntegrityConstraintViolationException {
        return repo.addTask(task);
    }
    AddTaskFrame(JFrame parent, TasksTable tasksTable) {super("Insert a Task", parent, tasksTable);}
}
