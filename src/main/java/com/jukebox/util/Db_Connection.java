package com.jukebox.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_Connection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/songs";
            String user = "root";
            String pass = "admin";
            connection = DriverManager.getConnection(url, user, pass);
            return connection;
    }
}
