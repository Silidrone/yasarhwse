package taskplanner;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

interface PostRefreshFunction {
    void call(TasksTable t);
}

interface TasksTableOnRowClickCallback {
    void call(TasksTable t);
}

public class TasksTable extends JComponent {
    protected JTable jTable;
    protected DefaultTableModel tableModel;

    protected TasksTableOnRowClickCallback tasksTableOnRowClickCallback;
    protected PostRefreshFunction postRefreshF;
    protected Repo repo = new Repo();

    TasksTable(ArrayList<Task> tasks) {
        postRefreshF = null;
        String[] columns = {"ID", "Task Name", "Short Desc.", "Deadline", "Priority", "Reminder Image"};
        tableModel = new DefaultTableModel(new Object[][]{}, columns);
        jTable = new JTable(tableModel);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.setDefaultEditor(Object.class, null);
        TableColumnModel tableColumnModel = jTable.getColumnModel();
        tableColumnModel.removeColumn(tableColumnModel.getColumn(5));
        tableColumnModel.removeColumn(tableColumnModel.getColumn(0));

        jTable.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (event.getValueIsAdjusting() || jTable.getSelectedRow() == -1) return;
            if(tasksTableOnRowClickCallback != null) tasksTableOnRowClickCallback.call(this);
        });

        refreshWithData(tasks);
    }

    TasksTable() {
        this((new Repo()).getTasks());
    }

    protected void addTask(Task task) {
        var taskDataList = Arrays.asList(task.getId(), task.getName(), task.getShortDescription(), task.getDeadline(), task.getPriority(), task.isReminderImageOn());
        tableModel.addRow(taskDataList.toArray());
    }

    public Task getSelectedTask() {
        var selectedRowIndex = jTable.getSelectedRow();
        if (selectedRowIndex == -1) return null;
        try {
            return new Task(
                    Integer.parseInt(tableModel.getValueAt(selectedRowIndex, 0).toString()),
                    tableModel.getValueAt(selectedRowIndex, 1).toString(),
                    tableModel.getValueAt(selectedRowIndex, 2).toString(),
                    LocalDate.parse(tableModel.getValueAt(selectedRowIndex, 3).toString()),
                    Integer.parseInt(tableModel.getValueAt(selectedRowIndex, 4).toString()),
                    Boolean.parseBoolean(tableModel.getValueAt(selectedRowIndex, 5).toString())
            );
        } catch (NumberFormatException | DateTimeParseException e) {
            return null;
        }
    }

    void setPostRefreshF(PostRefreshFunction f) {
        postRefreshF = f;
    }

    public void refreshWithData(ArrayList<Task> tasks) {
        tableModel.setRowCount(0);
        for (Task task : tasks) {
            addTask(task);
        }
    }

    //Fetch data from DB (used to fetch initial data and also used in the context of fetching data after any of add/update/delete operations or when clicking on "Show All Tasks" button).
    public void refreshWithAllData() {
        refreshWithData(repo.getTasks());
        if(postRefreshF != null) postRefreshF.call(this);

    }

    public void setTasksTableOnRowClickCallback(TasksTableOnRowClickCallback tasksTableOnRowClickCallback) {
        this.tasksTableOnRowClickCallback = tasksTableOnRowClickCallback;
    }

    public void refreshWithSortedByPriorityData(LocalDate deadline) {
        refreshWithData(repo.getTasksByDeadlineAndSortedPriority(deadline));
    }

    public void refreshWithDateRangeFilteredData(LocalDate start, LocalDate end) {
        refreshWithData(repo.getTasksByDateRange(start, end));
    }

    public JTable getJTable() {
        return jTable;
    }
}
