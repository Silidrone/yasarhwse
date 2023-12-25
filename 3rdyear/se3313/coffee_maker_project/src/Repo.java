import java.sql.*;
public class Repo {
    public static int getSumOfCount() {
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
    }

    public static void addBrewRecord(int count) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:coffeedb.db")) {
            String insertQuery = "INSERT INTO brews (count, date) VALUES (?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setInt(1, count);
                statement.setDate(2, new Date((new java.util.Date()).getTime()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
