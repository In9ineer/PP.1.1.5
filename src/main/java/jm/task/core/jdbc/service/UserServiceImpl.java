package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {                   //Служба поддержки пользователей

    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {                                    //создать таблицу пользователей
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {                                      //удалить таблицу пользователей
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) { userDaoJDBC.removeUserById(id); }

    public List<User> getAllUsers() { return userDaoJDBC.getAllUsers(); }

    public void cleanUsersTable() {                                     //очистить таблицу пользователей
        userDaoJDBC.cleanUsersTable();
    }
}
