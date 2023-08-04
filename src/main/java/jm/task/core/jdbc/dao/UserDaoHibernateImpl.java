package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {


    }

    @Override
    @Transactional
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS userDB (id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL, name VARCHAR(64)," +
                "lastName VARCHAR(64), age TINYINT)";
        Query query = session.createSQLQuery(sql)
                .addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    @Transactional
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS userDB";
        Query query = session.createSQLQuery(sql)
                .addEntity(User.class);
        query.executeUpdate();
        transaction.commit();

    }

    @Override
    @Transactional
    public void saveUser(String name, String lastName, byte age) {
        //каждый запрос к базе выполняется в своей собственной транзакции
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(User.class, id));
        session.getTransaction().commit();
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM User", User.class).list();
    }

    @Override
    @Transactional
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "TRUNCATE TABLE userDB";
        Query query = session.createSQLQuery(sql)
                .addEntity(User.class);
        query.executeUpdate();
        transaction.commit();

    }
}
