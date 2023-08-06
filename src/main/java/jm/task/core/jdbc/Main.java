package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl(); //для Hibernate Task1.1.5

        userService.createUsersTable();
        userService.saveUser("Mariya", "Volkova", (byte) 20);
        userService.saveUser("Elena", "Stepnaya", (byte) 30);
        userService.saveUser("Ivan", "Stepanov", (byte) 25);
        userService.saveUser("Semen", "Astafyev", (byte) 40);

        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}