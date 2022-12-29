package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    // реализуйте настройку соеденения с БД
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL ="jdbc:mysql://localhost:3306/mydbtest";

    public Connection getConnection() throws SQLException{

        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
    }




}
