package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/usersql";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "ilovejava";
    private static SessionFactory sessionFactory;


    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            System.out.println("Get connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection ERROR");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DB_DRIVER);
                settings.put(Environment.URL, DB_URL);
                settings.put(Environment.USER, DB_USER);
                settings.put(Environment.PASS,DB_PASS);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put (Environment.SHOW_SQL,"true"); //Hibernate будет дублировать в консоль все запросы, которые выполняет
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "validate");
                // объект SessionFactory проверяет, что в БД существуют  таблицы с колонками требуемых типов
                sessionFactory = configuration.addProperties(settings)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();


                System.out.println("Get connection successful");
            } catch (HibernateException e) {
                System.out.println("Connection Error");
                e.printStackTrace();
            }
        }
      return sessionFactory;
    }

}
