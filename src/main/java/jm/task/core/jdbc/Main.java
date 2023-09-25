package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Кто", "Здесь", (byte) 20);
        userService.saveUser("Второй", "Ктото", (byte) 30);
        userService.saveUser("Еще", "Юзер", (byte) 25);
        userService.saveUser("Вот", "Последний", (byte) 40);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
