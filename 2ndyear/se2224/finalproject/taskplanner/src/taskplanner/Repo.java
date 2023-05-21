package taskplanner;

import java.sql.*;

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
        try(Connection connection = DriverManager.getConnection(url, db_username, db_password)){
            ResultSet resultSet = connection.createStatement().executeQuery(String.format("select password from users where username='%s';", "muhamed"));
            while (resultSet.next()) {
                if(resultSet.getString("password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
