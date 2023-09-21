package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {                   //Служба поддержки пользователей

    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {                                    //создать таблицу пользователей
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {                                      //удалить таблицу пользователей
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {      //сохранить пользователя
        userDaoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {                               //удалить идентификатор пользователя
        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {                                   //получить всех пользователей
        userDaoJDBC.getAllUsers();
        return null;
    }

    public void cleanUsersTable() {                                     //очистить таблицу пользователей
        userDaoJDBC.cleanUsersTable();
    }
}
