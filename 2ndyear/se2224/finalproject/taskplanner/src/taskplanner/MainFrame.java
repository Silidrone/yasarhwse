package taskplanner;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainFrame extends SubFrame {
    MainFrame(JFrame parent) {
        super("TaskPlanner", 1500, 600, parent);
    }

    public void main() {
        super.main();
        TasksTable tasksTable = addTasksTable(100, 50, 1300, 300);

        addButton("Show all tasks", 300, 500, 150, 60, (ActionEvent e) -> {
            tasksTable.refreshWithAllData();
        });

        addButton("Insert a task", 470, 500, 150, 60, (ActionEvent e) -> {
            AddTaskFrame addTaskFrame = new AddTaskFrame(this, tasksTable);
            addTaskFrame.init();
        });

        var editTaskButton = addButton("Edit Task", 640, 500, 150, 60, (ActionEvent e) -> {
            Task taskToEdit = tasksTable.getSelectedTask();
            if(taskToEdit != null) {
                UpdateTaskFrame updateTaskFrame = new UpdateTaskFrame(this, tasksTable, taskToEdit);
                updateTaskFrame.init();
            }
        });

        var viewTaskImageButton = addButton("View Task Image", 810, 500, 170, 60, (ActionEvent e) -> {
            Task task = tasksTable.getSelectedTask();
            if(task.isReminderImageOn()) {
                try {
                    BufferedImage myPicture = ImageIO.read(new File("images/task"));
                    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                    JOptionPane.showMessageDialog(null, picLabel, null, JOptionPane.PLAIN_MESSAGE, null);
                } catch (IOException ex) {
                    showDialog("Info", "This task's image does not exist on the system!", w / 2,h / 2);
                }
            } else {
                showDialog("Info", "This task does not include an image!", w / 2,h / 2);
            }
        });

        var deleteTaskButton = addButton("Delete Task", 1000, 500, 150, 60,(ActionEvent e) -> {
            Task taskToDelete = tasksTable.getSelectedTask();
            if(taskToDelete != null) {
                Repo.getInstance().deleteTask(taskToDelete.getName(), taskToDelete.getDeadline().toString());
                tasksTable.refreshWithAllData();
            }
        });

        deleteTaskButton.setEnabled(false);
        editTaskButton.setEnabled(false);
        viewTaskImageButton.setEnabled(false);

        tasksTable.setPostRefreshF((TasksTable t) -> {
            boolean enabled = t.getSelectedTask() != null;
            deleteTaskButton.setEnabled(enabled);
            editTaskButton.setEnabled(enabled);
            viewTaskImageButton.setEnabled(enabled);
        });

        tasksTable.setTasksTableOnRowClickCallback((TasksTable t) -> {
            deleteTaskButton.setEnabled(true);
            editTaskButton.setEnabled(true);
            viewTaskImageButton.setEnabled(true);
        });

        var datePickerPair = addDatePickerWithLabel("Deadline", 100, 360);
        addButton("Sort by Priority", 180, 405, 150, 30, (ActionEvent e) -> {
            LocalDate deadline = datePickerPair.getValue().getDate();
            if(deadline != null) {
                tasksTable.refreshWithSortedByPriorityData(deadline);
            } else {
                showDialog("Error", "Please enter the deadline before trying to sort by priority!", w / 2,h / 2);
            }
        });

        addLabel("Start", 850, 360 + DEFAULT_LABEL_GAP_Y);
        DatePickerComponent startDatePicker = addDatePicker(900, 360);
        addLabel("End", 850, 405 + DEFAULT_LABEL_GAP_Y);
        DatePickerComponent endDatePicker = addDatePicker(900, 405);
        addButton("Filter tasks", 1100, 390, 130, 30, (ActionEvent e) -> {
            LocalDate start = startDatePicker.getDate();
            LocalDate end = endDatePicker.getDate();

            if(start != null && end != null) {
                if(start.isBefore(end) || start.isEqual(end)) {
                    tasksTable.refreshWithDateRangeFilteredData(start, end);
                } else {
                    showDialog("Error", "Please make sure the start date is before or equal to the end date before trying to filter tasks!", w / 2,h / 2);
                }
            } else {
                showDialog("Error", "Please enter both start and end dates before trying to filter tasks!", w / 2,h / 2);
            }
        });
    }
}
