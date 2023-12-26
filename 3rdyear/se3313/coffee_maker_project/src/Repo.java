import java.sql.*;
import java.text.SimpleDateFormat;

public class Repo {
    public static int getSumOfCount() {
        try {
            Class.forName("org.sqlite.JDBC");
            int totalSum = 0;

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:coffeedb.db")) {
                String query = "SELECT SUM(count) AS total_count FROM brews WHERE strftime('%Y-%m', date) = strftime('%Y-%m', 'now')";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            totalSum = resultSet.getInt("total_count");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return totalSum;
        } catch (ClassNotFoundException e) {
            System.exit(1);
        }

        return -1;
    }

    public static void addBrewRecord(int count) {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:coffeedb.db")) {
                String insertQuery = "INSERT INTO brews (count, date) VALUES (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                    statement.setInt(1, count);

                    String currentDateAsString = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
                    statement.setString(2, currentDateAsString);

                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.exit(1);
        }
    }
}
