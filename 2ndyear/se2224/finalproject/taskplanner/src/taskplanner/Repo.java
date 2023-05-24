package taskplanner;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Repo {
    private static Repo instance = null;
    private static String url = "jdbc:mysql://localhost:3306/taskplanner";
    private static String db_username = "root";
    private static String db_password = "mynewpassword";

    private Repo() {

    }

    public static Repo getInstance() {
        return (instance == null) ? new Repo() : instance;
    }

    public boolean checkLogin(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, db_username, db_password)) {
            PreparedStatement ps = connection.prepareStatement("SELECT password FROM users WHERE username=?;");
            ps.setString(1, username);
            ps.execute();

            ResultSet resultSet = ps.getResultSet();
            while (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Task> getTasksFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            try {
                tasks.add(new Task(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("name"),
                        resultSet.getString("short_description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getInt("priority"),
                        resultSet.getBoolean("reminder_image")
                ));
            } catch (NumberFormatException ignore) {
                throw new SQLException("The id column is missing for the records in the result set");
            }
        }

        return tasks;
    }

    //After adding the task to the DB it sets its ID
    public boolean addTask(Task task) {
        try (Connection connection = DriverManager.getConnection(url, db_username, db_password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tasks (name, short_description, deadline, priority, reminder_image, entry_date) values (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getShortDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(task.getDeadline()));
            var priority = task.getPriority();
            if (priority != null) {
                preparedStatement.setInt(4, priority);
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            preparedStatement.setBoolean(5, task.isReminderImageOn());
            preparedStatement.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.execute();

            //Set the task's id
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM tasks WHERE name=? AND deadline=?;");
            ps.setString(1, task.getName());
            ps.setDate(2, java.sql.Date.valueOf(task.getDeadline()));
            ps.execute();

            ResultSet resultSet = ps.getResultSet();
            resultSet.next();
            task.setId(Integer.parseInt(resultSet.getString("id")));
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ArrayList<Task> getTasks() {
        try (Connection connection = DriverManager.getConnection(url, db_username, db_password)) {
            return getTasksFromResultSet(connection.createStatement().executeQuery("SELECT * FROM tasks;"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Task> getTasksByDateRange(LocalDate start, LocalDate end) {
        try (Connection connection = DriverManager.getConnection(url, db_username, db_password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks WHERE deadline BETWEEN ? AND ? ORDER BY deadline;");
            preparedStatement.setDate(1, java.sql.Date.valueOf(start));
            preparedStatement.setDate(2, java.sql.Date.valueOf(end));
            preparedStatement.execute();
            return getTasksFromResultSet(preparedStatement.getResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteTask(String taskName, String deadline) {
        try (Connection connection = DriverManager.getConnection(url, db_username, db_password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tasks WHERE name=? AND deadline=?;");
            preparedStatement.setString(1, taskName);
            preparedStatement.setString(2, deadline);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean editTask(String taskName, String deadline, Task newTask) {
        try (Connection connection = DriverManager.getConnection(url, db_username, db_password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tasks SET name=?, short_description=?, deadline=?, priority=?, reminder_image=? WHERE name=? AND deadline=?;");
            preparedStatement.setString(1, newTask.getName());
            preparedStatement.setString(2, newTask.getShortDescription());
            preparedStatement.setDate(3, java.sql.Date.valueOf(newTask.getDeadline()));
            preparedStatement.setInt(4, newTask.getPriority());
            preparedStatement.setBoolean(5, newTask.isReminderImageOn());
            preparedStatement.setString(6, taskName);
            preparedStatement.setString(7, deadline);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ArrayList<Task> getTasksByDeadlineAndSortedPriority(LocalDate deadline) {
        try (Connection connection = DriverManager.getConnection(url, db_username, db_password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks WHERE deadline = ? ORDER BY priority;");
            preparedStatement.setDate(1, java.sql.Date.valueOf(deadline));
            preparedStatement.execute();
            return getTasksFromResultSet(preparedStatement.getResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
