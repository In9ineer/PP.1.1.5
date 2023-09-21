package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {                                        //создать таблицу пользователей
        try (Connection connection = Util.open()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "lastName VARCHAR(100) NOT NULL, " +
                    "age TINYINT NOT NULL, " +
                    "PRIMARY KEY (id)" +
                    ")"
            );
            System.out.println("Таблица users успешно создана.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы users.");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {                                          //удалить таблицу пользователей
        try (Connection connection = Util.open()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Таблица users успешно удалена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы users.");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {          //сохранить пользователя

        try (Connection connection = Util.open()) {
            String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);

            int rowsAffected = statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении записи пользователя.");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {                                   //удалить пользователя по id
        Connection connection = Util.open();

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Запись с id " + id + " успешно удалена из таблицы.");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления записи из таблицы: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {                                       //получить всех польхователей
        List<User> people = new ArrayList<>();

        try (Connection connection = Util.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
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

    public void cleanUsersTable() {                                         // очистить таблицу пользователей
        try (Connection connection = Util.open();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users")) {
            statement.executeUpdate();
            System.out.println("Таблица users успешно очищена.");
        } catch (SQLException e) {
            System.out.println("Ошибка очистки таблицы users: " + e.getMessage());
        }
    }
}
