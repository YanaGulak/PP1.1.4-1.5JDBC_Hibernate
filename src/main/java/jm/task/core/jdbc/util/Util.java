package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/usersql";
    private static final String DB_User = "root";
    private static final String DB_PASS = "ilovejava";


    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, DB_User,DB_PASS);
            System.out.println("Get connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection ERROR");
        }
        return connection;
    }

}
