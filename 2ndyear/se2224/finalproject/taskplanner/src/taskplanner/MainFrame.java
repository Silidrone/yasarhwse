package taskplanner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainFrame extends SubFrame {
    private Repo repo = new Repo();
    MainFrame(JFrame parent) {
        super("TaskPlanner", 1500, 600, parent);
    }

    void showUpcomingTasksAlert(ArrayList<Task> upcomingTasks) {
        JDialog upcomingTasksDialog = new JDialog(this, "Upcoming Tasks");
        upcomingTasksDialog.setLocationRelativeTo(this);
        int dialogW = 600;
        int dialogH = 500;
        upcomingTasksDialog.setBounds(upcomingTasksDialog.getX() - dialogW / 2, upcomingTasksDialog.getY() - dialogH / 2, dialogW, dialogH);
        TasksTable dialogTasksTable = new TasksTable(upcomingTasks);
        var scrollPane = new JScrollPane(dialogTasksTable.getJTable());
        scrollPane.setSize(dialogW, dialogH - 100);
        upcomingTasksDialog.add(scrollPane);

        Container pane = upcomingTasksDialog.getContentPane();
        pane.setLayout(null);
        JButton okayButton = new JButton("Okay");
        okayButton.addActionListener((ActionEvent e) -> {
            upcomingTasksDialog.dispose();
        });
        pane.add(okayButton);
        okayButton.setBounds(225, 420, 150, 30);

        upcomingTasksDialog.setVisible(true);
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
            if (taskToEdit != null) {
                UpdateTaskFrame updateTaskFrame = new UpdateTaskFrame(this, tasksTable, taskToEdit);
                updateTaskFrame.init();
            }
        });

        var viewTaskImageButton = addButton("View Task Image", 810, 500, 170, 60, (ActionEvent e) -> {
            Task task = tasksTable.getSelectedTask();
            if (task.isReminderImageOn()) {
                try {
                    BufferedImage myPicture = ImageIO.read(new File("images/task" + task.getId() + ".jpg"));
                    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                    JOptionPane.showMessageDialog(this, picLabel, null, JOptionPane.PLAIN_MESSAGE, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "This task's image does not exist on the system!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "This task does not include an image!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        var deleteTaskButton = addButton("Delete Task", 1000, 500, 150, 60, (ActionEvent e) -> {
            Task taskToDelete = tasksTable.getSelectedTask();
            if (taskToDelete != null) {
                repo.deleteTask(taskToDelete.getName(), taskToDelete.getDeadline().toString());
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
            if (deadline != null) {
                tasksTable.refreshWithSortedByPriorityData(deadline);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter the deadline before trying to sort by priority!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        addLabel("Start", 850, 360 + DEFAULT_LABEL_GAP_Y);
        DatePickerComponent startDatePicker = addDatePicker(900, 360);
        addLabel("End", 850, 405 + DEFAULT_LABEL_GAP_Y);
        DatePickerComponent endDatePicker = addDatePicker(900, 405);
        addButton("Filter tasks", 1100, 390, 130, 30, (ActionEvent e) -> {
            LocalDate start = startDatePicker.getDate();
            LocalDate end = endDatePicker.getDate();

            if (start != null && end != null) {
                if (start.isBefore(end) || start.isEqual(end)) {
                    tasksTable.refreshWithDateRangeFilteredData(start, end);
                } else {
                    JOptionPane.showMessageDialog(this, "Please make sure the start date is before or equal to the end date before trying to filter tasks!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both the start and the end date before trying to filter tasks!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        ArrayList<Task> upcomingTasks = repo.getUpcomingTasks();
        if (upcomingTasks != null && !upcomingTasks.isEmpty()) {
            showUpcomingTasksAlert(upcomingTasks);
        }
    }
}
