package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Mariya", "Volkova", (byte) 20);
        userDao.saveUser("Elena", "Stepnaya", (byte) 30);
        userDao.saveUser("Ivan", "Stepanov", (byte) 25);
        userDao.saveUser("Semen", "Astafyev", (byte) 40);

        userDao.removeUserById(2);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}