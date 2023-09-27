package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/kata_1.1.4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.driver_class", DRIVER);
            configuration.setProperty("hibernate.connection.url", URL);
            configuration.setProperty("hibernate.connection.username", USERNAME);
            configuration.setProperty("hibernate.connection.password", PASSWORD);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            configuration.addAnnotatedClass(User.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}


