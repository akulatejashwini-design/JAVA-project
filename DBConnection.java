import java.sql.*;

public class DBConnection {

    public static void main(String[] args) {

        try {

            // Load JDBC Driver
            Class.forName(
            "com.mysql.cj.jdbc.Driver");

            // Connect to MySQL Database
            Connection con =
            DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC",
            "root",
            "root");

            // Success Message
            System.out.println(
            "Database Connected");

            // Close Connection
            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}