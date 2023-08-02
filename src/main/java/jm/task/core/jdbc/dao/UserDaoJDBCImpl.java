package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    //создать таблицу user
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS userDB (id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL, name VARCHAR(64)," +
                "lastName VARCHAR(64), age TINYINT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("TABLE userDB is created!");
        } catch (SQLException e) {
            System.out.println("TABLE CREATING ERROR");
        }
    }

    //удалить таблицу из БД
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS userDB";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("TABLE userDB is deleted from DataBase!");
        } catch (SQLException e) {
            System.out.println("TABLE Delete ERROR");
        }
    }

    //добавить нового user-a  в таблицу
    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO userDB (name, lastName, age) VALUES (?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    //удалить user по id
    public void removeUserById(long id) {
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM userDB WHERE id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //получить лист всех эл-в user
    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        String sql = "SELECT * FROM userDB";

        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                listOfUsers.add(user);
                listOfUsers.forEach(System.out::println);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOfUsers;
    }

    //очистить таблицу (будет пустой)
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE userDB";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("TABLE userDB is truncated!");
        } catch (SQLException e) {
            System.out.println("TABLE TRUNCATION ERROR");
        }
    }

}
