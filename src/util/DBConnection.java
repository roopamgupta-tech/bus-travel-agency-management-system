package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String url = "jdbc:mysql://localhost:3306/BusTravelAgency";
    private static final String username = "enter your database username";
    private static final String password = "enter your database password";

    /**
     * Creates and returns a MySQL database connection.
     */    
    public static Connection getConnection() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
            
        } catch (ClassNotFoundException  e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
