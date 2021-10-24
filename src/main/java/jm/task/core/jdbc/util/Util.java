package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.hibernate.cfg.*;

import org.hibernate.service.ServiceRegistry;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "0313362289password";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение установленно");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка соединения");
        }
        return connection;
    }

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory = null;
public static SessionFactory getSessionFactory(){
    if (sessionFactory == null) {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydatabase");
        settings.setProperty(Environment.USER, "root");
        settings.setProperty(Environment.PASS, "0313362289password");
        settings.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        settings.setProperty(Environment.SHOW_SQL, "true");
        settings.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    return sessionFactory;
    }
}













