package jm.task.core.jdbc.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String URL = "jdbc:mysql://localhost/users";
    private static String USERNAME = "root";
    private static String PASSWORD = "root";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Соединение не установлено");
        }
        return null;
    }
    public static SessionFactory getSessionFactory() {
        return new Configuration().addAnnotatedClass(jm.task.core.jdbc.model.User.class).buildSessionFactory();
    }

}
