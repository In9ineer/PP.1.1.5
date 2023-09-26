package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {                                        //создать таблицу пользователей
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(100) NOT NULL, " +
                "lastName VARCHAR(100) NOT NULL, " +
                "age TINYINT NOT NULL, " +
                "PRIMARY KEY (id)" +
                ")";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {                                          //удалить таблицу пользователей
        String sql = "DROP TABLE IF EXISTS users";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {          //сохранить пользователя
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {                                   //удалить пользователя по id
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {                                       //получить всех польхователей
        List<User> people = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                people.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public void cleanUsersTable() {                                         //очистить таблицу пользователей
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
