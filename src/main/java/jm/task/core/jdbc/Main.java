package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Кто", "Здесь", (byte) 20);
        userService.saveUser("Второй", "Ктото", (byte) 30);
        userService.saveUser("Еще", "Юзер", (byte) 25);
        userService.saveUser("Вот", "Последний", (byte) 40);
        userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();

    }
}
