package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String url =
        System.getenv("DB_URL");

    private static final String username =
            System.getenv("DB_USERNAME");
    
    private static final String password =
            System.getenv("DB_PASSWORD");

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
